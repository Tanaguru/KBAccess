/*
 * KBAccess - Collaborative database of accessibility examples
 * Copyright (C) 2012-2016  Open-S Company
 *
 * This file is part of KBAccess.
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
import org.opens.kbaccess.command.ChangePasswordCommand;
import org.opens.kbaccess.command.NewPasswordCommand;
import org.opens.kbaccess.controller.utils.AController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.AccountPresentation;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.utils.TgolTokenHelper;
import org.opens.kbaccess.validator.AccountDetailsValidator;
import org.opens.kbaccess.validator.ChangePasswordValidator;
import org.opens.kbaccess.validator.NewPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/account/")
public class AccountController extends AController {
    
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    
    /*
     * Private methods
     */
    
    private String displayNewPasswordForm(Model model, NewPasswordCommand newPasswordCommand) {
        // Breadcrumb
        handleBreadcrumbTrail(model, "KBAccess", "/", "Nouveau mot de passe");
        
        model.addAttribute("newPasswordCommand", newPasswordCommand);
        
        return "account/new-password";
    }
    
    /*
     * account details handler
     */
    @RequestMapping(value="my-account", method=RequestMethod.GET)
    public String myAccountHandler(Model model) {    
        return this.detailsHandler(AccountUtils.getInstance().getCurrentUser().getId(), model);
    }
    
    @RequestMapping(value="my-account", method=RequestMethod.POST)
    public String myAccountHandler(
             @ModelAttribute("accountCommand") AccountCommand accountCommand,
             BindingResult result,
             Model model
             ) {
        
        return this.detailsHandler(accountCommand, result, model);
    }
    
    @RequestMapping(value="details/{id}/*", method=RequestMethod.GET)
    public String detailsHandler(
            @PathVariable("id") Long id,
            Model model
            ) {
        boolean requestedUserIsCurrentUser;
        AccountPresentation accountPresentation;
        Account requestedUser;
        Account currentUser;
        AccountCommand accountCommand;
        
        // handle login form
        handleUserLoginForm(model);
        
        currentUser = AccountUtils.getInstance().getCurrentUser();

        
        if (currentUser == null) {
            requestedUserIsCurrentUser = false;
        } else {
            requestedUserIsCurrentUser = id == null || id.equals(currentUser.getId());
        }
        
        // fetch the requested account and check it exists
        if (requestedUserIsCurrentUser) {
            requestedUser = currentUser;
        } else {
            requestedUser = accountDataService.read(id);
            if (requestedUser == null) {          
                return "home";
            } 
        }
        accountPresentation = new AccountPresentation(requestedUser, accountDataService);
        // if the user consult its account details, offer him the possibility to update it 
        if (requestedUserIsCurrentUser) {
            
            accountCommand = new AccountCommand(requestedUser);
            model.addAttribute("accountCommand", accountCommand);
            model.addAttribute("account", accountPresentation);
        }
        
        // and give a more explicit breadcrumb path
        if (requestedUserIsCurrentUser) {
            model.addAttribute("title", "Mon compte - KBAccess");
            handleBreadcrumbTrail(model, "KBAccess", "/", "Mon compte");
        } else {
            model.addAttribute("title", "Utilisateur " + accountPresentation.getDisplayedName() + " - KBAccess");
            handleBreadcrumbTrail(model, "KBAccess", "/", "Utilisateur " + accountPresentation.getDisplayedName());
        }
        
        model.addAttribute("account", accountPresentation);
        model.addAttribute(
                ModelAttributeKeyStore.TESTCASE_LIST_KEY, 
                TestcasePresentation.fromCollection(
                testcaseDataService.getLastTestcasesFromAccount(requestedUser, 5),
                true
                ));
        return "account/details";
    }
    
    @RequestMapping(value="details/{id}/*", method=RequestMethod.POST)
    public String detailsHandler(
            @ModelAttribute("accountCommand") AccountCommand accountCommand,
            BindingResult result,
            Model model
            ) {
        Account currentUser = AccountUtils.getInstance().getCurrentUser();
        Account requestedUser = accountDataService.getAccountFromEmail(accountCommand.getEmail());
        boolean requestedUserIsCurrentUser;
        AccountPresentation accountPresentation;
           
        // check authority (spring security should avoid that this test passed)
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached /account/details, check spring security configuration");
            return "guest/login";
        }
        
        requestedUserIsCurrentUser = currentUser.equals(requestedUser);
        
        if (!requestedUserIsCurrentUser) {
            LogFactory.getLog(AccountController.class).error("An authentified user tried to edit the mail input form in order to edit another account than his (potential XSS)");
            return "home";
        }
        
        // Handle login and breadcrumb
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mon Compte");
        
        // validate new account details
        AccountDetailsValidator accountDetailsValidator = new AccountDetailsValidator(accountDataService, currentUser.getEmail());
        accountDetailsValidator.validate(accountCommand, result);
        model.addAttribute("accountCommand", accountCommand);
        
        if (result.hasErrors()) {
            handleUserLoginForm(model);
            accountPresentation = new AccountPresentation(currentUser, accountDataService);
            model.addAttribute("account", accountPresentation);
            return "account/details";
        }
        
        accountCommand.updateAccount(currentUser);
        accountDataService.update(currentUser);
        
        accountPresentation = new AccountPresentation(currentUser, accountDataService);
        
        handleUserLoginForm(model);
        model.addAttribute("account", accountPresentation);
        model.addAttribute("successMessage", "Vos modifications ont bien été enregistrées.");
        return "account/details";
    }
    
    /*
     * other handlers
     */
    
    @RequestMapping("my-webarchives")
    public String myWebarchivesHandler(Model model) {
        Account currentUser;
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mes webarchives");
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/my-webpages, check spring security configuration");
            return "guest/login";
        }
        model.addAttribute(ModelAttributeKeyStore.WEBARCHIVE_LIST_KEY, webarchiveDataService.getAllFromUserAccount(currentUser));
        model.addAttribute("webarchiveListH1", "Mes webarchives");
        model.addAttribute("title", "Mes Webarchives");
        return "webarchive/list";
    }
    
    @RequestMapping("my-examples")
    public String myTestcasesHanlder(Model model) {
        Account currentUser;

        // 
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mes exemples");
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/my-testcases, check spring security configuration");
            return "guest/login";
        }
        //
        
        model.addAttribute(
                ModelAttributeKeyStore.TESTCASE_LIST_KEY, 
                TestcasePresentation.fromCollection(
                testcaseDataService.getAllFromAccount(currentUser),
                true
                ));
        model.addAttribute("testcaseListH1", "Mes exemples");
        return "testcase/list";
    }
    
    @RequestMapping(value="change-password", method=RequestMethod.GET)
    public String changePasswordHandler(Model model) {   
        ChangePasswordCommand changePasswordCommand;
        Account currentUser;
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mon compte", "/account/my-account.html", "Changement de mot de passe");
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/change-password, check spring security configuration");
            return "guest/login";
        }
        
        changePasswordCommand = new ChangePasswordCommand();
        model.addAttribute("changePasswordCommand", changePasswordCommand);
        
        return "account/change-password";
    }
    
    @RequestMapping(value="change-password", method=RequestMethod.POST)
    public String changePasswordHandler(
             @ModelAttribute("changePasswordCommand") ChangePasswordCommand changePasswordCommand,
             BindingResult result,
             Model model
             ) {
        Account currentUser = AccountUtils.getInstance().getCurrentUser();
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mon compte", "/account/my-account.html", "Changement de mot de passe");
        
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/change-password, check spring security configuration");
            return "guest/login";
        }
        
        // validate new account details
        ChangePasswordValidator changePasswordValidator = new ChangePasswordValidator(accountDataService, currentUser.getEmail());
        changePasswordValidator.validate(changePasswordCommand, result);
        model.addAttribute("changePasswordCommand", changePasswordCommand);
        
        if (result.hasErrors()) {
            return "account/change-password";
        }
        
        changePasswordCommand.updateAccount(currentUser);
        accountDataService.update(currentUser);
        
        
        handleUserLoginForm(model);
        model.addAttribute("successMessage", "Le mot de passe a bien été modifié.");
        
        return "account/change-password";
    }
    
    @RequestMapping(value="new-password", method=RequestMethod.GET)
    public String newPasswordHandler(
            Model model,
            @RequestParam(value="token", required=true) String token
            ) {    
        NewPasswordCommand newPasswordCommand;
        
        LogFactory.getLog(AccountController.class).info("token : " + token);
        
        if (!AccountUtils.getInstance().isTokenValid(token)) {
            return "guest/login";
        }

        // New password form
        newPasswordCommand = new NewPasswordCommand();
        newPasswordCommand.setToken(token);

        return displayNewPasswordForm(model, newPasswordCommand);    
    }
    
    @RequestMapping(value="new-password", method=RequestMethod.POST)
    public String newPasswordHandler(
            @ModelAttribute("newPasswordCommand") NewPasswordCommand newPasswordCommand,
            BindingResult result,
            Model model
            ) {   
        String token = newPasswordCommand.getToken();
        TgolTokenHelper tokenHelper = TgolTokenHelper.getInstance();
        
        if (!AccountUtils.getInstance().isTokenValid(token)) {
            return "guest/login";
        }
        
        NewPasswordValidator newPasswordValidator = new NewPasswordValidator(accountDataService, null);
        newPasswordValidator.validate(newPasswordCommand, result);
        
        if (result.hasErrors()) {
            return displayNewPasswordForm(model, newPasswordCommand);
        }
        
        // Update account's password
        String requestedUserEmail = tokenHelper.getUserEmailFromToken(token);
        Account requestedUser = accountDataService.getAccountFromEmail(requestedUserEmail);
        
        // Set the token as used
        requestedUser.setAuthCode(null);
        
        // Update account
        newPasswordCommand.updateAccount(requestedUser);
        accountDataService.update(requestedUser);
        
        model.addAttribute("successMessage", "Le mot de passe a bien été modifié.");
        
        return displayNewPasswordForm(model, newPasswordCommand);        
    }
    
    /*
     * Accessors
     */

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

}
