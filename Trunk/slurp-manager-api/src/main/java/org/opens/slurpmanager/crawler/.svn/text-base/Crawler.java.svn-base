/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.crawler;

import java.util.Date;
import org.opens.slurpmanager.scope.CrawlScope;

/**
 *
 * @author jkowalczyk
 */
public interface Crawler {
    /**
     *
     * @return the result of the crawl
     */
    String getResult();

    /**
     *
     * @return the date of the result
     */
    Date getResultDate();

    /**
     * Run the Crawl
     */
    boolean run();

    /**
     * Set the Page Url
     * @param pageURL
     */
    void setUrl(String url);

    /**
     * Set the Page Url
     * @param pageURL
     */
    void setScope(CrawlScope scope);

}
