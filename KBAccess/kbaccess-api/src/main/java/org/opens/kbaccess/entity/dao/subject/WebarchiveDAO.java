package org.opens.kbaccess.entity.dao.subject;

import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface WebarchiveDAO extends GenericDAO<Webarchive, Long> {


    /**
     *
     * @return
     *      The highest value for the Priority field in the webarchive table
     */
    int findMaxPriorityValueFromTable();

    /**
     *
     * @param account
     * @return
     *      All the webarchives for a given account
     */
    List<Webarchive> findAllFromAccount(Account account);

    /**
     * 
     * @return The number of webarchive in database
     */
    Long count();
    
}
