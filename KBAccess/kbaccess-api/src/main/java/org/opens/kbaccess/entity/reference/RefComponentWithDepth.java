/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

/**
 *
 * @author blebail
 */
public interface RefComponentWithDepth extends RefComponent {
    
    public ReferenceDepth getReferenceDepth();
    
    public void setReferenceDepth(ReferenceDepth referenceDepth);
}
