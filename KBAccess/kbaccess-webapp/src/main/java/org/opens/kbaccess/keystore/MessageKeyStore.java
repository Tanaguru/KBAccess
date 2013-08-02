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
package org.opens.kbaccess.keystore;

/**
 *
 * @author bcareil
 */
public final class MessageKeyStore {
    
    private MessageKeyStore() {
    }
    
    public static final String EMAIL_ALREADY_IN_USE_KEY = "emailAlreadyInUse";
    public static final String GENERIC_ERROR_KEY = "genericError";
    public static final String ERROR_PASSWORD_CHANGE_KEY = "errorPasswordChange";
    public static final String INACTIVE_ACCOUNT_KEY = "inactiveAccount";

    public static final String INVALID_CRITERION_KEY = "invalidCriterion";
    public static final String INVALID_EMAIL_KEY = "invalidEmail";
    public static final String INVALID_PASSWORD_KEY = "invalidPassword";
    public static final String INVALID_NEW_PASSWORD_KEY = "invalidNewPassword";
    public static final String INVALID_RESULT_KEY = "invalidResult";
    public static final String INVALID_TEST_FOR_GIVEN_CRITERION_KEY = "invalidTestForGivenCriterion";
    public static final String INVALID_TEST_KEY = "invalidTest";
    public static final String INVALID_REFERENCE_KEY = "invalidReference";
    public static final String INVALID_TITLE_KEY = "invalidTitle";
    public static final String INVALID_URL_KEY = "invalidUrl";
    public static final String INVALID_WEBARCHIVE_KEY = "invalidWebarchive";
    public static final String INVALID_FIRSTNAME_KEY = "invalidFirstName";
    public static final String INVALID_LASTNAME_KEY = "invalidLastName";
    
    public static final String MISSING_CONFIRMATION_PASSWORD_KEY = "missingConfirmationPassword";
    public static final String MISSING_CREATE_WEBARCHIVE_KEY = "missingCreateWebarchive";
    public static final String MISSING_CRITERION_KEY = "missingCriterion";
    public static final String MISSING_EMAIL_KEY = "missingEmail";
    public static final String MISSING_PASSWORD_KEY = "missingPassword";
    public static final String MISSING_CURRENT_PASSWORD_KEY = "missingCurrentPassword";
    public static final String MISSING_NEW_PASSWORD_KEY = "missingNewPassword";
    public static final String MISSING_REQUIRED_FIELD = "requiredFieldIsMissing";
    public static final String MISSING_RESULT_KEY = "missingResult";
    public static final String MISSING_TEST_KEY = "missingTest";
    public static final String MISSING_REFERENCE_KEY = "missingReference";
    public static final String MISSING_TITLE_KEY = "missingTitle";    
    public static final String MISSING_URL_KEY = "missingUrl";
    public static final String MISSING_WEBARCHIVE_KEY = "missingWebarchive";
    
    public static final String NOT_RESPONDING_URL = "notRespondingUrl";
    public static final String PASSWORD_MISMATCH_KEY = "passwordMismatch";
    public static final String WEBARCHIVE_TOO_LONG_DESCRIPTION = "webarchiveTooLongDescription";
    public static final String TESTCASE_TOO_LONG_DESCRIPTION = "testcaseTooLongDescription";
    
    public static final String TESTCASE_EDITED = "testcaseEdited";
    public static final String TESTCASE_DELETED = "testcaseDeleted";
    public static final String TESTCASE_DOESNT_EXIST = "testcaseDoesntExist";
    public static final String NOT_AUTHORIZED_TO_EDIT_TESTCASE = "notAuthorizedToEditTestcase";
    public static final String NOT_AUTHORIZED_TO_DELETE_TESTCASE = "notAuthorizedToDeleteTestcase";
    public static final String ACCOUNT_EDITED = "accountEdited";
    public static final String PASSWORD_CHANGED = "passwordChanged";
    
    public static final String SUBSCRIBE_ERROR = "subscribeError";
    public static final String PASSWORD_LOST_ERROR = "passwordLostError";
    public static final String CANNOT_CREATE_WEBARCHIVE = "cannotCreateWebarchive";
    public static final String USER_DOESNT_EXIST = "userDoesntExist";
    public static final String NOT_AUTHORIZED_TO_EDIT_USER = "notAuthorizedToEditUser";
    public static final String USER_EDITED = "userEdited";
}
