package org.opens.sdk.entity.i18n;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Internationalized {

	/**
	 * 
	 * @return the language
	 */
	Language getLanguage();

	/**
	 * 
	 * @param language
	 *            the language to set
	 */
	void setLanguage(Language language);
}
