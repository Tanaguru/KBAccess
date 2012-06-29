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
import org.opens.kbaccess.entity.reference.Level;
import org.opens.kbaccess.entity.reference.LevelImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class LevelDAOImplTest extends AbstractDaoTestCase {
    
    public LevelDAOImplTest(String testName) {
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

    private LevelDAO getBean() {
        return (LevelDAO) springBeanFactory.getBean("levelDAO");
    }
    
    /**
     * Test of getEntityClass method, of class LevelDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        LevelDAOImpl instance = new LevelDAOImpl();
        Class expResult = LevelImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByCode method, of class LevelDAOImpl.
     */
    public void testFindByCode() {
        System.out.println("findByCode : [nuc]");
        /* */
        String code = "aw-or";
        /* */
        LevelDAO instance = getBean();
        /* */
        Level result = instance.findByCode(code);
        /* */
        assertNotNull(result);
        assertEquals(Long.valueOf(3L), result.getId());
        // TODO error case
    }
}
