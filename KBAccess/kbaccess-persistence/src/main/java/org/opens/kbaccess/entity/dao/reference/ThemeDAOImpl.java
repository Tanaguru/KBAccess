package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.CriterionImpl;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.kbaccess.entity.reference.ThemeImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ThemeDAOImpl extends AbstractJPADAO<Theme, Long> implements
        ThemeDAO {

    public ThemeDAOImpl() {
        super();
    }

    @Override
    protected Class<ThemeImpl> getEntityClass() {
        return ThemeImpl.class;
    }

    @Override
    public Collection<Theme> findAll(Reference reference) {
        Query query = entityManager.createQuery("SELECT DISTINCT t FROM "
                + getEntityClass().getName() + " t, "
                + CriterionImpl.class.getName() + " c"
                + " WHERE c.reference = :reference AND c.theme = t");
        query.setParameter("reference", reference);
        return query.getResultList();
    }

    @Override
    public Collection<Theme> findAllByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                    + getEntityClass().getName() + " r WHERE r.code = :code");
            query.setParameter("code", code);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Theme findByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT n FROM "
                    + getEntityClass().getName() + " n" + " WHERE n.code = :code");
            query.setParameter("code", code);
            return (Theme) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }
}
