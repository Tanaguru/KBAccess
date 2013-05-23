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

import org.opens.kbaccess.command.ChangePasswordCommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.utils.SHA1Hasher;
import org.opens.kbaccess.validator.utils.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class ChangePasswordValidator implements Validator {

    @Autowired
    private AccountDataService accountDataService;
    
    @Override
    public boolean supports(Class type) {
        return ChangePasswordCommand.class.isAssignableFrom(type);
    }

    private boolean validateNewPassword(ChangePasswordCommand cmd, Errors errors) {
        if (cmd.getNewPassword() == null || cmd.getNewPassword().isEmpty()) {
            errors.rejectValue(FormKeyStore.NEW_PASSWORD_KEY, MessageKeyStore.MISSING_PASSWORD_KEY);
            return false;
        } else if (cmd.getPasswordConfirmation() == null ||
                cmd.getPasswordConfirmation().isEmpty()) {
            errors.rejectValue(FormKeyStore.CONFIRMATION_PASSWORD_KEY, MessageKeyStore.MISSING_CONFIRMATION_PASSWORD_KEY);
            return false;
        } else if (!cmd.getNewPassword().equals(cmd.getPasswordConfirmation())) {
            errors.rejectValue(FormKeyStore.NEW_PASSWORD_KEY, MessageKeyStore.PASSWORD_MISMATCH_KEY);
            return false;
        } else if (PasswordValidator.validate(cmd.getNewPassword())) {
            errors.rejectValue(FormKeyStore.NEW_PASSWORD_KEY, MessageKeyStore.INVALID_PASSWORD_KEY);
            return false;
        }
        return true;
    }

    private boolean validateOldPassword(ChangePasswordCommand cmd, Errors errors) {
        if (cmd.getOldPassword() == null || cmd.getOldPassword().isEmpty()) {
            errors.reject(FormKeyStore.NEW_PASSWORD_KEY, MessageKeyStore.MISSING_PASSWORD_KEY);
            return false;
        } else {
            Account account = AccountUtils.getInstance().getCurrentUser();
            
            if (account.getPassword().equals(SHA1Hasher.getInstance().hashAsString(cmd.getOldPassword()))) {
                return true;
            }
        }
        return false;
    }    
    
    @Override
    public void validate(Object o, Errors errors) {
        ChangePasswordCommand cmd = (ChangePasswordCommand) o;
        boolean hasError = false;
        
        if (!validateNewPassword(cmd, errors)) {
            hasError = true;
        }
        
        if (!validateOldPassword(cmd, errors)) {
            hasError = true;
        }
        
        if (hasError) {
            // TODO : use keys
            errors.rejectValue("formGeneralErrorMessage", "mandatoryFieldsContainErrors");
        }
    }

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

}
