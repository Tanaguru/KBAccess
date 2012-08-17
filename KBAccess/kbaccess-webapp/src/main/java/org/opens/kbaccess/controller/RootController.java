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

import org.opens.kbaccess.controller.utils.AController;
import org.opens.kbaccess.entity.service.authorization.AccountDataService;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.presentation.StatisticsPresentation;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private AccountDataService accountDataService;
    @Autowired
    private StatisticsDataService statisticsDataService;
    
    /*
     * Endpoints
     */
    @RequestMapping(value={"index"})
    public String homeHandler(Model model) {
        handleUserLoginForm(model);
        handleTestcaseSearchForm(model);
        model.addAttribute(
                ModelAttributeKeyStore.STATISTICS_KEY,
                new StatisticsPresentation(
                    testcaseDataService,
                    webarchiveDataService,
                    referenceDataService,
                    accountDataService,
                    statisticsDataService
                    )
                );
        model.addAttribute(
                ModelAttributeKeyStore.TESTCASE_LIST_KEY,
                TestcasePresentation.fromCollection(
                testcaseDataService.getLastTestcases(5),
                false
                ));
        return "home";
    }
    
    @RequestMapping("contact")
    public String contactHandler(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Contact");
        return "contact";
    }

    @RequestMapping("legal")
    public String legalHandler(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Mentions légales");
        return "legal";
    }

    @RequestMapping("thanks")
    public String thanksHandler(Model model) {
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Remerciement");
        return "thanks";
    }
    
    /*
     * Accessors
     */

    public AccountDataService getAccountDataService() {
        return accountDataService;
    }

    public void setAccountDataService(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

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

}
