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
import org.opens.kbaccess.command.NewPasswordCommand;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.springframework.validation.Errors;

/**
 *
 * @author blebail
 */
public class NewPasswordValidator extends ChangePasswordValidator {

    @Override
    public boolean supports(Class type) {
        return NewPasswordCommand.class.isAssignableFrom(type);
    }

    public NewPasswordValidator(AccountDataService accountDataService, String originalEmail) {
        super(accountDataService, originalEmail);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        ChangePasswordCommand cmd = (ChangePasswordCommand) o;
        boolean hasError = false;
        
        if (!validateNewPassword(cmd, errors)) {
            hasError = true;
        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.ERROR_PASSWORD_CHANGE_KEY);
        }
    }
}
