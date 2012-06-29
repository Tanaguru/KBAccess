/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.dao.authorization;

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

}
