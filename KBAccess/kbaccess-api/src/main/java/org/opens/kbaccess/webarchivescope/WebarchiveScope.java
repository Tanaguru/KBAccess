/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.webarchivescope;

/**
 *
 * @author jkowalczyk
 */
public enum WebarchiveScope {
    SITE("site"),
    PAGE("page");

    /**
     * Constructor
     * @param type
     */
    private WebarchiveScope(String type){
        this.type = type;
    }

    /**
     *
     */
    private final String type;

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
    public WebarchiveScope getCrawlScope(String type){
        for (WebarchiveScope member : values()) {
            if (member.getType().equalsIgnoreCase(type)) {
                return member;
            }
        }
        return PAGE;
    }
}
