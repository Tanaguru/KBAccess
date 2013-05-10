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

import org.opens.kbaccess.command.WebarchiveCommand;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.validator.utils.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class WebarchiveCommandValidator implements Validator {

    public WebarchiveCommandValidator() {
    }

    @Override
    public boolean supports(Class type) {
        return WebarchiveCommand.class.isAssignableFrom(type);
    }

    private boolean validateUrl(WebarchiveCommand webarchiveCommand, Errors errors) {
        if (webarchiveCommand.getUrl() == null || webarchiveCommand.getUrl().isEmpty()) {
            errors.rejectValue(FormKeyStore.URL_KEY, MessageKeyStore.MISSING_URL_KEY);
            return false;
        } else if (!UrlValidator.validate(webarchiveCommand.getUrl())) {
            errors.rejectValue(FormKeyStore.URL_KEY, MessageKeyStore.INVALID_URL_KEY);
            return false;
        }
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        WebarchiveCommand webarchiveCommand = (WebarchiveCommand) o;
        boolean hasError = false;
        
        if (!validateUrl(webarchiveCommand, errors)) {
            hasError = true;
        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.MISSING_REQUIRED_FIELD);
        }
    }
    
    
}
