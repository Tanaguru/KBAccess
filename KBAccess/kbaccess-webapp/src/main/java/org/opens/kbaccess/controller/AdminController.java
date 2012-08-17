/*
 * URLManager - URL Indexer
 * Copyright (C) 2008-2012  Open-S Company
 *
 * This file is part of URLManager.
 *
 * URLManager is free software: you can redistribute it and/or modify
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
import org.opens.kbaccess.controller.utils.AController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.AccountDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/admin/")
public class AdminController extends AController {
    
    @Autowired
    private AccountDataService accountDataService;
    
    /*
     * account details handler
     */
    
    @RequestMapping(value="users", method=RequestMethod.GET)
    public String usersHandler(Model model) {
        Account currentUser;
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Liste des utilisateurs");
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AdminController.class).error("An unauthentified user reached admin/users, check spring security configuration");
            return "home";
        }
        model.addAttribute("title", "Liste des utilisateurs - KBAccess");
        model.addAttribute("accountList", accountDataService.findAll());
        model.addAttribute("accountListH1", "Liste des utilisateurs");
        return "account/list";
    }
    
    @RequestMapping(value="details", method=RequestMethod.POST)
    public String detailsHandler(
            @ModelAttribute("accountAdapter") AccountCommand accountCommand,
            BindingResult result,
            Model model
            ) {
        Account currentUser = AccountUtils.getInstance().getCurrentUser();
        AccountDetailsValidator accountDetailsValidator = new AccountDetailsValidator(accountDataService, currentUser.getEmail());
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mon Compte");
        // check authority (spring security should avoid that this test passed)
        if (currentUser == null) {
            LogFactory.getLog(AdminController.class).error("An unauthentified user reached /account/details, check spring security configuration");
            return "home";
        }
        // validate new account details
        accountDetailsValidator.validate(accountCommand, result);
        if (result.hasErrors()) {
            model.addAttribute("accountDetailsCommand", accountCommand);
            return "account/details";
        }
        accountCommand.updateAccount(currentUser);
        accountDataService.update(currentUser);
        return "account/details";
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
