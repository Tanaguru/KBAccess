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

import java.util.Arrays;
import java.util.List;
import org.opens.kbaccess.entity.statistics.CriterionStatistics;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class StatisticsDAOImplTest extends AbstractDaoTestCase {
    
    public StatisticsDAOImplTest(String testName) {
        super(testName, "src/test/resources/datasets/dataset.xml");
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private StatisticsDAO getBean() {
        return (StatisticsDAO) springBeanFactory.getBean("statisticsDAO");
    }
    
    /**
     * Test of findCriterionOrderByTestcaseCount method, of class StatisticsDAOImpl.
     */
    public void testFindCriterionOrderByTestcaseCount() {
    }
    /*
    public void testFindCriterionOrderByTestcaseCount() {
        System.out.println("findCriterionOrderByTestcaseCount [nuc] asc");
        fail("WARNING: This test can't be run with HSQLDB. " +
                "You can \"safely\" comment this line if you are using mysql.");
        // args
        boolean asc = true;
        int limit = 2;
        /// expected
        List<Long> expResult = Arrays.asList(1L, 3L, 2L);
        // instance
        StatisticsDAO instance = getBean();
        // run method
        List<CriterionStatistics> result = instance.findCriterionOrderByTestcaseCount(asc, limit);
        // asserts 
        assertNotNull(result);
        assertTrue(limit >= result.size());
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < result.size(); ++i) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }
    */

}
