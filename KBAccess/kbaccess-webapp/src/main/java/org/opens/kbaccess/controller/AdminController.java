/*
 * Copyright (C) 2012-2016  Open-S Company
 *
 *
 * KBAccess is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.kbaccess.controller;

import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.command.AccountCommand;
import org.opens.kbaccess.command.AccountWithRoleCommand;
import org.opens.kbaccess.controller.utils.AController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccessLevelDataService;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.presentation.AccountPresentation;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.AccountWithRoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author blebail
 */
@Controller
@RequestMapping("/admin/")
public class AdminController extends AController {
    
    @Autowired
    private AccessLevelDataService accessLevelDataService;
    
    /*
     * Utils
     */
    private String displayUserList(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        model.addAttribute("title", "Liste des utilisateurs - KBAccess");
        model.addAttribute("accountList", accountDataService.findAll());
        model.addAttribute("accountListH1", "Liste des utilisateurs");
        return "admin/users";
    }
    
     private String displayEditUserForm(
             Model model, 
             AccountCommand accountCommand, 
             AccountPresentation accountPresentation, 
             String errorMessage) {
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            
            // accountPresentation null means the user who wants to edit this user don't have rights to do so
            // but we display the user infos anyway
            if (accountPresentation != null) {
                model.addAttribute("account", accountPresentation);
            }
        } else {
              // Breadcrumb
            handleBreadcrumbTrail(model);
            // create form
            model.addAttribute("title", "Utilisateur " + accountPresentation.getDisplayedName());
            model.addAttribute("account", accountPresentation);
            model.addAttribute("accountCommand", accountCommand);
            model.addAttribute("accessLevelList", accessLevelDataService.findAll());
        }

        return "admin/edituser";
    }
    
    /*
     * account details handler
     */
    
    @RequestMapping(value="users", method=RequestMethod.GET)
    public String usersHandler(Model model) {
        Account currentUser;
 
        currentUser = AccountUtils.getInstance().getCurrentUser();
        
        if (currentUser == null) {
            LogFactory.getLog(AdminController.class).error("An unauthentified user reached admin/users, check spring security configuration");
            return "guest/login";
        }
        
        return displayUserList(model);
    }
    
    /*
     * account details handler
     */
    
    @RequestMapping(value="edituser/{id}/*", method=RequestMethod.GET)
    public String editUserHandler(
            @PathVariable("id") Long id,
            Model model
            ) {
        AccountPresentation accountPresentation;
        Account requestedUser;
        AccountWithRoleCommand accountCommand;
        boolean currentUserIsValid = (AccountUtils.getInstance().getCurrentUser() != null);
        boolean currentUserHasPermissions;
        
        // current user not authentified
        if (!currentUserIsValid) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached admin/edituser, check spring security configuration");
            return "guest/login";
        }
       
        // Fetch account and check if it exists
        requestedUser = accountDataService.read(id);
        if (requestedUser == null) {
            return displayEditUserForm(model, null, null, "Cet utilisateur n'existe pas.");
        }
        
        // create account presentation and check permissions to edit the account
        currentUserHasPermissions = (AccountUtils.getInstance().currentUserHasPermissionToEditAccountWithRole(requestedUser));
        accountPresentation = new AccountPresentation(requestedUser, accountDataService);
        
        if (!currentUserHasPermissions) {       
            return displayEditUserForm(model, null, accountPresentation, "Vous n'êtes pas authorisé à modifier cet utilisateur.");
        }
        // Form
        accountCommand = new AccountWithRoleCommand(requestedUser);
        accountCommand.setAccountId(requestedUser.getId());
        
        return displayEditUserForm(model, accountCommand, accountPresentation, null);
    }
    
    
    @RequestMapping(value="edituser/{id}/*", method=RequestMethod.POST)
    public String editUserHandler(
            @ModelAttribute("accountCommand") AccountWithRoleCommand accountCommand,
            BindingResult result,
            Model model
            ) {
        AccountPresentation accountPresentation;
        Account requestedUser;
        boolean currentUserIsValid = (AccountUtils.getInstance().getCurrentUser() != null);
        boolean currentUserHasPermissions;
        boolean passwordChanged;
        
        // check authority (spring security should avoid that this test passed)
        if (!currentUserIsValid) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached /admin/edituser, check spring security configuration");
            return "guest/login";
        }

        // Fetch account and check if it exists
        requestedUser = accountDataService.read(accountCommand.getAccountId());
        
        if (requestedUser == null) {
            return displayEditUserForm(model, null, null, "Cet utilisateur n'existe pas.");
        }
        // create account presentation and check permissions to edit the account
        currentUserHasPermissions = (AccountUtils.getInstance().currentUserHasPermissionToEditAccountWithRole(requestedUser));
        
        if (!currentUserHasPermissions) {
            accountPresentation = new AccountPresentation(requestedUser, accountDataService);
            return displayEditUserForm(model, null, accountPresentation, "Vous n'êtes pas authorisé à modifier cet utilisateur.");
        }
        // Check if password has been changed
        passwordChanged = (accountCommand.getPassword() != null) && !(accountCommand.getPassword().isEmpty());
        
        // validate account modifications
        AccountWithRoleValidator accountWithRoleValidator = new AccountWithRoleValidator(
                accountDataService, 
                accessLevelDataService,
                requestedUser.getEmail(), 
                passwordChanged
                );
        
        accountWithRoleValidator.validate(accountCommand, result);
        
        if (result.hasErrors()) {
            accountPresentation = new AccountPresentation(requestedUser, accountDataService);
            model.addAttribute("account", accountPresentation);
            return displayEditUserForm(model, accountCommand, accountPresentation, null);
        } 
        
        // Update the account
        accountCommand.updateAccount(accessLevelDataService, requestedUser, passwordChanged);
        accountDataService.update(requestedUser);
        
        accountPresentation = new AccountPresentation(requestedUser, accountDataService);
        model.addAttribute("successMessage", "L'utilisateur a bien été modifié.");
        return displayEditUserForm(model, accountCommand, accountPresentation, null);
    }
    
    /*
     * Accessors
     */

    public AccessLevelDataService getAccessLevelDataService() {
        return accessLevelDataService;
    }

    public void setAccessLevelDataService(AccessLevelDataService accessLevelDataService) {
        this.accessLevelDataService = accessLevelDataService;
    }
}
