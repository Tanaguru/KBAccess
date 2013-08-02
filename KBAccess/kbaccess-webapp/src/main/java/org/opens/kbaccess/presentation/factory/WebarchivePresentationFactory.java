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
package org.opens.kbaccess.presentation.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.presentation.WebarchivePresentation;
import org.springframework.stereotype.Component;

/**
 *
 * @author blebail
 */
@Component
public class WebarchivePresentationFactory {
    
  /**
   * 
   * @param webarchive
   * @return 
   */  
    public WebarchivePresentation create(Webarchive webarchive) {
        WebarchivePresentation webarchivePresentation = new WebarchivePresentation(webarchive);

        return webarchivePresentation;
    }
    
    /**
     * 
     * @param webarchives
     * @return 
     */
    public List<WebarchivePresentation> createFromCollection(Collection<Webarchive> webarchives) {
        List<WebarchivePresentation> presentations;

        presentations = new ArrayList<WebarchivePresentation>(webarchives.size());

        for (Webarchive webarchive : webarchives) {
            presentations.add(create(webarchive));
        }
        return presentations;
    }
}

