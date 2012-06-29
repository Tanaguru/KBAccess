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

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.reference.ResultDAO;
import org.opens.kbaccess.entity.reference.Result;

/**
 *
 * @author bcareil
 */
public class ResultDataServiceImplTest extends TestCase {
    
    private ResultDAO mockedResultDAO;
    
    public ResultDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedResultDAO = createMock(ResultDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getByCode method, of class ResultDataServiceImpl.
     */
    public void testGetByCode() {
        System.out.println("getByCode");
        /* */
        String code = "code";
        Result expResult = createMock(Result.class);
        /* set-up instance */
        ResultDataServiceImpl instance = new ResultDataServiceImpl();
        instance.setEntityDao(mockedResultDAO);
        /* set-up mock */
        expect(mockedResultDAO.findByCode(code)).andReturn(expResult);
        /* replay mock */
        replay(mockedResultDAO);
        replay(expResult);
        /* run test */
        Result result = instance.getByCode(code);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedResultDAO);
        verify(expResult);
    }
}
