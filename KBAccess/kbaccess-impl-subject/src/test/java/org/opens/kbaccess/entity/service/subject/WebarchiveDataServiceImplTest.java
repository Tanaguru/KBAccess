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
package org.opens.kbaccess.entity.service.subject;

import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.subject.WebarchiveDAO;
import org.opens.kbaccess.entity.factory.subject.WebarchiveFactory;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 *
 * @author bcareil
 */
public class WebarchiveDataServiceImplTest extends TestCase {
    
    WebarchiveDAO mockedWebarchiveDAO;
    WebarchiveFactory mockedWebarchiveFactory;
    
    public WebarchiveDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedWebarchiveDAO = createMock(WebarchiveDAO.class);
        mockedWebarchiveFactory = createMock(WebarchiveFactory.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private WebarchiveDataService newInstance() {
        WebarchiveDataServiceImpl ofthejedi = new WebarchiveDataServiceImpl();
        
        ofthejedi.setEntityDao(mockedWebarchiveDAO);
        ofthejedi.setEntityFactory(mockedWebarchiveFactory);
        return ofthejedi;
    }

    /**
     * Test of findAll method, of class WebarchiveDataServiceImpl.
     */
    public void testFindAll() {
        System.out.println("findAll");
        /* */
        List expResult = createMock(List.class);
        /* set-up instance */
        WebarchiveDataService instance = newInstance();
        /* set-up mock */
        expect(mockedWebarchiveDAO.findAll()).andReturn(expResult);
        /* replay mock */
        replay(mockedWebarchiveDAO, mockedWebarchiveFactory);
        replay(expResult);
        /* run test */
        Collection result = instance.findAll();
        /* check result */
        assertEquals(expResult, result);
        verify(mockedWebarchiveDAO, mockedWebarchiveFactory);
        verify(expResult);
    }

    /**
     * Test of getAllFromUserAccount method, of class WebarchiveDataServiceImpl.
     */
    public void testGetAllFromUserAccount() {
        System.out.println("getAllFromUserAccount");
        /* */
        Account account = createMock(Account.class);
        List expResult = createMock(List.class);
        /* set-up instance */
        WebarchiveDataService instance = newInstance();
        /* set-up mock */
        expect(mockedWebarchiveDAO.findAllFromAccount(account)).andReturn(expResult);
        /* replay mock */
        replay(mockedWebarchiveDAO, mockedWebarchiveFactory);
        replay(account);
        replay(expResult);
        /* run test */
        Collection result = instance.getAllFromUserAccount(account);
        /* check result */
        assertEquals(expResult, result);
        verify(mockedWebarchiveDAO, mockedWebarchiveFactory);
        verify(account);
        verify(expResult);
    }

    /**
     * Test of createWebarchive method, of class WebarchiveDataServiceImpl.
     */
    public void testCreateWebarchive() {
        System.out.println("createWebarchive");
        /* */
        Webarchive expResult = createMock(Webarchive.class);
        /* set-up instance */
        WebarchiveDataService instance = newInstance();
        /* set-up mock */
        expect(mockedWebarchiveFactory.create()).andReturn(expResult);
        /* replay mock */
        replay(mockedWebarchiveDAO, mockedWebarchiveFactory);
        replay(expResult);
        /* run test */
        Webarchive result = instance.createWebarchive();
        /* check result */
        assertEquals(expResult, result);
        verify(mockedWebarchiveDAO, mockedWebarchiveFactory);
        verify(expResult);
    }

    /**
     * Test of deleteWebArchive method, of class WebarchiveDataServiceImpl.
     * 
     * Not implemented, no tests needed yet.
     */
    /*
    public void testDeleteWebArchive() {
        System.out.println("deleteWebArchive");
        Webarchive webarchive = null;
        WebarchiveDataServiceImpl instance = new WebarchiveDataServiceImpl();
        int expResult = 0;
        int result = instance.deleteWebArchive(webarchive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
