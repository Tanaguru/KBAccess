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

import org.opens.kbaccess.command.SelectReferenceCommand;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class SelectReferenceValidator implements Validator {

    private ReferenceDataService referenceDataService;

    public SelectReferenceValidator(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }
    
    /*
     * private methods
     */
    private boolean validateIdReference(SelectReferenceCommand selectReferenceCommand, Errors errors) {
        if (selectReferenceCommand.getIdReference() == null) {
            errors.rejectValue(FormKeyStore.ID_REFERENCE_KEY, MessageKeyStore.MISSING_REFERENCE_KEY);
            return false;
        } else if (referenceDataService.read(selectReferenceCommand.getIdReference()) == null) {
            errors.rejectValue(FormKeyStore.ID_REFERENCE_KEY, MessageKeyStore.INVALID_REFERENCE_KEY);
            return false;
        }
        
        return true;
    }
    /*
     * Validator implementation
     */

    @Override
    public boolean supports(Class type) {
        return SelectReferenceCommand.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SelectReferenceCommand selectReferenceCommand = (SelectReferenceCommand)o;
        
        if (!validateIdReference(selectReferenceCommand, errors)) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.MISSING_REQUIRED_FIELD);
        }
   } 
}
