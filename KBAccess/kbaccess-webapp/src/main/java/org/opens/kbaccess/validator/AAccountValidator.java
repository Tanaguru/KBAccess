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
    protected boolean hasError = false;
    private static final int MAX_NAME_LENGTH = 80;
    
    public AAccountValidator(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }
    
    @Override
    public boolean supports(Class type) {
        return AccountCommand.class.isAssignableFrom(type);
    }

    protected boolean validatePassword(AccountCommand cmd, Errors errors) {
        if (cmd.getPassword() == null || cmd.getPassword().isEmpty()) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.MISSING_PASSWORD_KEY);
            return false;
        } else if (cmd.getPasswordConfirmation() == null ||
                cmd.getPasswordConfirmation().isEmpty()) {
            errors.rejectValue(FormKeyStore.CONFIRMATION_PASSWORD_KEY, MessageKeyStore.MISSING_CONFIRMATION_PASSWORD_KEY);
            return false;
        } else if (!cmd.getPassword().equals(cmd.getPasswordConfirmation())) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.PASSWORD_MISMATCH_KEY);
            return false;
        } else if (!PasswordValidator.validate(cmd.getPassword())) {
            errors.rejectValue(FormKeyStore.PASSWORD_KEY, MessageKeyStore.INVALID_PASSWORD_KEY);
            return false;
        }
        return true;
    }
    
    protected abstract boolean validateEmail(AccountCommand cmd, Errors errors);
    
    protected abstract boolean validateAccessLevel(AccountCommand cmd, Errors errors);

    private boolean validateUrl(AccountCommand cmd, Errors errors) {
        if (cmd.getUrl() == null || cmd.getUrl().trim().isEmpty()) {
            // it's ok, the url is not required
            return true;
        }
        if (!UrlValidator.validate(cmd.getUrl())) {
            errors.rejectValue(FormKeyStore.URL_KEY, MessageKeyStore.INVALID_URL_KEY);
            return false;
        }
        return true;
    }
    
    private boolean validateFirstName(AccountCommand cmd, Errors errors) {
        String firstName = cmd.getFirstName();
        boolean hasCorrectSize = firstName.length() >= 0 && firstName.length() <= MAX_NAME_LENGTH;
            
        if (!hasCorrectSize) {
            errors.rejectValue(FormKeyStore.FIRSTNAME_KEY, MessageKeyStore.INVALID_FIRSTNAME_KEY);
            return false;
        }
        return true;
    }
    
    private boolean validateLastName(AccountCommand cmd, Errors errors) {
        String lastName = cmd.getLastName();
        boolean hasCorrectSize = (lastName.length() >= 0 && lastName.length() < MAX_NAME_LENGTH);
            
        if (!hasCorrectSize) {
            errors.rejectValue(FormKeyStore.LASTNAME_KEY, MessageKeyStore.INVALID_LASTNAME_KEY);
            return false;
        }
        return true;
    }   
    
    @Override
    public void validate(Object o, Errors errors) {
        AccountCommand cmd = (AccountCommand) o;
          
        if (!validateEmail(cmd, errors)
            || !validatePassword(cmd, errors)
            || !validateUrl(cmd, errors)
            || !validateFirstName(cmd, errors)   
            || !validateLastName(cmd, errors) 
            || !validateAccessLevel(cmd, errors)) {
          
            this.hasError = true;
        }
                 
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
