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
package org.opens.kbaccess.validator;

import org.opens.kbaccess.command.PasswordLostCommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class PasswordLostValidator implements Validator {

    private AccountDataService accountDataService;

    public PasswordLostValidator(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }
    
    @Override
    public boolean supports(Class type) {
        return PasswordLostCommand.class.isAssignableFrom(type);
    }

    private boolean validateEmail(PasswordLostCommand cmd, Errors errors) {
        Account account;
        
        account = accountDataService.getAccountFromEmail(cmd.getEmail());
        if (account == null) {
            errors.rejectValue("email", MessageKeyStore.INVALID_EMAIL_KEY);
            return false;
        } else if (!account.isActivated()) {
            errors.rejectValue("email", MessageKeyStore.INACTIVE_ACCOUNT_KEY);
        }
        return true;
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        PasswordLostCommand command = (PasswordLostCommand)o;
        boolean hasError = false;
        
        if (!validateEmail(command, errors)) {
            hasError = true;
        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, "Invalid email address");
        }
    }
    
}
