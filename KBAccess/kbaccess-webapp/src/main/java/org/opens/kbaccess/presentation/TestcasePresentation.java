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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.opens.kbaccess.entity.subject.TestResult;
import org.opens.kbaccess.entity.subject.Testcase;

/**
 *
 * @author bcareil
 */
public class TestcasePresentation {
    private Long authorId;
    private Long criterionId;
    private Long id;
    private Long referenceId;
    private String referenceCode;
    private Long resultId;
    private String resultCode;
    private Long webarchiveId;

    private String authorDisplayedName;
    private String description;
    private String detailsUrl;
    private String referenceLabel;
    private String referenceLabelForUrl;
    private String webarchiveLocalUrl;
    private String webarchiveOriginalUrl;
    private String criterionLabel;
    private String themeLabel;

    private Date webarchiveCreationDate;
    private Date creationDate;
    
    private Collection<TestResultPresentation> testResults;
    private String testLabel;
    private String testCode; 
    
    // Description of the test and criterion on the original website of the accessibility reference
    private String webRefTestLabel;
    private String webRefCriterionLabel;

    public static String createDetailsSubUrl(Testcase testcase) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(testcase.getId().toString());
        sb.append("-");
//        sb.append(
//                Normalizer.normalize(
//                    testcase.getTitle(),
//                    Normalizer.Form.NFD
//                ).replaceAll(
//                    "[^\\p{ASCII}]", ""
//                ).toLowerCase(
//                ).replace(
//                    ' ',
//                    '-'
//                ));
        sb.append(".html");
        return sb.toString();
    }
    
    public static List<TestcasePresentation>fromCollection(
            Collection<Testcase> testcases,
            boolean withTestResults
            ) {
        List<TestcasePresentation> presentations;
        
        presentations = new ArrayList<TestcasePresentation>(testcases.size());
        for (Testcase testcase : testcases) {
            presentations.add(new TestcasePresentation(testcase, withTestResults));
        }
        return presentations;
    }

    public TestcasePresentation() {
    }

    public TestcasePresentation(Testcase testcase, boolean withTestResults) {
        this.authorId = testcase.getAccount().getId();
        this.criterionId = testcase.getCriterion().getId();
        this.id = testcase.getId();
        this.referenceId = testcase.getCriterion().getReference().getId();
        this.referenceCode = testcase.getCriterion().getReference().getCode();
        this.resultId = testcase.getResult().getId();
        this.resultCode = testcase.getResult().getCode();
        this.webarchiveId = testcase.getWebarchive().getId();
        this.themeLabel = testcase.getCriterion().getTheme().getLabel();
        this.authorDisplayedName = AccountPresentation.generateDisplayedName(testcase.getAccount());
        this.criterionLabel = testcase.getCriterion().getLabel();
        this.description = testcase.getDescription();
        this.detailsUrl = createDetailsSubUrl(testcase);
        this.referenceLabel = testcase.getCriterion().getReference().getLabel();
        this.referenceLabelForUrl = this.referenceLabel.replaceAll("\\s", "");
        this.webarchiveLocalUrl = testcase.getWebarchive().getLocalUrl().replaceAll("/http:/", "");
        this.webarchiveOriginalUrl = testcase.getWebarchive().getUrl();
        this.criterionLabel = testcase.getCriterion().getLabel();
        this.webRefCriterionLabel = this.criterionLabel.replace(".", "-");
        this.webarchiveCreationDate = testcase.getWebarchive().getCreationDate();
        this.creationDate = testcase.getCreationDate();
        if (withTestResults) {
            this.testResults = testResultCollectionToPresentation(testcase.getTestResults());  
            
            TestResultPresentation testResult = this.testResults.iterator().next();
            this.testLabel = testResult.getTestLabel();
            this.testCode = testResult.getTestCode();
            this.webRefTestLabel = this.testLabel.replace(".", "-");
        } else {
            this.testResults = null;
        }
    }
    
    private Collection<TestResultPresentation> testResultCollectionToPresentation(Collection<TestResult> testResults) {
        Collection<TestResultPresentation> ret = new ArrayList<TestResultPresentation>(testResults.size());
        
        for (TestResult testResult : testResults) {
            ret.add(new TestResultPresentation(testResult));
        }
        return ret;
    }

    public String getAuthorDisplayedName() {
        return authorDisplayedName;
    }

    public void setAuthorDisplayedName(String authorDisplayedName) {
        this.authorDisplayedName = authorDisplayedName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Long getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Long criterionId) {
        this.criterionId = criterionId;
    }

    public String getCriterionLabel() {
        return criterionLabel;
    }

    public void setCriterionLabel(String criterionLabel) {
        this.criterionLabel = criterionLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getReferenceLabel() {
        return referenceLabel;
    }

    public void setReferenceLabel(String referenceLabel) {
        this.referenceLabel = referenceLabel;
    }

    public Long getResultId() {
        return resultId;
    }
    
    public String getResultCode() {
        return resultCode;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Collection<TestResultPresentation> getTestResults() {
        return testResults;
    }
    
    public String getTestResultLabel() {
        return testResults.iterator().next().getTestLabel();
    }

    public void setTestResults(Collection<TestResultPresentation> testResults) {
        this.testResults = testResults;
    }

    public Date getWebarchiveCreationDate() {
        return webarchiveCreationDate;
    }

    public void setWebarchiveCreationDate(Date webarchiveCreationDate) {
        this.webarchiveCreationDate = webarchiveCreationDate;
    }

    public Long getWebarchiveId() {
        return webarchiveId;
    }

    public void setWebarchiveId(Long webarchiveId) {
        this.webarchiveId = webarchiveId;
    }

    public String getWebarchiveLocalUrl() {
        return webarchiveLocalUrl;
    }

    public void setWebarchiveLocalUrl(String webarchiveLocalUrl) {
        this.webarchiveLocalUrl = webarchiveLocalUrl;
    }

    public String getWebarchiveOriginalUrl() {
        return webarchiveOriginalUrl;
    }

    public void setWebarchiveOriginalUrl(String webarchiveOriginalUrl) {
        this.webarchiveOriginalUrl = webarchiveOriginalUrl;
    }
    
     public String getTestLabel() {
        return testLabel;
    }

    public void setTestLabel(String testLabel) {
        this.testLabel = testLabel;
    }
    
     public String getWebRefTestLabel() {
        return webRefTestLabel;
    }

    public void setWebRefTestLabel(String webRefTestLabel) {
        this.webRefTestLabel = webRefTestLabel;
    }
    
    public String getWebRefCriterionLabel() {
        return webRefCriterionLabel;
    }

    public void setWebRefCriterionLabel(String webRefCriterionLabel) {
        this.webRefCriterionLabel = webRefCriterionLabel;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceLabelForUrl() {
        return referenceLabelForUrl;
    }

    public void setReferenceLabelForUrl(String referenceLabelForUrl) {
        this.referenceLabelForUrl = referenceLabelForUrl;
    }

    public String getThemeLabel() {
        return themeLabel;
    }

    public void setThemeLabel(String themeLabel) {
        this.themeLabel = themeLabel;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
