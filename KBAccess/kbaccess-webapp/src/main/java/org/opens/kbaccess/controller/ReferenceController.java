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
import org.opens.kbaccess.controller.utils.AMailerController;
import org.opens.kbaccess.presentation.factory.ReferenceCoveragePresentationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author bcareil
 */
@Controller
@RequestMapping("/reference/")
public class ReferenceController extends AMailerController {
    
    @Autowired
    ReferenceCoveragePresentationFactory referenceCoveragePresentationFactory;
    
    /*
     * End points
     */
    @RequestMapping(value={"list"}, method=RequestMethod.GET)
    public String searchHandler(Model model) {
        // handle login form, breadcrumb and search form
        handleUserLoginForm(model);
        handleBreadcrumbTrail(model);
        
        model.addAttribute(
                "referenceCoverageList", 
                referenceCoveragePresentationFactory.createFromCollection(
                    (Collection)referenceDataService.findAll()
                )
            );
        
        return "reference/list";
    }

    public ReferenceCoveragePresentationFactory getReferenceCoveragePresentationFactory() {
        return referenceCoveragePresentationFactory;
    }

    public void setReferenceCoveragePresentationFactory(ReferenceCoveragePresentationFactory referenceCoveragePresentationFactory) {
        this.referenceCoveragePresentationFactory = referenceCoveragePresentationFactory;
    }
}
