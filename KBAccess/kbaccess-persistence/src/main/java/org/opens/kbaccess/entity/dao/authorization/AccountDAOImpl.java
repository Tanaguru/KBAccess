package org.opens.kbaccess.entity.dao.authorization;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class AccountDAOImpl extends AbstractJPADAO<Account, Long> implements
        AccountDAO {

    public AccountDAOImpl() {
        super();
    }

    @Override
    protected final Class<? extends Account> getEntityClass() {
        return AccountImpl.class;
    }

    private Query selectAccounts(String where) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT n FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" n ");
        sb.append(where);
        return entityManager.createQuery(sb.toString());
    }

    @Override
    public Account findByEmail(String email) {
        try {
            Query query = selectAccounts("WHERE n.email = :email");
            query.setParameter("email", email);
            return (Account) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery(
                "SELECT COUNT(*) FROM " + getEntityClass().getName()
                );
        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException ex) {
            LogFactory.getLog(AccountDAOImpl.class).error("Count of account in the db fails", ex);
            return 0L;
        }
    }
    
    
}
