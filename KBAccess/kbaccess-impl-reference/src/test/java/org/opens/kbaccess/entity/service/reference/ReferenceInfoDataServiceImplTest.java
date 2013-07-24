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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.kbaccess.entity.dao.reference.ReferenceInfoDAO;
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
public class ReferenceInfoDataServiceImplTest extends TestCase {
    
    private ReferenceInfoDAO mockedReferenceInfoDAO;
    private ReferenceDataService mockedReferenceDataService;
    private ReferenceTestDataService mockedReferenceTestDataService;
    private List<Reference> references = new ArrayList<Reference>();
    private List<ReferenceDepth> referenceDepths = new ArrayList<ReferenceDepth>();
    
    private Map<Reference, Map<ReferenceDepth, Set<ReferenceInfo>>> referenceInfos = 
            new TreeMap<Reference, Map<ReferenceDepth, Set<ReferenceInfo>>>();
    
    public ReferenceInfoDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         
        mockedReferenceInfoDAO = EasyMock.createMock(ReferenceInfoDAO.class); 
        Collection<ReferenceInfo> referenceInfoCollection = new ArrayList<ReferenceInfo>();
        
        mockedReferenceDataService = EasyMock.createMock(ReferenceDataService.class);
        mockedReferenceTestDataService = EasyMock.createMock(ReferenceTestDataService.class);
        
        for (int i = 0; i < 2; i++) {
            Reference reference = new ReferenceImpl();
            reference.setCode("ref" + i);
            references.add(reference);
            referenceInfos.put(reference, new TreeMap<ReferenceDepth, Set<ReferenceInfo>>());
            EasyMock.expect(mockedReferenceDataService.getByCode("ref" + i)).andReturn(reference).anyTimes();
            
            for (int j = 0; j < 2; j++) {
                ReferenceDepth referenceDepth = new ReferenceDepthImpl();
                referenceDepth.setCode("ref" + i + "-depth" + j);
                referenceDepth.setDepth(j);
                referenceDepths.add(referenceDepth);
                referenceInfos.get(reference).put(referenceDepth, new TreeSet<ReferenceInfo>());
                
                for (int k = 0 ; k < 3 ; k++) {
                    ReferenceInfo referenceInfo = new ReferenceInfoImpl();
                    referenceInfo.setCode("ref" + i + "-depth-"+ j + "-info" + k);
                    referenceInfo.setReferenceDepth(referenceDepth);
                    referenceInfoCollection.add(referenceInfo);

                    referenceInfos.get(reference).get(referenceDepth).add(referenceInfo);
                }
        
            }
        }
        
        EasyMock.expect(mockedReferenceInfoDAO.findAll()).andReturn(referenceInfoCollection).once();
        EasyMock.replay(mockedReferenceInfoDAO);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        EasyMock.verify(mockedReferenceInfoDAO);
        EasyMock.verify(mockedReferenceDataService);
        EasyMock.verify(mockedReferenceTestDataService);
    }

    /**
     * Test of findAll method, of class ReferenceInfoDataServiceImpl
     */
    public void testFindAll() {
        System.out.println("findAll()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        EasyMock.replay(mockedReferenceTestDataService);
        
        /* */
        ReferenceInfoDataServiceImpl instance = new ReferenceInfoDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setReferenceTestDataService(mockedReferenceTestDataService);
        instance.setEntityDao(mockedReferenceInfoDAO);
        
        /* */
        List<ReferenceInfo> expResult = new ArrayList<ReferenceInfo>();
        
        for (Map<ReferenceDepth, Set<ReferenceInfo>> map : referenceInfos.values()) {
            for (Set<ReferenceInfo> referenceInfoSet : map.values()) {
                    expResult.addAll(referenceInfoSet);
            }
        }
        
        List<ReferenceInfo> result = new ArrayList<ReferenceInfo>(instance.findAll());
        
        assertEquals(expResult, result);
        
        /* */
        System.out.println("findAll() : reset of DAO");
        instance.setEntityDao(mockedReferenceInfoDAO);
        result = new ArrayList<ReferenceInfo>(instance.findAll());
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllByReference method, of class ReferenceInfoDataServiceImpl.
     */
    public void testGetAllByReferenceAndReferenceDepth() {
        System.out.println("getAllByReferenceAndReferenceDepth");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        EasyMock.replay(mockedReferenceTestDataService);
        
        /* */
        ReferenceInfoDataServiceImpl instance = new ReferenceInfoDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setReferenceTestDataService(mockedReferenceTestDataService);
        instance.setEntityDao(mockedReferenceInfoDAO);
        
        /* */
        List<ReferenceInfo> expResult = new ArrayList<ReferenceInfo>(
                    referenceInfos.get(references.get(0)).get(referenceDepths.get(0))
                );
        
        List<ReferenceInfo> result = new ArrayList<ReferenceInfo>(
                    instance.getAllByReferenceAndReferenceDepth(references.get(0), referenceDepths.get(0))
                );
        
        assertEquals(expResult, result);
        
        System.out.println("getAllByReferenceAndReferenceDepth : reference null");
        result = new ArrayList<ReferenceInfo>(
                    instance.getAllByReferenceAndReferenceDepth(null, referenceDepths.get(0))
                );
        
        assertTrue(result.isEmpty());
        
        System.out.println("getAllByReferenceAndReferenceDepth : referenceDepth null");
        result = new ArrayList<ReferenceInfo>(
                    instance.getAllByReferenceAndReferenceDepth(references.get(0), null)
                );
        
        assertTrue(result.isEmpty());
    }
    
    /**
     * Test of getInternMap accessor, of class ReferenceInfoDataServiceImpl.
     */
    public void testGetInternMapByDepth() {
        System.out.println("getInternMap()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        EasyMock.replay(mockedReferenceTestDataService);
        
        /* */
        ReferenceInfoDataServiceImpl instance = new ReferenceInfoDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setReferenceTestDataService(mockedReferenceTestDataService);
        instance.setEntityDao(mockedReferenceInfoDAO);
             
        Map<Reference, Map<ReferenceDepth, Set<ReferenceInfo>>> result = instance.getInternMapByDepth();
        
        assertEquals(referenceInfos, result);        
    }
    /**
     * Test of getAllReferenceTestsOfReferenceInfo, of class ReferenceInfoDataServiceImpl
     */
    public void testAllReferenceTestsOfReferenceInfo() {
        /* Init result & level stub */
        Result resultStub = new ResultImpl();
        resultStub.setCode("failed");
        ReferenceLevel levelStub = new ReferenceLevelImpl();        
        levelStub.setCode("WCAG20-A");
        
        /* Init test stubs */
        ReferenceTest testParent1 = new ReferenceTestImpl();
        testParent1.setCode("WCAG20-010101");
        testParent1.setReferenceLevel(levelStub);
        ReferenceTest testParent2 = new ReferenceTestImpl();
        testParent2.setCode("WCAG20-010201");
        testParent2.setReferenceLevel(levelStub);
        ReferenceTest testChild1 = new ReferenceTestImpl();
        testChild1.setCode("WCAG20-GL01");
        
        Set<ReferenceTest> testParentSet1 = new HashSet<ReferenceTest>();
        testParentSet1.add(testParent1);
        Set<ReferenceTest> testParentSet2 = new HashSet<ReferenceTest>();
        testParentSet2.add(testParent2);
        Set<ReferenceTest> testChildSet = new HashSet<ReferenceTest>();
        testChildSet.add(testChild1);
        
        testParent1.setChildren(testChildSet);
        testChild1.setParents(testParentSet1);
        
        testChild1.setParents(testParentSet2);
        
        /* Init info stubs */
        ReferenceInfo infoParent = new ReferenceInfoImpl();
        infoParent.setCode("WCAG20-01");
        ReferenceInfo infoChild1 = new ReferenceInfoImpl();
        infoChild1.setCode("WCAG20-0101");
        ReferenceInfo infoChild2 = new ReferenceInfoImpl();
        infoChild2.setCode("WCAG20-0102");
        
        Set<ReferenceInfo> infoChildSet = new HashSet<ReferenceInfo>();
        infoChildSet.add(infoChild1);
        infoChildSet.add(infoChild2);
        
        infoParent.setChildren(infoChildSet);
        infoChild1.setParent(infoParent);
        infoChild2.setParent(infoParent);
        
        infoChild1.setReferenceTestSet(testParentSet1);
        infoChild2.setReferenceTestSet(testParentSet2);
        
        /* Init test stub for second testcase */
        ReferenceTest test = new ReferenceTestImpl();
        test.setCode("WCAG20-020101");
        test.setReferenceLevel(levelStub);

        Set<ReferenceTest> testSet = new HashSet<ReferenceTest>();
        testSet.add(test);
        
        /* Init info stub for second testcase  */
        ReferenceInfo info = new ReferenceInfoImpl();
        info.setCode("WCAG20-0201");
        info.setReferenceTestSet(testSet);
        
        System.out.println("getAllReferenceTestsOfReferenceInfo : multiple referenceInfo with multiple referenceTest");
        /* Mock expectations */
        Set<ReferenceTest> testSet1 = new HashSet<ReferenceTest>();
        testSet1.add(test);
        EasyMock.expect(mockedReferenceTestDataService.getReferenceTestWithAllChildren(test, levelStub, resultStub)).andReturn(testSet1).anyTimes();
        Set<ReferenceTest> testSet2 = new HashSet<ReferenceTest>();
        testSet2.add(testParent1);
        testSet2.add(testChild1);
        EasyMock.expect(mockedReferenceTestDataService.getReferenceTestWithAllChildren(testParent1, levelStub, resultStub)).andReturn(testSet2).anyTimes();
        Set<ReferenceTest> testSet3 = new HashSet<ReferenceTest>();
        testSet3.add(testParent2);
        EasyMock.expect(mockedReferenceTestDataService.getReferenceTestWithAllChildren(testParent2, levelStub, resultStub)).andReturn(testSet3).anyTimes();
        Set<ReferenceTest> testSet4 = new HashSet<ReferenceTest>();
        testSet4.add(testChild1);
        EasyMock.expect(mockedReferenceTestDataService.getReferenceTestWithAllChildren(testChild1, levelStub, resultStub)).andReturn(testSet4).anyTimes();
        
        EasyMock.replay(mockedReferenceDataService);
        EasyMock.replay(mockedReferenceTestDataService);
        
        /* */
        ReferenceInfoDataServiceImpl instance = new ReferenceInfoDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setReferenceTestDataService(mockedReferenceTestDataService);
        instance.setEntityDao(mockedReferenceInfoDAO);
        
        /* Test */
        List<ReferenceTest> expResult = new ArrayList<ReferenceTest>();
        expResult.add(testParent1);
        expResult.add(testParent2);
        expResult.add(testChild1);
        
        List<ReferenceTest> result = new ArrayList<ReferenceTest>(
                    instance.getAllReferenceTestsOfReferenceInfo(infoParent, levelStub, resultStub)
                );
        
        assertEquals(expResult.size(), result.size());
        assertTrue(expResult.containsAll(result));
        
        System.out.println("getAllReferenceTestsOfReferenceInfo : one referenceInfo with one referenceTest");
        /* Test */
        expResult = new ArrayList<ReferenceTest>();
        expResult.add(test);
        
        result = new ArrayList<ReferenceTest>(
                    instance.getAllReferenceTestsOfReferenceInfo(info, levelStub, resultStub)
                );
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getReferenceTestDataService accessor, of ReferenceInfoDataServiceImpl
     */
    public void testGetReferenceTestDataService() {
        System.out.println("getReferenceTestDataService()");
        /* */
        EasyMock.replay(mockedReferenceDataService);
        EasyMock.replay(mockedReferenceTestDataService);
            
        /* */
        ReferenceInfoDataServiceImpl instance = new ReferenceInfoDataServiceImpl();
        instance.setReferenceDataService(mockedReferenceDataService);
        instance.setReferenceTestDataService(mockedReferenceTestDataService);
        instance.setEntityDao(mockedReferenceInfoDAO);
             
        ReferenceTestDataService result = instance.getReferenceTestDataService();
        
        assertNotNull(result);      
    }
}
