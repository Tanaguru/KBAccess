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
package org.opens.kbaccess.entity.factory.reference;

import junit.framework.TestCase;
import org.opens.kbaccess.entity.reference.Nomenclature;

/**
 *
 * @author bcareil
 */
public class NomenclatureFactoryImplTest extends TestCase {
    
    public NomenclatureFactoryImplTest(String testName) {
        super(testName);
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
     * Test of create method, of class NomenclatureFactoryImpl.
     */
    public void testCreate_String() {
        System.out.println("create");
        //
        String code = "";
        // create instance
        NomenclatureFactoryImpl instance = new NomenclatureFactoryImpl();
        // run test
        Nomenclature result = instance.create(code);
        // check result
        assertEquals(code, result.getCode());
    }
}
