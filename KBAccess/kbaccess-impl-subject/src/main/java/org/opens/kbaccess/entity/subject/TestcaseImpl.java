/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.subject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringEscapeUtils;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.ReferenceTestImpl;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;

/**
 *
 * @author blebail
 */
@Entity
@Table(name = "testcase")
@XmlRootElement
public class TestcaseImpl implements Testcase, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TESTCASE")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "RANK")
    private int rank;
    @OneToOne
    @JoinColumn(name = "ID_RESULT", nullable = false)
    protected ResultImpl result;
    @OneToOne
    @JoinColumn(name = "ID_ACCOUNT", nullable = false)
    protected AccountImpl account;
    @OneToOne
    @JoinColumn(name = "ID_WEBARCHIVE", nullable = false)
    protected WebarchiveImpl webarchive;
    @OneToOne
    @JoinColumn(name = "ID_REFERENCE_TEST", nullable = false)
    protected ReferenceTestImpl referenceTest;

    public TestcaseImpl() {
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
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    } 

    @Override
    public Result getResult() {
        return this.result;
    }

    @Override
    public void setResult(Result result) {
        this.result = (ResultImpl)result;
    }

    @Override
    public Account getAccount() {
        return this.account;
    }

    @Override
    public void setAccount(Account account) {
        this.account = (AccountImpl)account;
    }

    @Override
    public Webarchive getWebarchive() {
        return this.webarchive;
    }

    @Override
    public void setWebarchive(Webarchive webarchive) {
        this.webarchive = (WebarchiveImpl)webarchive;
    }

    @Override
    public ReferenceTest getReferenceTest() {
        return this.referenceTest;
    }

    @Override
    public void setReferenceTest(ReferenceTest referenceTest) {
        this.referenceTest = (ReferenceTestImpl)referenceTest;
    }

    @Override
    public String toString() {
        return "TestcaseImpl{" + "id=" + id + ", creationDate=" + creationDate + ", result=" + result + ", referenceTest=" + referenceTest + '}';
    }
}
