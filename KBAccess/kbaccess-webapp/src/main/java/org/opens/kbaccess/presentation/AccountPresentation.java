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
package org.opens.kbaccess.presentation;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;

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
    
    private Date subscriptionDate;
    private Date lastOperationDate;
    
    public static String generateDisplayedName(Account account) {
        boolean hasAValidFirstName;
        boolean hasAValidLastName;
        
        hasAValidFirstName = (account.getFirstName() != null && account.getFirstName().isEmpty() == false);
        hasAValidLastName = (account.getLastName() != null && account.getLastName().isEmpty() == false);
        if (hasAValidFirstName && hasAValidLastName) {
            return new StringBuilder().append(account.getFirstName()).append(" ").append(account.getLastName()).toString();
        } else if (hasAValidFirstName) {
            return account.getFirstName();
        } else if (hasAValidLastName) {
            return account.getLastName();
        } else {
            /*String[] userAndDomain;
            
            userAndDomain = account.getEmail().replace(".", " point ").split("@", 2);
            return new StringBuilder().append(userAndDomain[0]).append(" chez ").append(userAndDomain[1]).toString();*/
            
            return account.getEmail();
        }
    }

    public AccountPresentation() {
    }

    public AccountPresentation(Account account) {
        this.id = account.getId();
        this.nbCreatedTestcases = 0L; // TODO
        this.nbCreatedWebarchives = 0L; // TODO
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.displayedName = generateDisplayedName(account);
        this.subscriptionDate = account.getSubscriptionDate();
        this.lastOperationDate = new Date(0L); // TODO
    }

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

    public Date getLastOperationDate() {
        return lastOperationDate;
    }

    public void setLastOperationDate(Date lastOperationDate) {
        this.lastOperationDate = lastOperationDate;
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

}
