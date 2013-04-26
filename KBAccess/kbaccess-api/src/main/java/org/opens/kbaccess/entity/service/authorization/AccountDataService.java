package org.opens.kbaccess.entity.service.authorization;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface AccountDataService extends
		GenericDataService<Account, Long> {

    /**
     * Return the account associated with the given email.
     * 
     * @see AccountDAO
     * @param email
     * @return 
     */
    Account getAccountFromEmail(String email);
    
    /**
     * 
     * @return  The number of Account in database
     */
    Long getCount();
    
    /**
     * 
     * @param idAccount
     * @return the number of testcases from the account of idAccount
     */
    Long getNbTestcases(Long idAccount);
    
    /**
     * 
     * @param idAccount
     * @return the number or webarchives from the account of idAccount
     */
    Long getNbWebarchives(Long idAccount);
}
