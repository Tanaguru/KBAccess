package org.opens.kbaccess.entity.subject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringEscapeUtils;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.TestImpl;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "testcase")
@XmlRootElement
public class TestcaseImpl implements Testcase, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_TESTCASE", nullable=false)
    protected Long id;
    @OneToOne
    @JoinColumn(name="result_ID_RESULT", nullable=false)
    protected ResultImpl result;
    @OneToOne
    @JoinColumn(name="test_ID_TEST", nullable=false)
    protected TestImpl test;
    @OneToOne
    @JoinColumn(name="account_ID_ACCOUNT", nullable=false)
    protected AccountImpl account;
    @OneToOne
    @JoinColumn(name="webarchive_ID_WEBARCHIVE", nullable=true)
    protected WebarchiveImpl webarchive = null;
    @Column(name = "CD_TESTCASE", nullable=false)
    protected String code;
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description = null;
    @Column(name = "LABEL", nullable=true)
    protected String label = null;
    @Column(name = "PRIORITY", nullable=false)
    protected int rank;
    @Column(name = "NUM_TC", nullable=false)
    protected int numTC;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DATE_C", nullable=false)
    protected Date date;

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.ResultImpl.class)
    public Result getResult() {
        return result;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.TestImpl.class)
    public Test getTest() {
        return test;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.authorization.AccountImpl.class)
    public Account getAccount() {
        return account;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.subject.WebarchiveImpl.class)
    public Webarchive getWebarchive() {
        return webarchive;
    }

    @Override
    public String getCdTestcase() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLabel() {
        return label;
    }

//    @Override
//    public boolean isDeleted() {
//        if (deleted == 0) {
//            return true;
//        }
//        else return false;
//    }

    @Override
    public Integer getNumTc() {
        return numTC;
    }

    @Override
    public Date getDateC() {
        return date;
    }

    @Override
    public void setResult(Result result) {
        this.result = (ResultImpl)result;
    }

    @Override
    public void setTest(Test test) {
        this.test = (TestImpl)test;
    }

    @Override
    public void setAccount(Account account) {
        this.account = (AccountImpl)account;
    }

    @Override
    public void setWebarchive(Webarchive webarchive) {
        this.webarchive = (WebarchiveImpl)webarchive;
    }

    @Override
    public void setCdTestcase(String testcaseCode) {
        this.code = testcaseCode;
    }

    @Override
    public void setDescription(String description) {
        this.description = StringEscapeUtils.escapeHtml(description);
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

//    @Override
//    public void setDeleted(boolean deleted) {
//        if (deleted){
//           this.deleted = 0;
//        } else {
//            this.deleted = 1;
//        }
//
//    }

    @Override
    public void setNumTc(Integer numTC) {
        this.numTC = numTC;
    }

    @Override
    public void setDateC(Date date) {
        this.date = date;
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
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }
}
