package org.opens.kbaccess.entity.service.statistics;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceTestStatistics;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface StatisticsDataService {

    /**
    * NOTE: Its result may be cached.
    *
    * @param asc Whether to sort the list ascending or descending
    * @param limit The maximum number of entity to return
    * @return A list of reference test statistic entity sorted by
    * the number of testcases associated with each one.
    */
    Collection<ReferenceTestStatistics> getReferenceTestOrderByTestcaseCount(
            final boolean asc,
            int limit
            );

    /**
    * NOTE: Its result may be cached.
    *
    * @param asc Whether to sort the list ascending or descending
    * @param limit The maximum number of entity to return
    * @return A list of account statistic entity sorted by
    * the number of testcases associated with each one.
    */
    Collection<AccountStatistics> getAccountOrderByTestcaseCount(
            boolean asc,
            int limit
            );
    
    /**
     * 
     * @param reference The reference to get the coverage of
     * @return A reference statistics
     */
    ReferenceStatistics getReferenceCoverage(Reference reference);
    
    /**
     * 
     * @return A collection of reference statistics
     */
    Collection<ReferenceStatistics> getReferencesCoverage();
}
