/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.slurpmanager.exception.WebarchiveCreationException;
import org.opens.slurpmanager.scope.CrawlScope;

/**
 *
 * @author jkowalczyk
 */
public class CrawlLauncher {

    public static void main(String[] args) {
        run("slurp-manager");
    }

    private static void run(String bundleName) {
        ResourceBundle parametersBundle = ResourceBundle.getBundle(bundleName);
        SlurpManager slurpManager = new SlurpManagerImpl();
        String url = parametersBundle.getString("url");
        String scope = parametersBundle.getString("scope");
        if (!url.isEmpty()) {
            try {
                System.out.println(slurpManager.create(url, CrawlScope.valueOf(scope), ""));
            } catch (WebarchiveCreationException ex) {
                Logger.getLogger(CrawlLauncher.class.getName()).log(Level.WARNING, null, ex);
            }
        }
     }

}
