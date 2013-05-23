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
package org.opens.kbaccess.entity.dao.reference;

import java.util.Arrays;
import java.util.Collection;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class CriterionDAOImplTest extends AbstractDaoTestCase {
    
    public CriterionDAOImplTest(String testName) {
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
    
    private CriterionDAO getBean() {
        return (CriterionDAO) springBeanFactory.getBean("criterionDAO");
    }

    /**
     * Test of getEntityClass method, of class CriterionDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        CriterionDAOImpl instance = new CriterionDAOImpl();
        Class expResult = CriterionImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class CriterionDAOImpl.
     */
    public void testFindAll() {
        System.out.println("findAll : [nuc]");
        /* */
        CriterionDAO instance = getBean();
        Reference reference = new ReferenceImpl();
        Theme theme = new ThemeImpl();
        
        reference.setId(1L);
        theme.setId(1L);
        /* */;
        Collection<Criterion> result = instance.findAll(reference, theme);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Long.valueOf(1L), ((Criterion) (result.toArray()[0])).getId());
        // TODO error case
    }

    /**
     * Test of findAllFromReference method, of class CriterionDAOImpl.
     */
    public void testFindAllFromReference() {
        System.out.println("findAllFromReference : [nuc]");
        /* */
        Reference reference = new ReferenceImpl();
        
        reference.setId(1L);
        /* */
        CriterionDAO instance = getBean();
        /* */
        Collection<Criterion> result = instance.findAllFromReference(reference);
        /* */
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findAllFromTheme method, of class CriterionDAOImpl.
     */
    public void testFindAllFromTheme() {
        System.out.println("findAllFromTheme : [nuc]");
        /* */
        Theme theme = new ThemeImpl();
        
        theme.setId(1L);
        /* */
        CriterionDAO instance = getBean();
        /* */
        Collection<Criterion> result = instance.findAllFromTheme(theme);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertFalse(Arrays.asList(1L).retainAll(asIdList(result)));
        // TODO error case
    }

    /**
     * Test of findByCode method, of class CriterionDAOImpl.
     */
    public void testFindByCode() {
        System.out.println("findByCode : [nuc]");
        /* */
        String code = "AW2.1-0101";
        /* */
        CriterionDAO instance = getBean();
        /* */
        Criterion result = instance.findByCode(code);
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        // TODO error case
    }
}
