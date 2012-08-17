package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ReferenceDataService extends
		GenericDataService<Reference, Long> {

    /**
     * 
     * @param code
     * @return 
     */
     Reference getByCode(String code);
     
     /**
      * 
      * @return The number of Reference in database
      */
     Long getCount();
     
}
