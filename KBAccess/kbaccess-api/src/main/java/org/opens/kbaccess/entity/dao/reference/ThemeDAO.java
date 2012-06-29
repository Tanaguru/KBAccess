package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ThemeDAO extends GenericDAO<Theme, Long> {

    /**
     * 
     * @param code
     * @return 
     * 
     * TODO : document
     */
    Theme findByCode(String code);

    /**
     * 
     * @param reference
     * @return 
     * 
     * TODO : document
     */
    Collection<Theme> findAll(Reference reference);
}
