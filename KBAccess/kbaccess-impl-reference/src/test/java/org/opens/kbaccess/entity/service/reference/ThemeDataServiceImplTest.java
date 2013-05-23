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
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.ThemeDAO;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;

/**
 *
 * @author bcareil
 */
public class ThemeDataServiceImplTest extends TestCase {
    
    private ThemeDAO mockedThemeDAO;
    
    public ThemeDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedThemeDAO = createMock(ThemeDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of read method, of class ThemeDataServiceImpl.
     */
    public void testRead() {
        System.out.println("read");
        /* */
        Long key = 1L;
        Theme expResult = createMock(Theme.class);
        List<Criterion> list = new ArrayList<Criterion>();
        /* set-up instance */
        ThemeDataServiceImpl instance = new ThemeDataServiceImpl();
        instance.setEntityDao(mockedThemeDAO);
        /* set-up mock */
        expect(mockedThemeDAO.read(key)).andReturn(expResult);
        expect(expResult.getCriterionList()).andReturn(list);
        /* replay mock */
        replay(mockedThemeDAO);
        replay(expResult);
        /* run test */
        Theme result = instance.read(key);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedThemeDAO);
        verify(expResult);
    }

    /**
     * Test of getByCode method, of class ThemeDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Theme expResult = createMock(Theme.class);
        /* set-up instance */
        ThemeDataServiceImpl instance = new ThemeDataServiceImpl();
        instance.setEntityDao(mockedThemeDAO);
        /* set-up mock */
        expect(mockedThemeDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedThemeDAO);
        replay(expResult);
        /* run test */
        Theme result = instance.getByCode(code);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedThemeDAO);
        verify(expResult);
    }

    /**
     * Test of getThemeListFromReference method, of class ThemeDataServiceImpl.
     */
    public void testGetThemeListFromReference() {
        System.out.println("getThemeListFromReference");
        /* */
        Reference ref = createMock(Reference.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        ThemeDataServiceImpl instance = new ThemeDataServiceImpl();
        instance.setEntityDao(mockedThemeDAO);
        /* set-up mock */
        expect(mockedThemeDAO.findAll(ref)).andReturn(expResult);
        /* replay mock */
        replay(mockedThemeDAO);
        replay(expResult);
        /* run test */
        Collection result = instance.getThemeListFromReference(ref);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedThemeDAO);
        verify(expResult);
    }
}
