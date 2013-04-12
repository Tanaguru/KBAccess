/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.slurpmanager.entity.service.subject;

import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import org.opens.slurpmanager.entity.subject.Webresource;

/**
 *
 * @author jkowalczyk
 */
public interface WebresourceDataService extends
        GenericDataService<Webresource, Long> {

    public Webresource findByUrl(String url);
}
