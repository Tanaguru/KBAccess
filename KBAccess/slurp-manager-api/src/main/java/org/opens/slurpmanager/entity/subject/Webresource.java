/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.subject;

import java.util.Date;
import java.util.List;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author jkowalczyk
 */
public interface Webresource extends Entity, Reorderable {

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
     * @param the list of webarchives
     */
    List<? extends Webarchive> getWebarchiveList();

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
     * @param date
     */
    void setWebarchiveList(List<? extends Webarchive> webarchiveList);

    /**
     *
     * @param date
     */
    void addWebarchive(Webarchive webarchive);
}
