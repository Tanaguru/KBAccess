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
package org.opens.kbaccess.entity.service.reference;

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.NomenclatureDAO;
import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.kbaccess.entity.reference.NomenclatureElement;

/**
 *
 * @author bcareil
 */
public class NomenclatureDataServiceImplTest extends TestCase {
    
    private NomenclatureDAO mockedNomenclatureDAO;
    
    public NomenclatureDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedNomenclatureDAO = createMock(NomenclatureDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getByCode method, of class NomenclatureDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Nomenclature expResult = createMock(Nomenclature.class);
        /* set-up instance */
        NomenclatureDataServiceImpl instance = new NomenclatureDataServiceImpl();
        instance.setEntityDao(mockedNomenclatureDAO);
        /* set-up mock */
        expect(mockedNomenclatureDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedNomenclatureDAO);
        replay(expResult);
        /* run test */
        Nomenclature result = instance.getByCode(code);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedNomenclatureDAO);
        verify(expResult);
    }

    /**
     * Test of read method, of class NomenclatureDataServiceImpl.
     */
    public void testRead() {
        System.out.println("read");
        /* */
        Long key = 1L;
        Nomenclature expResult = createMock(Nomenclature.class);
        Collection<NomenclatureElement> collection = new ArrayList<NomenclatureElement>();
        /* set-up instance */
        NomenclatureDataServiceImpl instance = new NomenclatureDataServiceImpl();
        instance.setEntityDao(mockedNomenclatureDAO);
        /* set-up mock */
        expect(mockedNomenclatureDAO.read(key)).andReturn(expResult);
        expect(expResult.getElementList()).andReturn(collection);
        /* replay mock */
        replay(mockedNomenclatureDAO);
        replay(expResult);
        /* run test */
        Nomenclature result = instance.read(key);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedNomenclatureDAO);
        verify(expResult);
    }
}
