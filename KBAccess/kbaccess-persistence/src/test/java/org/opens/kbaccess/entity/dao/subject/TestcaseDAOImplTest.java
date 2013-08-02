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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.ReferenceTestImpl;
import org.opens.kbaccess.entity.reference.ResultImpl;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class TestcaseDAOImplTest extends AbstractDaoTestCase {
    
    public TestcaseDAOImplTest(String testName) {
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
    
    private TestcaseDAO getBean() {
        return (TestcaseDAO) springBeanFactory.getBean("testcaseDAO");
    }

    /**
     * Test of getEntityClass method, of class TestcaseDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        TestcaseDAOImpl instance = new TestcaseDAOImpl();
        Class expResult = TestcaseImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMaxPriorityValueFromTable method, of class TestcaseDAOImpl.
     */
    public void testFindMaxPriorityValueFromTable() {
        System.out.println("findMaxPriorityValueFromTable");
        /* */
        /* */
        TestcaseDAO instance = getBean();
        /* */
        int result = instance.findMaxPriorityValueFromTable();
        /* */
        assertEquals(5, result);
    }

    /**
     * Test of findAllFromAccount method, of class TestcaseDAOImpl.
     */
    public void testFindAllFromAccount() {
        System.out.println("findAllFromAccount : [nuc]");
        /* */
        Account account = new AccountImpl();
        
        account.setId(1L);
        /* */
        TestcaseDAO instance = getBean();
        /* */
        List result = instance.findAllFromAccount(account);
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertFalse(Arrays.asList(3L, 4L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findLastTestcasesFromAccount method, of class TestcaseDAOImpl.
     */
    public void testFindLastTestcasesFromAccount() {
        System.out.println("findLastTestcasesFromAccount : [nuc]");
        /* */
        Account account = new AccountImpl();
        int nbOfTestcases = 5;
        
        account.setId(2L);
        /* */
        TestcaseDAO instance = getBean();
        /* */
        List result = instance.findLastTestcasesFromAccount(account, nbOfTestcases);
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findLastTestcases method, of class TestcaseDAOImpl.
     */
    public void testFindLastTestcases() {
        System.out.println("findLastTestcases : [nuc]");
        /* */
        int nbOfTestcases = 3;
        /* */
        TestcaseDAO instance = getBean();
        /* */
        List result = instance.findLastTestcases(nbOfTestcases);
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        // result are ordered by date desc
        assertFalse(Arrays.asList(2L, 3L, 4L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findAll method, of class TestcaseDAOImpl.
     */
    public void testFindAll() {
        System.out.println("findAll : [nuc]");
        /* */
        TestcaseDAO instance = getBean();
        /* */
        Collection result = instance.findAll();
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(5, result.size());
        assertFalse(Arrays.asList(1L, 2L, 3L, 4L).retainAll(asIdList(result)));
        // TODO error case
                
    }

    /**
     * Test of findAllFromUserSelection method, of class TestcaseDAOImpl.
     */
    public void testFindAllFromUserSelection() {
        /* */
        TestcaseDAO instance = getBean();
        
        System.out.println("findAllFromUserSelection : [nuc] no parameters");
        /* */
        List result = instance.findAllFromUserSelection(null, null);
        /* */
        assertNotNull(result);
        assertEquals(5, result.size());
        assertFalse(Arrays.asList(1L, 2L, 3L, 4L, 5L).retainAll(asIdList(result)));
        
        
        System.out.println("findAllFromUserSelection : [nuc] referenceTestCollection passed");
        /* */
        List<ReferenceTest> referenceTestCollection = new ArrayList<ReferenceTest>();
        ReferenceTest rt1 = new ReferenceTestImpl();
        ReferenceTest rt2 = new ReferenceTestImpl();
        ReferenceTest rt3 = new ReferenceTestImpl();
        
        rt1.setId(1L);
        rt2.setId(3L);
        rt3.setId(5L);
        referenceTestCollection.add(rt1);
        referenceTestCollection.add(rt2);
        referenceTestCollection.add(rt3);
        
        ResultImpl resultArg = new ResultImpl() ;
        resultArg.setId(1L);
        /* */
        List result2 = instance.findAllFromUserSelection(referenceTestCollection, resultArg);
        /* */
        assertNotNull(result2);
        assertFalse(result.isEmpty());
        assertEquals(2, result2.size());
        assertFalse(Arrays.asList(1L, 3L).retainAll(asIdList(result2)));
    }
    
    /**
     * Test of count method, of class TestcaseDAOImpl.
     */
    public void testCount() {
        System.out.println("count");
        TestcaseDAO instance = getBean();
        
        assertEquals(5L, instance.count().longValue());
    }
}
