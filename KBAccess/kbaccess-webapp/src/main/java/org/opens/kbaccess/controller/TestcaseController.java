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
package org.opens.kbaccess.controller;

import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.command.EditTestcaseCommand;
import org.opens.kbaccess.command.NewTestcaseCommand;
import org.opens.kbaccess.command.TestcaseSearchCommand;
import org.opens.kbaccess.controller.utils.AMailerController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.factory.subject.TestcaseFactory;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.EditTestcaseValidator;
import org.opens.kbaccess.validator.NewTestcaseValidator;
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
@RequestMapping("/testcase/")
public class TestcaseController extends AMailerController {
    
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    @Autowired
    private TestcaseFactory testcaseFactory;
    @Autowired
    private WebarchiveController webarchiveController;

    /*
     * Private methods
     */    
    private String buildListTitleFromSearchCriteria(
            Reference reference,
            Criterion criterion,
            Theme theme,
            Test test,
            Level level,
            Result result
            ) {
        StringBuilder title = new StringBuilder();
        StringBuilder criteria = new StringBuilder();
        
        // building criteria list
        if (criterion != null) {
            criteria.append("Critère ");
            criteria.append(criterion.getLabel());
            criteria.append(", ");
        }
        if (level != null) {
            criteria.append("Niveau ");
            criteria.append(level.getLabel());
            criteria.append(", ");
        }
        if (reference != null) {
            criteria.append("Référenciel ");
            criteria.append(reference.getLabel());
            criteria.append(", ");
        }
        if (result != null) {
            criteria.append("Résultat ");
            criteria.append(result.getLabel());
            criteria.append(", ");
        }
        if (test != null) {
            criteria.append("Test ");
            criteria.append(test.getLabel());
            criteria.append(", ");
        }
        if (theme != null) {
            criteria.append("Thématique ");
            criteria.append(theme.getLabel());
            criteria.append(", ");
        }
        // building title
        title.append("Résultat de la recherche : ");
        // post process criteria list
        if (criteria.length() == 0) {
            // no criteria
            title.append("tous les testcases");
        } else {
            // delete ending comma of the criteria
            criteria.delete(criteria.length() - 2, criteria.length());
            // append the result to the title
            title.append(criteria);
        }
        return title.toString();
    }
    
    private String displayAddTestcaseForm(Model model, NewTestcaseCommand newTestcaseCommand) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Ajout d'un testcase (1/3)");
        // create the form command
        model.addAttribute("testByRef", getTestByRef());
        model.addAttribute("resultList", getResults());
        model.addAttribute("newTestcaseCommand", newTestcaseCommand);
        return "testcase/add";
    }
    
    private String displayAttachWebarchiveForm(Model model, NewTestcaseCommand newTestcaseCommand) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Ajout d'un testcase (2/3)");
        // create the form command
        model.addAttribute("webarchiveList", webarchiveDataService.findAll());
        model.addAttribute("newTestcaseCommand", newTestcaseCommand);
        return "testcase/add-webarchive";        
    }
    
    private String displayEditTestcaseForm(Model model, EditTestcaseCommand editTestcaseCommand, String errorMessage) {
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Edition des détails d'un testcase");
        // create form
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        } else {
            model.addAttribute("testList", getTestByRef());
            model.addAttribute("resultList", getResults());
            model.addAttribute("editTestcaseCommand", editTestcaseCommand);
        }
        return "testcase/edit-details";
    }
    
    private boolean hasPermissionToEditTestcase(Account account, Testcase testcase) {
        return (account.getId() == testcase.getAccount().getId() ||
                (account.getAccessLevel().getPriority() < testcase.getAccount().getAccessLevel().getPriority() &&
                 account.getAccessLevel().getPriority() < 2));
    }

    /*
     * Endpoints
     */
    
    @RequestMapping(value="search-by-url")
    public String searchByUrlHandler(Model model) {
        // handle login form and breadcrumb trail
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Recherche de testcases par URL");
        return "testcase/search-by-url";
    }
    
    @RequestMapping(value={"search", "result"}, method=RequestMethod.GET)
    public String searchHandler(Model model) {
        // handle login form, breadcrumb and search form
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Recherche de testcases");
        handleTestcaseSearchForm(model);
        return "testcase/search";
    }
    
    @RequestMapping(value="result", method=RequestMethod.POST)
    public String searchHandler(
            @ModelAttribute("testcaseSearchCommand") TestcaseSearchCommand testcaseSearchCommand,
            Model model
            ) {
        List testcaseResultList;
        Reference reference;
        Criterion criterion;
        Theme theme;
        Test test;
        Level level;
        Result result;
        
        reference = referenceDataService.getByCode(testcaseSearchCommand.getReference());
        criterion = criterionDataService.getByCode(testcaseSearchCommand.getCriterion());
        theme = themeDataService.getByCode(testcaseSearchCommand.getTheme());
        test = testDataService.getByCode(testcaseSearchCommand.getTest());
        level = levelDataService.getByCode(testcaseSearchCommand.getGrade());
        result = resultDataService.getByCode(testcaseSearchCommand.getResult());
        testcaseResultList = TestcasePresentation.fromCollection(
                testcaseDataService.getAllFromUserSelection(reference, criterion, theme, test, level, result),
                false
                );
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Recherche de testcases", "/testcase/search.html", "Resultat");
        // display user search criteria in the search form
        handleTestcaseSearchForm(model, testcaseSearchCommand);
        model.addAttribute("showTestcaseSearchForm", true);
        // display result list
        model.addAttribute(
                ModelAttributeKeyStore.TESTCASE_LIST_KEY,
                testcaseResultList
                );
        // set the title
        model.addAttribute(
                "title",
                buildListTitleFromSearchCriteria(reference, criterion, theme, test, level, result)
                );
        // set h1
        model.addAttribute("testcaseListH1", "Résultat de la recherche");
        //
        return "testcase/list";
    }
    
    @RequestMapping(value="list")
    public String listHandler(
            Model model,
            @RequestParam(value="reference", required=false) Long idReference,
            @RequestParam(value="theme", required=false) Long idTheme,
            @RequestParam(value="level", required=false) Long idLevel,
            @RequestParam(value="criterion", required=false) Long idCriterion,
            @RequestParam(value="test", required=false) Long idTest,
            @RequestParam(value="result", required=false) Long idResult
            ) {
        Collection<TestcasePresentation> testcases;
        boolean joker;
        Reference reference;
        Theme theme;
        Level level;
        Criterion criterion;
        Test test;
        Result result;
        
        // fetch all testcases ?
        joker = (idReference == null
                && idTheme == null
                && idLevel == null
                && idCriterion == null
                && idTest == null
                && idResult == null
                );
        // fetch entities and set title and H1
        if (joker) {
            testcases = TestcasePresentation.fromCollection(
                    (Collection) testcaseDataService.findAll(),
                    false
                    );
            model.addAttribute("title", "Tous les testcases - KBAccess");
            model.addAttribute("testcaseListH1", "Liste des testcases");
        } else {
            reference = (idReference == null ? null : referenceDataService.read(idReference));
            theme = (idTheme == null ? null : themeDataService.read(idTheme));
            level = (idLevel == null ? null : levelDataService.read(idLevel));
            criterion = (idCriterion == null ? null : criterionDataService.read(idCriterion));
            test = (idTest == null ? null : testDataService.read(idTest));
            result = (idResult == null ? null : resultDataService.read(idResult));
            testcases = TestcasePresentation.fromCollection(
                    testcaseDataService.getAllFromUserSelection(reference, criterion, theme, test, level, result),
                    false
                    );
            model.addAttribute("title", buildListTitleFromSearchCriteria(reference, criterion, theme, test, level, result));
            model.addAttribute("testcaseListH1", buildListTitleFromSearchCriteria(reference, criterion, theme, test, level, result));
        }
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Liste des testcases");
        // result list
        model.addAttribute(ModelAttributeKeyStore.TESTCASE_LIST_KEY, testcases);
        model.addAttribute("showTestcaseSearchForm", false);
        return "testcase/list";
    }
    
    /*
     * Handlers to add a testcase
     */
    
    @RequestMapping(value={"add", "add-finalize"}, method=RequestMethod.GET)
    public String addHandler(Model model) {
        return displayAddTestcaseForm(model, new NewTestcaseCommand());
    }
    
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String addHandler(
            @ModelAttribute("newTestcaseCommand") NewTestcaseCommand testcaseCommand,
            BindingResult result,
            Model model
            ) {
        NewTestcaseValidator newTestcaseValidator = new NewTestcaseValidator(
                criterionDataService,
                testDataService,
                resultDataService,
                webarchiveDataService,
                NewTestcaseValidator.Step.STEP_TESTCASE
                );

        // validate command
        newTestcaseValidator.validate(testcaseCommand, result);
        if (result.hasErrors()) {
            // return to the first step
            return displayAddTestcaseForm(model, testcaseCommand);
        }
        // display the second step
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
        NewTestcaseValidator newTestcaseValidator = new NewTestcaseValidator(
                criterionDataService,
                testDataService,
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
        handleBreadcrumbTrail(model, "KBAccess", "/", "Ajout d'un testcase (3/3)");
        // sanity check
        currentUser = AccountUtils.getInstance().getCurrentUser();
        if (currentUser == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/add-finalize. Check spring security configuration.");
            return "home";
        }
        // get webarchive
        if (testcaseCommand.getCreateWebarchive() == false) {
            webarchive = webarchiveDataService.read(testcaseCommand.getIdWebarchive());
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
        }
        // sanity check
        if (webarchive == null) {
            // most of the time, if the webarchive is null, it means its creation
            // fails.
            testcaseCommand.setGeneralErrorMessage("Unable to create the webarchive.");
            // return to the second step
            return displayAttachWebarchiveForm(model, testcaseCommand);
        }
        // create testcase
        if (testcaseCommand.getIdTest() != null) {
            newTestcase = testcaseFactory.createFromTest(
                    currentUser,
                    webarchive,
                    resultDataService.read(testcaseCommand.getIdResult()),
                    testDataService.read(testcaseCommand.getIdTest()),
                    testcaseCommand.getDescription()
                    );
        } else {
            newTestcase = testcaseFactory.createFromCriterion(
                    currentUser,
                    webarchive,
                    resultDataService.read(testcaseCommand.getIdResult()),
                    criterionDataService.read(testcaseCommand.getIdCriterion()),
                    testcaseCommand.getDescription()
                    );            
        }
        // persist testcase
        testcaseDataService.saveOrUpdate(newTestcase);
        // email notification
        sendTestcaseCreationNotification(newTestcase);
        // display testcase
        model.addAttribute("testcase", newTestcase);
        return "testcase/add-summary";
    }
    
    /*
     * Handlers to modify a testcase
     */
    @RequestMapping(value="edit-details", method=RequestMethod.GET, params="id")
    public String editDetailsHandler(
            @RequestParam("id") Long id,
            Model model
            ) {
        EditTestcaseCommand editTestcaseCommand;
        Testcase testcase;
        Account account;
        
        // handle form login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Edition des détails d'un testcase");
        // fetch test case
        testcase = testcaseDataService.read(id);
        if (testcase == null) {
            model.addAttribute("errorMessage", "Testcase invalid.");
            return "testcase/edit-details";
        }
        // check permissions
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached testcase/edit-details. Check spring security configuration.");
            return "home";
        } else if (hasPermissionToEditTestcase(account, testcase) == false) {
            model.addAttribute("errorMessage", "Vous n'êtes pas authorisé à modifier ce testcase.");
            return "testcase/edit-details";
        }
        // create form
        editTestcaseCommand = new EditTestcaseCommand(testcase);
        model.addAttribute("editTestcaseCommand", editTestcaseCommand);
        //
        return "testcase/edit-details";
    }
    
    @RequestMapping(value="edit-details", method=RequestMethod.POST)
    public String editDetailsHandler(
            @ModelAttribute("editTestcaseCommand") EditTestcaseCommand editTestcaseCommand,
            BindingResult result,
            Model model
            ) {
        EditTestcaseValidator testcaseValidator;
        Testcase testcase;
        Account account;
        
        testcaseValidator = new EditTestcaseValidator(
                testcaseDataService,
                resultDataService,
                criterionDataService,
                testDataService
                );
        // validate form
        testcaseValidator.validate(editTestcaseCommand, result);
        if (result.hasErrors()) {
            return displayEditTestcaseForm(model, editTestcaseCommand, null);
        }
        // fetch testcase
        testcase = testcaseDataService.read(editTestcaseCommand.getId());
        if (testcase == null) {
            return displayEditTestcaseForm(model, null, "Testcase invalide.");
        }
        // fetch account
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(TestcaseController.class).error("An unauthentified user reached edit-details. Check spring security configuration.");
            return "home";
        }
        // check permisions
        if (hasPermissionToEditTestcase(account, testcase) == false) {
            return displayEditTestcaseForm(model, null, "Vous n'êtes pas authorisé à modifier ce testcase.");
        }
        // update testcase
        editTestcaseCommand.update(testcase, criterionDataService, resultDataService, testDataService);
        testcaseDataService.saveOrUpdate(testcase);
        // confirmation message
        model.addAttribute("successMessage", "Vos modifications ont bien été enregistrées.");
        return displayEditTestcaseForm(model, editTestcaseCommand, null);
    }
    
    @RequestMapping(value="details/{id:\\d+}*", method={RequestMethod.GET, RequestMethod.POST })
    public String detailsHandler(
            @PathVariable("id") Long id,
            Model model
            ) {
        TestcasePresentation testcasePresentation;
        Testcase testcase;
        
        // handle form login
        handleUserLoginForm(model);
        // fetch testcase
        testcase = testcaseDataService.read(id, true);
        if (testcase == null) {
            // handle breadcrumb
            handleBreadcrumbTrail(model, "KBAccess", "/", "Testcase introuvable");
            return "testcase/details";
        }
        // handle breadcrumb
        handleBreadcrumbTrail(model, "KBAccess", "/", "Testcase n°" + id);
        testcasePresentation = new TestcasePresentation(testcase, true);
        model.addAttribute("testcase", testcasePresentation);
        return "testcase/details";
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

    public TestcaseFactory getTestcaseFactory() {
        return testcaseFactory;
    }

    public void setTestcaseFactory(TestcaseFactory testcaseFactory) {
        this.testcaseFactory = testcaseFactory;
    }

}
