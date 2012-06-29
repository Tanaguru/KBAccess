package org.opens.kbaccess.entity.dao.reference;

import java.util.List;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface TestDAO extends GenericDAO<Test, Long> {

    /**
     *
     * @param reference
     *            the reference of the tests to find
     * @return the collection of tests found
     */
    List<Test> findAll(Reference reference);

    /**
     *
     * @param criterion
     * @return
     *      The list of tests corresponding to a criterion
     */
    List<Test> findAll(Criterion criterion);

    /**
     *
     * @param codeArray
     * @return
     */
    List<Test> findAllByCode(String[] codeArray);

    /**
     *
     * @param code
     * @return
     *      The test corresponding to a code
     */
    Test findByCode(String code);

}
