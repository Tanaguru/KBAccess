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
package org.opens.kbaccess.entity.authorization;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class AccountImplTest extends TestCase {
    
    public AccountImplTest(String testName) {
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
     * Test of setFirstName method, of class AccountImpl.
     */
    public void testSetFirstName() {
        System.out.println("setFirstName");
        /* */
        String firstName = "&<>";
        String expectedFirstName = "&amp;&lt;&gt;";
        /* */
        AccountImpl instance = new AccountImpl();
        /* run test */
        instance.setFirstName(firstName);
        /* check result */
        assertEquals(expectedFirstName, instance.getFirstName());
    }

    /**
     * Test of setLastName method, of class AccountImpl.
     */
    public void testSetLastName() {
        System.out.println("setLastName");
        /* */
        String lastName = "&<>";
        String expectedLastName = "&amp;&lt;&gt;";
        /* */
        AccountImpl instance = new AccountImpl();
        /* run test */
        instance.setLastName(lastName);
        /* check result */
        assertEquals(expectedLastName, instance.getLastName());
    }

    /**
     * Test of setPassword method, of class AccountImpl.
     * 
     * I'm not sure this is really usefull since the password is
     * hashed and it is not expected to display it, but well.
     */
    public void testSetPassword() {
        System.out.println("setPassword");
        /* */
        String password = "&<>";
        String expectedPassword = "&amp;&lt;&gt;";
        /* */
        AccountImpl instance = new AccountImpl();
        /* run test */
        instance.setPassword(password);
        /* check result */
        assertEquals(expectedPassword, instance.getPassword());
    }

}
