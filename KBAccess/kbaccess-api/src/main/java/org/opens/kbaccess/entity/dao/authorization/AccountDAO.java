package org.opens.kbaccess.entity.dao.authorization;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface AccountDAO extends GenericDAO<Account, Long> {

    /**
     * Return the account associated with the given email.
     * 
     * If the given email does not match any account, null is returned
     * @param email The email of the account to look for.
     * @return The account having the given email or null if no accounts have
     *         this email.
     */
    Account findByEmail(String email);

    /**
     * 
     * @return The number of Account in database
     */
    Long count();
    
    /**
     * 
     * @param idAccount
     * @return the number of testcases from the account of idAccount
     */
    Long countTestcases(Long idAccount);
    
    /**
     * 
     * @param idAccount
     * @return the number or webarchives from the account of idAccount
     */
    Long countWebarchives(Long idAccount);
}
