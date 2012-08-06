/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.dao.authorization;

import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author amchaar
 */
public interface AccessLevelDAO extends GenericDAO<AccessLevel, Long>{

    /**
     * 
     * @param code
     * @return The access level corresponding to the given code or null
     */
    AccessLevel findByCode(String code);
    
}
