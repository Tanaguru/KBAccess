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

import java.util.Calendar;
import java.util.Random;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.opens.kbaccess.command.AccountCommand;
import org.opens.kbaccess.command.PasswordLostCommand;
import org.opens.kbaccess.controller.utils.AMailerController;
import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.kbaccess.entity.authorization.AccessLevelEnumType;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccessLevelDataService;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.utils.MailingServiceProperties;
import org.opens.kbaccess.utils.SHA1Hasher;
import org.opens.kbaccess.validator.NewAccountValidator;
import org.opens.kbaccess.validator.PasswordLostValidator;
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
@RequestMapping("/guest/")
public class GuestController extends AMailerController {
    
    @Autowired
    private AccountDataService accountDataService;
    @Autowired
    private AccessLevelDataService accessLevelDataService;
    @Autowired
    private MailingServiceProperties mailingServiceProperties;
    private Random random = new Random();
    
    /*
     * private methods
     */
    private String generateNewPassword() {
        String passwordChars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password;
        final int passwordLength = 8;
        
        password = new StringBuilder();
        for (int i = 0; i < passwordLength; ++i) {
            password.append(passwordChars.charAt(random.nextInt(passwordChars.length())));
        }
        return password.toString();
    }

    private String generateAuthToken(String email) {
        StringBuilder sb;
        
        sb = new StringBuilder();
        sb.append(email).append(String.valueOf(random.nextInt()));
        return SHA1Hasher.getInstance().hashAsString(sb.toString());
    }
    
    private boolean sendAuthTokenAndSubscribeNotificationByMail(String lang, Account account) {
        if (!sendSubsciptionNotification(account)) {
            LogFactory.getLog(GuestController.class).error("Unable to send the subscription notification");
        }
        if (!sendAuthToken(lang, account)) {
            LogFactory.getLog(GuestController.class).debug("Unable to send the auth token");
            return false;
        }
        return true;
    }

    private boolean sendNewPasswordByMail(String lang, Account account, String password) {
        if (!sendNewPassword(lang, account, password)) {
            LogFactory.getLog(GuestController.class).error("Error sending new password by mail to " + account.getEmail());
            return false;
        }
        return true;
    }
    
    /**
     * Return true whether or not the user has the right to access this controller.
     * @return 
     */
    private boolean checkAuthority() {
        // we ban the authenticated user from the guest controller
        return (AccountUtils.getInstance().getCurrentUser() == null);
    }
    
    /**
     * The method called when checkAuthority return false : lead the user to
     * another page.
     * 
     * @param model
     * @return 
     */
    private String forwardBannedUsers() {
        return "redirect:/";
    }
    
    /*
     * Subscription handlers
     */
    @RequestMapping(value="subscribe", method=RequestMethod.GET)
    public String subscribeHandler(Model model) {
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        //
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Inscription");
        //
        model.addAttribute("newAccountCommand", new AccountCommand());
        return "guest/subscribe";
    }
    
    @RequestMapping(value="subscribe", method=RequestMethod.POST)
    public String subscribeHandler(
            @ModelAttribute("newAccountCommand") AccountCommand accountCommand,
            BindingResult result,
            Model model
            ) {
        NewAccountValidator newAccountValidator = new NewAccountValidator(accountDataService);
        Account newAccount;
        String authToken;
        
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Inscription");
        // validate the user input
        newAccountValidator.validate(accountCommand, result);
        if (result.hasErrors()) {
            model.addAttribute("newAccountCommand", accountCommand);
            return "guest/subscribe";
        }
        // create auth token
        authToken = generateAuthToken(accountCommand.email);
        // create account
        newAccount = accountDataService.create();
        accountCommand.updateAccount(newAccount);
        newAccount.setAuthCode(authToken);
        AccessLevel al = accessLevelDataService.getByCode(AccessLevelEnumType.CONTRIBUTOR.getType());
        newAccount.setAccessLevel(al);
        newAccount.setSubscriptionDate(Calendar.getInstance().getTime());
        
        Logger.getLogger(this.getClass()).debug(al.getId());
        Logger.getLogger(this.getClass()).debug(al.getCdAccessLevel());
        // send subscribe confirmation, with auth token, and notification
        if (!sendAuthTokenAndSubscribeNotificationByMail(null, newAccount)) {
            model.addAttribute("subscribeError", "Une erreur s'est produite. Merci de contacter l'administrateur.");
        } else {
            accountDataService.saveOrUpdate(newAccount);
            model.addAttribute("newAccountCreated", true);
        }
        return "guest/subscribe";
    }
    
    /*
     * Login handlers
     */
    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginHandler(Model model) {
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        //
        handleBreadcrumbTrail(model, "KBAccess", "/", "Identification");
        return "guest/login";
    }
    
    /*
     * password lost handler
     */
    @RequestMapping(value="password-lost", method= RequestMethod.GET)
    public String passwordLostHandler(Model model) {
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mot de passe oublié");
        //
        model.addAttribute("passwordLostCommand", new PasswordLostCommand());
        return "guest/password-lost";
    } 
    
    @RequestMapping(value="password-lost", method= RequestMethod.POST)
    public String passwordLostHandler(
            @ModelAttribute("passwordLostCommand") PasswordLostCommand passwordLostCommand,
            BindingResult result,
            Model model
            ) {
        PasswordLostValidator passwordLostValidator =  new PasswordLostValidator(accountDataService);
        Account account;
        String password;
        
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mot de passe oublié");
        // validate form
        passwordLostValidator.validate(passwordLostCommand, result);
        if (result.hasErrors()) {
            model.addAttribute("passwordLostCommand", passwordLostCommand);
            return "guest/password-lost";
        }
        // generate the new password and send it by mail
        password = generateNewPassword();
        account = accountDataService.getAccountFromEmail(passwordLostCommand.getEmail());
        if (account != null && sendNewPasswordByMail(null, account, password)) {
            account.setPassword(SHA1Hasher.getInstance().hashAsString(password));
            accountDataService.saveOrUpdate(account);
            model.addAttribute("passwordSent", true);
        } else {
            model.addAttribute("passwordLostError", "Une erreure s'est produite. Merci de contacter l'administrateur.");
        }
        return "guest/password-lost";
    }
    
    /*
     * Account activation handler
     */
    @RequestMapping(value="activate-account", method=RequestMethod.GET)
    public String activateAccountHandler(
            @RequestParam(value="token", required=false) String token,
            @RequestParam(value="email", required=false) String email,
            Model model
            ) {
        Account account;
        
        LogFactory.getLog(GuestController.class).info("activateAccountHandler()");
        
        // check if the user has the right to be there
        if (!checkAuthority()) {
            return forwardBannedUsers();
        }
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Activation du compte");
        // sanity check
        if (AccountUtils.getInstance().getCurrentUser() != null) {
            LogFactory.getLog(GuestController.class).error("An authentified user reached guest/activate-account. Check spring security configuration");
            return "home";
        } 
        // check parameters
        if (token == null || token.isEmpty() || email == null || email.isEmpty()) {
            // if there are empty, display the form
            return "guest/activate-account";
        } else {
            // otherwise, activate the associated account
            account = accountDataService.getAccountFromEmail(email);
            if (account != null && token.equals(account.getAuthCode())) {
                account.setActivated(true);
                account.setAuthCode(null);
                accountDataService.saveOrUpdate(account);
                model.addAttribute("accountActivated", true);
            } else {
                // or display an error
                model.addAttribute("activateAccountError", "Jeton ou adresse email invalide.");
            }
        }
        
        LogFactory.getLog(GuestController.class).info("endOfActivateAccountHandler()");
       
        return "guest/activate-account";
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

//    public AccountFactory getAccountFactory() {
//        return accountFactory;
//    }
//
//    public void setAccountFactory(AccountFactory accountFactory) {
//        this.accountFactory = accountFactory;
//    }

    @Override
    public MailingServiceProperties getMailingServiceProperties() {
        return mailingServiceProperties;
    }

    @Override
    public void setMailingServiceProperties(MailingServiceProperties mailingServiceProperties) {
        this.mailingServiceProperties = mailingServiceProperties;
    }

    public AccessLevelDataService getAccessLevelDataService() {
        return accessLevelDataService;
    }

    public void setAccessLevelDataService(AccessLevelDataService accessLevelDataService) {
        this.accessLevelDataService = accessLevelDataService;
    }
 
}
