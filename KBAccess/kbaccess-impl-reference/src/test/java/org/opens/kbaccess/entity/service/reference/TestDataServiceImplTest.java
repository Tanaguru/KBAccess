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
package org.opens.kbaccess.entity.service.reference;

import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.TestDAO;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;

/**
 *
 * @author bcareil
 */
public class TestDataServiceImplTest extends TestCase {
    
    private TestDAO mockedTestDAO;
    
    public TestDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedTestDAO = createMock(TestDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTestListFromReference method, of class TestDataServiceImpl.
     */
    public void testGetTestListFromReference() {
        System.out.println("getTestListFromReference");
        /* */
        Reference reference = createMock(Reference.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        TestDataServiceImpl instance = new TestDataServiceImpl();
        instance.setEntityDao(mockedTestDAO);
        /* set-up mock */
        expect(mockedTestDAO.findAll(reference)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestDAO);
        replay(expResult);
        /* run test */
        List result = instance.getTestListFromReference(reference);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestDAO);
        verify(expResult);
    }

    /**
     * Test of getTestListFromCriterion method, of class TestDataServiceImpl.
     */
    public void testGetTestListFromCriterion() {
        System.out.println("getTestListFromCriterion");
        /* */
        Criterion criterion = createMock(Criterion.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        TestDataServiceImpl instance = new TestDataServiceImpl();
        instance.setEntityDao(mockedTestDAO);
        /* set-up mock */
        expect(mockedTestDAO.findAll(criterion)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestDAO);
        replay(expResult);
        /* run test */
        List result = instance.getTestListFromCriterion(criterion);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestDAO);
        verify(expResult);
    }

    /**
     * Test of getAllByCode method, of class TestDataServiceImpl.
     */
    public void testGetAllByCode() {
        System.out.println("getAllByCode");
        /* */
        String[] codeArray = new String[] { "lol" };
        List expResult = createMock(List.class);
        /* set-up instance */
        TestDataServiceImpl instance = new TestDataServiceImpl();
        instance.setEntityDao(mockedTestDAO);
        /* set-up mock */
        expect(mockedTestDAO.findAllByCode(codeArray)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestDAO);
        replay(expResult);
        /* run test */
        List result = instance.getAllByCode(codeArray);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestDAO);
        verify(expResult);
    }

    /**
     * Test of getByCode method, of class TestDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Test expResult = createMock(Test.class);
        /* set-up instance */
        TestDataServiceImpl instance = new TestDataServiceImpl();
        instance.setEntityDao(mockedTestDAO);
        /* set-up mock */
        expect(mockedTestDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestDAO);
        replay(expResult);
        /* run test */
        Test result = instance.getByCode(code);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestDAO);
        verify(expResult);
    }

    /**
     * Test of getByCodeAndReference method, of class TestDataServiceImpl.
     */
    public void testGetByCodeAndReference() {
        System.out.println("getByCodeAndReference");
        /* */
        String code = "code";
        Reference ref = createMock(Reference.class);
        Test expResult = createMock(Test.class);
        /* set-up instance */
        TestDataServiceImpl instance = new TestDataServiceImpl();
        instance.setEntityDao(mockedTestDAO);
        /* set-up mock */
        expect(mockedTestDAO.findByCodeAndReference(code, ref)).andReturn(expResult);
        /* replay mock */
        replay(mockedTestDAO);
        replay(expResult);
        /* run test */
        Test result = instance.getByCodeAndReference(code, ref);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedTestDAO);
        verify(expResult);
    }
}
