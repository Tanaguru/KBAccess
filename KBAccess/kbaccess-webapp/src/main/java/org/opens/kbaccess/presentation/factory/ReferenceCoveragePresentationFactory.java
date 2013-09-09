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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.presentation.ReferenceCoveragePresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author blebail
 */
@Component
public class ReferenceCoveragePresentationFactory {
    
    @Autowired
    private ReferenceTestDataService referenceTestDataService;
    @Autowired
    private TestcaseDataService testcaseDataService;
    @Autowired
    private StatisticsDataService statisticsDataService;
    @Autowired
    private ResultDataService resultDataService;
    
    private Map<Reference, Long> referenceCoverageTestcaseCountMap;
    private Map<Reference, ReferenceCoveragePresentation> referenceCoverageMap;

    public ReferenceCoveragePresentationFactory() {
        referenceCoverageTestcaseCountMap = new LinkedHashMap<Reference, Long>();
        referenceCoverageMap = new LinkedHashMap<Reference, ReferenceCoveragePresentation>();
    }
    
    /**
     * 
     * @param testcase
     * @return 
     */  
    public ReferenceCoveragePresentation create(Reference reference) {
        long newTestcaseCount = testcaseDataService.getAllFromUserSelection(
                    referenceTestDataService.getAllByReference(reference),
                    null
                ).size();
        
        if (referenceCoverageMap.get(reference) == null 
                || newTestcaseCount > referenceCoverageTestcaseCountMap.get(reference)) {
            referenceCoverageTestcaseCountMap.put(reference, newTestcaseCount);
            
            referenceCoverageMap.put(reference, new ReferenceCoveragePresentation(
                    reference,
                    referenceTestDataService,
                    testcaseDataService,
                    resultDataService,
                    statisticsDataService
                    )
                );
        }
        
        return referenceCoverageMap.get(reference);
    }
    
    public List<ReferenceCoveragePresentation> createFromCollection(
            Collection<Reference> referenceCollection) {
        List<ReferenceCoveragePresentation> presentations;

        presentations = new ArrayList<ReferenceCoveragePresentation>(referenceCollection.size());

        for (Reference reference  : referenceCollection) {
            presentations.add(create(reference));
        }
        return presentations;
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

    public ReferenceTestDataService getReferenceTestDataService() {
        return referenceTestDataService;
    }

    public void setReferenceTestDataService(ReferenceTestDataService referenceTestDataService) {
        this.referenceTestDataService = referenceTestDataService;
    }

    public StatisticsDataService getStatisticsDataService() {
        return statisticsDataService;
    }

    public void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }

    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }
}

