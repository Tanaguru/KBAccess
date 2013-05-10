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

    String create(String url, CrawlScope scope, String description) throws WebarchiveCreationException;

    List<String> retrieveAll();

    String retrieve(String url);

    void update(String url);

    void delete(String url);

}
