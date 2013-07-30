package org.opens.kbaccess.entity.service.reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;

/**
 * 
 * @author blebail
 */
public class ReferenceTestDataServiceImpl extends AbstractRefComponentWithDepthDataService<ReferenceTest, Long> 
    implements ReferenceTestDataService {
    
    /*
     * NOTE : 
     * At the moment it goes through only 1 test child depth because WCAG20, AW21 & Rgaa22 don't need more
     * This function should be recursive on n test child depths 
     */
    @Override
    public Collection<ReferenceTest> getReferenceTestWithChildren(
            ReferenceTest referenceTest,
            ReferenceLevel referenceLevel,
            Result result) {
        
        List<ReferenceTest> referenceTestList = new ArrayList<ReferenceTest>();
        boolean addToCollection = true;
        boolean addChildrenToCollection = false;
        
        Logger.getLogger(ReferenceTestDataService.class.getName()).info("referenceTest : " + referenceTest);
        Logger.getLogger(ReferenceTestDataService.class.getName()).info("referenceLevel : " + referenceLevel);
        Logger.getLogger(ReferenceTestDataService.class.getName()).info("result : " + result);
        Logger.getLogger(ReferenceTestDataService.class.getName()).info("referenceTest.level : " + referenceTest.getReferenceLevel());
        
        // Filter on the referenceLevel
        if (referenceTest.getReferenceLevel() != null) {
            if (referenceLevel != null && !referenceTest.getReferenceLevel().equals(referenceLevel)) {
                addToCollection = false;
            }
        }
        // Filter on the result
        if (result != null && result.getCode().equals("failed")) {
            addChildrenToCollection = true;
        }
        
        // Adding to collection
        if (addToCollection) {
            referenceTestList.add(referenceTest);
            
            if (addChildrenToCollection) {
                for (ReferenceTest referenceTestChild : referenceTest.getChildren()) {
                    referenceTestList.add(referenceTestChild);
                }
            }
        }
        
        return referenceTestList;
    }
    
    /*
     * NOTE : 
     * At the moment it goes through only 1 test child depth because WCAG20, AW21 & Rgaa22 don't need more
     * This function should be recursive on n test child depths 
     */
    @Override
    public Collection<ReferenceTest> getReferenceTestWithAllChildren(
            ReferenceTest referenceTest,
            ReferenceLevel referenceLevel,
            Result result) {
        
        List<ReferenceTest> referenceTestList = new ArrayList<ReferenceTest>();
        boolean addToCollection = true;
        
        // Filter on the referenceLevel
        if (referenceLevel != null && !referenceTest.getReferenceLevel().equals(referenceLevel)) {
            addToCollection = false;
        }
        
        // Adding to collection
        if (addToCollection) {
            referenceTestList.add(referenceTest);
            
            for (ReferenceTest referenceTestChild : referenceTest.getChildren()) {
                referenceTestList.add(referenceTestChild);
            }
        }
        
        return referenceTestList;
    }
    
    @Override
    public ReferenceTest getByLabelAndReferenceCode(String label, String referenceCode) {
        Reference reference = referenceDataService.getByCode(referenceCode);
        ReferenceTest referenceTest = null;
        
        if (reference != null) {
            Map<String, ReferenceTest> referenceTestMap = internMap.get(reference);
            
            for (Map.Entry<String, ReferenceTest> entry : referenceTestMap.entrySet()) {
                ReferenceTest refTest = entry.getValue();
                
                if (refTest.getLabel().equals(label)) {
                    referenceTest = refTest;
                    break;
                }
            }
        }
        
        return referenceTest;
    }
}
