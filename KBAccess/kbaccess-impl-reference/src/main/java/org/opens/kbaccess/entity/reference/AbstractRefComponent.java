/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

/**
 *
 * @author blebail
 */
public abstract class AbstractRefComponent implements RefComponent {

    @Override
    public boolean isComponentOfReference(Reference reference) {
        if (reference == null) {
            return false;
        }
        
        return this.getCode().startsWith(reference.getCode());
    }
}
