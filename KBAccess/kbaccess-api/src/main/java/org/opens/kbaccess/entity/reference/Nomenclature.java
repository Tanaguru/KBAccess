package org.opens.kbaccess.entity.reference;

import java.util.Collection;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface Nomenclature extends Entity {

	/**
	 * 
	 * @param element
	 *            the element to add
	 */
	void addElement(NomenclatureElement element);

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
	 * @return the elements
	 */
	Collection<NomenclatureElement> getElementList();

	/**
	 * 
	 * @return the integer values of the elements
	 */
	Collection<Integer> getIntegerValueList();

	/**
	 * 
	 * @return the long label
	 */
	String getLabel();

	/**
	 * 
	 * @return the values from the elements
	 */
	Collection<String> getValueList();

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
	 * @param elements
	 *            the elements to set
	 */
	void setElementList(Collection<NomenclatureElement> elements);

	/**
	 * 
	 * @param longLabel
	 *            the long label to set
	 */
	void setLabel(String label);

}
