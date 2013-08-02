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
package org.opens.kbaccess.utils;

import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.controller.AccountController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author bcareil
 */
public class AccountUtils {
    
    private static AccountUtils instance = null;
    
    private AccountDataService accountDataService;
    
    /*
     * Private methods
     */
    
    /**
     * 
     * @param currentUser
     * @return true if the current user is an admin or a moderator, false otherwise
     */
     private boolean isAdminOrModerator(Account currentUser) {
        boolean isAdmin = (currentUser.getAccessLevel().getPriority() == 1);
        boolean isModerator = (currentUser.getAccessLevel().getPriority() == 2);
                
        return (isAdmin || isModerator);
    }
    
    private AccountUtils() {
    }
    
    public static synchronized AccountUtils getInstance() {
        if (instance == null) {
            instance = new AccountUtils();
        }
        return instance;
    }
    
    /**
     * 
     * @return The user name if the user is logged in or null otherwise
     */
    public String getCurrentUserName() {
        Authentication authentication;
        
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }
    
    /**
     * 
     * @return The user account of the logged in user or null
     */
    public Account getCurrentUser() {
        String userName;
        
        userName = getCurrentUserName();
        if (userName == null) {
            return null;
        }
        return accountDataService.getAccountFromEmail(userName);
    }
   
    /**
     * 
     * @return true if the current user has the permission to edit a testcase, false otherwise
     */
    public boolean currentUserhasPermissionToEditTestcase(Testcase testcase) {
        Account currentUser = getCurrentUser();
        
        boolean isTestcaseOwner = currentUser.getId().equals(testcase.getAccount().getId());
        
        // Only the testcase owner and an admin or moderator can edit a testcase
        return (isTestcaseOwner || isAdminOrModerator(currentUser));
    }
    
    /**
     * 
     * @return true if the current user has the permission to edit an account
     */
    public boolean currentUserhasPermissionToEditAccount(Account account) {
        Account currentUser = getCurrentUser();
        
        boolean isAccountOwner = currentUser.getId().equals(account.getId());
        boolean isAdmin = currentUser.getAccessLevel().getPriority() == 1;
        boolean isNotAnAdminAccount = account.getAccessLevel().getPriority() != 1;
        
        // Only the account owner or an admin can edit an account (unless it's another admin account)
        return ( isAccountOwner || (isAdmin && isNotAnAdminAccount) );
    }
    
    /**
     * 
     * @return true if the current user has the permission to edit an account ans its role
     */
    public boolean currentUserHasPermissionToEditAccountWithRole(Account account) {
        Account currentUser = getCurrentUser();
        
        boolean isAdmin = currentUser.getAccessLevel().getPriority() == 1;
        boolean isNotAnAdminAccount = account.getAccessLevel().getPriority() != 1;
        
        // The only false case is if an admin tries to edit his own account role
        // This is done to avoid an admin to accidentally downgrade his own role and not be able to come back
        return (isAdmin && isNotAnAdminAccount);
    }
    
    /**
     * @param token
     * @return true if the token is valid
     */
    public boolean isTokenValid(String token) {
        boolean isAccountValid = true;
        boolean isTokenValid = true;
        TgolTokenHelper tokenHelper = TgolTokenHelper.getInstance();
        
        String requestedUserEmail = tokenHelper.getUserEmailFromToken(token);
                
        if (accountDataService.getAccountFromEmail(requestedUserEmail) == null) {
            isAccountValid = false;
            LogFactory.getLog(AccountController.class).info("Token with an invalid email");
        }
        
        if (!tokenHelper.checkUserToken(token)) {
            isTokenValid = false;
            LogFactory.getLog(AccountController.class).info("Token expired/with an invalid structure");
        }
        
        return (isAccountValid && isTokenValid);
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
