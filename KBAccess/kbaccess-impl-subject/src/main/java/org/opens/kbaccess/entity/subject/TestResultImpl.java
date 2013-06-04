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
package org.opens.kbaccess.entity.subject;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.TestImpl;

/**
 *
 * @author bcareil
 */
@Entity
@Table(name = "test_result")
@XmlRootElement
public class TestResultImpl implements TestResult, Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "ID_TEST_RESULT")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ID_TEST")
    private TestImpl test;
    
    @ManyToOne
    @JoinColumn(name = "ID_RESULT")
    private ResultImpl result;

    public TestResultImpl() {
    }

    public TestResultImpl(Long id, TestImpl test, ResultImpl result) {
        this.id = id;
        this.test = test;
        this.result = result;
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
    public ResultImpl getResult() {
        return result;
    }

    @Override
    public void setResult(Result result) {
        this.result = (ResultImpl)result;
    }

    @Override
    public Test getTest() {
        return test;
    }

    @Override
    public void setTest(Test test) {
        this.test = (TestImpl)test;
    }

}
