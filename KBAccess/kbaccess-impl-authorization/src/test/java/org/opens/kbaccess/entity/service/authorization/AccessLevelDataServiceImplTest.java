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
package org.opens.kbaccess.entity.service.authorization;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.kbaccess.entity.dao.authorization.AccessLevelDAO;

/**
 *
 * @author bcareil
 */
public class AccessLevelDataServiceImplTest extends TestCase {
    
    private AccessLevelDAO mockedAccessLevelDAO;
    
    public AccessLevelDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedAccessLevelDAO = createMock(AccessLevelDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getByCode method, of class AccessLevelDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        String code = "code";
        AccessLevelDataServiceImpl instance = new AccessLevelDataServiceImpl();
        AccessLevel expResult = createMock(AccessLevel.class);

        // set-up instance
        instance.setEntityDao(mockedAccessLevelDAO);
        // set-up mock
        expect(mockedAccessLevelDAO.findByCode(code)).andReturn(expResult);
        // replay mocks
        replay(mockedAccessLevelDAO);
        // run test
        AccessLevel result = instance.getByCode(code);
        // check result
        assertEquals(expResult, result);
        verify(mockedAccessLevelDAO);
    }
}
