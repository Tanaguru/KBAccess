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

import org.opens.kbaccess.command.EditTestcaseCommand;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class EditTestcaseValidator implements Validator {

    public static final String RE_TESTCASE_TITLE_VALIDATOR = ".*[A-Za-z]+.*";
    
    private TestcaseDataService testcaseDataService;
    private ResultDataService resultDataService;
    private ReferenceTestDataService referenceTestDataService;

    public EditTestcaseValidator(
            TestcaseDataService testcaseDataService,
            ResultDataService resultDataService, 
            ReferenceTestDataService referenceTestDataService) {
        
        this.testcaseDataService = testcaseDataService;
        this.resultDataService = resultDataService;
        this.referenceTestDataService = referenceTestDataService;
    }
    
    @Override
    public boolean supports(Class type) {
        return EditTestcaseCommand.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EditTestcaseCommand editTestcaseCommand = (EditTestcaseCommand) o;
        boolean hasError = false;
        
        if (!validateId(editTestcaseCommand, errors)) {
            return;
        }
        if (!validateIdResult(editTestcaseCommand, errors)) {
            hasError = true;
        }
        
        if (!validateIdTest(editTestcaseCommand, errors)) {
            hasError = true;
        }
        
        if (!validateDescription(editTestcaseCommand, errors)) {
            hasError = true;
        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.MISSING_REQUIRED_FIELD);
        }
    }

    private boolean validateId(EditTestcaseCommand editTestcaseCommand, Errors errors) {
        if (editTestcaseCommand.getId() == null) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.GENERIC_ERROR_KEY);
            return false;
        } else if (testcaseDataService.read(editTestcaseCommand.getId()) == null) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.GENERIC_ERROR_KEY);
            return false;
        }
        return true;
    }

    private boolean validateIdTest(EditTestcaseCommand editTestcaseCommand, Errors errors) {
        if (editTestcaseCommand.getIdReferenceTest() == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.MISSING_TEST_KEY);
            return false;
        } else if (referenceTestDataService.read(editTestcaseCommand.getIdReferenceTest()) == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.INVALID_TEST_KEY);
            return false;
        }
        return true;
    }
    
    private boolean validateIdResult(EditTestcaseCommand editTestcaseCommand, Errors errors) {
        if (editTestcaseCommand.getIdResult() == null) {
            errors.rejectValue(FormKeyStore.ID_RESULT_KEY, MessageKeyStore.MISSING_RESULT_KEY);
            return false;
        } else if (resultDataService.read(editTestcaseCommand.getIdResult()) == null) {
            errors.rejectValue(FormKeyStore.ID_RESULT_KEY, MessageKeyStore.INVALID_RESULT_KEY);
            return false;
        }
        return true;
    }
    
     private boolean validateDescription(EditTestcaseCommand editTestcaseCommand, Errors errors) {
        if (editTestcaseCommand.getDescription().length() > 5000) {
            errors.rejectValue(FormKeyStore.DESCRIPTION_TESTCASE_KEY, MessageKeyStore.TESTCASE_TOO_LONG_DESCRIPTION);
            return false;
        }
        
        return true;
    }
}
