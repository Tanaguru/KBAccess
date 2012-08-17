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
package org.opens.kbaccess.entity.subject;

import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.Entity;

/**
 *
 * @author bcareil
 */
public interface TestResult extends Entity {
    
    /**
     * 
     * @return The test
     */
    Test getTest();

    /**
     * 
     * @param test The test
     */
    void setTest(Test test);
    
    /**
     * 
     * @return The result of the test
     */
    Result getResult();
    
    /**
     * 
     * @param result The result of the test
     */
    void setResult(Result result);
    
}
