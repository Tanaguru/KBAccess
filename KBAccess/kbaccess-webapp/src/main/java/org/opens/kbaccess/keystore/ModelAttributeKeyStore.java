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
package org.opens.kbaccess.keystore;

/**
 *
 * @author bcareil
 */
public final class ModelAttributeKeyStore {
    
    private ModelAttributeKeyStore() {
    }
    
    public static final String AUTHENTICATED_USER_KEY = "authenticatedUser";
    public static final String BREADCRUMB_TRAIL_KEY = "breadcrumbTrail";
    public static final String STATISTICS_KEY = "statistics";    
    public static final String TESTCASE_LIST_KEY = "testcaseList";
    public static final String USER_LOGIN_COMMAND_KEY = "userLoginCommand";
    public static final String WEBARCHIVE_LIST_KEY = "webarchiveList";
}
