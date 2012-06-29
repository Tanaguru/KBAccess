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
import org.opens.kbaccess.entity.reference.NomenclatureElement;

/**
 *
 * @author bcareil
 */
public class NomenclatureElementFactoryImplTest extends TestCase {
    
    public NomenclatureElementFactoryImplTest(String testName) {
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
     * Test of create method, of class NomenclatureElementFactoryImpl.
     */
    public void testCreate_String_String() {
        System.out.println("create");
        //
        String code = "code";
        String label = "label";
        // create instance
        NomenclatureElementFactoryImpl instance = new NomenclatureElementFactoryImpl();
        // run test
        NomenclatureElement result = instance.create(code, label);
        // check result
        assertEquals(code, result.getCode());
        assertEquals(label, result.getLabel());
    }
}
