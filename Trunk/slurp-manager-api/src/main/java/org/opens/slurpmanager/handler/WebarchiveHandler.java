/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.handler;

import java.util.List;
import org.opens.slurpmanager.exception.WebarchiveCreationException;
import org.opens.slurpmanager.scope.CrawlScope;

/**
 *
 * @author jkowalczyk
 */
public interface WebarchiveHandler {

    public String create(String url, CrawlScope scope, String description) throws WebarchiveCreationException;

    public List<String> retrieveAll();

    public String retrieve(String url);

    public void update(String url);

    public void delete(String url);

}
