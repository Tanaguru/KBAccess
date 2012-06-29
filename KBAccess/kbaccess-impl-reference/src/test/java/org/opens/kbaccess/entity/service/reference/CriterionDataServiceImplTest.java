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

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.CriterionDAO;
import org.opens.kbaccess.entity.reference.*;

/**
 *
 * @author bcareil
 */
public class CriterionDataServiceImplTest extends TestCase {
    
    private CriterionDAO mockedCriterionDAO;
    
    public CriterionDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedCriterionDAO = createMock(CriterionDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of read method, of class CriterionDataServiceImpl.
     */
    public void testRead() {
        System.out.println("read");
        //
        List<Test> testList = new ArrayList<Test>();
        Long key = 1L;
        Criterion expResult = createMock(Criterion.class);
        /* set-up instance */
        CriterionDataServiceImpl instance = new CriterionDataServiceImpl();
        instance.setEntityDao(mockedCriterionDAO);
        /* set-up mock */
        expect(mockedCriterionDAO.read(key)).andReturn(expResult);
        // to fetch the test, the service layer fo a getTestList.size()
        expect(expResult.getTestList()).andReturn(testList);
        /* replay mock */
        replay(mockedCriterionDAO);
        replay(expResult);
        /* run test */
        Criterion result = instance.read(key);
        /* test result */
        assertEquals(expResult, result);
        verify(mockedCriterionDAO);
        verify(expResult);
    }

    /**
     * Test of getByCode method, of class CriterionDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Criterion expResult = createMock(Criterion.class);
        /* set-up instance */
        CriterionDataServiceImpl instance = new CriterionDataServiceImpl();
        instance.setEntityDao(mockedCriterionDAO);
        /* set-up mock */
        expect(mockedCriterionDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedCriterionDAO);
        replay(expResult);
        /* run tests */
        Criterion result = instance.getByCode(code);
        /* test result */
        assertEquals(expResult, result);
        verify(mockedCriterionDAO);
        verify(expResult);
    }

    /**
     * Test of getCriterionListFromReference method, of class CriterionDataServiceImpl.
     */
    public void testGetCriterionListFromReference() {
        System.out.println("getCriterionListFromReference");
        /* */
        Reference ref = createMock(Reference.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        CriterionDataServiceImpl instance = new CriterionDataServiceImpl();
        instance.setEntityDao(mockedCriterionDAO);
        /* set-up mock */
        expect(mockedCriterionDAO.findAllFromReference(ref)).andReturn(expResult);
        /* replay mock */
        replay(mockedCriterionDAO);
        replay(expResult);
        /* run tests */
        List result = instance.getCriterionListFromReference(ref);
        /* test result */
        assertEquals(expResult, result);
        verify(mockedCriterionDAO);
        verify(expResult);
    }

    /**
     * Test of getCriterionListFromTheme method, of class CriterionDataServiceImpl.
     */
    public void testGetCriterionListFromTheme() {
        System.out.println("getCriterionListFromTheme");
        /* */
        Theme theme = createMock(Theme.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        CriterionDataServiceImpl instance = new CriterionDataServiceImpl();
        instance.setEntityDao(mockedCriterionDAO);
        /* set-up mock */
        expect(mockedCriterionDAO.findAllFromTheme(theme)).andReturn(expResult);
        /* replay mock */
        replay(mockedCriterionDAO);
        replay(expResult);
        /* run tests */
        List result = instance.getCriterionListFromTheme(theme);
        /* test result */
        assertEquals(expResult, result);
        verify(mockedCriterionDAO);
        verify(expResult);
    }
}
