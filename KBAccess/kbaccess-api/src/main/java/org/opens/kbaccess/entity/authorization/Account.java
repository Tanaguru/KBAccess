package org.opens.kbaccess.entity.authorization;

import java.util.Date;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author jkowalczyk
 */
public interface Account extends Entity, Reorderable {

    /**
     *
     * @return the access level
     */
    AccessLevel getAccessLevel();

    /**
     *
     * @param accessLevel
     */
    void setAccessLevel(AccessLevel accessLevel);

    /**
     *
     * @return true if the account is activated
     */
    boolean isActivated();

    /**
     *
     * @param activated Set to true to activate the account, false to deactivate it
     */
    void setActivated(boolean activated);

    /**
     *
     * @return the account email
     */
    String getEmail();

    /**
     *
     * @param email The new account email
     */
    void setEmail(String email);

    /**
     *
     * @return the user first name
     */
    String getFirstName();

    /**
     *
     * @param firstName the new first name of the user
     */
    void setFirstName(String firstName);

    /**
     *
     * @return the use last name
     */
    String getLastName();

    /**
     *
     * @param lastName The last name of the user
     */
    void setLastName(String lastName);

    /**
     * 
     * @return the hased user's password (SHA1)
     */
    String getPassword();

    /**
     *
     * @param password The new hased user's password (SHA1)
     */
    void setPassword(String password);

    /**
     *
     * @return user's website
     */
    String getUrl();

    /**
     * 
     * @param url The new user's website
     */
    void setUrl(String url);

    /**
     *
     * @return The user authentication code (for account activation)
     */
    String getAuthCode();

    /**
     *
     * @param authCode The user's authentication code to activate the account
     */
    void setAuthCode(String authCode);

    /**
     * @return the user's subscription date
     */
    Date getSubscriptionDate();

    /**
     * @param subscribeDate The date the user subscribed
     */
    void setSubscriptionDate(Date subscribeDate);
}
