package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import java.util.List;
import org.opens.kbaccess.entity.reference.Criterion;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface TestDataService extends GenericDataService<Test, Long> {

    /**
     *
     * @param reference
     *            the reference of the tests to find
     * @return the tests found
     */
    List<Test> getTestListFromReference(Reference reference);

    /**
     *
     * @param codeArray
     * @return
     */
    List<Test> getAllByCode(String[] codeArray);

    /**
     *
     * @param code
     * @return
     *      The test corresponding to the code
     */
    Test getByCode(String code);

    /**
     *
     * @param criterion
     * @return
     *      The list of tests corresponding to a criterion
     */
    List<Test> getTestListFromCriterion(Criterion criterion);
    
    /**
     * This method is used for the search by URL i.e : /AW21/1.1.1/
     * @param label
     * @param referenceCode
     * @return
     *      The test corresponding to a label and a reference
     */
    Test getByLabelAndReferenceCode(String label, String referenceCode);
}
