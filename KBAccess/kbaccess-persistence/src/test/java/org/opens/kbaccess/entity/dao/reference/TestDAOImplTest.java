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
package org.opens.kbaccess.entity.dao.reference;

import java.util.Arrays;
import java.util.List;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class TestDAOImplTest extends AbstractDaoTestCase {
    
    public TestDAOImplTest(String testName) {
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

    private TestDAO getBean() {
        return (TestDAO) springBeanFactory.getBean("testDAO");
    }
    
    /**
     * Test of getEntityClass method, of class TestDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        TestDAOImpl instance = new TestDAOImpl();
        Class expResult = TestImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class TestDAOImpl.
     */
    public void testFindAll_Reference() {
        System.out.println("findAll(Reference) : [nuc]");
        /* */
        Reference reference = new ReferenceImpl();
        
        reference.setId(1L);
        /* */
        TestDAO instance = getBean();
        /* */
        List<Test> result = instance.findAll(reference);
        /* */
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findAll method, of class TestDAOImpl.
     * 
     * The implementation does not work, it may be deprecated
     * 
     * FIXME s deprecated ?
     */
    /*
    public void testFindAll_Criterion() {
        System.out.println("findAll(Criterion) : [nuc]");
        //
        Criterion criterion = new CriterionImpl();
        
        criterion.setId(1L);
        //
        TestDAO instance = getBean();
        //
        List result = instance.findAll(criterion);
        //
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error case
    }
    */

    /**
     * Test of findAll method, of class TestDAOImpl.
     */
    public void testFindAll_String_Criterion() {
        System.out.println("findAll(String code, Criterion) : [nuc]");
        /* */
        String code = "test010101";
        Criterion criterion = new CriterionImpl();
        
        criterion.setId(1L);
        /* */
        TestDAO instance = getBean();
        /* */
        List result = instance.findAll(code, criterion);
        /* */
        assertNotNull(result);
        assertEquals(1, result.size());
        assertFalse(Arrays.asList(1L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findAllByCode method, of class TestDAOImpl.
     */
    public void testFindAllByCode() {
        System.out.println("findAllByCode : [nuc]");
        /* */
        String[] codeArray = new String[]{"test010101", "test010102"};
        /* */
        TestDAO instance = getBean();
        /* */
        List result = instance.findAllByCode(codeArray);
        /* */
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error code
    }

    /**
     * Test of findByCode method, of class TestDAOImpl.
     */
    public void testFindByCode() {
        System.out.println("findByCode : [nuc]");
        /* */
        String code = "test010102";
        /* */
        TestDAO instance = getBean();
        /* */
        Test result = instance.findByCode(code);
        /* */
        assertNotNull(result);
        assertEquals(Long.valueOf(2L), result.getId());
        // TODO error code
    }

    /**
     * Test of findByCodeAndReference method, of class TestDAOImpl.
     */
    public void testFindByCodeAndReference() {
        System.out.println("findByCodeAndReference : [nuc]");
        /* */
        String code = "test010101";
        Reference ref = new ReferenceImpl();
        
        ref.setId(1L);
        /* */
        TestDAO instance = getBean();
        /* */
        Test result = instance.findByCodeAndReference(code, ref);
        /* */
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        // TODO error code
    }
}
