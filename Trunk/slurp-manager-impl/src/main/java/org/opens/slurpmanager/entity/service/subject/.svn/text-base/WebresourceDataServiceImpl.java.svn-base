/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.service.subject;

import org.opens.sdk.entity.service.AbstractGenericDataService;
import org.opens.slurpmanager.entity.dao.subject.WebresourceDAO;
import org.opens.slurpmanager.entity.subject.Webresource;

/**
 *
 * @author jkowalczyk
 */
public class WebresourceDataServiceImpl extends AbstractGenericDataService<Webresource, Long>
        implements WebresourceDataService{

    public WebresourceDataServiceImpl(){
        super();
    }

    public Webresource findByUrl(String url) {
        return ((WebresourceDAO) entityDao).retrieveByUrl(url);
    }
}
