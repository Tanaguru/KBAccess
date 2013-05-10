/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.dao.subject;

import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.slurpmanager.entity.subject.Webresource;

/**
 *
 * @author jkowalczyk
 */
public interface WebresourceDAO extends GenericDAO<Webresource, Long> {

    Webresource retrieveByUrl(String url);
    
}
