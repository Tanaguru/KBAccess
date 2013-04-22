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
package org.opens.kbaccess.controller.utils;

import java.util.*;
import org.opens.kbaccess.command.TestcaseSearchCommand;
import org.opens.kbaccess.command.UserLoginCommand;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.reference.*;
import org.opens.kbaccess.entity.service.subject.TestResultDataService;
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

    private static Collection<Reference> references;
    private static Set<Map.Entry<Reference, Collection<Criterion>>> criterionByRef;
    private static Set<Map.Entry<Reference, Collection<Level>>> levelByRef;
    private static Set<Map.Entry<Reference, Collection<Test>>> testByRef;
    private static Set<Map.Entry<Reference, Collection<Theme>>> themeByRef;
    private static Collection<Result> results;
    
    @Autowired
    protected AccountDataService accountDataService;
    @Autowired
    protected TestcaseDataService testcaseDataService;
    @Autowired
    protected ReferenceDataService referenceDataService;
    @Autowired
    protected CriterionDataService criterionDataService;
    @Autowired
    protected ThemeDataService themeDataService;
    @Autowired
    protected TestDataService testDataService;
    @Autowired
    protected LevelDataService levelDataService;
    @Autowired
    protected ResultDataService resultDataService;
    @Autowired
    protected TestResultDataService testresultDataservice;
        
    /*
     * private methods
     */
    
    private synchronized void initTestcaseSearchFormFields() {
        if (references == null &&
                criterionByRef == null &&
                levelByRef == null &&
                testByRef == null &&
                themeByRef == null &&
                results == null) {
            Map<Reference, Collection<Theme>> themeByRefMap = new HashMap<Reference, Collection<Theme>>();
            Map<Reference, Collection<Criterion>> criterionByRefMap = new HashMap<Reference, Collection<Criterion>>();
            Map<Reference, Collection<Test>> testByRefMap = new HashMap<Reference, Collection<Test>>();
            Map<Reference, Collection<Level>> levelByRefMap = new HashMap<Reference, Collection<Level>>();

            results = (Collection) resultDataService.findAll();
            references = (Collection) referenceDataService.findAll();
            for (Reference reference : references) {
                themeByRefMap.put(reference, themeDataService.getThemeListFromReference(reference));
                criterionByRefMap.put(reference, criterionDataService.getCriterionListFromReference(reference));
                testByRefMap.put(reference, testDataService.getTestListFromReference(reference));
                // FIXME
                levelByRefMap.put(reference, (Collection) levelDataService.findAll());
            }
            criterionByRef = criterionByRefMap.entrySet();
            levelByRef = levelByRefMap.entrySet();
            testByRef = testByRefMap.entrySet();
            themeByRef = themeByRefMap.entrySet();
        }
    }
    
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
    
    /**
     * Build a data structure for the breadcrumb-trail.jspf.
     * 
     * The argument has to be of the form
     * (label, url, label, url, label)
     * For exemple :
     * ("KBAccess", "/", "Testcase Search", "/testcase/search.html", "Results")
     * 
     * @param list
     * @return 
     */
    public List<Map.Entry<String, String>> buildBreadcrumbTrail(String ... list) {
       List<Map.Entry<String, String>> ret;
       
       ret = new ArrayList<Map.Entry<String, String>>(list.length / 2 + 1);
       for (int i = 0; i < list.length; i += 2) {
           Map.Entry<String, String> entry;
           
           entry = new AbstractMap.SimpleEntry<String, String>(
                   list[i],
                   (i + 1 < list.length ? list[i + 1] : null)
                   );
           ret.add(entry);
       }
       return ret;
    }
    
    public void handleBreadcrumbTrail(Model model, String ... breadcrumb ) {
        model.addAttribute(
                ModelAttributeKeyStore.BREADCRUMB_TRAIL_KEY,
                buildBreadcrumbTrail(breadcrumb)
                );
    }
    
    public void handleTestcaseSearchForm(Model model) {
        handleTestcaseSearchForm(model, new TestcaseSearchCommand());
    }
    
    public void handleTestcaseSearchForm(Model model, TestcaseSearchCommand testcaseSearchCommand) {
        initTestcaseSearchFormFields();
        
        model.addAttribute("testcaseSearchCommand", testcaseSearchCommand);
        model.addAttribute("referenceList", references);
        model.addAttribute("themeByRef", themeByRef);
        model.addAttribute("criterionByRef", criterionByRef);
        model.addAttribute("testByRef", testByRef);
        model.addAttribute("levelByRef", levelByRef);
        model.addAttribute("resultList", results);
    }
    
    /*
     * properties
     */
    
    public Set<Map.Entry<Reference, Collection<Test>>> getTestByRef() {
        initTestcaseSearchFormFields();
        
        return testByRef;
    }
    
    public Collection<Result> getResults() {
        initTestcaseSearchFormFields();

        return results;
    }
    
    /*
     * data service accessors
     */

    public CriterionDataService getCriterionDataService() {
        return criterionDataService;
    }

    public void setCriterionDataService(CriterionDataService criterionDataService) {
        this.criterionDataService = criterionDataService;
    }

    public LevelDataService getLevelDataService() {
        return levelDataService;
    }

    public void setLevelDataService(LevelDataService levelDataService) {
        this.levelDataService = levelDataService;
    }

    public ReferenceDataService getReferenceDataService() {
        return referenceDataService;
    }

    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }

    public TestDataService getTestDataService() {
        return testDataService;
    }

    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    public TestcaseDataService getTestcaseDataService() {
        return testcaseDataService;
    }

    public void setTestcaseDataService(TestcaseDataService testcaseDataService) {
        this.testcaseDataService = testcaseDataService;
    }

    public ThemeDataService getThemeDataService() {
        return themeDataService;
    }

    public void setThemeDataService(ThemeDataService themeDataService) {
        this.themeDataService = themeDataService;
    }
 
}
