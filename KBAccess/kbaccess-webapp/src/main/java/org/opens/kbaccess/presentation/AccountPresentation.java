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
package org.opens.kbaccess.presentation;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;

/**
 *
 * @author bcareil
 */
public class AccountPresentation {
    
    private Long id;
    private Long nbCreatedTestcases;
    private Long nbCreatedWebarchives;
    
    private String firstName;
    private String lastName;
    private String email;
    private String displayedName;
    private String myUrl;
    
    private Date subscriptionDate;
    
    private AccessLevel accessLevel;
    
    public static String generateDisplayedName(Account account) {
        boolean hasAValidFirstName;
        boolean hasAValidLastName;
        
        hasAValidFirstName = (account.getFirstName() != null && !account.getFirstName().isEmpty());
        hasAValidLastName = (account.getLastName() != null && !account.getLastName().isEmpty());
        if (hasAValidFirstName && hasAValidLastName) {
            return new StringBuilder().append(account.getFirstName()).append(" ").append(account.getLastName()).toString();
        } else if (hasAValidFirstName) {
            return account.getFirstName();
        } else if (hasAValidLastName) {
            return account.getLastName();
        } else {          
            return account.getEmail();
        }
    }

    public AccountPresentation(Account account, AccountDataService accountDataService) {
        this.id = account.getId();
        this.accessLevel = account.getAccessLevel();
        this.nbCreatedTestcases = accountDataService.getNbTestcases(this.id);
        this.nbCreatedWebarchives = accountDataService.getNbWebarchives(this.id);
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.myUrl = account.getUrl();
        this.displayedName = generateDisplayedName(account);
        this.subscriptionDate = account.getSubscriptionDate();
    }

    
    /*
     * Accessors
     */
    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNbCreatedTestcases() {
        return nbCreatedTestcases;
    }

    public void setNbCreatedTestcases(Long nbCreatedTestcases) {
        this.nbCreatedTestcases = nbCreatedTestcases;
    }

    public Long getNbCreatedWebarchives() {
        return nbCreatedWebarchives;
    }

    public void setNbCreatedWebarchives(Long nbCreatedWebarchives) {
        this.nbCreatedWebarchives = nbCreatedWebarchives;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getMyUrl() {
        return myUrl;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }
}

