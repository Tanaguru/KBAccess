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
package org.opens.kbaccess.entity.service.statistics;

import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.kbaccess.entity.dao.statistics.StatisticsDAO;

/**
 *
 * @author bcareil
 */
public class StatisticsDataServiceImplTest extends TestCase {
    
    private StatisticsDAO statisticsDAO;
    
    public StatisticsDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        statisticsDAO = createMock(StatisticsDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private StatisticsDataService getNewInstance() {
        StatisticsDataServiceImpl ofthejedi = new StatisticsDataServiceImpl();
        
        ofthejedi.setStatisticsDAO(statisticsDAO);
        return ofthejedi;
    }

    /**
     * Test of getCriterionOrderByTestcaseCount method, of class StatisticsDataServiceImpl.
     */
    public void testGetCriterionOrderByTestcaseCount() {
        System.out.println("getCriterionOrderByTestcaseCount");
        /* local mocks */
        List list = createMock(List.class);
        /* expected result */
        Collection expResult = list;
        /* args */
        boolean asc = false;
        int limit = 0;
        /* set-up mocks */
        expect(statisticsDAO.findCriterionOrderByTestcaseCount(asc, limit)).andReturn(list);
        /* set-up instance */
        StatisticsDataService instance = getNewInstance();
        /* replay mocks */
        replay(statisticsDAO);
        replay(list);
        /* test */
        Collection result = instance.getCriterionOrderByTestcaseCount(asc, limit);
        /* asserts */
        verify(statisticsDAO);
        verify(list);
        assertEquals(expResult, result);
    }

}
