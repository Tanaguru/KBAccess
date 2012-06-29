package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Level;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface LevelDataService extends GenericDataService<Level, Long> {

    /**
     *
     * @param code
     * @return
     *      Find a Level from its code
     */
    Level getByCode(String code);

}
