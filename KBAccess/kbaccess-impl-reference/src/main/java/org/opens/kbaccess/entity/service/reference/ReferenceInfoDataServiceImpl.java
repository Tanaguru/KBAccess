package org.opens.kbaccess.entity.service.reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;

/**
 * 
 * @author opens
 */
public class ReferenceInfoDataServiceImpl extends AbstractRefComponentWithDepthDataService<ReferenceInfo, Long> 
    implements ReferenceInfoDataService {   
    
    protected ReferenceTestDataService referenceTestDataService; 

    /*
     * Utilities
     */
    private Collection<ReferenceTest> getReferenceTestsOfReferenceInfo(
            ReferenceInfo referenceInfo, 
            ReferenceLevel referenceLevel,
            Result result) {
        
        List<ReferenceTest> referenceTestList = new ArrayList<ReferenceTest>();
        
        for (ReferenceTest referenceTest : referenceInfo.getReferenceTestSet()) {
            referenceTestList.addAll(referenceTestDataService.getReferenceTestWithAllChildren(
                        referenceTest, 
                        referenceLevel, 
                        result)
                    );
        }
        
        return referenceTestList;
    }
    
    /*
     * Implementation 
     */
    
    /*
     * NOTE : 
     * At the moment it goes through only 1 info child depth because WCAG20, AW21 & Rgaa22 don't need more
     * This function should be recursive on n info child depths 
     */
    @Override
    public Collection<ReferenceTest> getAllReferenceTestsOfReferenceInfo(
            ReferenceInfo referenceInfo, 
            ReferenceLevel referenceLevel, 
            Result result) {
        
        List<ReferenceTest> referenceTestList = new ArrayList<ReferenceTest>();
            
        if (referenceInfo.getChildren() == null || referenceInfo.getChildren().isEmpty()) {
            referenceTestList.addAll(getReferenceTestsOfReferenceInfo(referenceInfo, referenceLevel, result));
        } else {
            for (ReferenceInfo referenceInfoChild : referenceInfo.getChildren()) {
                referenceTestList.addAll(getReferenceTestsOfReferenceInfo(referenceInfoChild, referenceLevel, result));
            }
        }
        
        return referenceTestList;
    }
    
    /*
     * Accessors
     */
    public ReferenceTestDataService getReferenceTestDataService() {
        return referenceTestDataService;
    }

    public void setReferenceTestDataService(ReferenceTestDataService referenceTestDataService) {
        this.referenceTestDataService = referenceTestDataService;
    }
}
