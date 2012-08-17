package org.opens.kbaccess.entity.subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringEscapeUtils;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.CriterionImpl;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;

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
    @Column(name = "ID_TESTCASE")
    protected Long id;
    @Column(name = "TITLE", nullable = false)
    protected String title;
    @Column(name = "DESCRIPTION", nullable = true)
    protected String description;
    @Column(name = "PRIORITY", nullable = false)
    protected int rank;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    protected Date creationDate;
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
    @JoinColumn(name = "ID_CRITERION", nullable = false)
    protected CriterionImpl criterion;
    @ManyToMany
    @JoinTable(
            name = "testcase_test_result",
            joinColumns = @JoinColumn(name = "ID_TESTCASE", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ID_TEST_RESULT", nullable = false)
            )
    protected Collection<TestResultImpl> testResults;

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.ResultImpl.class)
    public Result getResult() {
        return result;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.TestImpl.class)
    public Criterion getCriterion() {
        return criterion;
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
    public void addTestResult(TestResult testResult) {
        this.testResults.add((TestResultImpl) testResult);
    }

    @Override
    public Collection<TestResult> getTestResults() {
        return (Collection) this.testResults;
    }

    @Override
    public void setTestResults(Collection<TestResult> testResults) {
        this.testResults.clear();
        this.testResults.addAll((Collection) testResults);
    }
    
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setResult(Result result) {
        this.result = (ResultImpl)result;
    }

    @Override
    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl)criterion;
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
    public void setDescription(String description) {
        this.description = StringEscapeUtils.escapeHtml(description);
    }

    @Override
    public void setTitle(String title) {
        this.title = StringEscapeUtils.escapeHtml(title);
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
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
