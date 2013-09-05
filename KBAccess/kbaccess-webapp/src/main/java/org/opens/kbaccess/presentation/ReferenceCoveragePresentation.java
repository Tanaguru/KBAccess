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
package org.opens.kbaccess.presentation;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.service.statistics.StatisticsDataService;
import org.opens.kbaccess.entity.statistics.ReferenceStatistics;

/**
 *
 * @author bcareil
 */
public class ReferenceCoveragePresentation {
    
    private Long id;
    private String code;
    private String country;
    private int coverage;
    

    public ReferenceCoveragePresentation(
            Reference reference,
            StatisticsDataService statisticsDataService
            ) {
        
        ReferenceStatistics referenceStatistics 
                = statisticsDataService.getReferenceCoverage(reference);
        
        this.id = reference.getId();
        this.code = referenceStatistics.getReference().getCode();
        this.country = reference.getCountry();
        this.coverage = referenceStatistics.getCoveragePercentage();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
     public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }
}
