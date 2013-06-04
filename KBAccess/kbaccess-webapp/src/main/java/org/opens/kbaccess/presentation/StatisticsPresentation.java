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
package org.opens.kbaccess.presentation;

import java.util.Collection;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.statistics.CriterionStatistics;

/**
 *
 * @author bcareil
 */
public class StatisticsPresentation {
    
    private static final int NB_CRITERION_STATISTICS = 3;
    private static final int NB_ACCOUNT_STATISTICS = 3;
    
    private Long testcaseCount;
    private Long webarchiveCount;
    private Long frameOfReferenceCount;
    private Long userCount;
    
    Collection<CriterionStatistics> mostReferencedCriterion;
    Collection<CriterionStatistics> leastReferencedCriterion;
    
    Collection<AccountStatistics> bestContributors;

    public StatisticsPresentation() {
    }

    public StatisticsPresentation(
            TestcaseDataService testcaseDataService,
            WebarchiveDataService webarchiveDataService,
            ReferenceDataService referenceDataService,
            AccountDataService accountDataService,
            StatisticsDataService statisticsDataService
            ) {
        testcaseCount = testcaseDataService.getCount();
        webarchiveCount = webarchiveDataService.getCount();
        frameOfReferenceCount = referenceDataService.getCount();
        userCount = accountDataService.getCount();
        
        mostReferencedCriterion = statisticsDataService.getCriterionOrderByTestcaseCount(false, NB_CRITERION_STATISTICS);
        leastReferencedCriterion = statisticsDataService.getCriterionOrderByTestcaseCount(true, NB_CRITERION_STATISTICS);
        bestContributors = statisticsDataService.getAccountOrderByTestcaseCount(false, NB_ACCOUNT_STATISTICS);
    }

    public Long getFrameOfReferenceCount() {
        return frameOfReferenceCount;
    }

    public void setFrameOfReferenceCount(Long frameOfReferenceCount) {
        this.frameOfReferenceCount = frameOfReferenceCount;
    }

    public Collection<CriterionStatistics> getLeastReferencedCriterion() {
        return leastReferencedCriterion;
    }

    public void setLeastReferencedCriterion(Collection<CriterionStatistics> leastReferencedCriterion) {
        this.leastReferencedCriterion = leastReferencedCriterion;
    }

    public Collection<CriterionStatistics> getMostReferencedCriterion() {
        return mostReferencedCriterion;
    }

    public void setMostReferencedCriterion(Collection<CriterionStatistics> mostReferencedCriterion) {
        this.mostReferencedCriterion = mostReferencedCriterion;
    }

    public Collection<AccountStatistics> getBestContributors() {
        return bestContributors;
    }

    public void setBestContributors(Collection<AccountStatistics> bestContributors) {
        this.bestContributors = bestContributors;
    }

    public Long getTestcaseCount() {
        return testcaseCount;
    }

    public void setTestcaseCount(Long testcaseCount) {
        this.testcaseCount = testcaseCount;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getWebarchiveCount() {
        return webarchiveCount;
    }

    public void setWebarchiveCount(Long webarchiveCount) {
        this.webarchiveCount = webarchiveCount;
    }
    
}
