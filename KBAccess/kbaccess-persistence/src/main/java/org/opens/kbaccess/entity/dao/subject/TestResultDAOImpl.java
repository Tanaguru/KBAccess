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
package org.opens.kbaccess.entity.dao.subject;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.subject.TestResult;
import org.opens.kbaccess.entity.subject.TestResultImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author bcareil
 */
public class TestResultDAOImpl extends AbstractJPADAO<TestResult, Long>
        implements TestResultDAO {

    @Override
    protected Class<? extends TestResult> getEntityClass() {
        return TestResultImpl.class;
    }

    private Query selectTestResult(String where) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT tc FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" tc ");
        sb.append(where);
        return entityManager.createQuery(sb.toString());
    }

    @Override
    public TestResult findByTestResult(Test test, Result result) {
        Query query = selectTestResult("WHERE tc.test = :test AND tc.result = :result");
        
        query.setParameter("test", test);
        query.setParameter("result", result);
        try {
            return (TestResult) query.getSingleResult();
        } catch (NoResultException e) {
            LogFactory.getLog(TestResultDAOImpl.class).error("Unable to find the test_result for the test " + test.getCode() + " and the result " + result.getCode() + ".");
            return null;
        }
    }
    
}
