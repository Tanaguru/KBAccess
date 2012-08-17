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
package org.opens.kbaccess.utils;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author bcareil
 */
public class AccountUtils {
    
    private static AccountUtils instance = null;
    
    private AccountDataService accountDataService;
    
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

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }
    
}
