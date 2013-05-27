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
package org.opens.kbaccess.command;

import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.utils.SHA1Hasher;

/**
 *
 * @author blebail
 */
public class ChangePasswordCommand extends ACommand {
    
    private String currentPassword;
    private String newPassword;
    private String passwordConfirmation;

    public ChangePasswordCommand() {
    }

    public ChangePasswordCommand(String currentPassword, String newPassword, String confirmationPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.passwordConfirmation = confirmationPassword;
    }

    public void updateAccount(Account account) {
        account.setPassword(SHA1Hasher.getInstance().hashAsString(newPassword));
    }
    
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    } 
}
