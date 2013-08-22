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

import java.util.ArrayList;
import java.util.List;
import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.service.reference.ReferenceDataService;

/**
 *
 * @author bcareil
 */
public class TestcaseSearchCommand extends ACommand {
    
    private Long idAccount;
    private String codeReference;
    private List<Long> idReferenceInfoList;
    private List<Long> idReferenceTestList;
    private Long idReferenceLevel;
    private Long idResult;
    private boolean searchSingleTest = false;

    public TestcaseSearchCommand() {
        
    }
    
    public TestcaseSearchCommand(ReferenceDataService referenceDataService) {
        this.idReferenceInfoList = new ArrayList<Long>();
        this.idReferenceTestList = new ArrayList<Long>();
        
        /*
         * Init the idReferenceInfoList and referenceTestList with the maximum sizes possible
         * witch are respectively the biggest infoMaxDepth and testMaxDepth of all references
         */
        int infoMaxDepth = Integer.MIN_VALUE;
        int testMaxDepth = Integer.MAX_VALUE;
        
        for (Reference reference : referenceDataService.findAll()) {
            if (reference.getInfoMaxDepth() > infoMaxDepth) {
                infoMaxDepth = reference.getInfoMaxDepth();
            }
            
            if (reference.getTestMaxDepth() < testMaxDepth) {
                testMaxDepth = reference.getTestMaxDepth();
            }
        }
        
        
        for (int i = testMaxDepth; i < 0; i++) {
            idReferenceTestList.add(-1L);
        }
        
        for (int i = 0; i < infoMaxDepth; i++) {
            idReferenceInfoList.add(-1L);
        }
    }
    
    /*
     * Scope of search methods
     */
    public boolean searchAll() {
        boolean searchAll = false;

        if (idAccount == null
                && (codeReference == null || codeReference.length() == 0)
                && idReferenceLevel == null
                && idReferenceInfoList == null
                && idReferenceTestList == null
                && idResult == null) {
            searchAll = true;
        }
        
        return searchAll;
    }
    
    public boolean searchReferenceOrResult() {
        boolean referenceOrResult = (codeReference != null) || (idResult != null);
        boolean noReferenceSpecifics = (idReferenceLevel == null) && (idAccount == null);
        boolean noReferenceInfo = idReferenceInfoList == null;
        boolean noReferenceTest = idReferenceTestList == null;
        
        if (!noReferenceInfo) {
            noReferenceInfo = true;
            
            for (Long idReferenceInfo : idReferenceInfoList) {
                noReferenceInfo = noReferenceInfo && (idReferenceInfo == null);
            }
        }
        
        if (!noReferenceTest) {
            noReferenceTest = true;
            
            for (Long idReferenceTest : idReferenceTestList) {
                noReferenceTest = noReferenceTest && (idReferenceTest == null);
            }
        }
        
        noReferenceSpecifics = noReferenceSpecifics && noReferenceInfo && noReferenceTest;
        
        return (referenceOrResult && noReferenceSpecifics);
    }
    
    public boolean searchAccount() {
        boolean searchAccount = false;
        
        if (this.idAccount != null) {
            searchAccount = true;
        }
        
        return searchAccount;
    }
    
    public boolean searchSingleTest() {
        return this.searchSingleTest;
    }
    /*
     * Accessors
     */
    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getCodeReference() {
        return codeReference;
    }

    public void setCodeReference(String codeReference) {
        this.codeReference = codeReference;
    }

    public List<Long> getIdReferenceInfoList() {
        return idReferenceInfoList;
    }

    public void setIdReferenceInfoList(List<Long> idReferenceInfoList) {
        this.idReferenceInfoList = idReferenceInfoList;
    }

    public List<Long> getIdReferenceTestList() {
        return idReferenceTestList;
    }

    public void setIdReferenceTestList(List<Long> idReferenceTestList) {
        this.idReferenceTestList = idReferenceTestList;
    }

    public Long getIdReferenceLevel() {
        return idReferenceLevel;
    }

    public void setIdReferenceLevel(Long idReferenceLevel) {
        this.idReferenceLevel = idReferenceLevel;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public boolean isSearchSingleTest() {
        return searchSingleTest;
    }

    public void setSearchSingleTest(boolean searchSingleTest) {
        this.searchSingleTest = searchSingleTest;
    }
}
