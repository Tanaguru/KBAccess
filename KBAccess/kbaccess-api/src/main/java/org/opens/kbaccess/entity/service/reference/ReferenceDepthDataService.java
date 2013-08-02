package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceDepth;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ReferenceDepthDataService extends
		RefComponentDataService<ReferenceDepth, Long> {
    /**
     * 
     * @param reference
     * @param depth
     * @return 
     */
    public ReferenceDepth getByReferenceAndDepth(Reference reference, int depth);
}
