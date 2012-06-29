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
package org.opens.kbaccess.entity.dao.authorization;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class AccountDAOImplTest extends AbstractDaoTestCase {
    
    public AccountDAOImplTest(String testName) {
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
    
    private AccountDAO getBean() {
        return (AccountDAO) springBeanFactory.getBean("accountDAO");
    }

    /**
     * Test of getEntityClass method, of class AccountDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        AccountDAOImpl instance = new AccountDAOImpl();
        Class expResult = AccountImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByEmail method, of class AccountDAOImpl.
     */
    public void testFindByEmail() {
        /* nominal use case */
        System.out.println("findByEmail : [nuc]");
        /* */
        String email = "account2@domain.com";
        /* set-up instance */
        AccountDAO instance = getBean();
        /* run test */
        Account result = instance.findByEmail(email);
        /* check result */
        assertNotNull(result);
        assertEquals(Long.valueOf(2L), result.getId());
        
        
        /* error uc 1 : invalid email */
        System.out.println("findByEmail : [euc] invalid email");
        /* */
        email = "lol";
        /* run test */
        result = instance.findByEmail(email);
        /* check result */
        assertNull(result);
    }
}
