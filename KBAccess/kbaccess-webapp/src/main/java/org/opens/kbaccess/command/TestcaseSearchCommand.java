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
    private String referenceInfo;
    private String referenceTest;
    private String level;
    private String result;

    public TestcaseSearchCommand() {
    }

    public TestcaseSearchCommand(String reference, String referenceInfo, String referenceTest, String level, String result) {
        this.reference = reference;
        this.referenceInfo = referenceInfo;
        this.referenceTest = referenceTest;
        this.level = level;
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferenceInfo() {
        return referenceInfo;
    }

    public void setReferenceInfo(String referenceInfo) {
        this.referenceInfo = referenceInfo;
    }

    public String getReferenceTest() {
        return referenceTest;
    }

    public void setReferenceTest(String referenceTest) {
        this.referenceTest = referenceTest;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
