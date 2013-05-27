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
package org.opens.kbaccess.entity.service.reference;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.ReferenceDAO;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;

/**
 *
 * @author bcareil
 */
public class ReferenceDataServiceImplTest extends TestCase {
    
    private ReferenceDAO mockedReferenceDAO;
    
    public ReferenceDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedReferenceDAO = createMock(ReferenceDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of read method, of class ReferenceDataServiceImpl.
     */
    public void testRead() {
        System.out.println("read");
        /* */
        Long key = 1L;
        Reference expResult = createMock(Reference.class);
        List<Criterion> collection = new ArrayList<Criterion>();
        /* set-up instance */
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        instance.setEntityDao(mockedReferenceDAO);
        /* set-up mock */
        expect(mockedReferenceDAO.read(key)).andReturn(expResult);
        expect(expResult.getCriterionList()).andReturn(collection);
        /* replay mock */
        replay(mockedReferenceDAO);
        replay(expResult);
        /* run test */
        Reference result = instance.read(key);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedReferenceDAO);
        verify(expResult);
    }

    /**
     * Test of getByCode method, of class ReferenceDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Reference expResult = createMock(Reference.class);
        /* set-up instance */
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        instance.setEntityDao(mockedReferenceDAO);
        /* set-up mock */
        expect(mockedReferenceDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedReferenceDAO);
        replay(expResult);
        /* run test */
        Reference result = instance.getByCode(code);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedReferenceDAO);
        verify(expResult);
    }
}
