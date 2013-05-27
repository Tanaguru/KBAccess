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

import org.opens.kbaccess.entity.reference.NomenclatureElementImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class NomenclatureElementDAOImplTest extends AbstractDaoTestCase {
    
    public NomenclatureElementDAOImplTest(String testName) {
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

    private NomenclatureElementDAO getBean() {
        return (NomenclatureElementDAO) springBeanFactory.getBean("nomenclatureElementDAO");
    }
    
    /**
     * Test of getEntityClass method, of class NomenclatureElementDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        NomenclatureElementDAOImpl instance = new NomenclatureElementDAOImpl();
        Class expResult = NomenclatureElementImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class NomenclatureElementDAOImpl.
     * 
     * Seems to be unused since the SQL request in the DAO implementation is
     * invalid.
     */
    /*
    public void testFindAll() {
        System.out.println("findAll");
        //
        Nomenclature nomenclature = null;
        String nomenclatureValue = "";
        //
        NomenclatureElementDAO instance = getBean();
        //
        Collection result = instance.findAll(nomenclature, nomenclatureValue);
        //
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(1L), asIdList(result));
        // TODO error case
    }
    */
}
