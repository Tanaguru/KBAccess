package org.opens.kbaccess.entity.statistics;

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

/**
 *
 * @author blebail
 */
public class AccountStatisticsImpl implements AccountStatistics {

    private Long id;
    private Long testcaseCount;
    private String displayedName;
    
    public AccountStatisticsImpl() {
    }

    public AccountStatisticsImpl(Long id, Long testcaseCount) {
        this.id = id;
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
    public String getDisplayedName() {
        return displayedName;
    }

    @Override
    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    @Override
    public Long getTestcaseCount() {
        return testcaseCount;
    }

    @Override
    public void setTestcaseCount(Long testcaseCount) {
        this.testcaseCount = testcaseCount;
    }

    @Override
    public String toString() {
        return "AccountStatisticsImpl{" + "id=" + id + ", testcaseCount=" + testcaseCount + '}';
    }
}
