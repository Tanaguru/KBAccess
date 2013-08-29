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
public class NewTestcaseCommand extends ACommand {

    // step webarchive
    private Long idWebarchive;
    private String urlNewWebarchive;
    private String descriptionNewWebarchive;
    
    // step testcase
    private Long idReference;
    private Long idReferenceDepth; 
    private Long idReferenceTest;
    private Long idResult;
    private String description;

    public NewTestcaseCommand() {
    }

    public boolean isOnCreateWebarchive() {
        boolean onCreateWebarchive = true;
        
        if (idWebarchive != null) {
            onCreateWebarchive = false;
        }
        
        return onCreateWebarchive;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionNewWebarchive() {
        return descriptionNewWebarchive;
    }

    public void setDescriptionNewWebarchive(String descriptionNewWebarchive) {
        this.descriptionNewWebarchive = descriptionNewWebarchive;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public Long getIdReference() {
        return idReference;
    }

    public void setIdReference(Long idReference) {
        this.idReference = idReference;
    }

    public Long getIdReferenceDepth() {
        return idReferenceDepth;
    }

    public void setIdReferenceDepth(Long idReferenceDepth) {
        this.idReferenceDepth = idReferenceDepth;
    }
    
    public Long getIdReferenceTest() {
        return idReferenceTest;
    }

    public void setIdReferenceTest(Long idReferenceTest) {
        this.idReferenceTest = idReferenceTest;
    }

    public Long getIdWebarchive() {
        return idWebarchive;
    }

    public void setIdWebarchive(Long idWebarchive) {
        this.idWebarchive = idWebarchive;
    }

    public String getUrlNewWebarchive() {
        return urlNewWebarchive;
    }

    public void setUrlNewWebarchive(String urlNewWebarchive) {
        this.urlNewWebarchive = urlNewWebarchive;
    }
    
}
