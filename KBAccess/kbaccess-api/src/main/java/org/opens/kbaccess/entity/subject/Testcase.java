package org.opens.kbaccess.entity.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Testcase extends Entity, Reorderable {

    /**
     *
     * @return the associated result
     */
    Result getResult();

    /**
     *
     * @return the associated test
     */
    Test getTest();

    /**
     *
     * @return the associated account
     */
    Account getAccount();

    /**
     * 
     * @return the associated webarchive
     */
    Webarchive getWebarchive();

    /**
     *
     * @return the code of the testcase
     */
    String getCdTestcase();

    /**
     *
     * @return the testcase description
     */
    String getDescription();

    /**
     *
     * @return the label of the testcase
     */
    String getLabel();

    /**
     *
     * @return the priority
     */
    Integer getNumTc();

    /**
     *
     * @return the creation date of the test case
     */
    Date getDateC();

    /**
     * 
     * @param result
     */
    void setResult(Result result);

    /**
     *
     * @param test
     */
    void setTest(Test test);

    /**
     * 
     * @param account
     */
    void setAccount(Account account);

    /**
     *
     * @param webarchive
     */
    void setWebarchive(Webarchive webarchive);

    /**
     * 
     * @param testcaseCode
     */
    void setCdTestcase(String testcaseCode);

    /**
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @param label
     */
    void setLabel(String label);

    /**
     * 
     * @param num
     */
    void setNumTc(Integer num);

    /**
     * 
     * @param date
     */
    void setDateC(Date date);
}
