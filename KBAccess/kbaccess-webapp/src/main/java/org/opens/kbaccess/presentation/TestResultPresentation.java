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

import org.opens.kbaccess.entity.subject.TestResult;

/**
 *
 * @author bcareil
 */
public class TestResultPresentation {
    
    private Long resultId;
    private Long testId;
    
    private String testLabel;
    private String testCode;

    public TestResultPresentation() {
    }

    public TestResultPresentation(TestResult testResult) {
        this.resultId = testResult.getResult().getId();
        this.testId = testResult.getTest().getId();
        this.testLabel = testResult.getTest().getLabel();
        this.testCode = testResult.getTest().getCode();
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestLabel() {
        return testLabel;
    }

    public void setTestLabel(String testLabel) {
        this.testLabel = testLabel;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
