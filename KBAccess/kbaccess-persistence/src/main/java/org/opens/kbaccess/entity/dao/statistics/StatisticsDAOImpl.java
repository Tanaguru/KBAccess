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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.AccountStatisticsImpl;

/**
 *
 * @author bcareil
 */
public class StatisticsDAOImpl implements StatisticsDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
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
                "FROM account AS a, testcase AS t " +
                "WHERE t.ID_ACCOUNT = a.ID_ACCOUNT " +
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
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
