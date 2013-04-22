package org.opens.sdk.entity.i18n.service;

import org.opens.sdk.entity.Entity;
import org.opens.sdk.entity.i18n.Language;
import org.opens.sdk.entity.i18n.dao.GenericI18nDAO;
import org.opens.sdk.entity.service.GenericDataService;
import java.io.Serializable;

/**
 * 
 * @param <E>
 * @param <K>
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface GenericI18nService<E extends Entity, K extends Serializable>
		extends GenericDataService<E, K> {

	/**
	 * 
	 * @param language
	 *            the language of the internationalized entity to find
	 * @param entity
	 *            the entity to be internationalized
	 */
	void internationalize(Language language, E entity);

	/**
	 * 
	 * @param i18nDao
	 *            the internationalization DAO to set
	 */
	void setI18nDao(GenericI18nDAO<E, K> i18nDao);
}
