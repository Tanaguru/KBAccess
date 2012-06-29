package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureElement extends Entity {

        /**
         * 
         * @return the code
         */
	String getCode();

	/**
	 * 
	 * @return the nomenclature
	 */
	Nomenclature getNomenclature();

	/**
	 * 
	 * @return the value
	 */
	String getLabel();

	/**
	 * 
	 * @param nomenclature
	 *            the nomenclature to set
	 */
	void setNomenclature(Nomenclature nomenclature);

	/**
	 * 
	 * @param value
	 *            the value to set
	 */
	void setLabel(String label);

        /**
         *
         * @param code
         */
	void setCode(String code);
}
