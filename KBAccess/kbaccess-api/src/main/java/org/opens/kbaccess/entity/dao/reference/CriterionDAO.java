package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface CriterionDAO extends GenericDAO<Criterion, Long> {

    /**
     * Return all the Criteria matching with the given parameters.
     * 
     * All parameters are restrictive, meaning that the returning criteria
     * will match all the given parameters.
     * 
     * @param reference The reference of criteria to search for.
     * @param theme The theme of criteria to search for.
     * @return The list of criteria matching the given parameters
     */
    Collection<Criterion> findAll(Reference reference, Theme theme);

    /**
     * 
     * @param code The unique code of the criterion to look for.
     * @return The unique criterion associated with the given code or null
     *         if no criteria have this code.
     */
    Criterion findByCode(String code);

    /**
     * 
     * @param reference Return all criteria of this reference.
     * @return The criteria of the given reference
     */
    Collection<Criterion> findAllFromReference(Reference reference);

    /**
     * 
     * @param theme Return all the criteria relating to this theme
     * @return The criteria relating to the given theme
     */
    Collection<Criterion> findAllFromTheme(Theme theme);
}
