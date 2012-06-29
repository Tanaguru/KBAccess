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
import java.util.Collection;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceImpl;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.kbaccess.entity.reference.ThemeImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class ThemeDAOImplTest extends AbstractDaoTestCase {
    
    public ThemeDAOImplTest(String testName) {
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

    private ThemeDAO getBean() {
        return (ThemeDAO) springBeanFactory.getBean("themeDAO");
    }
    
    /**
     * Test of getEntityClass method, of class ThemeDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        ThemeDAOImpl instance = new ThemeDAOImpl();
        Class expResult = ThemeImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class ThemeDAOImpl.
     */
    public void testFindAll() {
        System.out.println("findAll : [nuc]");
        /* */
        Reference reference = new ReferenceImpl();
        
        reference.setId(1L);
        /* */
        ThemeDAO instance = getBean();
        /* */
        Collection<Theme> result = instance.findAll(reference);
        /* */
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
        // TODO error code
    }

    /**
     * Test of findByCode method, of class ThemeDAOImpl.
     */
    public void testFindByCode() {
        System.out.println("findByCode : [nuc]");
        /* */
        String code = "th1";
        /* */
        ThemeDAO instance = getBean();
        /* */
        Theme result = instance.findByCode(code);
        /* */
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        // TODO error case
    }
}
