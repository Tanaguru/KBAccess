package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.CriterionImpl;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.kbaccess.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class CriterionDAOImpl extends AbstractJPADAO<Criterion, Long> implements
        CriterionDAO {

    public CriterionDAOImpl() {
        super();
    }

    @Override
    protected Class<CriterionImpl> getEntityClass() {
        return CriterionImpl.class;
    }

    @Override
    public Collection<Criterion> findAll(Reference reference,
            Theme theme) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                    + getEntityClass().getName()
                    + " r WHERE r.reference = :reference AND r.theme = :theme");
            query.setParameter("reference", reference);
            query.setParameter("theme", theme);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Collection<Criterion> findAllFromReference(Reference reference) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                    + getEntityClass().getName()
                    + " r WHERE r.reference = :reference");
            query.setParameter("reference", reference);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Collection<Criterion> findAllFromTheme(Theme theme) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.theme = :theme");
            query.setParameter("theme", theme);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Criterion findByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n" + " WHERE n.code = :code");
            query.setParameter("code", code);
            return (Criterion) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }    
        
}
