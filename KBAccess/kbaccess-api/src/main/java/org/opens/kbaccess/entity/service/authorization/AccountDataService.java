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
}
