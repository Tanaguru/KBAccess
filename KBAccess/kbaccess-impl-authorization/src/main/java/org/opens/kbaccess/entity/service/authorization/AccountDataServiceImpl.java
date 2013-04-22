package org.opens.kbaccess.entity.service.authorization;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.authorization.AccountDAO;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class AccountDataServiceImpl extends AbstractGenericDataService<Account, Long>
        implements AccountDataService {

    public AccountDataServiceImpl(){
        super();
    }

    @Override
    public Account getAccountFromEmail(String email) {
        return ((AccountDAO) entityDao).findByEmail(email);
    }

    @Override
    public Long getCount() {
        return ((AccountDAO) entityDao).count();
    }
    
    
}