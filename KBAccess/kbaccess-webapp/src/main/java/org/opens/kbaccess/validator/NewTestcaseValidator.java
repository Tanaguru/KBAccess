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

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.command.NewTestcaseCommand;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.HTTPKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.validator.utils.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bcareil
 */
public class NewTestcaseValidator implements Validator {

    public enum Step {
        STEP_TESTCASE,
        STEP_WEBARCHIVE
    }
    
    private ReferenceDataService referenceDataService;
    private ReferenceTestDataService referenceTestDataService;
    private TestcaseDataService testcaseDataService;
    private ResultDataService resultDataService;
    private WebarchiveDataService webarchiveDataService;
    private Step step;
    private static final int HTTP_STATUS_OK= 200;

    public NewTestcaseValidator(
            ReferenceTestDataService referenceTestDataService,
            ReferenceDataService referenceDataService,
            TestcaseDataService testcaseDataService,
            ResultDataService resultDataService,
            WebarchiveDataService webarchiveDataService,
            Step step
            ) {
        this.referenceDataService = referenceDataService;
        this.referenceTestDataService = referenceTestDataService;
        this.testcaseDataService = testcaseDataService;
        this.resultDataService = resultDataService;
        this.webarchiveDataService = webarchiveDataService;
        this.step = step;
    }
    
    /*
     * private methods
     */
    private boolean hasValidHttpResponse(String url, String method) {
        int responseCode = -1;
        
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method);

            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            LogFactory.getLog(NewTestcaseValidator.class.getName()).error(method + " request failed : " + e.getMessage());
        } 
        
        return (responseCode == HTTP_STATUS_OK);      
    }
    
    private boolean validateIdReferenceTest(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getIdReferenceTest() == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.MISSING_TEST_KEY);
            return false;
        } else if (referenceTestDataService.read(newTestcaseCommand.getIdReferenceTest()) == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.INVALID_TEST_KEY);
            return false;
        } else {
            ReferenceTest test = referenceTestDataService.read(newTestcaseCommand.getIdReferenceTest());
            Reference referenceSelected = referenceDataService.read(newTestcaseCommand.getIdReference());
            Reference referenceOfTest = referenceTestDataService.getReferenceOf(test);
            
            if (!referenceOfTest.equals(referenceSelected)) {
                errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.INVALID_TEST_KEY);
                return false;
            }
        } 
        
        return true;
    }

    private boolean validateIdResult(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getIdResult() == null) {
            errors.rejectValue(FormKeyStore.ID_RESULT_KEY, MessageKeyStore.MISSING_RESULT_KEY);
            return false;
        } else if (resultDataService.read(newTestcaseCommand.getIdResult()) == null) {
            errors.rejectValue(FormKeyStore.ID_RESULT_KEY, MessageKeyStore.MISSING_RESULT_KEY);
            return false;
        }
        return true;
    }

    private boolean validateUrlNewWebarchive(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getUrlNewWebarchive() == null || newTestcaseCommand.getUrlNewWebarchive().isEmpty()) {
            errors.rejectValue(FormKeyStore.URL_NEW_WEBARCHIVE_KEY, MessageKeyStore.MISSING_URL_KEY);
            return false;
        } else if (!UrlValidator.validate(newTestcaseCommand.getUrlNewWebarchive())) {
            errors.rejectValue(FormKeyStore.URL_NEW_WEBARCHIVE_KEY, MessageKeyStore.INVALID_URL_KEY);
            return false;
        } else if (!hasValidHttpResponse(newTestcaseCommand.getUrlNewWebarchive(), HTTPKeyStore.REQUEST_HEAD)) {
            if (!hasValidHttpResponse(newTestcaseCommand.getUrlNewWebarchive(), HTTPKeyStore.REQUEST_GET)) {
                errors.rejectValue(FormKeyStore.URL_NEW_WEBARCHIVE_KEY, MessageKeyStore.NOT_RESPONDING_URL);
                return false;
            }
            errors.rejectValue(FormKeyStore.URL_NEW_WEBARCHIVE_KEY, MessageKeyStore.NOT_RESPONDING_URL);
            return false;
        }
        return true;
    }

    private boolean validateIdWebarchive(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getIdWebarchive() == null) {
            errors.rejectValue(FormKeyStore.ID_WEBARCHIVE_KEY, MessageKeyStore.MISSING_WEBARCHIVE_KEY);
            return false;
        } else if (webarchiveDataService.read(newTestcaseCommand.getIdWebarchive()) == null) {
            errors.rejectValue(FormKeyStore.ID_WEBARCHIVE_KEY, MessageKeyStore.INVALID_WEBARCHIVE_KEY);
            return false;
        }
        return true;
    }
    
    private boolean validateDescription(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getDescription().length() > 5000) {
            errors.rejectValue(FormKeyStore.DESCRIPTION_TESTCASE_KEY, MessageKeyStore.TESTCASE_TOO_LONG_DESCRIPTION);
            return false;
        }
        
        return true;
    }
    
    private boolean validateDescriptionWebArchive(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getDescriptionNewWebarchive().length() > 255) {
            errors.rejectValue(FormKeyStore.DESCRIPTION_WEBARCHIVE_KEY, MessageKeyStore.WEBARCHIVE_TOO_LONG_DESCRIPTION);
            return false;
        }
        
        return true;
    }
    
    /*
     * Validator implementation
     */

    @Override
    public boolean supports(Class type) {
        return NewTestcaseCommand.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        boolean hasError = false;
        NewTestcaseCommand newTestcaseCommand = (NewTestcaseCommand)o;
        
        /* validate testcase */
        if (!validateIdReferenceTest(newTestcaseCommand, errors)
            || !validateIdResult(newTestcaseCommand, errors)
            || !validateDescription(newTestcaseCommand, errors)) {
            hasError = true;
        }
        
        /* validate webarchive */
        if (!hasError && step == Step.STEP_WEBARCHIVE) {
            if (newTestcaseCommand.isOnCreateWebarchive()) {
                if (!validateUrlNewWebarchive(newTestcaseCommand, errors)
                    || !validateDescriptionWebArchive(newTestcaseCommand, errors)) {
                    hasError = true;
                }
            } else {
                if (!validateIdWebarchive(newTestcaseCommand, errors)) {
                    hasError = true;
                }
            }
        }
//        if (!hasError && step == Step.STEP_WEBARCHIVE) {
//            if (!validateCreateWebarchive(newTestcaseCommand, errors)) {
//                hasError = true;
//            } else if (newTestcaseCommand.getCreateWebarchive()) {
//                if (!validateUrlNewWebarchive(newTestcaseCommand, errors)) {
//                    hasError = true;
//                }
//            } else if (!validateDescriptionWebArchive(newTestcaseCommand, errors)) {
//              hasError = true;  
//            } else {
//                if (!validateIdWebarchive(newTestcaseCommand, errors)) {
//                    hasError = true;
//                }
//            }
//        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.MISSING_REQUIRED_FIELD);
        }
   } 
}
