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
package org.opens.kbaccess.presentation.factory;

import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.presentation.StatisticsPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author blebail
 */
@Component
public class StatisticsPresentationFactory {
    
    @Autowired
    private TestcaseDataService testcaseDataService;
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    @Autowired
    private ReferenceDataService referenceDataService;
    @Autowired
    private AccountDataService accountDataService;
    @Autowired
    private StatisticsDataService statisticsDataService;
    
    private long testcaseCount;
    private StatisticsPresentation statisticsPresentation = null;

    public StatisticsPresentationFactory() {
        testcaseCount = 0L;
    }
    
    /**
     * 
     * @param testcase
     * @return 
     */  
    public StatisticsPresentation create() {
        long newTestcaseCount = testcaseDataService.getCount();
        
        if (statisticsPresentation == null || (newTestcaseCount > testcaseCount)) {
            testcaseCount = newTestcaseCount;
            
            statisticsPresentation = new StatisticsPresentation(
                    testcaseDataService,
                    webarchiveDataService,
                    referenceDataService,
                    accountDataService,
                    statisticsDataService
                    );
        }
        
        return statisticsPresentation;
    }
    
    /*
     * Accessors
     */
    public TestcaseDataService getTestcaseDataService() {
        return testcaseDataService;
    }

    public void setTestcaseDataService(TestcaseDataService testcaseDataService) {
        this.testcaseDataService = testcaseDataService;
    }

    public WebarchiveDataService getWebarchiveDataService() {
        return webarchiveDataService;
    }

    public void setWebarchiveDataService(WebarchiveDataService webarchiveDataService) {
        this.webarchiveDataService = webarchiveDataService;
    }

    public ReferenceDataService getReferenceDataService() {
        return referenceDataService;
    }

    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

    public StatisticsDataService getStatisticsDataService() {
        return statisticsDataService;
    }

    public void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }
}

