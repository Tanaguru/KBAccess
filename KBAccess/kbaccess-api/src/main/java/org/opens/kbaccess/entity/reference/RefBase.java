/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 *
 * @author blebail
 */
public interface RefBase extends Entity {
    
    public String getCode();
    
    public void setCode(String code);
    
    public String getLabel();
    
    public void setLabel(String label);
}
