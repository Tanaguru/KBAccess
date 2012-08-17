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
package org.opens.kbaccess.validator;

import org.opens.kbaccess.command.AccountCommand;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.validator.utils.PasswordValidator;
import org.opens.kbaccess.validator.utils.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public abstract class AAccountValidator implements Validator {

    protected AccountDataService accountDataService;
    
    public AAccountValidator(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }
    
    @Override
    public boolean supports(Class type) {
        return AccountCommand.class.isAssignableFrom(type);
    }

    private boolean validatePassword(AccountCommand cmd, Errors errors) {
        if (cmd.getPassword() == null || cmd.getPassword().isEmpty()) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.MISSING_PASSWORD_KEY);
            return false;
        } else if (cmd.getPasswordConfirmation() == null ||
                cmd.getPasswordConfirmation().isEmpty()) {
            errors.rejectValue(FormKeyStore.CONFIRMATION_PASSWORD_KEY, MessageKeyStore.MISSING_CONFIRMATION_PASSWORD_KEY);
            return false;
        } else if (cmd.getPassword().equals(cmd.getPasswordConfirmation()) == false) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.PASSWORD_MISMATCH_KEY);
            return false;
        } else if (PasswordValidator.validate(cmd.getPassword()) == false) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.INVALID_PASSWORD_KEY);
            return false;
        }
        return true;
    }
    
    protected abstract boolean validateEmail(AccountCommand cmd, Errors errors);

    private boolean validateUrl(AccountCommand cmd, Errors errors) {
        if (cmd.getUrl() == null || cmd.getUrl().trim().isEmpty()) {
            // it's ok, the url is not required
            return true;
        }
        if (UrlValidator.validate(cmd.getUrl()) == false) {
            errors.rejectValue(FormKeyStore.URL_KEY, MessageKeyStore.INVALID_URL_KEY);
        }
        return true;
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        AccountCommand cmd = (AccountCommand) o;
        boolean hasError = false;
        
        if (validatePassword(cmd, errors) == false) {
            hasError = true;
        }
        if (validateEmail(cmd, errors) == false) {
            hasError = true;
        }
        if (validateUrl(cmd, errors) == false) {
            hasError = true;
        }
        /*
         * No controls done on first name and last name.
         */
        
        if (hasError) {
            // TODO : use keys
            errors.rejectValue(
                    FormKeyStore.GENERAL_ERROR_MESSAGE_KEY,
                    MessageKeyStore.MISSING_REQUIRED_FIELD
                    );
        }
    }

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

}
