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
package org.opens.kbaccess.entity.service.authorization;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.authorization.AccountDAO;

/**
 *
 * @author bcareil
 */
public class AccountDataServiceImplTest extends TestCase {
    
    private AccountDAO mockedAccountDAO;
    
    public AccountDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockedAccountDAO = createMock(AccountDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getAccountFromEmail method, of class AccountDataServiceImpl.
     */
    public void testGetAccountFromEmail() {
        System.out.println("getAccountFromEmail");
        String email = "mail@domain.com";
        AccountDataServiceImpl instance = new AccountDataServiceImpl();
        Account expResult = createMock(Account.class);

        // set-up instance
        instance.setEntityDao(mockedAccountDAO);
        // set-up mock
        expect(mockedAccountDAO.findByEmail(email)).andReturn(expResult);
        // replay mocks
        replay(mockedAccountDAO);
        // run test
        Account result = instance.getAccountFromEmail(email);
        // check result
        assertEquals(expResult, result);
        verify(mockedAccountDAO);
    }
}
