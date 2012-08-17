package org.opens.kbaccess.entity.statistics;

import org.opens.kbaccess.entity.statistics.CriterionStatistics;

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
    private Long testcaseCount;
    
    public CriterionStatisticsImpl() {
    }

    public CriterionStatisticsImpl(Long id, String code, Long testcaseCount) {
        this.id = id;
        this.code = code;
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
    public Long getTestcaseCount() {
        return testcaseCount;
    }

    @Override
    public void setTestcaseCount(Long testcaseCount) {
        this.testcaseCount = testcaseCount;
    }
    
}
