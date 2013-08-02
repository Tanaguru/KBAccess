package org.opens.kbaccess.entity.dao.authorization;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.entity.subject.WebarchiveImpl;
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
            query.setParameter("email", email.toLowerCase());
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
        return (Long) query.getSingleResult();
    }
    
    @Override
    public Long countTestcases(Long idAccount) {
        Query query = entityManager.createQuery(
            "SELECT COUNT(*) "
            + "FROM " + getEntityClass().getName() + " ac, " + TestcaseImpl.class.getName() + " tc "
            + "WHERE ac.id = tc.account "
            + "AND ac.id = :id"
            );
        
        query.setParameter("id", idAccount);
        
        return (Long) query.getSingleResult();
    }
    
    @Override
    public Long countWebarchives(Long idAccount) {
        Query query = entityManager.createQuery(
            "SELECT COUNT(*) "
            + "FROM " + getEntityClass().getName() + " ac, " + WebarchiveImpl.class.getName() + " wa "
            + "WHERE ac.id = wa.account "
            + "AND ac.id = :id"
            );
        
        query.setParameter("id", idAccount);
        return (Long) query.getSingleResult();
    }
}
