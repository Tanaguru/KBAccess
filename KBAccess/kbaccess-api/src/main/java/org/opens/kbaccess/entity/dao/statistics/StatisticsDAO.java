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

import java.util.List;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.CriterionStatistics;

/**
 *
 * @author bcareil
 */
public interface StatisticsDAO {

    /**
     * Return a list of criterion statistic entity sorted by
     * the number of testcases associated with each one.
     * 
     * @param asc   Whether to sort the list ascending or descending
     * @param limit The maximum number of entity to return
     * @return      A list of criterion statistic entity sorted by
     *              the number of testcases associated with each one.
     */
    List<CriterionStatistics> findCriterionOrderByTestcaseCount(
            boolean asc,
            int limit
            );
    
   /**
     * Return a list of account statistic entity sorted by
     * the number of testcases associated with each one.
     * 
     * @param asc   Whether to sort the list ascending or descending
     * @param limit The maximum number of entity to return
     * @return      A list of account statistic entity sorted by
     *              the number of testcases associated with each one.
     */
    List<AccountStatistics> findAccountOrderByTestcaseCount(
            boolean asc,
            int limit
            );
}
