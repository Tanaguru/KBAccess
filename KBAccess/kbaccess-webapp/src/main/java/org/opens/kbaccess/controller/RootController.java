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
package org.opens.kbaccess.controller;

import java.util.Collection;
import java.util.Iterator;
import org.opens.kbaccess.controller.utils.AController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.AccountPresentation;
import org.opens.kbaccess.presentation.StatisticsPresentation;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.presentation.factory.StatisticsPresentationFactory;
import org.opens.kbaccess.presentation.factory.TestcasePresentationFactory;
import org.opens.kbaccess.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/")
public class RootController extends AController {
    
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    @Autowired
    private StatisticsDataService statisticsDataService;
    @Autowired
    private StatisticsPresentationFactory statisticsPresentationFactory;
    @Autowired
    private TestcasePresentationFactory testcasePresentationFactory;
    
    private static final int NB_TESTCASES_DISPLAYED = 10;
    
    /*
     * Private methods
     */
        private String getAndDisplayTestcasesFromUrlSearch(Model model, String codeRef, String codeTest, String codeResult) {
            Collection<TestcasePresentation> testcases = null;
            Reference reference = null;
            ReferenceTest test = null;
            Result result = null;
            StringBuilder errorMessage = new StringBuilder();

            // Reference
            model.addAttribute("codeRef", codeRef);
            reference = referenceDataService.getByCode(codeRef);
            
            if (reference == null) {
                errorMessage.append("Le référentiel ").append(codeRef).append(" n'existe pas. ");
            }


            // Test
            if (codeTest != null) {
                model.addAttribute("codeTest", codeTest);
                test = referenceTestDataService.getByLabelAndReferenceCode(codeTest, codeRef);

                if (test == null) {
                    errorMessage.append("Le test ").append(codeTest).append("(").append(codeRef).append(") n'existe pas. ");
                }
            } else {
                model.addAttribute("codeTest", "Tous");
            }

            // Result
            // FIX : internationalisation
            if (codeResult != null) {
                model.addAttribute("codeResult", codeResult);

                if (codeResult.equals("Passed")) {
                    codeResult = "passed";
                }

                if (codeResult.equals("Failed")) {
                    codeResult = "failed";
                }

                result = resultDataService.getByCode(codeResult);
                if (result == null) {
                    errorMessage.append("Le résultat ").append(codeResult).append(" n'existe pas. ");
                }
            } else {
                model.addAttribute("codeResult", "Tous");
            }

            if (errorMessage.length() != 0) {
                model.addAttribute("errorMessage", errorMessage.toString());
            } else {
                Collection<ReferenceTest> referenceTestList = null;
                
                if (test != null) {
                    referenceTestList = referenceTestDataService.getReferenceTestWithAllChildren(
                                            test, 
                                            null, 
                                            result);
                } else {
                    referenceTestList = referenceTestDataService.getAllByReference(reference);
                }
                
                // testcases fetch
                testcases = testcasePresentationFactory.createFromCollection(
                        (Collection<Testcase>)testcaseDataService.getAllFromUserSelection(
                            referenceTestList, 
                            result)
                        );
            }
            // result list
            model.addAttribute(ModelAttributeKeyStore.TESTCASE_LIST_KEY, testcases);
            return "testcase/url-search-result";
        }
    /*
     * Endpoints
     */

    @RequestMapping(value = {"index"})
    public String homeHandler(Model model) {
        handleUserLoginForm(model);
        //handleTestcaseSearchForm(model);

        StatisticsPresentation statisticsPresentation = statisticsPresentationFactory.create();

        // Generate a displayable name for most active contributors
        for (Iterator it = statisticsPresentation.getBestContributors().iterator(); it.hasNext();) {
            AccountStatistics accountStatistics = (AccountStatistics) it.next();
            Account account = accountDataService.read(accountStatistics.getId());

            accountStatistics.setDisplayedName(AccountPresentation.generateDisplayedName(account));
        }

        model.addAttribute(
                ModelAttributeKeyStore.STATISTICS_KEY,
                statisticsPresentation);
        model.addAttribute(
                ModelAttributeKeyStore.TESTCASE_LIST_KEY,
                testcasePresentationFactory.createFromCollection(
                    testcaseDataService.getLastTestcases(NB_TESTCASES_DISPLAYED)
                )
            );
        
        model.addAttribute("referenceCoverageList", referenceDataService.findAll());

        return "home";
    }
    
    @RequestMapping("contribute")
    public String contributeHandler(Model model) {
        Account account = AccountUtils.getInstance().getCurrentUser();
        
        if (account == null) {
            return "forward:/guest/subscribe.html";
        } else {
            return "forward:/example/add.html";
        }
    }
    
    @RequestMapping("contact")
    public String contactHandler(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        return "contact";
    }

    @RequestMapping("legal")
    public String legalHandler(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        return "legal";
    }
    
    /*
     * Search by url
     * Only 3 patterns
     */
    @RequestMapping(value="{ref}/{testOrResult}/")
    public String searchByRefAndTest(
            @PathVariable("ref") String codeRef,
            @PathVariable("testOrResult") String codeTestOrResult,
            Model model
            ) {
        
        // if the code contains a number, then it's a test code and not a result code
        boolean isATestCode = codeTestOrResult.matches(".*\\d.*");
        
        if (isATestCode) {
            return getAndDisplayTestcasesFromUrlSearch(model, codeRef, codeTestOrResult, null);
        } else {
            return getAndDisplayTestcasesFromUrlSearch(model, codeRef, null, codeTestOrResult);
        }
    }
     
    @RequestMapping(value="{ref}/{test}/{result}/")
    public String searchByRefAndTestAndResult(
            @PathVariable("ref") String codeRef,
            @PathVariable("test") String codeTest,
            @PathVariable("result") String codeResult,
            Model model
            ) {
        
        return getAndDisplayTestcasesFromUrlSearch(model, codeRef, codeTest, codeResult);
    }
    
    /*
     * Accessors
     */
    public StatisticsDataService getStatisticsDataService() {
        return statisticsDataService;
    }

    public void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }

    public WebarchiveDataService getWebarchiveDataService() {
        return webarchiveDataService;
    }

    public void setWebarchiveDataService(WebarchiveDataService webarchiveDataService) {
        this.webarchiveDataService = webarchiveDataService;
    }

    public StatisticsPresentationFactory getStatisticsPresentationFactory() {
        return statisticsPresentationFactory;
    }

    public void setStatisticsPresentationFactory(StatisticsPresentationFactory statisticsPresentationFactory) {
        this.statisticsPresentationFactory = statisticsPresentationFactory;
    }

    public TestcasePresentationFactory getTestcasePresentationFactory() {
        return testcasePresentationFactory;
    }

    public void setTestcasePresentationFactory(TestcasePresentationFactory testcasePresentationFactory) {
        this.testcasePresentationFactory = testcasePresentationFactory;
    }
}
