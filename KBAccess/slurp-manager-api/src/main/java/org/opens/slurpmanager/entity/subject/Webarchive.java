/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.subject;

import java.util.Date;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;


/**
 *
 * @author jkowalczyk
 */
public interface Webarchive extends Entity, Reorderable {

    /**
     *
     * @return the url
     */
    String getUrl();

    /**
     *
     * @return the date
     */
    Date getDate();

    /**
     *
     * @return the date
     */
    String getDescription();

    /**
     *
     * @return the scope
     */
    String getScope();

    /**
     *
     * @return the parent webresource
     */
    Webresource getWebresourceParent();

    /**
     *
     * @param url
     */
    void setUrl(String url);

    /**
     *
     * @param date
     */
    void setDate(Date date);

    /**
     *
     * @param date
     */
    void setDescription(String description);

    /**
     * 
     * @param scope
     */
    void setScope(String scope);

    /**
     *
     * @param date
     */
    void setWebresourceParent(Webresource webresource);
}
