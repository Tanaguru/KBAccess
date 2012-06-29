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
package org.opens.kbaccess.entity.service.subject;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.subject.TestcaseDAO;
import org.opens.kbaccess.entity.factory.subject.TestcaseFactory;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 *
 * @author bcareil
 */
public class TestcaseDataServiceImplTest extends TestCase {
    
    TestcaseDAO mockedTestcaseDAO;
    TestcaseFactory mockedTestcaseFactory;
    
    public TestcaseDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedTestcaseDAO = createMock(TestcaseDAO.class);
        mockedTestcaseFactory = createMock(TestcaseFactory.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private TestcaseDataService newInstance() {
        TestcaseDataServiceImpl ofthejedi = new TestcaseDataServiceImpl();
        
        ofthejedi.setEntityDao(mockedTestcaseDAO);
        ofthejedi.setEntityFactory(mockedTestcaseFactory);
        return ofthejedi;
    }
    
    /**
     * Test of findAll method, of class TestcaseDataServiceImpl.
     */
    public void testFindAll() {
        System.out.println("findAll");
        /* */
        List expResult = createMock(List.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findAll()).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(expResult);
        /* run test */
        Collection result = instance.findAll();
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(expResult);
    }

    /**
     * Test of getAllFromAccount method, of class TestcaseDataServiceImpl.
     */
    public void testGetAllFromAccount() {
        System.out.println("getAllFromAccount");
        /* */
        Account account = createMock(Account.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findAllFromAccount(account)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(account);
        replay(expResult);
        /* run test */
        Collection result = instance.getAllFromAccount(account);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(expResult);
        verify(account);
    }

    /**
     * Test of getMaxPriorityFromTable method, of class TestcaseDataServiceImpl.
     */
    public void testGetMaxPriorityFromTable() {
        System.out.println("getMaxPriorityFromTable");
        /* */
        int expResult = 1;
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findMaxPriorityValueFromTable()).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        /* run test */
        int result = instance.getMaxPriorityFromTable();
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
    }

    /**
     * Test of createTestcase method, of class TestcaseDataServiceImpl.
     */
    public void testCreateTestcase_0args() {
        System.out.println("createTestcase");
        /* */
        Testcase expResult = createMock(Testcase.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        // this method does not do any DAO operations
        expect(mockedTestcaseFactory.create()).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(expResult);
        /* run test */
        Testcase result = instance.createTestcase();
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(expResult);
    }

    /**
     * Test of createTestcase method, of class TestcaseDataServiceImpl.
     */
    public void testCreateTestcase_5args() {
        System.out.println("createTestcase");
        /* */
        Account account = createMock(Account.class);
        Webarchive webarchive = createMock(Webarchive.class);
        Result resultArg = createMock(Result.class);
        Test test = createMock(Test.class);
        String description = "desc";
        String testCode = "code";
        String resultLabel = "label";
        Testcase expResult = createMock(Testcase.class);
        int rank = 1;
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseFactory.create()).andReturn(expResult);
        expResult.setWebarchive(webarchive);
        expResult.setAccount(account);
        expResult.setResult(resultArg);
        expResult.setTest(test);
        expResult.setDescription(description);
        expect(resultArg.getLabel()).andReturn(resultLabel);
        expect(test.getCode()).andReturn(testCode);
        expResult.setCdTestcase(testCode + '-' + resultLabel);
        expResult.setDateC(anyObject(Date.class));
        expect(mockedTestcaseDAO.findMaxPriorityValueFromTable()).andReturn(rank);
        expResult.setRank(rank + 1);
        expect(mockedTestcaseDAO.saveOrUpdate(expResult)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(account, webarchive, resultArg, test);
        replay(expResult);
        /* run test */
        Testcase result = instance.createTestcase(account, webarchive, resultArg, test, description);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(account, webarchive, resultArg, test);
        verify(expResult);
    }

    /**
     * Test of getLastTestcasesFromAccount method, of class TestcaseDataServiceImpl.
     */
    public void testGetLastTestcasesFromAccount() {
        /* Nominal use case */
        System.out.println("getLastTestcasesFromAccount : [nuc]");
        /* */
        Account account = createMock(Account.class);
        int nbOfTestcases = 1;
        List expResult = createMock(List.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findLastTestcasesFromAccount(account, nbOfTestcases)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(account);
        replay(expResult);
        /* run test */
        Collection result = instance.getLastTestcasesFromAccount(account, nbOfTestcases);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(account);
        verify(expResult);

        /* Error UC 1 : the DAO return null */
        System.out.println("getLastTestcasesFromAccount : [euc] dao return null");
        /* reset mocks */
        reset(mockedTestcaseDAO, mockedTestcaseFactory);
        reset(account);
        reset(expResult);
        /* set-up new instance */
        instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findLastTestcasesFromAccount(account, nbOfTestcases)).andReturn(null);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(account);
        replay(expResult);
        /* run test */
        result = instance.getLastTestcasesFromAccount(account, nbOfTestcases);
        /* check result */
        assertTrue(result.isEmpty());
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(account);
        verify(expResult);
    }

    /**
     * Test of getLastTestcases method, of class TestcaseDataServiceImpl.
     */
    public void testGetLastTestcases() {
        /* Nominal use case */
        System.out.println("getLastTestcases");
        /* */
        int nbOfTestcases = 1;
        List expResult = createMock(List.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findLastTestcases(nbOfTestcases)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(expResult);
        /* run test */
        Collection result = instance.getLastTestcases(nbOfTestcases);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(expResult);

        /* Error UC 1 : the DAO return null */
        System.out.println("getLastTestcases : [euc] dao return null");
        /* reset mocks */
        reset(mockedTestcaseDAO, mockedTestcaseFactory);
        reset(expResult);
        /* set-up new instance */
        instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findLastTestcases(nbOfTestcases)).andReturn(null);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(expResult);
        /* run test */
        result = instance.getLastTestcases(nbOfTestcases);
        /* check result */
        assertTrue(result.isEmpty());
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(expResult);
    }

    /**
     * Test of getAllFromUserSelection method, of class TestcaseDataServiceImpl.
     */
    public void testGetAllFromUserSelection() {
        /* Nominal use case */
        System.out.println("getAllFromUserSelection : [nuc]");
        /* */
        Reference reference = createMock(Reference.class);
        Criterion criterion = createMock(Criterion.class);
        Theme theme = createMock(Theme.class);
        Test test = createMock(Test.class);
        Level level = createMock(Level.class);
        Result resultArg = createMock(Result.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        TestcaseDataService instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(reference, criterion, theme, level, resultArg);
        replay(expResult);
        /* run test */
        Collection result = instance.getAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(reference, criterion, theme, level, resultArg);
        verify(expResult);

        /* Error UC 1 : DAO return null */
        System.out.println("getAllFromUserSelection : [euc] dao return null");
        /* reset mocks */
        reset(mockedTestcaseDAO, mockedTestcaseFactory);
        reset(reference, criterion, theme, level, resultArg);
        reset(expResult);
        /* set-up new instance */
        instance = newInstance();
        /* set-up mock */
        expect(mockedTestcaseDAO.findAllFromUserSelection(reference, criterion, theme, test, level, resultArg)).andReturn(null);
        /* replay mock */
        replay(mockedTestcaseDAO, mockedTestcaseFactory);
        replay(reference, criterion, theme, level, resultArg);
        replay(expResult);
        /* run test */
        result = instance.getAllFromUserSelection(reference, criterion, theme, test, level, resultArg);
        /* check result */
        assertTrue(result.isEmpty());
        verify(mockedTestcaseDAO, mockedTestcaseFactory);
        verify(reference, criterion, theme, level, resultArg);
        verify(expResult);
    }
}
