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
package org.opens.kbaccess.entity.service.subject;

import org.opens.kbaccess.entity.dao.subject.TestResultDAO;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.subject.TestResult;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 *
 * @author bcareil
 */
public class TestResultDataServiceImpl extends AbstractGenericDataService<TestResult, Long>
        implements TestResultDataService {

    public TestResultDataServiceImpl() {
        super();
    }
    
    @Override
    public TestResult getByTestResult(Test test, Result result) {
        return ((TestResultDAO)entityDao).findByTestResult(test, result);
    }
    
}
