/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.service.subject;

import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author jkowalczyk
 */
public interface WebarchiveDataService extends GenericDataService<Webarchive, Long> {

//    /**
//     *
//     * @return
//     */
//    int getMaxPriorityFromTable();

    /**
     * 
     * @param account
     * @param url
     * @param description
     * @return 
     */
    Webarchive create(Account account, String url, String description);
    
    /**
     *
     * @param account
     * @return
     */
    List<Webarchive> getAllFromUserAccount(Account account);

    /**
     * 
     * @return
     */
    Webarchive createWebarchive();

    /**
     *
     * @param webarchive
     * @return
     */
    int deleteWebArchive(Webarchive webarchive);
    
    /**
     * 
     * @return The number of webarchive in database
     */
    Long getCount();
}
