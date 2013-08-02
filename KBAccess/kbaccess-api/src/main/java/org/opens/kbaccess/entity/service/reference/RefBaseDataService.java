package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import org.opens.kbaccess.entity.reference.RefBase;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface RefBaseDataService<E extends RefBase, K extends Serializable> 
    extends GenericDataService<E, K> {
    /**
     * 
     * @param code
     * @return 
     */
     E getByCode(String code);
     
    /**
     * 
     * @return 
     */
     Long getCount();
}
