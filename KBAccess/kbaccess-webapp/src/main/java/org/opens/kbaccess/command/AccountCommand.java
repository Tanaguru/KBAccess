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
package org.opens.kbaccess.command;

import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.utils.SHA1Hasher;

/**
 *
 * @author bcareil
 */
public class AccountCommand extends ACommand {
    
    public String email;
    public String firstName;
    public String lastName;
    public String url;
    public String password;
    public String passwordConfirmation;

    public AccountCommand(String email, String firstName, String lastName, String url, String password, String passwordConfirmation) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }
    
    public AccountCommand(Account account) {
        this.email = account.getEmail();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.url = account.getUrl();
        this.password = "";
        this.passwordConfirmation = "";
    }

    public AccountCommand() {
    }
    
    public void updateAccount(Account account) {
        account.setEmail(email);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUrl(url);
        account.setPassword(SHA1Hasher.getInstance().hashAsString(password));
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }  
}
