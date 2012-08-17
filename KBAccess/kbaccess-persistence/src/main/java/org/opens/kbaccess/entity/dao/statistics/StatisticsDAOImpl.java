/*
 * URLManager - URL Indexer
 * Copyright (C) 2008-2012  Open-S Company
 *
 * This file is part of URLManager.
 *
 * URLManager is free software: you can redistribute it and/or modify
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.opens.kbaccess.entity.statistics.CriterionStatistics;
import org.opens.kbaccess.entity.statistics.CriterionStatisticsImpl;

/**
 *
 * @author bcareil
 */
public class StatisticsDAOImpl implements StatisticsDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<CriterionStatistics> findCriterionOrderByTestcaseCount(
            boolean asc,
            int limit
            ) {
        StringBuilder sb = new StringBuilder();
        Query query;
        List result;
        List<CriterionStatistics> retval = new ArrayList<CriterionStatistics>(limit);

        /*
         * Full request exemple :
         * SELECT c.ID_CRITERION AS id, c.CD_CRITERION AS code, COUNT(t.ID_TESTCASE) AS testcaseCount
         * FROM criterion AS c
         * LEFT JOIN testcase AS t ON t.ID_CRITERION = c.ID_CRITERION
         * GROUP BY c.ID_CRITERION ORDER BY testcaseCount ASC LIMIT 3;
         * 
         * This request only work with MySQL since the count on a null element
         * (null elements introduced by the left join) has not a standard
         * behavior (or this standard is not respected) :
         * - On Mysql, a count on a null element return 0
         * - On HSQLDB, if the column specified in the count is null, the row is discarded
         * 
         * I do not find any solution to this problem. The HSQLDB's documentation
         * says that COUNT(*) has not the above mentioned behavior, and that's
         * true. However, as it does not focus on a column, COUNT return the
         * number of row which have the same value in the columns specified
         * by GROUP BY, which is intended actually.
         */
        sb.append(
                "SELECT c.ID_CRITERION, c.CD_CRITERION, COUNT(t.ID_TESTCASE) AS testcaseCount " +
                "FROM criterion AS c " +
                "LEFT JOIN testcase AS t ON t.ID_CRITERION = c.ID_CRITERION " +
                "GROUP BY c.ID_CRITERION ORDER BY testcaseCount "
                );
        if (asc) {
            sb.append("ASC");
        } else {
            sb.append("DESC");
        }
        sb.append(" LIMIT ").append(limit);
        query = entityManager.createNativeQuery(sb.toString());
        //query.setMaxResults(limit);
        result = query.getResultList();
        for (Iterator it = result.iterator(); it.hasNext();) {
            Object[] line = (Object[]) it.next();
            
            retval.add(new CriterionStatisticsImpl(
                    ((BigInteger)line[0]).longValue(),
                    (String)line[1],
                    ((BigInteger)line[2]).longValue()
                    ));
        }
        return retval;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
}
