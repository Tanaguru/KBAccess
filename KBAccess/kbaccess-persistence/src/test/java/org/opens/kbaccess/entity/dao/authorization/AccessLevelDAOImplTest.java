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
package org.opens.kbaccess.entity.dao.authorization;

import org.opens.kbaccess.entity.authorization.AccessLevelImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class AccessLevelDAOImplTest extends AbstractDaoTestCase {
    
    public AccessLevelDAOImplTest(String testName) {
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

    /**
     * Test of getEntityClass method, of class AccessLevelDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        AccessLevelDAOImpl instance = new AccessLevelDAOImpl();
        Class expResult = AccessLevelImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }
}
