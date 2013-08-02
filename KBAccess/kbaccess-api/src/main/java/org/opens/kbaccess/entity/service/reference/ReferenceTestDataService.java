package org.opens.kbaccess.entity.service.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface ReferenceTestDataService extends RefComponentWithDepthDataService<ReferenceTest, Long> {
     /**
      * 
      * @param referenceTest
      * @param level
      * @param result
      * @return 
      */
     public Collection<ReferenceTest> getReferenceTestWithChildren(
             ReferenceTest referenceTest, 
             ReferenceLevel level, 
             Result result);
     
     /**
      * 
      * @param referenceTest
      * @param level
      * @param result
      * @return 
      */
     public Collection<ReferenceTest> getReferenceTestWithAllChildren(
             ReferenceTest referenceTest, 
             ReferenceLevel level, 
             Result result);
     
     /**
      * Used only for the search by URL
      * 
      * @param label
      * @param referenceCode
      * @return 
      */
     public ReferenceTest getByLabelAndReferenceCode(String label, String referenceCode);
}
