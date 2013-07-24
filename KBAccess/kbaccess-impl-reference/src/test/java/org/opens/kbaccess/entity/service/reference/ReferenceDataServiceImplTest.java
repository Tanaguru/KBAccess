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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.ReferenceDAO;
import org.opens.kbaccess.entity.reference.Reference;

/**
 *
 * @author blebail
 */
public class ReferenceDataServiceImplTest extends TestCase {
    
    private ReferenceDAO mockedReferenceDAO;
    private Map<String,Reference> mockedReferences = new TreeMap<String, Reference>();
    
    /**
     * Init the mocks for testFindAll()
     */
    private void initMockDaoFindAll() {
        mockedReferenceDAO = createMock(ReferenceDAO.class); 
        Collection<Reference> references = new ArrayList<Reference>();
        for (int i=0 ; i<2 ; i++) {
            Reference ref = createMock(Reference.class);
            expect(ref.getCode()).andReturn("AW"+i).once();
            
            references.add(ref);
            mockedReferences.put("AW"+i, ref);
            replay(ref);
        }
        
        expect(mockedReferenceDAO.findAll()).andReturn(references).once();
        replay(mockedReferenceDAO);
    }
    
    /**
     * Init the mocks for testRead()
     */
    private void initMockDaoRead() {
        mockedReferenceDAO = createMock(ReferenceDAO.class); 
        Reference ref = createMock(Reference.class);
        expect(ref.getCode()).andReturn("MyCode").once();
        mockedReferences.put("MyCode", ref);
        replay(ref);
        expect(mockedReferenceDAO.read(Long.valueOf(1))).andReturn(ref).once();
        expect(mockedReferenceDAO.findAll()).andReturn(new ArrayList<Reference>()).once();
        replay(mockedReferenceDAO);
    }
    
    public ReferenceDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        verify(mockedReferenceDAO);
        for (Reference ref : mockedReferences.values()) {
            verify(ref);
        }
    }

    /**
     * Test of read method, of class ReferenceDataServiceImpl.
     */
    public void testRead() {
        System.out.println("read");
        /* */
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        initMockDaoRead();
        instance.setEntityDao(mockedReferenceDAO);
        Reference result = instance.read(Long.valueOf(1));
        assertEquals("MyCode", result.getCode());
    }

    /**
     * Test of getByCode method, of class ReferenceDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "AW1";
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        initMockDaoFindAll();
        instance.setEntityDao(mockedReferenceDAO);
        /* */
         
        Reference result = instance.getByCode(code);
        assertEquals(mockedReferences.get(code), result);
        
        System.out.println("getByCode : error case");
        /* */
        code = "AW4";
        /* */
        result = instance.getByCode(code);
        /* check result */
        assertNull(result);
    }
    
    /**
     * Test of getCount method, of class ReferenceDataServiceImpl.
     */
    public void testGetCount() {
        System.out.println("getCount");
        
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        initMockDaoFindAll();
        instance.setEntityDao(mockedReferenceDAO);
        
        Long expResult = Long.valueOf(mockedReferences.size());
        Long result = instance.getCount();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getInternMap accessor, of class ReferenceDataServiceImpl.
     */
    public void testGetInternMap() {
        System.out.println("getInternMap");
        
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        initMockDaoFindAll();
        instance.setEntityDao(mockedReferenceDAO);
        
        Map<String, Reference> result = instance.getInternMap();
        
        assertEquals(mockedReferences, result);        
    }
    
    /**
     * Test of findAll method, of class ReferenceDataServiceImpl
     */
    public void testFindAll() {
        System.out.println("findAll()");
        /* */        
        /* */
        ReferenceDataServiceImpl instance = new ReferenceDataServiceImpl();
        initMockDaoFindAll();
        instance.setEntityDao(mockedReferenceDAO);
        
        
        /* */
        List<Reference> expResult = new ArrayList<Reference>(mockedReferences.values());
        List<Reference> result = new ArrayList<Reference>(instance.findAll());
        
        assertEquals(expResult, result);
        
        /* */
        System.out.println("findAll() : reset of DAO");
        instance.setEntityDao(mockedReferenceDAO);
        result = new ArrayList<Reference>(instance.findAll());
        
        assertEquals(expResult, result);
    }
}
