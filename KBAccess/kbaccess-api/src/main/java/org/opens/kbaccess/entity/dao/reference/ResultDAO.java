package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.Result;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ResultDAO extends GenericDAO<Result, Long> {

    /**
     * 
     * @param code
     * @return 
     * TODO : document
     */
    Result findByCode(String code);
}
