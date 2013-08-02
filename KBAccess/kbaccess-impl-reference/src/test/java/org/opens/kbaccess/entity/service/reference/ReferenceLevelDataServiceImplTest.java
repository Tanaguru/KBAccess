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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.kbaccess.entity.dao.reference.ReferenceLevelDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceImpl;
import org.opens.kbaccess.entity.reference.ReferenceLevel;

/**
 *
 * @author blebail
 */
public class ReferenceLevelDataServiceImplTest extends TestCase {
    
    private ReferenceLevelDAO mockedReferenceLevelDAO;
    private ReferenceDataService mockedReferenceDataService;
    private List<Reference> references = new ArrayList<Reference>();
    private Map<Reference, Map<String,ReferenceLevel>> mockedReferenceLevels = new TreeMap<Reference, Map<String, ReferenceLevel>>();
    
    public ReferenceLevelDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         
        mockedReferenceLevelDAO = EasyMock.createMock(ReferenceLevelDAO.class); 
        Collection<ReferenceLevel> referenceLevels = new ArrayList<ReferenceLevel>();
        
        mockedReferenceDataService = EasyMock.createMock(ReferenceDataService.class);
        
        for (int i = 0; i < 2; i++) {
            Reference reference = new ReferenceImpl();
            reference.setCode("ref" + i);
            references.add(reference);
            mockedReferenceLevels.put(reference, new TreeMap<String, ReferenceLevel>());
            
            EasyMock.expect(mockedReferenceDataService.getByCode("ref" + i)).andReturn(reference).anyTimes();
            
            for (int j = 0 ; j < 3 ; j++) {
                ReferenceLevel referenceLevel = EasyMock.createMock(ReferenceLevel.class);
                referenceLevels.add(referenceLevel);
                        
                EasyMock.expect(referenceLevel.getCode()).andReturn("ref" + i + "-level" + j).anyTimes();
                mockedReferenceLevels.get(reference).put("ref" + i + "-level" + j, referenceLevel);
                EasyMock.replay(referenceLevel);
            }
        }
        
        EasyMock.expect(mockedReferenceLevelDAO.findAll()).andReturn(referenceLevels).once();
        EasyMock.replay(mockedReferenceLevelDAO);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        EasyMock.verify(mockedReferenceLevelDAO);
        EasyMock.verify(mockedReferenceDataService);
        
        for (Map<String, ReferenceLevel> map : mockedReferenceLevels.values()) {
            for (ReferenceLevel referenceLevel : map.values())
                EasyMock.verify(referenceLevel);
        }
    }

    /**
     * Test of getByCode method, of class ReferenceLevelDataServiceImpl.
     */
    public void testGetByCode() {
        String errorCode = "zefiopzen";
        /* Mock expectations */
        EasyMock.expect(mockedReferenceDataService.getByCode(errorCode)).andReturn(null).once();
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        System.out.println("getByCode");
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);

        /* */
        String code = "ref0-level0";
        ReferenceLevel expResult = mockedReferenceLevels.get(references.get(0)).get(code);
        ReferenceLevel result = instance.getByCode(code);
        assertEquals(expResult, result);
        
        /* */
        System.out.println("getByCode : error case");
        result = instance.getByCode(errorCode);
        
        assertNull(result);
    }
    
    /**
     * Test of getCount method, of class ReferenceLevelDataServiceImpl.
     */
    public void testGetCount() {
        System.out.println("getCount");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
        
        /* */
        long count = 0L;
        
        for (Map<String, ReferenceLevel> map : mockedReferenceLevels.values()) {
            count += map.size();
        }
        
        Long expResult = count;
        Long result = instance.getCount();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllByReference method, of class ReferenceLevelDataServiceImpl.
     */
    public void testGetAllByReference() {
        System.out.println("getAllByReference");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
        
        /* */
        List<ReferenceLevel> expResult = new ArrayList<ReferenceLevel>(mockedReferenceLevels.get(references.get(0)).values());
        List<ReferenceLevel> result = new ArrayList<ReferenceLevel>(instance.getAllByReference(references.get(0)));
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findAll method, of class ReferenceLevelDataServiceImpl
     */
    public void testFindAll() {
        System.out.println("findAll()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
        
        /* */
        List<ReferenceLevel> expResult = new ArrayList<ReferenceLevel>();
        
        for (Map<String, ReferenceLevel> map : mockedReferenceLevels.values()) {
            for (Map.Entry<String, ReferenceLevel> entry : map.entrySet()) {
                expResult.add(entry.getValue());
            }
        }
        
        List<ReferenceLevel> result = new ArrayList<ReferenceLevel>(instance.findAll());
        
        assertEquals(expResult, result);
        
        /* */
        System.out.println("findAll() : reset of DAO");
        instance.setEntityDao(mockedReferenceLevelDAO);
        result = new ArrayList<ReferenceLevel>(instance.findAll());
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getInternMap accessor, of class ReferenceLevelDataServiceImpl.
     */
    public void testGetInternMap() {
        System.out.println("getInternMap()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
             
        Map<Reference, Map<String, ReferenceLevel>> result = instance.getInternMap();
        
        assertEquals(mockedReferenceLevels, result);        
    }
    
    /**
     * Test of getReferenceDataService accessor, of class ReferenceLevelDataServiceImpl.
     */
    public void testGetReferenceDataService() {
        System.out.println("getReferenceDataService()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
             
        ReferenceDataService result = instance.getReferenceDataService();
        
        assertNotNull(result);      
    }
    
    /*
     * Test of getReferenceOf accessor, of class ReferenceLevelDataServiceImpl
     */
    public void testGetReferenceOf() {
        System.out.println("getReferenceOf()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */   
        ReferenceLevelDataServiceImpl instance = new ReferenceLevelDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceLevelDAO);
        
        Reference expResult = references.get(0);
        Reference result = instance.getReferenceOf(mockedReferenceLevels.get(references.get(0)).get("ref0-level0"));
        
        assertEquals(expResult, result);  
    }
}
