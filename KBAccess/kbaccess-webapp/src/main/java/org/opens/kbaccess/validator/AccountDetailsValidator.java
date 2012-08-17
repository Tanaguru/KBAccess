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
import org.opens.kbaccess.validator.utils.EmailValidator;
import org.springframework.validation.Errors;

/**
 *
 * @author bcareil
 */
public class AccountDetailsValidator extends AAccountValidator {

    private final String originalEmail;
    
    public AccountDetailsValidator(AccountDataService accountDataService, String originalEmail) {
        super(accountDataService);
        this.originalEmail = originalEmail;
    }
    
    @Override
    protected boolean validateEmail(AccountCommand cmd, Errors errors) {
        if (cmd.getEmail() == null || cmd.getEmail().isEmpty()) {
            errors.rejectValue(FormKeyStore.EMAIL_KEY, MessageKeyStore.MISSING_EMAIL_KEY);
            return false;
        }
        if (EmailValidator.validate(cmd.getEmail()) == false) {
            errors.rejectValue(FormKeyStore.EMAIL_KEY, MessageKeyStore.INVALID_EMAIL_KEY);
            return false;
        }
        if (originalEmail.equals(cmd.getEmail()) == false &&
                accountDataService.getAccountFromEmail(cmd.getEmail()) != null) {
            errors.rejectValue(FormKeyStore.EMAIL_KEY, MessageKeyStore.EMAIL_ALREADY_IN_USE_KEY);
            return false;
        }
        return true;
    }
}
