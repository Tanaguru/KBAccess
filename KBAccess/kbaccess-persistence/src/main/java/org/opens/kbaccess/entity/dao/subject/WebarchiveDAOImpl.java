package org.opens.kbaccess.entity.dao.subject;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.entity.subject.WebarchiveImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class WebarchiveDAOImpl extends AbstractJPADAO<Webarchive, Long>
        implements WebarchiveDAO {

    public WebarchiveDAOImpl() {
        super();
    }

    @Override
    protected Class<WebarchiveImpl> getEntityClass() {
        return WebarchiveImpl.class;
    }

    @Override
    public int findMaxPriorityValueFromTable() {
        try {
            Query query = entityManager.createQuery("SELECT MAX(t.rank) FROM "
                    + getEntityClass().getName() + " t");
            return (Integer) (query.getSingleResult());
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return 0;
        }
    }

    @Override
    public List<Webarchive> findAllFromAccount(Account account) {
        try {
            Query query = entityManager.createQuery("SELECT t FROM "
                    + getEntityClass().getName() + " t"
                    + " WHERE t.account = :account"
                    + " ORDER BY t.url, t.creationDate asc");
            query.setParameter("account", account);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Webarchive> findAll() {
        try {
            Query query = entityManager.createQuery("SELECT t FROM "
                    + getEntityClass().getName() + " t"
                    + " ORDER BY t.url, t.creationDate asc");
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public int deleteFromWebArchive(Webarchive webarchive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery(
                "SELECT COUNT(*) FROM " + getEntityClass().getName()
                );
        
        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException ex) {
            LogFactory.getLog(WebarchiveDAOImpl.class).error("Count of webarchive failed", ex);
            return 0L;
        }
    }
    
    
}
