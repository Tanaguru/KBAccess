/*
 * KBAccess - Collaborative database of accessibility examples
 * Copyright (C) 2012-2016  Open-S Company
 *
 * This file is part of KBAccess.
 *
 * KBAccess is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.kbaccess.entity.dao.statistics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.service.reference.ReferenceDepthDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.AccountStatisticsImpl;
import org.opens.kbaccess.entity.statistics.ReferenceStatistics;
import org.opens.kbaccess.entity.statistics.ReferenceStatisticsImpl;
import org.opens.kbaccess.entity.subject.TestcaseImpl;

/**
 *
 * @author bcareil
 */
public class StatisticsDAOImpl implements StatisticsDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /*
     * Utilities
     */
    private int getReferenceTestCoveredCount(
            Collection<ReferenceTest> referenceTestCollection,
            Result result) {
        int referenceTestCoveredCount = 0;
        
        StringBuilder sb = new StringBuilder();
        Query query;
        List queryResult;
        
        sb.append("SELECT tc FROM ");
        sb.append(TestcaseImpl.class.getName());
        sb.append(" tc, ");
        sb.append(ReferenceTest.class.getName());
        sb.append(" rt ");
        sb.append("WHERE tc.result = :result ");
        sb.append("AND rt in (?1) ");
        sb.append("AND tc.referenceTest = rt ");
        sb.append("GROUP BY rt.id");
        
//        sb.append(
//                "SELECT COUNT(rt.ID_REFERENCE_TEST) AS referenceTestCount " +
//                "FROM reference_test AS rt"+
//                ", testcase AS tc " +
//                "WHERE tc.ID_RESULT = " + result.getId() + 
//                "AND tc.ID_REFERENCE_TEST in (?1)"
//            );
        
        LogFactory.getLog(StatisticsDAOImpl.class.getName()).debug(sb.toString());
        query = entityManager.createQuery(sb.toString());
        query.setParameter("result", result);
        query.setParameter("1", referenceTestCollection);
        
        // Query result
        queryResult = query.getResultList();
        
        if (!queryResult.isEmpty()) {
            referenceTestCoveredCount = queryResult.size();
        }
        
        return referenceTestCoveredCount;
    }
    
//    private int getLowestDepthReferenceTestCountFromReference(Reference reference) {
//        int lowestDepthReferenceTestCount = 0;
//        
//        StringBuilder sb = new StringBuilder();
//        Query query;
//        List result;
//        
//        sb.append(
//                "SELECT COUNT(rt.ID_REFERENCE_TEST) AS referenceTestCount " +
//                "FROM reference_test AS rt"+
//                ", reference AS ref " +
//                ", reference_depth AS rd " +
//                "WHERE ref.ID_REFERENCE = " + reference.getId() + 
//                "rd.DEPTH = ref.TEST_MAX_DEPTH " +
//                "AND rt.ID_REFERENCE_DEPTH = rd.ID_REFERENCE_DEPTH " +
//                "AND rt.CD_REFERENCE_TEST LIKE CONCAT(ref.CD_REFERENCE, '%')"
//            );
//        
//        query = entityManager.createNativeQuery(sb.toString());
//
//        // Query result
//        result = query.getResultList();
//        
//        if (!result.isEmpty()) {
//            lowestDepthReferenceTestCount = (Integer)result.get(0);
//        }
//        
//        return lowestDepthReferenceTestCount;
//    }
    
    @Override
    public Map<String, Long> findAllReferenceTestOrderByTestcaseCount(boolean asc) {
        StringBuilder sb = new StringBuilder();
        Query query;
        List result;
        Map<String, Long> resultMap = new LinkedHashMap<String, Long>();
        
        /*
         * Gets the code of every ReferenceTest and the number of testcases created on it
         */
        sb.append(
                "SELECT rt.CD_REFERENCE_TEST, COUNT(tc.ID_TESTCASE) AS testcaseCount " +
                "FROM reference_test AS rt " +
                "LEFT JOIN testcase AS tc ON tc.ID_REFERENCE_TEST = rt.ID_REFERENCE_TEST " +
                "GROUP BY CD_REFERENCE_TEST " +
                "ORDER BY testcaseCount "
                );
        
        if (asc) {
            sb.append("ASC");
        } else {
            sb.append("DESC");
        }
        
        query = entityManager.createNativeQuery(sb.toString());
   
        LogFactory.getLog(this.getClass().getName()).info(sb.toString());
        
        // Query result
        result = query.getResultList();
        
        // Mapping with key=CD_REFERENCE_TEST, value=testcaseCount        
        for (Iterator it = result.iterator(); it.hasNext();) {
            Object[] line = (Object[]) it.next();
            
            String referenceTestCode = (String)line[0];
            long testcaseCount = ((BigInteger)line[1]).longValue();
            
            resultMap.put(referenceTestCode, testcaseCount);
        }
        
        return resultMap;
    }
    
    @Override
    public List<AccountStatistics> findAccountOrderByTestcaseCount(
            boolean asc,
            int limit
            ) {
        StringBuilder sb = new StringBuilder();
        Query query;
        List result;
        List<AccountStatistics> retval;

        sb.append(
                "SELECT a.ID_ACCOUNT, COUNT(t.ID_TESTCASE) AS testcaseCount " +
                "FROM account AS a LEFT JOIN testcase AS t " +
                "ON t.ID_ACCOUNT = a.ID_ACCOUNT " +
                "GROUP BY a.ID_ACCOUNT ORDER BY testcaseCount "
                );
        if (asc) {
            sb.append("ASC");
        } else {
            sb.append("DESC");
        }
        
        if (limit != 0) {
            sb.append(" LIMIT ").append(limit);
            retval =  new ArrayList<AccountStatistics>(limit);
        } else {
            retval =  new ArrayList<AccountStatistics>();
        }
        
        query = entityManager.createNativeQuery(sb.toString());
        
        // Query result
        result = query.getResultList();
        
        for (Iterator it = result.iterator(); it.hasNext();) {
            Object[] line = (Object[]) it.next();
            
            retval.add(new AccountStatisticsImpl(
                    ((BigInteger)line[0]).longValue(),
                    ((BigInteger)line[1]).longValue()
                    ));
        }
        return retval;
    }

    @Override
    public ReferenceStatistics findReferenceCoverage(
            Reference reference,
            ResultDataService resultDataService,
            ReferenceDepthDataService referenceDepthDataService,
            ReferenceTestDataService referenceTestDataService) {
        
        ReferenceStatistics referenceStatistics = null;
        Set<ReferenceTest> lowestReferenceTestSet;
        int lowestReferenceTestCount;
        int referenceTestCoveredFailedCount;
        int referenceTestCoveredPassedCount;
        
        // Get all reference test of the reference with the lowest test depth
        lowestReferenceTestSet = new TreeSet<ReferenceTest>(
                referenceTestDataService.getAllByReferenceAndReferenceDepth(
                    reference, 
                    referenceDepthDataService.getByReferenceAndDepth(
                        reference,
                        reference.getTestMaxDepth()
                    )
                )
            );
        
        lowestReferenceTestCount = lowestReferenceTestSet.size();
        LogFactory.getLog(StatisticsDAOImpl.class.getName()).debug(lowestReferenceTestCount);
        
        /*
         * A ReferenceTest is covered only if there is at least 
         * a testcase passed AND a testcase failed on it
         */
        referenceTestCoveredFailedCount = getReferenceTestCoveredCount(
                    lowestReferenceTestSet,
                    resultDataService.getByCode("failed")
                );
        LogFactory.getLog(StatisticsDAOImpl.class.getName()).debug(referenceTestCoveredFailedCount);
        
        referenceTestCoveredPassedCount = getReferenceTestCoveredCount(
                    lowestReferenceTestSet,
                    resultDataService.getByCode("passed")
                );
        LogFactory.getLog(StatisticsDAOImpl.class.getName()).debug(referenceTestCoveredPassedCount);
        
        /*
         * Compute the coverage percentage
         */
        float coverage = referenceTestCoveredFailedCount + referenceTestCoveredPassedCount;
        coverage /= (float)lowestReferenceTestCount * 2;
        coverage *= 100;
        
        LogFactory.getLog(StatisticsDAOImpl.class.getName()).debug(coverage);
        
        /*
         * Set the statitstics entity
         */
        referenceStatistics = new ReferenceStatisticsImpl(reference, Math.round(coverage));
        
        return referenceStatistics;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
