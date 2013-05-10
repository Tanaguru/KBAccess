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

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.command.NewTestcaseCommand;
import org.opens.kbaccess.entity.service.reference.CriterionDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.reference.TestDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.keystore.FormKeyStore;
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
    

    private CriterionDataService criterionDataService;
    private TestDataService testDataService;
    private ResultDataService resultDataService;
    private WebarchiveDataService webarchiveDataService;
    private Step step;
    private static final int HTTP_STATUS_OK= 200;

    public NewTestcaseValidator(
            CriterionDataService criterionDataService,
            TestDataService testcaseDataService,
            ResultDataService resultDataService,
            WebarchiveDataService webarchiveDataService,
            Step step
            ) {
        this.criterionDataService = criterionDataService;
        this.testDataService = testcaseDataService;
        this.resultDataService = resultDataService;
        this.webarchiveDataService = webarchiveDataService;
        this.step = step;
    }
    
    /*
     * private methods
     */
    private boolean hasValidHttpResponse(String url) {
        int responseCode = -1;
        
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            LogFactory.getLog(NewTestcaseValidator.class.getName()).error("HEAD request failed : " + e.getMessage());
        } 
        
        return (responseCode == HTTP_STATUS_OK);      
    }
    
//    private boolean validateIdCriterion(NewTestcaseCommand newTestcaseCommand, Errors errors) {
//        if (newTestcaseCommand.getIdCriterion() == null) {
//            if (newTestcaseCommand.getIdTest() == null) {
//                errors.rejectValue(FormKeyStore.ID_CRITERION_KEY, MessageKeyStore.MISSING_CRITERION_KEY);
//                return false;
//            }
//        } else if (criterionDataService.read(newTestcaseCommand.getIdCriterion()) == null) {
//            errors.rejectValue(FormKeyStore.ID_CRITERION_KEY, MessageKeyStore.INVALID_CRITERION_KEY);
//            return false;
//        }
//        /*
//         * NOTE: validateIdTest will check if the test is part of the
//         * selected criterion, if any.
//         */
//        return true;
//    }
    
    private boolean validateIdTest(NewTestcaseCommand newTestcaseCommand, Errors errors) {
//        if (newTestcaseCommand.getIdTest() == null) {
//            // if the criterion is specified, stops tests here and return true.
//            if (newTestcaseCommand.getIdCriterion() == null) {
//                // else, display an error message (both criterion and test
//                // will have an error message).
//                errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.MISSING_TEST_KEY);
//                return false;
//            }
//        } else {
//            Test test = testDataService.read(newTestcaseCommand.getIdTest());
//            if (test == null) {
//                errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.INVALID_TEST_KEY);
//                return false;
//            } else if (newTestcaseCommand.getIdCriterion() != null) {
//                // check that the selected criterion contains the selected test
//                if (test.getCriterion().getId() != newTestcaseCommand.getIdCriterion()) {
//                    errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.INVALID_TEST_FOR_GIVEN_CRITERION_KEY);
//                    return false;                    
//                }
//            }
//        }
//        return true;
        
        
        if (newTestcaseCommand.getIdTest() == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.MISSING_TEST_KEY);
            return false;
        } else if (testDataService.read(newTestcaseCommand.getIdTest()) == null) {
            errors.rejectValue(FormKeyStore.ID_TEST_KEY, MessageKeyStore.MISSING_TEST_KEY);
            return false;
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

    private boolean validateCreateWebarchive(NewTestcaseCommand newTestcaseCommand, Errors errors) {
        if (newTestcaseCommand.getCreateWebarchive() == null) {
            errors.rejectValue(FormKeyStore.CREATE_WEBARCHIVE_KEY, MessageKeyStore.MISSING_CREATE_WEBARCHIVE_KEY);
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
        } else if (!hasValidHttpResponse(newTestcaseCommand.getUrlNewWebarchive())) {
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
        //if (!validateIdCriterion(newTestcaseCommand, errors)
        if (!validateIdTest(newTestcaseCommand, errors)
            || !validateIdResult(newTestcaseCommand, errors)) {
            hasError = true;
        }
        
        /* validate webarchive */
        if (!hasError && step == Step.STEP_WEBARCHIVE) {
            if (!validateCreateWebarchive(newTestcaseCommand, errors)) {
                hasError = true;
            } else if (newTestcaseCommand.getCreateWebarchive()) {
                if (!validateUrlNewWebarchive(newTestcaseCommand, errors)) {
                    hasError = true;
                }
            } else {
                if (!validateIdWebarchive(newTestcaseCommand, errors)) {
                    hasError = true;
                }
            }
        }
        
        if (hasError) {
            errors.rejectValue(FormKeyStore.GENERAL_ERROR_MESSAGE_KEY, MessageKeyStore.MISSING_REQUIRED_FIELD);
        }
   } 
}
