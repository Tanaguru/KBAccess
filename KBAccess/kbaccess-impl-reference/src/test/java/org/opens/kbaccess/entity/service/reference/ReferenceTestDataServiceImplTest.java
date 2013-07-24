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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.kbaccess.entity.dao.reference.ReferenceTestDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceDepth;
import org.opens.kbaccess.entity.reference.ReferenceDepthImpl;
import org.opens.kbaccess.entity.reference.ReferenceImpl;
import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceInfoImpl;
import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceLevelImpl;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.ReferenceTestImpl;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;

/**
 *
 * @author blebail
 */
public class ReferenceTestDataServiceImplTest extends TestCase {
    
    private ReferenceTestDAO mockedReferenceTestDAO;
    private ReferenceDataService mockedReferenceDataService;
    private List<Reference> references = new ArrayList<Reference>();
    private List<ReferenceDepth> referenceDepths = new ArrayList<ReferenceDepth>();
    
    private Map<Reference, Map<ReferenceDepth, Set<ReferenceTest>>> referenceTests = 
            new TreeMap<Reference, Map<ReferenceDepth, Set<ReferenceTest>>>();
    
    public ReferenceTestDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         
        mockedReferenceTestDAO = EasyMock.createMock(ReferenceTestDAO.class); 
        Collection<ReferenceTest> referenceTestCollection = new ArrayList<ReferenceTest>();
        
        mockedReferenceDataService = EasyMock.createMock(ReferenceDataService.class);
        
        for (int i = 0; i < 2; i++) {
            Reference reference = new ReferenceImpl();
            reference.setCode("ref" + i);
            references.add(reference);
            referenceTests.put(reference, new TreeMap<ReferenceDepth, Set<ReferenceTest>>());
            EasyMock.expect(mockedReferenceDataService.getByCode("ref" + i)).andReturn(reference).anyTimes();
            
            for (int j = 0; j < 2; j++) {
                ReferenceDepth referenceDepth = new ReferenceDepthImpl();
                referenceDepth.setCode("ref" + i + "-depth" + j);
                referenceDepth.setDepth(j);
                referenceDepths.add(referenceDepth);
                referenceTests.get(reference).put(referenceDepth, new TreeSet<ReferenceTest>());
                
                for (int k = 0 ; k < 3 ; k++) {
                    ReferenceTest referenceTest = new ReferenceTestImpl();
                    referenceTest.setCode("ref" + i + "-depth" + j + "-test" + k);
                    referenceTest.setLabel("ref" + i + "-depth" + j + "-test" + k + "-label");
                    referenceTest.setReferenceDepth(referenceDepth);
                    referenceTestCollection.add(referenceTest);

                    referenceTests.get(reference).get(referenceDepth).add(referenceTest);
                }
        
            }
        }
        
        EasyMock.expect(mockedReferenceTestDAO.findAll()).andReturn(referenceTestCollection).once();
        EasyMock.replay(mockedReferenceTestDAO);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        EasyMock.verify(mockedReferenceTestDAO);
        EasyMock.verify(mockedReferenceDataService);
    }

    /**
     * Test of findAll method, of class ReferenceTestDataServiceImpl
     */
    public void testFindAll() {
        System.out.println("findAll()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceTestDataServiceImpl instance = new ReferenceTestDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceTestDAO);
        
        /* */
        List<ReferenceTest> expResult = new ArrayList<ReferenceTest>();
        
        for (Map<ReferenceDepth, Set<ReferenceTest>> map : referenceTests.values()) {
            for (Set<ReferenceTest> referenceTestSet : map.values()) {
                    expResult.addAll(referenceTestSet);
            }
        }
        
        List<ReferenceTest> result = new ArrayList<ReferenceTest>(instance.findAll());
        
        assertEquals(expResult, result);
        
        /* */
        System.out.println("findAll() : reset of DAO");
        instance.setEntityDao(mockedReferenceTestDAO);
        result = new ArrayList<ReferenceTest>(instance.findAll());
        
        assertEquals(expResult, result);
    }
    
    /**
     * test of getByLabelAndReferenceCode method, of class ReferenceTestDataServiceImpl
     */
    public void testByLabelAndReferenceCode() {
        System.out.println("getLabelAndReferenceCode()");
        /* Mock expectations */
        EasyMock.expect(mockedReferenceDataService.getByCode(null)).andReturn(null).once();
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceTestDataServiceImpl instance = new ReferenceTestDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceTestDAO);
        
        /* */
        ReferenceTest expResult = new ReferenceTestImpl();
        expResult.setCode("ref0-depth0-test1");
        expResult.setLabel("ref0-depth0-test1-label");
        
        ReferenceTest result = instance.getByLabelAndReferenceCode("ref0-depth0-test1-label", "ref0");
        
        assertEquals(expResult, result);
        
        /* error case */
        System.out.println("getLabelAndReferenceCode() : reference null");
        result = instance.getByLabelAndReferenceCode(expResult.getLabel(), null);
        
        assertNull(result);
    }
    
    /**
     * test of getReferenceTestWithAllChildren mthod, of class ReferenceTestDataServiceImpl
     */
    public void testGetReferenceTestWithAllChildren() {
        System.out.println("getReferenceTestWithAllChildren()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        
        /* */
        ReferenceTestDataServiceImpl instance = new ReferenceTestDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setEntityDao(mockedReferenceTestDAO);
        
        /* Init result & level stub */
        Result resultStub = new ResultImpl();
        resultStub.setCode("failed");
        ReferenceLevel levelStub = new ReferenceLevelImpl();        
        levelStub.setCode("WCAG20-A");
        
        /* Init test stubs */
        ReferenceTest testParent = new ReferenceTestImpl();
        testParent.setCode("WCAG20-010101");
        testParent.setReferenceLevel(levelStub);
        ReferenceTest testChild1 = new ReferenceTestImpl();
        testChild1.setCode("WCAG20-GL01");
        ReferenceTest testChild2 = new ReferenceTestImpl();
        testChild2.setCode("WCAG20-GL02");
        
        Set<ReferenceTest> testParentSet = new HashSet<ReferenceTest>();
        testParentSet.add(testParent);
        Set<ReferenceTest> testChildSet = new HashSet<ReferenceTest>();
        testChildSet.add(testChild1);
        testChildSet.add(testChild2);
        
        testParent.setChildren(testChildSet);
        testChild1.setParents(testParentSet);
        testChild2.setParents(testParentSet);
        
        /* Test */
        List<ReferenceTest> expResult = new ArrayList<ReferenceTest>();
        expResult.add(testParent);
        expResult.add(testChild1);
        expResult.add(testChild2);
        
        List<ReferenceTest> result = new ArrayList<ReferenceTest>(
                    instance.getReferenceTestWithAllChildren(testParent, levelStub, resultStub)
                );
        
        assertEquals(expResult.size(), result.size());
        assertTrue(expResult.containsAll(result));
        
        /* */
        System.out.println("getReferenceTestWithAllChildren() : different referenceLevel");
        /* init stubs */
        ReferenceLevel differentLevelStub = new ReferenceLevelImpl();
        differentLevelStub.setCode("WCAG20-AA");
        
        result = new ArrayList<ReferenceTest>(
                    instance.getReferenceTestWithAllChildren(testParent, differentLevelStub, resultStub)
                );
        
        assertTrue(result.isEmpty());
    }
}
