package org.opens.kbaccess.entity.dao.statistics;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.opens.kbaccess.entity.statistics.AccountStatistics;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface StatisticsDAO {

    /**
      * @param asc sorting order 
      * @return A list of reference test sorted by
      * the number of testcases associated with each one.
      */
    Map<String, Long> findAllReferenceTestOrderByTestcaseCount(boolean asc);

    /**
      * @param asc Whether to sort the list ascending or descending
      * @param limit The maximum number of entity to return
      * @return A list of account statistic entity sorted by
      * the number of testcases associated with each one.
      */
    List<AccountStatistics> findAccountOrderByTestcaseCount(
            boolean asc,
            int limit
            );
    
    /**
     * 
     * @return 
     */
    public EntityManager getEntityManager();
    
    /**
     * 
     * @param entityManager 
     */
    public void setEntityManager(EntityManager entityManager);
}
