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
package org.opens.kbaccess.entity.subject;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class TestcaseImplTest extends TestCase {
    
    public TestcaseImplTest(String testName) {
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
     * Test of setDescription method, of class TestcaseImpl.
     */
    public void testSetDescription() {
        System.out.println("setDescription");
        /* */
        String description = "desc12345&é";
        String expectedDescription = "desc12345&é";
        /* */
        TestcaseImpl instance = new TestcaseImpl();
        /* run test */
        instance.setDescription(description);
        /* check result */
        assertEquals(expectedDescription, instance.getDescription());
    }
}
