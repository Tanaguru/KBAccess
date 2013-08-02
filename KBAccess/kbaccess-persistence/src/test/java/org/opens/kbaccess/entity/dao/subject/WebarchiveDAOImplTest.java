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
package org.opens.kbaccess.entity.dao.subject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.entity.subject.WebarchiveImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class WebarchiveDAOImplTest extends AbstractDaoTestCase {
    
    public WebarchiveDAOImplTest(String testName) {
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

    private WebarchiveDAO getBean() {
        return (WebarchiveDAO) springBeanFactory.getBean("webarchiveDAO");
    }
    
    /**
     * Test of getEntityClass method, of class WebarchiveDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        WebarchiveDAOImpl instance = new WebarchiveDAOImpl();
        Class expResult = WebarchiveImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMaxPriorityValueFromTable method, of class WebarchiveDAOImpl.
     */
    public void testFindMaxPriorityValueFromTable() {
        System.out.println("findMaxPriorityValueFromTable");
        /* */
        WebarchiveDAO instance = getBean();
        /* */
        int result = instance.findMaxPriorityValueFromTable();
        /* */
        assertEquals(2, result);
    }

    /**
     * Test of findAllFromAccount method, of class WebarchiveDAOImpl.
     */
    public void testFindAllFromAccount() {
        System.out.println("findAllFromAccount [nuc]");
        /* */
        Account account = new AccountImpl();
        WebarchiveDAO instance = getBean();
        
        account.setId(1L);
        /* */
        List result = instance.findAllFromAccount(account);
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertFalse(Arrays.asList(1L).retainAll(asIdList(result)));
    }

    /**
     * Test of findAll method, of class WebarchiveDAOImpl.
     */
    public void testFindAll() {
        System.out.println("findAll : [nuc]");
        /* */
        WebarchiveDAO instance = getBean();
        /* */
        Collection<Webarchive> result = instance.findAll();
        /* */
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertFalse(Arrays.asList(1L, 2L).retainAll(asIdList(result)));
    }
    
    /**
     * Test of count method, of class WebarchiveDAOImpl.
     */
    public void testCount() {
        System.out.println("count : [nuc]");
        
        WebarchiveDAO instance = getBean();
        assertEquals(2L, instance.count().longValue());
    }
}
