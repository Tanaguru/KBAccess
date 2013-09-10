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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.opens.kbaccess.command.DeleteTestcaseCommand;
import org.opens.kbaccess.command.EditTestcaseCommand;
import org.opens.kbaccess.command.NewTestcaseCommand;
import org.opens.kbaccess.command.SelectReferenceCommand;
import org.opens.kbaccess.command.TestcaseSearchCommand;
import org.opens.kbaccess.controller.utils.AMailerController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.MessageKeyStore;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.AccountPresentation;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.presentation.factory.TestcasePresentationFactory;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.EditTestcaseValidator;
import org.opens.kbaccess.validator.NewTestcaseValidator;
import org.opens.kbaccess.validator.SelectReferenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/example/")
public class TestcaseController extends AMailerController {
    
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    @Autowired
    private WebarchiveController webarchiveController;
    @Autowired
    private TestcasePresentationFactory testcasePresentationFactory;
    
    /*
     * Private methods
     */
    
    /*
     * Returns the RefComponent with the lowest depth from a list of RefComponent
     * i.e : for a list containing :
     *  - a ReferenceInfo with depth +1
     *  - a ReferenceTest with depth -1
     *  - a ReferenceTest with depth -2
     * 
     * returns the ReferenceTest with depth -2
     */
    private RefComponentWithDepth getMinDepthRefComponentFromList(List<RefComponentWithDepth> refComponentList) {
        if (refComponentList == null || refComponentList.isEmpty()) {
            return null;
        } 
        
        RefComponentWithDepth minDepthRefComponent = null;
        int minDepth = Integer.MAX_VALUE;

        for (RefComponentWithDepth refComponent : refComponentList) {
            int depth = refComponent.getReferenceDepth().getDepth();
            
            if (depth < minDepth) {
                minDepth = depth;
                minDepthRefComponent = refComponent;
            }
        }
        
        return minDepthRefComponent;
    } 
    
    /*
     * Build a map that helps to make the title and breadcrumb of thelt of a search 
     * based on the parameters of the search
     */
    private Map<String, String> buildMapFromSearchParameters(
            Reference reference,
            RefComponentWithDepth minDepthRefComponent,
            ReferenceLevel referenceLevel,
            Result result
            ) {
        Map<String, String> parametersMap = new LinkedHashMap<String, String>();
        
        // building parameters list
        if (reference != null) {
            parametersMap.put("accessibility.reference", reference.getCode());
        }
        
        if (minDepthRefComponent != null) {
            parametersMap.put("accessibility.testDepth", minDepthRefComponent.getReferenceDepth().getCode());
            
            if (minDepthRefComponent instanceof ReferenceInfoImpl) {
                parametersMap.put("accessibility.infoLabel", minDepthRefComponent.getCode());
            } else if (minDepthRefComponent instanceof ReferenceTestImpl) {
                parametersMap.put("accessibility.testLabel", minDepthRefComponent.getLabel());
            }
        }
        
        if (referenceLevel != null) {
            parametersMap.put("accessibility.level", referenceLevel.getCode());
        }
        
        if (result != null) {
            parametersMap.put("result", result.getCode());
        }
        
        // no parameters
        if (parametersMap.isEmpty()) {
            parametersMap.put("testcase.searchAllTestcasesTitle", "allExamples");
        } 
        
        return parametersMap;
    }
    
    private String displaySelectReferenceForm(Model model, SelectReferenceCommand selectReferenceCommand) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // create the form command
        model.addAttribute("referenceList", referenceDataService.findAll());
        model.addAttribute("selectReferenceCommand", selectReferenceCommand);
        return "testcase/add";
    }
    
    private String displayAddTestcaseForm(Model model, NewTestcaseCommand newTestcaseCommand) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // Fetch the selected reference
        Reference reference = referenceDataService.read(newTestcaseCommand.getIdReference());
        
        // set the default test depth selected to the lowest
        // AW21 : test, Rgaa22 : test, WCAG20 : technique
        ReferenceDepth referenceDepth = referenceDepthDataService.getByReferenceAndDepth(reference, reference.getTestMaxDepth());
        newTestcaseCommand.setIdReferenceDepth(referenceDepth.getId());
        
        model.addAttribute("referenceTestMap", referenceTestDataService.getInternMapByDepth().get(reference));
        model.addAttribute("resultList", getResultCollection());
        model.addAttribute("newTestcaseCommand", newTestcaseCommand);
        return "testcase/add-details";
    }
    
    private String displayAttachWebarchiveForm(Model model, NewTestcaseCommand newTestcaseCommand) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        // create the form command
        model.addAttribute("webarchiveList", webarchiveDataService.findAll());
        model.addAttribute("newTestcaseCommand", newTestcaseCommand);
        return "testcase/add-webarchive";        
    }
    
    private Collection<ReferenceTest> getReferenceTestListOfTestcaseTestScope(ReferenceTest testcaseTest) {
        return referenceTestDataService.getAllByReferenceAndReferenceDepth(
                referenceTestDataService.getReferenceOf(testcaseTest), 
                testcaseTest.getReferenceDepth()
                );
    }
    
    private String displayEditTestcaseForm(
            Model model, 
            EditTestcaseCommand editTestcaseCommand, 
            ReferenceTest testcaseTest) {
        
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // create form
        model.addAttribute("editTestcaseCommand", editTestcaseCommand);
        /*
         * On a testcase we can edit :
         * - the referenceTest but only on the same reference and referenceDepth
         *   Indeed changing the reference or the referenceDepth of the referenceTest is not considered as an edition
         *   We consider that the user would delete the testcase and create a new one
         * - the result
         */
        model.addAttribute("referenceTestList", getReferenceTestListOfTestcaseTestScope(testcaseTest));
        model.addAttribute("resultList", getResultCollection());
        
        return "testcase/edit-details";
    }
    
    private String displayTestcaseDetails(Model model, Testcase testcase) {
       TestcasePresentation testcasePresentation = testcasePresentationFactory.create(testcase);
       
        // handle form login
        handleUserLoginForm(model);
        // handle breadcrumb
        handleBreadcrumbTrail(model);
        model.addAttribute("testcase", testcasePresentation);
        return "testcase/details";
    }
    
    private String displayTestcaseError(Model model, String errorMessage) {
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // create form    
        model.addAttribute("errorMessage", errorMessage);
        return "testcase/error";
    }
    
    @RequestMapping(value="search-by-url")
    public String searchByUrlHandler(Model model) {
        // handle login form and breadcrumb trail
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        return "testcase/search-by-url";
    }
    
    @RequestMapping(value={"search"}, method=RequestMethod.GET)
    public String searchHandler(Model model) {
        // handle login form, breadcrumb and search form
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        handleTestcaseSearchForm(model);
        
        return "testcase/search";
    }
    
    @RequestMapping(value="result", method=RequestMethod.GET)
    public String resultHandler(
            @RequestParam(value="account", required=false) Long idAccount,
            @RequestParam(value="reference", required=false) Long idReference,
            @RequestParam(value="test", required=false) Long idTest,
            Model model
            ) {
        Logger.getLogger(TestcaseController.class.getName()).info("list(1)");
        TestcaseSearchCommand testcaseSearchCommand = new TestcaseSearchCommand();
        
        if (idAccount != null && accountDataService.read(idAccount) != null) {
            Logger.getLogger(TestcaseController.class.getName()).info("idAccount != null");
            testcaseSearchCommand.setIdAccount(idAccount);
        } else if (idReference != null &&  referenceDataService.read(idReference) != null) {
            Logger.getLogger(TestcaseController.class.getName()).info("idReference != null");
            testcaseSearchCommand.setCodeReference(referenceDataService.read(idReference).getCode());
        } else if (idTest != null && referenceTestDataService.read(idTest) != null) {
            Logger.getLogger(TestcaseController.class.getName()).info("idTest != null");
            ReferenceTest referenceTest = referenceTestDataService.read(idTest);
            List<Long> referenceTests = new ArrayList<Long>();
            referenceTests.add(idTest);
            Reference reference = referenceTestDataService.getReferenceOf(referenceTest);
            
            testcaseSearchCommand.setCodeReference(reference.getCode());
            testcaseSearchCommand.setIdReferenceInfoList(new ArrayList<Long>());
            testcaseSearchCommand.setIdReferenceTestList(referenceTests);
            testcaseSearchCommand.setSearchSingleTest(true);
        } else {
            return "forward:/index.html";
        }
        
        return listHandler(testcaseSearchCommand, null, model);
    }
    
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String listHandler(
            @ModelAttribute("testcaseSearchCommand") TestcaseSearchCommand testcaseSearchCommand,
            BindingResult bindingResult,
            Model model
            ) {
        Collection<TestcasePresentation> testcases;
        String contextOfRequest = null;
        Reference reference;
        ReferenceLevel referenceLevel;
        Result result;

        // fetch entities and set title and H1
        if (testcaseSearchCommand.searchAll()) {
            LogFactory.getLog(TestcaseController.class.getName()).debug("searchAll()");
            testcases = testcasePresentationFactory.createFromCollection(
                    (Collection) testcaseDataService.findAll()
                    );
        contextOfRequest = "allTestcases";
        handleBreadcrumbTrail(model);
        // fetch the testcases of a precise user
        } else if (testcaseSearchCommand.searchAccount()) {
            LogFactory.getLog(TestcaseController.class.getName()).debug("searchAccount()");
            Account account = accountDataService.read(testcaseSearchCommand.getIdAccount());
            
            testcases = testcasePresentationFactory.createFromCollection(
                testcaseDataService.getAllFromAccount(account)
                );
            
            contextOfRequest = "userTestcases";
            handleBreadcrumbTrail(model);
            model.addAttribute("account", new AccountPresentation(account, accountDataService));
        } else if (testcaseSearchCommand.searchReferenceOrResult()) {
            LogFactory.getLog(TestcaseController.class.getName()).debug("searchReferenceOrResult()");
            
            result = (
                        testcaseSearchCommand.getIdResult() == null ? null 
                        : resultDataService.read(testcaseSearchCommand.getIdResult())
                    );
            
            reference = (
                        testcaseSearchCommand.getCodeReference() == null ? null 
                        : referenceDataService.getByCode(testcaseSearchCommand.getCodeReference())
                    );
            
            testcases = testcasePresentationFactory.createFromCollection(
                    testcaseDataService.getAllFromUserSelection(
                        referenceTestDataService.getAllByReference(reference),
                        result
                        )
                    );
            
            handleBreadcrumbTrail(model);
            model.addAttribute("parameterMap", buildMapFromSearchParameters(reference, null, null, result));
        } else {
            LogFactory.getLog(TestcaseController.class.getName()).debug("otherCombinations()");
            // All other requests combinations
            
            List<RefComponentWithDepth> referenceInfosAndReferenceTests = new ArrayList<RefComponentWithDepth>();
            List<ReferenceInfo> referenceInfos = new ArrayList<ReferenceInfo>();
            List<ReferenceTest> referenceTests = new ArrayList<ReferenceTest>();
            
            for (Long idReferenceInfo : testcaseSearchCommand.getIdReferenceInfoList()) {
                if (idReferenceInfo != null) {
                    referenceInfos.add(idReferenceInfo == null ? null : referenceInfoDataService.findById(idReferenceInfo));
                    referenceInfosAndReferenceTests.add(idReferenceInfo == null ? null : referenceInfoDataService.findById(idReferenceInfo));
                }
            }
            Logger.getLogger(TestcaseController.class.getName()).debug("referenceInfosAndReferenceTests : " + referenceInfosAndReferenceTests);
            
            for (Long idReferenceTest : testcaseSearchCommand.getIdReferenceTestList()) {
                if (idReferenceTest != null) {
                    referenceTests.add(idReferenceTest == null ? null : referenceTestDataService.findById(idReferenceTest));
                    referenceInfosAndReferenceTests.add(idReferenceTest == null ? null : referenceTestDataService.findById(idReferenceTest));
                }
            }
            Logger.getLogger(TestcaseController.class.getName()).debug("referenceInfosAndReferenceTests : " + referenceInfosAndReferenceTests);
            
            // We get the selected component with the minimum depth
            RefComponentWithDepth minDepthRefComponent = getMinDepthRefComponentFromList(referenceInfosAndReferenceTests);
            Logger.getLogger(TestcaseController.class.getName()).debug("minDepthRefComponent : " + minDepthRefComponent);
            
            reference = (
                        testcaseSearchCommand.getCodeReference() == null ? null 
                        : referenceDataService.getByCode(testcaseSearchCommand.getCodeReference())
                    );
            
            referenceLevel = (
                        testcaseSearchCommand.getIdReferenceLevel() == null ? null 
                        : referenceLevelDataService.read(testcaseSearchCommand.getIdReferenceLevel())
                    );
            
            result = (
                        testcaseSearchCommand.getIdResult() == null ? null 
                        : resultDataService.read(testcaseSearchCommand.getIdResult())
                    );
            
            // 
            List<ReferenceTest> referenceTestScope = null;
            
            // Depending on parameters, decides which referenceTests must be in the search scope
            if (reference == null) {
                referenceTestScope = (List)referenceTestDataService.findAll();
            } else {
                if (minDepthRefComponent instanceof ReferenceInfoImpl) {
                    Logger.getLogger(TestcaseController.class.getName()).debug("instanceof ReferenceInfoImpl");
                    
                    referenceTestScope = (List)referenceInfoDataService.getAllReferenceTestsOfReferenceInfo(
                            (ReferenceInfoImpl)minDepthRefComponent, 
                            referenceLevel, 
                            result);
                    
                } else if (minDepthRefComponent instanceof ReferenceTestImpl) {
                    Logger.getLogger(TestcaseController.class.getName()).debug("instanceof ReferenceTestImpl");
                    if (testcaseSearchCommand.isSearchSingleTest()) {
                        referenceTestScope = (List)referenceTestDataService.getReferenceTestWithAllChildren(
                                (ReferenceTestImpl)minDepthRefComponent, 
                                null, 
                                null);
                    } else {
                        referenceTestScope = (List)referenceTestDataService.getReferenceTestWithChildren(
                                (ReferenceTestImpl)minDepthRefComponent, 
                                referenceLevel, 
                                result);
                    }
                }
            }
            
            Logger.getLogger(TestcaseController.class.getName()).debug("referenceTestScope : " + referenceTestScope);
            testcases = testcasePresentationFactory.createFromCollection(
                    testcaseDataService.getAllFromUserSelection(referenceTestScope, result)
                    );
            
            handleBreadcrumbTrail(model);
            model.addAttribute("parameterMap", buildMapFromSearchParameters(reference, minDepthRefComponent, referenceLevel, result));
        }
        // handle login and breadcrumb
        handleUserLoginForm(model); 
        model.addAttribute("contextOfRequest", contextOfRequest);
        
        // result list
        model.addAttribute(ModelAttributeKeyStore.TESTCASE_LIST_KEY, testcases);
        model.addAttribute("showTestcaseSearchForm", false);
        return "testcase/list";
    }
    
    /*
     * Handlers to add a testcase
     */
    @RequestMapping(value={"add"}, method=RequestMethod.GET)
    public String selectReferenceHandler(
            @RequestParam(value="reference", required=false) Long idReference,
            Model model) {
        
        SelectReferenceCommand selectReferenceCommand = new SelectReferenceCommand();
        
        // If a reference is pre-selected
        if (idReference != null) {
            selectReferenceCommand.setIdReference(idReference);
        }
        
        return displaySelectReferenceForm(model, selectReferenceCommand);
    }
    
    @RequestMapping(value={"add"}, method=RequestMethod.POST)
    public String selectReferenceHandler(
            @ModelAttribute("selectReferenceCommand") SelectReferenceCommand selectReferenceCommand,
            BindingResult result,
            Model model) {
        SelectReferenceValidator selectReferenceValidator = new SelectReferenceValidator(referenceDataService);
        
        // validate command
        selectReferenceValidator.validate(selectReferenceCommand, result);
        if (result.hasErrors()) {
            // return to the first step
            return displaySelectReferenceForm(model, selectReferenceCommand);
        }
        
        // display the second step
        NewTestcaseCommand newTestcaseCommand = new NewTestcaseCommand();
        newTestcaseCommand.setIdReference(selectReferenceCommand.getIdReference());
        
        return displayAddTestcaseForm(model, newTestcaseCommand);
    }
    
    @RequestMapping(value={"add-details", "add-finalize"}, method=RequestMethod.GET)
    public String addHandler(Model model) {
        return selectReferenceHandler(null, model);
    }
    
    @RequestMapping(value="add-details", method=RequestMethod.POST)
    public String addHandler(
            @ModelAttribute("newTestcaseCommand") NewTestcaseCommand testcaseCommand,
            BindingResult result,
            Model model
            ) {
        NewTestcaseValidator newTestcaseValidator = new NewTestcaseValidator(
                referenceTestDataService,
                referenceDataService,
                testcaseDataService,
                resultDataService,
                webarchiveDataService,
                NewTestcaseValidator.Step.STEP_TESTCASE
                );

        // validate command
        newTestcaseValidator.validate(testcaseCommand, result);
        if (result.hasErrors()) {
            // return to the second step
            return displayAddTestcaseForm(model, testcaseCommand);
        }
        // display the third step
        return displayAttachWebarchiveForm(model, testcaseCommand);
    }
    
    @RequestMapping(value="add-finalize", method=RequestMethod.POST)
    public String finalizeAddHandler(
            @ModelAttribute("newTestcaseCommand") NewTestcaseCommand testcaseCommand,
            BindingResult result,
            Model model
            ) {
        Webarchive webarchive;
        Account currentUser;
        Testcase newTestcase;
        TestcasePresentation testcasePresentation;
        NewTestcaseValidator newTestcaseValidator = new NewTestcaseValidator(
                referenceTestDataService,
                referenceDataService,
                testcaseDataService,
                resultDataService,
                webarchiveDataService,
                NewTestcaseValidator.Step.STEP_WEBARCHIVE
                );
        
        // validate command
        newTestcaseValidator.validate(testcaseCommand, result);
        if (result.hasErrors()) {
            if (result.hasFieldErrors(FormKeyStore.ID_TEST_KEY) || result.hasFieldErrors(FormKeyStore.ID_RESULT_KEY)) {
                // return to the first step if we have an error on the test or result id
                return displayAddTestcaseForm(model, testcaseCommand);
            } else {
                // return to the second step
                return displayAttachWebarchiveForm(model, testcaseCommand);
            }
        }
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        // sanity check
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/add-finalize. Check spring security configuration.");
            return "guest/login";
        }
        // get webarchive
        if (!testcaseCommand.isOnCreateWebarchive()) {
            webarchive = webarchiveDataService.read(testcaseCommand.getIdWebarchive());
            // Tells the view to display an informative message about the time needed for the webarchive to be available
            model.addAttribute("webarchiveCreation", false);
        } else {
            webarchive = webarchiveController.createWebarchive(
                    currentUser,
                    testcaseCommand.getUrlNewWebarchive(),
                    testcaseCommand.getDescriptionNewWebarchive()
                    );
            if (webarchive != null) {
                // persist the webarchive
                webarchiveDataService.saveOrUpdate(webarchive);
            } // a null webarchive is handled below
            
            model.addAttribute("webarchiveCreation", true);
        }
        // sanity check
        if (webarchive == null) {
            // most of the time, if the webarchive is null, it means its creation
            // fails.
            model.addAttribute(
                    FormKeyStore.GENERAL_ERROR_MESSAGE_KEY,
                    MessageKeyStore.CANNOT_CREATE_WEBARCHIVE
                    );
            // return to the second step
            return displayAttachWebarchiveForm(model, testcaseCommand);
        }
        // create testcase
        newTestcase = testcaseDataService.createFromTest(
                currentUser,
                webarchive,
                resultDataService.read(testcaseCommand.getIdResult()),
                referenceTestDataService.read(testcaseCommand.getIdReferenceTest()),
                testcaseCommand.getDescription()
                );
         
        
        // persist testcase
        testcaseDataService.saveOrUpdate(newTestcase);
        // email notification
        sendTestcaseCreationNotification(newTestcase);
        
        // display testcase
        testcasePresentation = testcasePresentationFactory.create(newTestcase);
        model.addAttribute("testcase", testcasePresentation);
        return "testcase/add-summary";
    }
    
    /*
     * Handlers to modify a testcase
     */
    @RequestMapping(value="edit-details/{id}/*", method=RequestMethod.GET)
    public String editDetailsHandler(
            @PathVariable("id") Long id,
            Model model
            ) {
        EditTestcaseCommand editTestcaseCommand;
        Testcase testcase = null;
        Account account;
        TestcasePresentation testcasePresentation;
        
        // fetch test case
        try {
            testcase = testcaseDataService.read(id);
        } catch (NullPointerException e) {
            LogFactory.getLog(TestcaseController.class.getName()).debug("testcase doesn't exist");
        }
        
        if (testcase == null) {
            LogFactory.getLog(TestcaseController.class.getName()).debug("testcase is null");
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }
        // check permissions
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/edit-details. Check spring security configuration.");
            return "guest/login";
        } else if (!AccountUtils.getInstance().currentUserhasPermissionToEditTestcase(testcase)) {
            return displayTestcaseError(model, MessageKeyStore.NOT_AUTHORIZED_TO_EDIT_TESTCASE);
        }
        
        testcasePresentation = testcasePresentationFactory.create(testcase);
        model.addAttribute("testcase", testcasePresentation);
        // create form
        editTestcaseCommand = new EditTestcaseCommand(testcase);

        return displayEditTestcaseForm(model, editTestcaseCommand, testcase.getReferenceTest());
    }
    
    @RequestMapping(value="edit-details/{id}/*", method=RequestMethod.POST)
    public String editDetailsHandler(
            @ModelAttribute("editTestcaseCommand") EditTestcaseCommand editTestcaseCommand,
            BindingResult result,
            Model model
            ) {
        Testcase testcase;
        Account account;
        EditTestcaseValidator testcaseValidator = new EditTestcaseValidator(
                testcaseDataService,
                resultDataService,
                referenceTestDataService
                );
        
        // fetch account
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached edit-details. Check spring security configuration.");
            return "guest/login";
        }
        
        // fetch testcase
        testcase = testcaseDataService.read(editTestcaseCommand.getId());
        
        if (testcase == null) {
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }
        
        // check permisions
        if (!AccountUtils.getInstance().currentUserhasPermissionToEditTestcase(testcase)) {
            return displayTestcaseError(model, MessageKeyStore.NOT_AUTHORIZED_TO_EDIT_TESTCASE);
        }
        
        // validate form
        testcaseValidator.validate(editTestcaseCommand, result);
        
        if (result.hasErrors()) {    
            return displayEditTestcaseForm(model, editTestcaseCommand, testcase.getReferenceTest());
        }
        
        // update testcase
        editTestcaseCommand.update(testcase, referenceTestDataService, resultDataService);
        testcaseDataService.saveOrUpdate(testcase);
        
        // confirmation message
        model.addAttribute("successMessage", MessageKeyStore.TESTCASE_EDITED);
        return displayTestcaseDetails(model, testcase);
    }
    
    @RequestMapping(value="details/{id}/*", method={RequestMethod.GET, RequestMethod.POST})
    public String detailsHandler(
            @PathVariable("id") Long id,
            Model model
            ) {       
        Testcase testcase;
        
        // fetch testcase
        try {
            testcase = testcaseDataService.read(id);
        } catch (NullPointerException e) { 
            LogFactory.getLog(TestcaseController.class.getName()).debug("testcase doesn't exist");
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }
        
        return displayTestcaseDetails(model, testcase);
    }
    
    /*
     * Handlers to delete a testcase
     */
    @RequestMapping(value="delete/{id}/*", method=RequestMethod.GET)
    public String deleteHandler(
            @PathVariable("id") Long id,
            Model model
            ) {
        DeleteTestcaseCommand deleteTestcaseCommand;
        Testcase testcase;
        Account account;
        TestcasePresentation testcasePresentation;
        
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // fetch test case
        try {
            testcase = testcaseDataService.read(id);
        } catch (NullPointerException e) {
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }

        // check permissions
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/delete. Check spring security configuration.");
            return "guest/login";
        } else if (!AccountUtils.getInstance().currentUserhasPermissionToEditTestcase(testcase)) {
            return displayTestcaseError(model, MessageKeyStore.NOT_AUTHORIZED_TO_DELETE_TESTCASE);
        }
        
        deleteTestcaseCommand = new DeleteTestcaseCommand(testcase);
        testcasePresentation = testcasePresentationFactory.create(testcase);
        model.addAttribute("deleteTestcaseCommand", deleteTestcaseCommand);
        model.addAttribute("testcase", testcasePresentation);
        
        return "testcase/delete";
    }
    
    @RequestMapping(value="delete/{id}/*", method=RequestMethod.POST)
    public String confirmDeleteHandler(
            @ModelAttribute("deleteTestcaseCommand") DeleteTestcaseCommand deleteTestcaseCommand,
            BindingResult result,
            Model model
            ) {
        Testcase testcase;
        Account account;
        TestcasePresentation testcasePresentation;
        
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        // fetch test case
        try {
            testcase = testcaseDataService.read(deleteTestcaseCommand.getId());
        } catch (NullPointerException e) {
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }
        
        if (testcase == null) {
            return displayTestcaseError(model, MessageKeyStore.TESTCASE_DOESNT_EXIST);
        }
        // check permissions
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/delete. Check spring security configuration.");
            return "guest/login";
        } else if (!AccountUtils.getInstance().currentUserhasPermissionToEditTestcase(testcase)) {
            return displayTestcaseError(model, MessageKeyStore.NOT_AUTHORIZED_TO_DELETE_TESTCASE);
        }
        
        // delete the testcase
        testcaseDataService.delete(testcase.getId());
        
        testcasePresentation = testcasePresentationFactory.create(testcase);
        model.addAttribute("testcase", testcasePresentation);
        // confirmation message
        model.addAttribute("successMessage", MessageKeyStore.TESTCASE_DELETED);
        
        return "testcase/delete";
    }
    
    /*
     * Accessors
     */
    public WebarchiveDataService getWebarchiveDataService() {
        return webarchiveDataService;
    }

    public void setWebarchiveDataService(WebarchiveDataService webarchiveDataService) {
        this.webarchiveDataService = webarchiveDataService;
    }

    public WebarchiveController getWebarchiveController() {
        return webarchiveController;
    }

    public void setWebarchiveController(WebarchiveController webarchiveController) {
        this.webarchiveController = webarchiveController;
    }

    @Override
    public TestcasePresentationFactory getTestcasePresentationFactory() {
        return testcasePresentationFactory;
    }

    @Override
    public void setTestcasePresentationFactory(TestcasePresentationFactory testcasePresentationFactory) {
        this.testcasePresentationFactory = testcasePresentationFactory;
    }
}
