package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Level;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface LevelDAO extends GenericDAO<Level, Long> {

    /**
     * 
     * @param code
     * @return
     * 
     * FIXME : really needed ?
     */
    Collection<Level> findAllByCode(String code);

    /**
     *
     * @param code
     * @return
     *      Retrieves a Level from its code
     */
    Level findByCode(String code);

}
