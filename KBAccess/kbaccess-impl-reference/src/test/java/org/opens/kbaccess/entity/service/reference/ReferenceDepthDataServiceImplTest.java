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
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.kbaccess.entity.dao.reference.ReferenceDepthDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceImpl;
import org.opens.kbaccess.entity.reference.ReferenceDepth;

/**
 *
 * @author blebail
 */
public class ReferenceDepthDataServiceImplTest extends TestCase {
    
    private ReferenceDepthDAO mockedReferenceDepthDAO;
    private ReferenceDataService mockedReferenceDataService;
    private List<Reference> references = new ArrayList<Reference>();
    private Map<Reference, Map<String,ReferenceDepth>> mockedReferenceDepths = new TreeMap<Reference, Map<String, ReferenceDepth>>();
    
    public ReferenceDepthDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         
        mockedReferenceDepthDAO = EasyMock.createMock(ReferenceDepthDAO.class); 
        Collection<ReferenceDepth> referenceDepths = new ArrayList<ReferenceDepth>();
        
        mockedReferenceDataService = EasyMock.createMock(ReferenceDataService.class);
        
        for (int i = 0; i < 2; i++) {
            Reference reference = new ReferenceImpl();
            reference.setCode("ref" + i);
            references.add(reference);
            mockedReferenceDepths.put(reference, new TreeMap<String, ReferenceDepth>());
            
            EasyMock.expect(mockedReferenceDataService.getByCode("ref" + i)).andReturn(reference).anyTimes();
            for (int j = 0 ; j < 3 ; j++) {
                ReferenceDepth referenceDepth = EasyMock.createMock(ReferenceDepth.class);
                referenceDepths.add(referenceDepth);
                        
                EasyMock.expect(referenceDepth.getCode()).andReturn("ref" + i + "-level" + j).anyTimes();
                mockedReferenceDepths.get(reference).put("ref" + i + "-level" + j, referenceDepth);
                EasyMock.replay(referenceDepth);
            }
        }
        
        EasyMock.expect(mockedReferenceDepthDAO.findAll()).andReturn(referenceDepths).once();
        EasyMock.replay(mockedReferenceDepthDAO);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        EasyMock.verify(mockedReferenceDepthDAO);
        EasyMock.verify(mockedReferenceDataService);
        
        for (Map<String, ReferenceDepth> map : mockedReferenceDepths.values()) {
            for (ReferenceDepth referenceDepth : map.values())
                EasyMock.verify(referenceDepth);
        }
    }
    
    /**
     * Test of findAll method, of class ReferenceDepthDataServiceImpl
     */
    public void testFindAll() {
        System.out.println("findAll()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceDepthDataServiceImpl instance = new ReferenceDepthDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceDepthDAO);
        
        /* */
        List<ReferenceDepth> expResult = new ArrayList<ReferenceDepth>();
        
        for (Map<String, ReferenceDepth> map : mockedReferenceDepths.values()) {
            for (Map.Entry<String, ReferenceDepth> entry : map.entrySet()) {
                expResult.add(entry.getValue());
            }
        }
        
        List<ReferenceDepth> result = new ArrayList<ReferenceDepth>(instance.findAll());
        
        assertEquals(expResult, result);
        
        /* */
        System.out.println("findAll() : reset of DAO");
        instance.setEntityDao(mockedReferenceDepthDAO);
        result = new ArrayList<ReferenceDepth>(instance.findAll());
        
        assertEquals(expResult, result);
    }
}
