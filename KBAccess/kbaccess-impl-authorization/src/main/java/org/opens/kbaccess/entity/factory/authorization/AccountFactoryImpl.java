package org.opens.kbaccess.entity.factory.authorization;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl; 

/**
 * 
 * @author jkowalczyk
 */
public class AccountFactoryImpl implements AccountFactory {

    public AccountFactoryImpl() {
        super();
    }

    @Override
    public Account create() {
        return new AccountImpl();
    }

}
