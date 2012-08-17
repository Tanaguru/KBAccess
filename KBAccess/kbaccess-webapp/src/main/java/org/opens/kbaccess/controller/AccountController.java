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
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.AccountPresentation;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.AccountDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private AccountDataService accountDataService;
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    
    /*
     * account details handler
     */
    
    @RequestMapping(value="details", method=RequestMethod.GET)
    public String detailsHandler(
            @RequestParam(value="id", required=false) Long id,
            Model model
            ) {
        boolean requestedUserIsCurrentUser;
        AccountPresentation account;
        Account requestedUser;
        Account currentUser;
        
        // handle login form
        handleUserLoginForm(model);
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/details, check spring security configuration");
            return "home";
        }
        requestedUserIsCurrentUser = (id == null || id == currentUser.getId());
        // fetch the requested account and check it exists
        if (requestedUserIsCurrentUser) {
            requestedUser = currentUser;
        } else {
            requestedUser = accountDataService.read(id);
            if (requestedUser == null) {
                handleBreadcrumbTrail(model, "KBAccess", "/", "Compte invalide");
                model.addAttribute("accountDetailsError", "Compte invalide");
                return "account/details";
            }
        }
        account = new AccountPresentation(requestedUser);
        // if the user consult its account details, offer him the possibility to
        // update it and give a more explicit breadcrumb path
        if (requestedUserIsCurrentUser) {
            model.addAttribute("accountDetailsCommand", new AccountCommand(currentUser));
            model.addAttribute("title", "Mon compte - KBAccess");
            handleBreadcrumbTrail(model, "KBAccess", "/", "Mon compte");
        } else {
            model.addAttribute("title", "Utilisateur " + account.getDisplayedName() + " - KBAccess");
            handleBreadcrumbTrail(model, "KBAccess", "/", "Utilisateur " + account.getDisplayedName());
        }
        model.addAttribute("account", account);
        return "account/details";
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
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached /account/details, check spring security configuration");
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
            return "home";
        }
        model.addAttribute(ModelAttributeKeyStore.WEBARCHIVE_LIST_KEY, webarchiveDataService.getAllFromUserAccount(currentUser));
        model.addAttribute("webarchiveListH1", "Mes webarchives");
        return "webarchive/list";
    }
    
    @RequestMapping("my-testcases")
    public String myTestcasesHanlder(Model model) {
        Account currentUser;
        
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mes testcases");
        //
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(AccountController.class).error("An unauthentified user reached account/my-testcases, check spring security configuration");
            return "home";
        }
        //
        model.addAttribute(ModelAttributeKeyStore.TESTCASE_LIST_KEY, testcaseDataService.getAllFromAccount(currentUser));
        model.addAttribute("testcaseListH1", "Mes testcases");
        return "testcase/list";
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
