/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.scope;

/**
 *
 * @author jkowalczyk
 */
public enum CrawlScope {
    site("site"),
    page("page");

    /**
     * Constructor
     * @param type
     */
    private CrawlScope(String type){
        this.type = type;
    }

    /**
     *
     */
    private String type;

    /**
     *
     * @return the type of the content
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @return the type of the content
     */
    public CrawlScope getCrawlScope(String type){
        if (type.equalsIgnoreCase(CrawlScope.page.getType())){
            return CrawlScope.page;
        } else if (type.equalsIgnoreCase(CrawlScope.site.getType())){
            return CrawlScope.site;
        }
        return CrawlScope.page;
    }
}
