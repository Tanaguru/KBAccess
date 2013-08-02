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
package org.opens.kbaccess.command;

import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.service.reference.ReferenceTestDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.subject.Testcase;

/**
 *
 * @author bcareil
 */
public class EditTestcaseCommand extends ACommand {
    private Long id;

    private Long idReferenceTest;
    private Long idResult;
    private String description;
    
    public EditTestcaseCommand() {
    }

    public EditTestcaseCommand(Testcase testcase) {
        this.id = testcase.getId();
        this.idResult = testcase.getResult().getId();  
        this.description = testcase.getDescription();
        this.idReferenceTest = testcase.getReferenceTest().getId();
    }

    public void update(
            Testcase testcase,
            ReferenceTestDataService testDataService,
            ResultDataService resultDataService
            ) {
        
        ReferenceTest referenceTest = testDataService.read(this.idReferenceTest);
        Result result = resultDataService.read(this.idResult);

        // update the testcase 
        testcase.setResult(result);
        testcase.setDescription(this.description);
        testcase.setReferenceTest(referenceTest);
    }

    /*
     * Accessors
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReferenceTest() {
        return idReferenceTest;
    }

    public void setIdReferenceTest(Long idReferenceTest) {
        this.idReferenceTest = idReferenceTest;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
