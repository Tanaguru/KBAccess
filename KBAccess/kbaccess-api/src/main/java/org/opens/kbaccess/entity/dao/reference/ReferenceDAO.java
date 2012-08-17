package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ReferenceDAO extends GenericDAO<Reference, Long> {

    /**
     * 
     * @param code
     * @return 
     * TODO : document
     */
    Reference findByCode(String code);
    
    /**
     * 
     * @return The number of Reference in database
     */
    Long count();
}
