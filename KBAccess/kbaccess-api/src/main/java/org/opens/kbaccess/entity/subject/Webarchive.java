package org.opens.kbaccess.entity.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Webarchive extends Entity, Reorderable { 

    /**
     *
     * @return the original website url
     */
    String getUrl();

    /**
     *
     * @return the local url to the snapshot
     */
    String getLocalUrl();

    /**
     *
     * @return the creation date
     */
    Date getCreationDate();

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the account that created the webarchive
     */
    Account getAccount();

    /**
     *
     * @return the archive scope
     * @see WebarchiveScope
     */
    String getScope();

    /**
     *
     * @param url
     */
    void setUrl(String url);

    /**
     * 
     * @param localUrl
     */
    void setLocalUrl(String localUrl);

    /**
     * 
     * @param date
     */
    void setCreationDate(Date date);

    /**
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @param idAccount
     */
    void setAccount(Account account);

    /**
     * 
     * @param scope
     */
    void setScope (String scope);
}
