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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.apache.commons.lang.StringEscapeUtils;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 *
 * @author blebail
 */
public class TestcasePresentation {
        
    private Long testcaseId;
    private Long accountId;
    private Long testId;
    private Long referenceId;
    private Long resultId;

    private Set<ReferenceTest> testParents;
    
    private String testCode;
    private String testDepthCode;
    private String testWebRefCode;
    private String referenceCode;
    private String referenceWebRefCode;
    private String resultCode;
    
    private String testLabel;
    private String referenceLabel;
    private String referenceLabelForUrl;
    
    private String accountDisplayedName;
    private String description;
    private String webarchiveLocalUrl;
    private String webarchiveOriginalUrl;
    
    /* 
     * protocol removed, only the domain, and "..." if there is more after
     * i.e : 
     *  http://www.google.com becomes "www.google.com"
     *  https://www.cdiscount.com/articles/laptops/index.php?lang=ru becomes "www.cdiscount.com/..."
     */
    private String webarchiveDisplayedUrl;
    
    
    private Date creationDate;
    private Date webarchiveCreationDate;
        
    public TestcasePresentation(
            Testcase testcase, 
            ReferenceTestDataService referenceTestDataService) {
        
        Account account = testcase.getAccount();
        Webarchive webarchive = testcase.getWebarchive();
        Result result = testcase.getResult();
        ReferenceTest referenceTest = referenceTestDataService.getByCode(testcase.getReferenceTest().getCode());
        Reference reference = referenceTestDataService.getReferenceOf(referenceTest);
        
        this.testcaseId = testcase.getId();
        this.accountId = account.getId();
        this.testId = referenceTest.getId();
        
        this.testParents = new TreeSet<ReferenceTest>(referenceTest.getParents());
        
        this.referenceId = reference.getId();
        this.resultId = testcase.getResult().getId();

        this.testCode = referenceTest.getCode();
        this.testDepthCode = referenceTest.getReferenceDepth().getCode();
        this.testWebRefCode = referenceTest.getCode() + "-url";
        this.referenceCode = reference.getCode();
        this.referenceWebRefCode = reference.getCode() + "-url";
        this.resultCode = result.getCode();

        this.testLabel = referenceTest.getLabel();
        this.referenceLabel = reference.getLabel();
        this.referenceLabelForUrl = this.referenceLabel.replaceAll("\\s", "");

        this.accountDisplayedName = AccountPresentation.generateDisplayedName(testcase.getAccount());
        this.description =  StringEscapeUtils.escapeHtml(testcase.getDescription());
        this.webarchiveLocalUrl = webarchive.getLocalUrl().replaceAll("/http:/", "");
        this.webarchiveOriginalUrl = webarchive.getUrl();
        
        try {
            URI uri = new URI(this.webarchiveOriginalUrl);
            this.webarchiveDisplayedUrl = uri.getHost();
            if (uri.getPath().length() > 1) {
                this.webarchiveDisplayedUrl = this.webarchiveDisplayedUrl.concat("/...");
            }
        } catch (URISyntaxException e) {
            Logger.getLogger(TestcasePresentation.class.getName()).info(e.getMessage());
        }
        
        this.creationDate = testcase.getCreationDate();
        this.webarchiveCreationDate = webarchive.getCreationDate();
    }

    /*
     * Accessors
     */
    public Long getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(Long testcaseId) {
        this.testcaseId = testcaseId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestDepthCode() {
        return testDepthCode;
    }

    public void setTestDepthCode(String testDepthCode) {
        this.testDepthCode = testDepthCode;
    }

    public Set<ReferenceTest> getTestParents() {
        return testParents;
    }

    public void setTestParents(Set<ReferenceTest> testParents) {
        this.testParents = testParents;
    }
    
    public String getTestWebRefCode() {
        return testWebRefCode;
    }

    public void setTestWebRefCode(String testWebRefCode) {
        this.testWebRefCode = testWebRefCode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getReferenceWebRefCode() {
        return referenceWebRefCode;
    }

    public void setReferenceWebRefCode(String referenceWebRefCode) {
        this.referenceWebRefCode = referenceWebRefCode;
    }
    
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getTestLabel() {
        return testLabel;
    }

    public void setTestLabel(String testLabel) {
        this.testLabel = testLabel;
    }

    public String getReferenceLabel() {
        return referenceLabel;
    }

    public void setReferenceLabel(String referenceLabel) {
        this.referenceLabel = referenceLabel;
    }

    public String getReferenceLabelForUrl() {
        return referenceLabelForUrl;
    }

    public void setReferenceLabelForUrl(String referenceLabelForUrl) {
        this.referenceLabelForUrl = referenceLabelForUrl;
    }

    public String getAccountDisplayedName() {
        return accountDisplayedName;
    }

    public void setAccountDisplayedName(String accountDisplayedName) {
        this.accountDisplayedName = accountDisplayedName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getWebarchiveDisplayedUrl() {
        return webarchiveDisplayedUrl;
    }

    public void setWebarchiveDisplayedUrl(String webarchiveDisplayedUrl) {
        this.webarchiveDisplayedUrl = webarchiveDisplayedUrl;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getWebarchiveCreationDate() {
        return webarchiveCreationDate;
    }

    public void setWebarchiveCreationDate(Date webarchiveCreationDate) {
        this.webarchiveCreationDate = webarchiveCreationDate;
    }
}
