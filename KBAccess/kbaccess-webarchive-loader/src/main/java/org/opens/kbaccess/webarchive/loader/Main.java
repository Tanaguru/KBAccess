/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.webarchive.loader;

/**
 *
 * @author blebail
 */
public class Main {
    public static void main(String args[]) {
        WebarchiveLoader wl = new WebarchiveLoader();
        wl.createWebarchive("http://www.kbaccess.org");
    }
}
