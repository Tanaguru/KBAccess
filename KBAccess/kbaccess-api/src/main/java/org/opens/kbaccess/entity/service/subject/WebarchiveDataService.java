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

    /**
     *
     * @return
     */
    int getMaxPriorityFromTable();

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
}
