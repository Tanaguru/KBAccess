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

/**
 *
 * @author bcareil
 */
public class TestcaseSearchCommand extends ACommand {
    
    private String reference;
    private String theme;
    private String criterion;
    private String test;
    private String grade;
    private String result;

    public TestcaseSearchCommand() {
    }

    public TestcaseSearchCommand(String reference, String theme, String criterion, String test, String grade, String result) {
        this.reference = reference;
        this.theme = theme;
        this.criterion = criterion;
        this.test = test;
        this.grade = grade;
        this.result = result;
    }

    /**
     * 
     * @return The code of the criterion to look for
     */
    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    /**
     * 
     * @return The code of the level to look for
     */
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 
     * @return The code of the reference to search in
     */
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return The code of the exptected result
     */
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 
     * @return The code of the test to look for
     */
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    /**
     * 
     * @return The code of the theme to look for
     */
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
}
