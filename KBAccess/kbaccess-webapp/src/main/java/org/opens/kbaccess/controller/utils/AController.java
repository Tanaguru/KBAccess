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
package org.opens.kbaccess.controller.utils;

import java.util.*;
import org.opens.kbaccess.command.TestcaseSearchCommand;
import org.opens.kbaccess.command.UserLoginCommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.reference.*;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 *
 * @author bcareil
 */
public abstract class AController {

    private Collection<Result> resultCollection = null; 
    
    @Autowired
    protected AccountDataService accountDataService;
    @Autowired
    protected TestcaseDataService testcaseDataService;
    @Autowired
    protected ReferenceDataService referenceDataService;
    @Autowired
    protected ReferenceTestDataService referenceTestDataService;
    @Autowired
    protected ReferenceInfoDataService referenceInfoDataService;
    @Autowired
    protected ReferenceLevelDataService referenceLevelDataService;
    @Autowired
    protected ResultDataService resultDataService;
    @Autowired
    protected ReferenceDepthDataService referenceDepthDataService;
        
    /*
     * utility methods
     */
    public void handleUserLoginForm(Model model) {
        Account account = AccountUtils.getInstance().getCurrentUser();
        
        if (account == null) {
            model.addAttribute(
                    ModelAttributeKeyStore.USER_LOGIN_COMMAND_KEY,
                    new UserLoginCommand()
                    );
        } else {
            model.addAttribute(
                    ModelAttributeKeyStore.AUTHENTICATED_USER_KEY,
                    account
                    );
        }
    }
    
    public void handleBreadcrumbTrail(Model model) {
        Map<String, String> breadcrumb = new LinkedHashMap<String, String>();        

            model.addAttribute(
                    ModelAttributeKeyStore.BREADCRUMB_TRAIL_KEY,
                    breadcrumb
                    );
    }
    
    public void handleTestcaseSearchForm(Model model) {
        handleTestcaseSearchForm(model, new TestcaseSearchCommand(referenceDataService));
    }
    
    public void handleTestcaseSearchForm(Model model, TestcaseSearchCommand testcaseSearchCommand) {
        model.addAttribute("testcaseSearchCommand", testcaseSearchCommand);
        model.addAttribute("referenceList", referenceDataService.findAll());
        model.addAttribute("resultList", resultDataService.findAll());
        
        model.addAttribute("referenceTestMap", referenceTestDataService.getInternMapByDepth());
        model.addAttribute("referenceInfoMap", referenceInfoDataService.getInternMapByDepth());
        model.addAttribute("referenceLevelMap", referenceLevelDataService.getInternMap());
    }
    
    /*
     * Accessors
     */
    
    public Map<Reference, Map<ReferenceDepth, Set<ReferenceTest>>> getMapReferenceTestByReference() {
        return referenceTestDataService.getInternMapByDepth();
    }
    
    public Collection<Result> getResultCollection() {
        if (resultCollection == null || resultCollection.isEmpty()) {
            resultCollection = (Collection)resultDataService.findAll();
        }
        
        return resultCollection;
    }

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

    public TestcaseDataService getTestcaseDataService() {
        return testcaseDataService;
    }

    public void setTestcaseDataService(TestcaseDataService testcaseDataService) {
        this.testcaseDataService = testcaseDataService;
    }

    public ReferenceDataService getReferenceDataService() {
        return referenceDataService;
    }

    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    public ReferenceTestDataService getReferenceTestDataService() {
        return referenceTestDataService;
    }

    public void setReferenceTestDataService(ReferenceTestDataService referenceTestDataService) {
        this.referenceTestDataService = referenceTestDataService;
    }

    public ReferenceInfoDataService getReferenceInfoDataService() {
        return referenceInfoDataService;
    }

    public void setReferenceInfoDataService(ReferenceInfoDataService referenceInfoDataService) {
        this.referenceInfoDataService = referenceInfoDataService;
    }

    public ReferenceLevelDataService getReferenceLevelDataService() {
        return referenceLevelDataService;
    }

    public void setReferenceLevelDataService(ReferenceLevelDataService referenceLevelDataService) {
        this.referenceLevelDataService = referenceLevelDataService;
    }

    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }

    public ReferenceDepthDataService getReferenceDepthDataService() {
        return referenceDepthDataService;
    }

    public void setReferenceDepthDataService(ReferenceDepthDataService referenceDepthDataService) {
        this.referenceDepthDataService = referenceDepthDataService;
    }
}
