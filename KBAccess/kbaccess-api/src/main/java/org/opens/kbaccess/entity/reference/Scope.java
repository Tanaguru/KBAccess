package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Scope extends Entity {

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
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param label
     *            the label to set
     */
    void setLabel(String label);
}
