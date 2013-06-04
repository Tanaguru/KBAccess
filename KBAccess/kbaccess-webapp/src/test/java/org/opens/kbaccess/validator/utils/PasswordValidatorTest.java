/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.validator.utils;

import junit.framework.TestCase;

/**
 *
 * @author blebail
 */
public class PasswordValidatorTest extends TestCase {
    
    public PasswordValidatorTest(String testName) {
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
     * Test of validate method, of class PasswordValidator.
     */
    public void testValidate() {
        System.out.println("validate");
        String password = "BOUboub0";
//        boolean expResult = false;
        boolean result = PasswordValidator.validate(password);
        assertEquals(true, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
