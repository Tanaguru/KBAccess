/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.authorization;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author amchaar
 */
public interface AccessLevel extends Entity, Reorderable {

    /**
     * 
     * 
     */
    String getCdAccessLevel();
 
    /**
     * 
     * 
     */
    void setCdAccessLevel(String accesslevel);
    
    /**
     * @return The AccessLevel name
     */
    String getLabel();
    
    /**
     * 
     * @param label The new AccessLevel name
     */
    void setLabel(String label);
    
    /**
     * 
     * @return The access level description
     */
    String getDescription();
    
    /**
     * 
     * @param description The access level description
     */
    void setDescription(String description);
    
    /**
     * 
     * 
     */
    int getPriority();
    
    /**
     * 
     * 
     */
    void setPriority(int priority);

    /**
     *
     * @return The access level as a member of AccessLebelEnumType
     */
    AccessLevelEnumType getAccessLevelEnumType();
}
