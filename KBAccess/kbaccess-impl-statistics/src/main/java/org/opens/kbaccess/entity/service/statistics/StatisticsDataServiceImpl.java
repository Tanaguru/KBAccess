/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.service.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.opens.kbaccess.entity.dao.statistics.StatisticsDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceDepthDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceTestStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceTestStatisticsImpl;

/**
 *
 * @author blebail
 */
public class StatisticsDataServiceImpl implements StatisticsDataService {
    
    private StatisticsDAO statisticsDAO;
    private ReferenceTestDataService referenceTestDataService;
    private ReferenceDepthDataService referenceDepthDataService;
    private ResultDataService resultDataService;
    private ReferenceDataService referenceDataService;

    /*
     * Utilities
     */
    
    /** 
     * Compute statistics per highest depth reference depth, thus only at -1 depth
     * The testcase count of a reference test of -1 depth is the sum of its children testcase count 
     * 
     * @param originalMap the input map
     * @return the computed map
     */
    private Map<String, Long> computeStatisticsPerHighestTestDepth(Map<String, Long> originalMap) {
        Map<String, Long> referenceTestWithTestcaseCountMap = new LinkedHashMap<String, Long>();
                
        for (Map.Entry<String, Long> entry : originalMap.entrySet()) {
            String referenceTestCode = entry.getKey();
            ReferenceTest referenceTest = referenceTestDataService.getByCode(referenceTestCode);
            Collection<ReferenceTest> referenceTestParents = (Collection<ReferenceTest>)referenceTest.getParents();
            long testcaseCount = entry.getValue();
            
            // No parents, it's a -1 depth reference test
            //      OR an orphan test (no link whatsoever inside the reference)
            //      Only 7 cases at the moment : WCAG20 techniques G136, G190, C29, SVR2, SVR3, SVR4, F19
            if (referenceTestParents == null || referenceTestParents.isEmpty()) {
                Long count = referenceTestWithTestcaseCountMap.get(referenceTestCode);
                
                // Not in the map yet
                if (count == null) {
                    count = 0L;
                } 
                
                referenceTestWithTestcaseCountMap.put(
                    referenceTestCode,
                    count + testcaseCount
                    );
                
            } else {
                // add testcase count to each parent
                for (ReferenceTest refTest : referenceTestParents) {
                    Long count = referenceTestWithTestcaseCountMap.get(refTest.getCode());
                
                    // Not in the map yet
                    if (count == null) {
                        count = 0L;
                    } 

                    referenceTestWithTestcaseCountMap.put(
                        refTest.getCode(),
                        count + testcaseCount
                        );
                    }
            }
        }
        
        return referenceTestWithTestcaseCountMap;
    }
    
    /**
     * Sort the referenceTestWithTestcaseCountMap on the values (testcaseCount) with the corresponding order
     * 
     * @param referenceTestWithTestcaseCountMap input map to be sorted on values (testcaseCount)
     * @param asc order 
     * @return the sorted entries list
     */
    private List<Map.Entry<String, Long>> sortByOrder(
            Map<String, Long> referenceTestWithTestcaseCountMap,
            final boolean asc) {
        
        List<Map.Entry<String, Long>> sortedEntriesList = new ArrayList<Map.Entry<String, Long>>(referenceTestWithTestcaseCountMap.entrySet());
        
        Collections.sort(sortedEntriesList,
                 new Comparator() {
                     @Override
                     public int compare(Object o1, Object o2) {
                         Map.Entry e1 = (Map.Entry) o1;
                         Map.Entry e2 = (Map.Entry) o2;
                         if (asc) {
                            return ((Comparable) e1.getValue()).compareTo(e2.getValue());
                         } else {
                            return -((Comparable) e1.getValue()).compareTo(e2.getValue());
                         }
                     }
                 });
        
        return sortedEntriesList;
    }
    
    /**
     * Build ReferenceTestStatistics list from the sorted list of <ReferenceTest.code, testcaseCount>
     * 
     * @param sortedEntriesList input list
     * @param limit the limit number of the statistics list
     * @retuyrn the statistics list
     */
    private List<ReferenceTestStatistics> buildRefenceTestStatisticsList(
            List<Map.Entry<String, Long>> sortedEntriesList, 
            int limit) {
        
        List<ReferenceTestStatistics> referenceTestStatisticsList = new ArrayList<ReferenceTestStatistics>();
                
        int i = 0;
        for (Map.Entry<String, Long> entry : sortedEntriesList) {
            
            long testcaseCount = entry.getValue();
            ReferenceTest referenceTest = referenceTestDataService.getByCode(entry.getKey());
            Reference reference = referenceTestDataService.getReferenceOf(referenceTest);
            
            referenceTestStatisticsList.add(new ReferenceTestStatisticsImpl(
                    referenceTest.getId(), 
                    referenceTest.getCode(), 
                    referenceTest.getLabel(), 
                    reference.getCode(), 
                    reference.getId(), 
                    reference.getLabel(), 
                    testcaseCount
                    )
                );
            
            if (++i == limit) {
                break;
            }
        }
        
        return referenceTestStatisticsList;
    }
    
    /*
     * Implementation
     */
    @Override
    public Collection<ReferenceTestStatistics> getReferenceTestOrderByTestcaseCount(
        final boolean asc,
        int limit
        ) {
        
        Map<String, Long> resultMap = statisticsDAO.findAllReferenceTestOrderByTestcaseCount(asc);
        Map<String, Long> referenceTestWithTestcaseCountMap;
        List<Map.Entry<String, Long>> sortedEntriesList;
        List<ReferenceTestStatistics> referenceTestStatisticsList;
        
        // Heavy implementation
        // Don't bother updating it, just rewrite it
        // Relying only on the DAO by doing it fully SQL would be WAY BETTER
        referenceTestWithTestcaseCountMap = computeStatisticsPerHighestTestDepth(resultMap);
        sortedEntriesList = sortByOrder(referenceTestWithTestcaseCountMap, asc);
        referenceTestStatisticsList = buildRefenceTestStatisticsList(sortedEntriesList, limit);
        
        return referenceTestStatisticsList;
    }

    @Override
    public Collection<AccountStatistics> getAccountOrderByTestcaseCount(
        boolean asc,
        int limit
        ) {
        
        return statisticsDAO.findAccountOrderByTestcaseCount(asc, limit);
    }

    @Override
    public ReferenceStatistics getReferenceCoverage(Reference reference) {
        return statisticsDAO.findReferenceCoverage(
                reference, 
                resultDataService, 
                referenceDepthDataService, 
                referenceTestDataService
            );
    }

    @Override
    public Collection<ReferenceStatistics> getReferencesCoverage() {
        List<ReferenceStatistics> referenceStatisticsList 
                = new ArrayList<ReferenceStatistics>();
        
        for (Reference reference : referenceDataService.findAll()) {
            referenceStatisticsList.add(getReferenceCoverage(reference));
        }
        
        return referenceStatisticsList;
    }

    /*
     * Accessors
     */
    public StatisticsDAO getStatisticsDAO() {
        return statisticsDAO;
    }

    public void setStatisticsDAO(StatisticsDAO statisticsDAO) {
        this.statisticsDAO = statisticsDAO;
    }
    
    public ReferenceTestDataService getReferenceTestDataService() {
        return referenceTestDataService;
    }

    public void setReferenceTestDataService(ReferenceTestDataService referenceTestDataService) {
        this.referenceTestDataService = referenceTestDataService;
    }

    public ReferenceDataService getReferenceDataService() {
        return referenceDataService;
    }

    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    public ReferenceDepthDataService getReferenceDepthDataService() {
        return referenceDepthDataService;
    }

    public void setReferenceDepthDataService(ReferenceDepthDataService referenceDepthDataService) {
        this.referenceDepthDataService = referenceDepthDataService;
    }

    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }
}
