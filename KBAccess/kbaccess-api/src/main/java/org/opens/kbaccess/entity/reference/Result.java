/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author jkowalczyk
 */
public interface Result extends Entity, Reorderable {

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the label
     */
    String getLabel();

    /**
     * set the entity code
     */
    void setCode(String code);

    /**
     *
     * set the description
     */
    void setDescription(String description);

    /**
     *
     * set the label
     */
    void setLabel(String label);
}
