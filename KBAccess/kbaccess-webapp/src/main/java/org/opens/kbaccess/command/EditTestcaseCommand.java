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
package org.opens.kbaccess.command;

import java.util.ArrayList;
import java.util.Collection;
import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.service.reference.CriterionDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.reference.TestDataService;
import org.opens.kbaccess.entity.service.subject.TestResultDataService;
import org.opens.kbaccess.entity.subject.TestResult;
import org.opens.kbaccess.entity.subject.Testcase;

/**
 *
 * @author bcareil
 */
public class EditTestcaseCommand extends ACommand {
    private Long id;

    private Long idTest;
    private Long idResult;
    private Long idTestResult;
    private Long idCriterion;
    private String description;
    
    public EditTestcaseCommand() {
    }

    public EditTestcaseCommand(Testcase testcase) {
        this.id = testcase.getId();
        this.idResult = testcase.getResult().getId();  
        this.description = testcase.getDescription();
        this.idTest = testcase.getTestResults().iterator().next().getTest().getId();
        this.idTestResult = testcase.getTestResults().iterator().next().getId();
        this.idCriterion = testcase.getCriterion().getId();
    }

    public void update(
            Testcase testcase,
            CriterionDataService criterionDataService,
            TestDataService testDataService,
            TestResultDataService testResultDataService,
            ResultDataService resultDataService
            ) {
        
        Test test = testDataService.read(this.idTest);
        Result result = resultDataService.read(this.idResult);
        
        /*
         * In case the current EditTestcaseCommand is instanciated with the default constructor
         * i.e : submission of the edited testcase
         * We need to compute his new Criterion, test and result
         */
        if (this.idTestResult == null) {
            this.idTestResult = testResultDataService.getByTestResult(test, result).getId();
        }
        
        if (this.idCriterion == null) {
            this.idCriterion = testcase.getCriterion().getId();
        }
        
        TestResult testResult = testResultDataService.read(this.idTestResult);
         
        // then update the testcase with new informations    
        testcase.setResult(resultDataService.read(this.idResult));
        testcase.setDescription(this.description);
        
        Collection<TestResult> newTestResultList = new ArrayList<TestResult>();
        newTestResultList.add(testResult);
        testcase.setTestResults(newTestResultList);
        testcase.setCriterion(test.getCriterion());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }
    
     public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }
    
     public Long getIdCriterion() {
        return idCriterion;
    }

    public void setIdCriterion(Long idCriterion) {
        this.idCriterion = idCriterion;
    }
}
