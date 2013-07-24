package org.opens.kbaccess.entity.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface Testcase extends Entity, Reorderable {
    /**
     *
     * @return the testcase description
     */
    String getDescription();

    /**
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @return the creation date of the test case
     */
    Date getCreationDate();

    /**
     * 
     * @param date
     */
    void setCreationDate(Date date);

    /**
     *
     * @return the associated result
     */
    Result getResult();

    /**
     * 
     * @param result
     */
    void setResult(Result result);

    /**
     *
     * @return the associated account
     */
    Account getAccount();

    /**
     * 
     * @param account
     */
    void setAccount(Account account);

    /**
     * 
     * @return the associated webarchive
     */
    Webarchive getWebarchive();
    
    /**
     *
     * @param webarchive
     */
    void setWebarchive(Webarchive webarchive);

    /**
     * 
     * @return the associated referenceTest
     */
    ReferenceTest getReferenceTest();
    
    /**
     *
     * @param referenceTest
     */
    void setReferenceTest(ReferenceTest referenceTest);

}
