/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.dao.authorization;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.kbaccess.entity.authorization.AccessLevelImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author amchaar
 */
public class AccessLevelDAOImpl extends AbstractJPADAO<AccessLevel, Long>
        implements AccessLevelDAO {

    @Override
    protected Class<? extends AccessLevel> getEntityClass() {
        return AccessLevelImpl.class ;
    }
    
    private Query selectAccessLevel(String where) {
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT al FROM ");
        query.append(getEntityClass().getName());
        query.append(" al ");
        query.append(where);
        return entityManager.createQuery(query.toString());
    }

    @Override
    public AccessLevel findByCode(String code) {
        Query query = selectAccessLevel("WHERE al.accessLevel = :code");
        
        query.setParameter("code", code);
        try {
            return (AccessLevel) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
