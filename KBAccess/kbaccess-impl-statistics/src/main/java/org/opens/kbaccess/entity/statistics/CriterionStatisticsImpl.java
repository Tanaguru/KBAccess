package org.opens.kbaccess.entity.statistics;

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

/**
 *
 * @author bcareil
 */
public class CriterionStatisticsImpl implements CriterionStatistics {

    private Long id;
    private String code;
    private String label;
    private String referenceCode;
    private Long referenceId;
    private String referenceLabel;
    private Long testcaseCount;
    
    public CriterionStatisticsImpl() {
    }

    public CriterionStatisticsImpl(
            Long id, 
            String code, 
            String label, 
            String referenceCode,
            Long referenceId, 
            String referenceLabel,
            Long testcaseCount) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.referenceCode = referenceCode;
        this.referenceId = referenceId;
        this.referenceLabel = referenceLabel;
        this.testcaseCount = testcaseCount;
    }
    
    public CriterionStatisticsImpl(
            Long id, 
            String code, 
            String label, 
            String referenceCode,
            Long referenceId, 
//            String referenceLabel,
            Long testcaseCount) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.referenceCode = referenceCode;
        this.referenceId = referenceId;
//        this.referenceLabel = referenceLabel;
        this.testcaseCount = testcaseCount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getReferenceCode() {
        return referenceCode;
    }

    @Override
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    
    @Override
    public Long getReferenceId() {
        return referenceId;
    }
    
    @Override
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }
    
    @Override
    public String getReferenceLabel() {
        return referenceLabel;
    }

    @Override
    public void setReferenceLabel(String referenceLabel) {
        this.referenceLabel = referenceLabel;
    }

    @Override
    public Long getTestcaseCount() {
        return testcaseCount;
    }

    @Override
    public void setTestcaseCount(Long testcaseCount) {
        this.testcaseCount = testcaseCount;
    }
    
}
