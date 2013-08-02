/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

/**
 *
 * @author blebail
 */
public interface RefComponent extends RefBase {
    
    public boolean isComponentOfReference(Reference reference);
}
