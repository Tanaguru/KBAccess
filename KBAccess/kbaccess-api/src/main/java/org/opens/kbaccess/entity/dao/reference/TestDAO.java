package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import java.util.List;

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
     * @param code
     * @param criterion
     * @return
     *      The list of tests corresponding to a code and a criterion
     * 
     * FIXME : really needed ?
     */
    List<Test> findAll(String code, Criterion criterion);

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

    /**
     * 
     * @param code
     * @param ref
     * @return
     *      The test corresponding to a code and a reference
     */
    Test findByCodeAndReference(String code, Reference ref);
}
