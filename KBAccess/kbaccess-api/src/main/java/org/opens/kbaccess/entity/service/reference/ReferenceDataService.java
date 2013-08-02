package org.opens.kbaccess.entity.service.reference;

import java.util.Map;
import org.opens.kbaccess.entity.reference.Reference;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface ReferenceDataService extends
		RefBaseDataService<Reference, Long> {
     
    /**
     * Only a getter because the map is loaded at the initialization of the application
     * The data it stores are read only and should not be changed
     * 
     * @return 
     */
    public Map<String, Reference> getInternMap();
}
