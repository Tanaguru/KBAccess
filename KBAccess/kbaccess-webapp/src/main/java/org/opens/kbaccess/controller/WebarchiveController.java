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

import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.command.WebarchiveCommand;
import org.opens.kbaccess.controller.utils.AMailerController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.factory.subject.WebarchiveFactory;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.keystore.FormKeyStore;
import org.opens.kbaccess.keystore.ModelAttributeKeyStore;
import org.opens.kbaccess.utils.AccountUtils;
import org.opens.kbaccess.validator.WebarchiveCommandValidator;
import org.opens.slurpmanager.SlurpManager;
import org.opens.slurpmanager.exception.WebarchiveCreationException;
import org.opens.slurpmanager.scope.CrawlScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/webarchive/")
public class WebarchiveController extends AMailerController {
    
    @Autowired
    private WebarchiveDataService webarchiveDataService;
    @Autowired
    private WebarchiveFactory webarchiveFactory;
    @Autowired
    private SlurpManager slurpManager;
    
    /*
     * public members
     */
    public Webarchive createWebarchive(
            Account account,
            String url,
            String description
            ) {
        Webarchive webarchive;
        
        webarchive = webarchiveFactory.create(account, url, description);
        try {
            webarchive.setLocalUrl(slurpManager.create(url, CrawlScope.page, description));
        } catch (WebarchiveCreationException ex) {
            LogFactory.getLog(WebarchiveController.class).info("Unable to create the webarchive on the url " + url, ex);
            return null;
        }
        return webarchive;
    }
    
    /*
     * Endpoints
     */
    @RequestMapping("list")
    public String listHandler(Model model) {
        // handle login and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Liste des webarchives");
        // result list, title and h1
        model.addAttribute(ModelAttributeKeyStore.WEBARCHIVE_LIST_KEY, webarchiveDataService.findAll());
        model.addAttribute("title", "Tous les testcases - KBAccess");
        model.addAttribute("webarchiveListH1", "Liste des webarchives");
        return "webarchive/list";
    }
    
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String addHandler(Model model) {
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Ajout d'une webarchive");
        // create form command
        model.addAttribute("newWebarchiveCommand", new WebarchiveCommand());
        return "webarchive/add";
    }
    
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String addHandler(
            @ModelAttribute("newWebarchiveCommand") WebarchiveCommand webarchiveCommand,
            BindingResult result,
            Model model
            ) {
        WebarchiveCommandValidator webarchiveCommandValidator;
        Webarchive webarchive;
        Account account;
        
        webarchiveCommandValidator = new WebarchiveCommandValidator();
        // handle login form and breadcrumb
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model, "KBAccess", "/", "Ajout d'une webarchive");
        // validate form
        webarchiveCommandValidator.validate(webarchiveCommand, result);
        if (result.hasErrors()) {
            model.addAttribute("newWebarchiveCommand", webarchiveCommand);
            return "webarchive/add";
        }
        // fetch account
        account = AccountUtils.getInstance().getCurrentUser();
        if (account == null) {
            LogFactory.getLog(WebarchiveController.class).error("An unauthentified user reached webarchive/add. Check spring security configuration.");
            return "home";
        }
        // create webarchive
        webarchive = createWebarchive(account, webarchiveCommand.getUrl(), webarchiveCommand.getDescription());
        if (webarchive == null) {
            result.rejectValue(
                    FormKeyStore.GENERAL_ERROR_MESSAGE_KEY,
                    "Impossible de créer la webarchive, vérifier l'url saisie puis réessayez."
                    );
            model.addAttribute("newWebarchiveCommand", webarchiveCommand);
            return "webarchive/add";
        }
        // persist webarchive
        webarchiveDataService.saveOrUpdate(webarchive);
        // send email notification
        sendWebarchiveCreationNotification(webarchive);
        // set success message
        model.addAttribute("webarchive", webarchive);
        return "webarchive/add-summary";
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

    public WebarchiveFactory getWebarchiveFactory() {
        return webarchiveFactory;
    }

    public void setWebarchiveFactory(WebarchiveFactory webarchiveFactory) {
        this.webarchiveFactory = webarchiveFactory;
    }
    
}
