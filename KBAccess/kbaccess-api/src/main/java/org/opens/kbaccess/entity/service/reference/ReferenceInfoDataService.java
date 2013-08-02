package org.opens.kbaccess.entity.service.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface ReferenceInfoDataService extends RefComponentWithDepthDataService<ReferenceInfo, Long> {
    
    /**
     * 
     * @param referenceInfo
     * @param referenceLevel
     * @param result
     * @return 
     */
    public Collection<ReferenceTest> getAllReferenceTestsOfReferenceInfo(
            ReferenceInfo referenceInfo,
            ReferenceLevel referenceLevel,
            Result result
            );
}
