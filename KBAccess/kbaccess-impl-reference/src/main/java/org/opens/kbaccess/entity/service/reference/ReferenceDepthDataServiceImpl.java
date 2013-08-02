package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceDepth;

/**
 * 
 * @author opens
 */
public class ReferenceDepthDataServiceImpl extends AbstractRefComponentDataService<ReferenceDepth, Long> 
        implements ReferenceDepthDataService {

    public ReferenceDepthDataServiceImpl() {
        super();
    }
    
    @Override
    public ReferenceDepth getByReferenceAndDepth(Reference reference, int depth) {
        if (reference == null ) {
            return null;
        }
        
        String depthLabel;
        
        if (depth > 0) {
            depthLabel = "+" + depth;
        } else if (depth < 0) {
            depthLabel = String.valueOf(depth);
        } else {
            return null;
        }
        
        ReferenceDepth referenceDepth;
        StringBuilder referenceDepthCode = new StringBuilder();
        
        referenceDepthCode.append(reference.getCode())
                .append("-")
                .append("depth")
                .append(depthLabel);
                
        referenceDepth = internMap.get(reference).get(referenceDepthCode.toString());
        
        return referenceDepth;
    }
}
