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
package org.opens.kbaccess.command;

import org.opens.kbaccess.entity.service.reference.CriterionDataService;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.reference.TestDataService;
import org.opens.kbaccess.entity.subject.Testcase;

/**
 *
 * @author bcareil
 */
public class EditTestcaseCommand {
    private Long id;
    private Long idCriterion;
    private Long idResult;
    private String title;
    private String description;

    public EditTestcaseCommand() {
    }

    public EditTestcaseCommand(Testcase testcase) {
        this.id = testcase.getId();
        this.idCriterion = testcase.getCriterion().getId();
        this.idResult = testcase.getResult().getId();
        this.title = testcase.getTitle();
        this.description = testcase.getDescription();
    }

    public void update(
            Testcase testcase,
            CriterionDataService criterionDataService,
            ResultDataService resultDataService,
            TestDataService testDataService
            ) {
        testcase.setResult(resultDataService.read(this.idResult));
        testcase.setCriterion(criterionDataService.read(this.idCriterion));
        testcase.setTitle(this.title);
        testcase.setDescription(this.description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public Long getIdCriterion() {
        return idCriterion;
    }

    public void setIdCriterion(Long idCriterion) {
        this.idCriterion = idCriterion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
