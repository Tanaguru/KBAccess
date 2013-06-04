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
package org.opens.kbaccess.entity.dao.subject;

import junit.framework.TestCase;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.TestImpl;
import org.opens.kbaccess.entity.subject.TestResult;
import org.opens.kbaccess.entity.subject.TestResultImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class TestResultDAOImplTest extends AbstractDaoTestCase {
    
    public TestResultDAOImplTest(String testName) {
        super(testName, "src/test/resources/datasets/dataset.xml");
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private TestResultDAO getBean() {
        return (TestResultDAO) springBeanFactory.getBean("testResultDAO");
    }
    
    /**
     * Test of getEntityClass method, of class TestResultDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        TestResultDAOImpl instance = new TestResultDAOImpl();
        Class expResult = TestResultImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByTestResult method, of class TestResultDAOImpl.
     */
    public void testFindByTestResult() {
        System.out.println("findByTestResult");
        /* */
        Test test = new TestImpl();
        Result qResult = new ResultImpl();
        
        test.setId(1L);
        qResult.setId(1L);
        /* */
        TestResultDAO instance = getBean();
        /* */
        TestResult result = instance.findByTestResult(test, qResult);
        /* */
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        // TODO: error case
    }
}
