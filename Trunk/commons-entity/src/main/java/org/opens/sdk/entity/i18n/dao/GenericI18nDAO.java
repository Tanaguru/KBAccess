package org.opens.sdk.entity.i18n.dao;

import org.opens.sdk.entity.i18n.InternationalizedEntity;
import org.opens.sdk.entity.i18n.Language;
import org.opens.sdk.entity.dao.GenericDAO;
import java.io.Serializable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface GenericI18nDAO<E, K extends Serializable> extends
		GenericDAO<InternationalizedEntity<E>, K> {

	/**
	 * 
	 * @param language
	 *            the language of the entity to find
	 * @param entity
	 *            the entity targeted by the entity to find
	 * @return the entity found
	 */
	InternationalizedEntity<E> find(Language language, E entity);
}
