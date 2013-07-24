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
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author blebail
 */
@Component
public class TestcasePresentationFactory {
    
    @Autowired
    private ReferenceTestDataService referenceTestDataService;
    
  /**
   * 
   * @param testcase
   * @return 
   */  
    public TestcasePresentation create(Testcase testcase) {
        TestcasePresentation testcasePresentation = new TestcasePresentation(testcase, referenceTestDataService);

        return testcasePresentation;
    }
    
    /**
     * 
     * @param testcases
     * @return 
     */
    public List<TestcasePresentation> createFromCollection(Collection<Testcase> testcases) {
        List<TestcasePresentation> presentations;

        presentations = new ArrayList<TestcasePresentation>(testcases.size());

        for (Testcase testcase : testcases) {
            presentations.add(create(testcase));
        }
        return presentations;
    }

    /*
     * Accessors
     */
    public ReferenceTestDataService getReferenceTestDataService() {
        return referenceTestDataService;
    }

    public void setReferenceTestDataService(ReferenceTestDataService referenceTestDataService) {
        this.referenceTestDataService = referenceTestDataService;
    }
}

