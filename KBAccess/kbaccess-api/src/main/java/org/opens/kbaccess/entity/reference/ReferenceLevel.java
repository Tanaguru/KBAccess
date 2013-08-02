/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author blebail
 */
public interface ReferenceLevel extends RefComponent, Reorderable{
    
    /**
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
