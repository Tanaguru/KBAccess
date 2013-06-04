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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.reference.*;
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
        assertEquals(4, result);
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
        assertEquals(2, result.size());
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
        assertEquals(4, result.size());
        assertFalse(Arrays.asList(1L, 2L, 3L, 4L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findAllFromUserSelection method, of class TestcaseDAOImpl.
     */
    public void testFindAllFromUserSelection() {
        /* nominal use case : no research criteria */
        System.out.println("findAllFromUserSelection : [nuc] joker");
        /* */
        Reference reference = null;
        Criterion criterion = null;
        Theme theme = null;
        Test test = null;
        Level level = null;
        Result resultArg = null;
        /* */
        TestcaseDAO instance = getBean();
        /* */
//        List result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(4, result.size());
//        assertFalse(Arrays.asList(1L, 2L, 3L, 4L).retainAll(asIdList(result)));
//        
//        /* nominal use case : using reference */
//        System.out.println("findAllFromUserSelection : [nuc] reference");
//        /* */
//        reference = new ReferenceImpl();
//        criterion = null;
//        theme = null;
//        test = null;
//        level = null;
//        resultArg = null;
//        
//        reference.setId(1L);
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
//        
//        /* nominal use case : using criterion */
//        System.out.println("findAllFromUserSelection : [nuc] criterion");
//        /* */
//        reference = null;
//        criterion = new CriterionImpl();
//        theme = null;
//        test = null;
//        level = null;
//        resultArg = null;
//        
//        criterion.setId(3L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(3L, 4L).retainAll(asIdList(result)));
//        
//        /* nominal use case : theme */
//        System.out.println("findAllFromUserSelection : [nuc] theme");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = new ThemeImpl();
//        test = null;
//        level = null;
//        resultArg = null;
//        
//        theme.setId(1L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
//        
//        /* nominal use case : test, result = * */
//        System.out.println("findAllFromUserSelection : [nuc] test");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = null;
//        test = new TestImpl();
//        level = null;
//        resultArg = null;
//        
//        test.setId(1L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
//        
//        /* nominal use case : test, result = passed */
//        System.out.println("findAllFromUserSelection : [nuc] test passed");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = null;
//        test = new TestImpl();
//        level = null;
//        resultArg = new ResultImpl();
//        
//        test.setId(1L);
//        resultArg.setId(1L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertFalse(Arrays.asList(1L).retainAll(asIdList(result)));
//        
//        /* nominal use case : test, result = failed */
//        System.out.println("findAllFromUserSelection : [nuc] test failed");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = null;
//        test = new TestImpl();
//        level = null;
//        resultArg = new ResultImpl();
//        
//        test.setId(1L);
//        resultArg.setId(2L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertFalse(Arrays.asList(2L).retainAll(asIdList(result)));
//        
//        /* nominal use case : level */
//        System.out.println("findAllFromUserSelection : [nuc] level");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = null;
//        test = null;
//        level = new LevelImpl();
//        resultArg = null;
//        
//        level.setId(4L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(3L, 4L).retainAll(asIdList(result)));
//        
//        /* nominal use case : result */
//        System.out.println("findAllFromUserSelection : [nuc] result");
//        /* */
//        reference = null;
//        criterion = null;
//        theme = null;
//        test = null;
//        level = null;
//        resultArg = new ResultImpl();
//        
//        resultArg.setId(2L);
//        /* */
//        instance = getBean();
//        /* */
//        result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
//        /* */
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertFalse(Arrays.asList(2L, 4L).retainAll(asIdList(result)));
        
        // TODO error cases
        /* nominal use case : using reference */
        System.out.println("findAllFromUserSelection : [nuc] reference and wrong relative test");
        /* */
        reference = new ReferenceImpl();
        criterion = null;
        theme = null;
        test = new TestImpl();
        level = null;
        resultArg = null;
        
        reference.setId(new Long(1));
        test.setId(new Long(1));
        Criterion crit = new CriterionImpl();
        Reference ref = new ReferenceImpl();
        crit.setId(new Long(1));
        ref.setId(new Long(1));
        Long long1 = new Long(1);
        Long long2 = new Long(1);
        System.out.println(long1.hashCode());
        System.out.println(long2.hashCode());
        crit.setReference(reference );
        test.setCriterion(crit);

        /* */
        List result = instance.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
        /* */
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
    }
}
