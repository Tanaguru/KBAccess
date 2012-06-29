package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.kbaccess.entity.reference.NomenclatureElement;
import org.opens.kbaccess.entity.reference.NomenclatureElementImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class NomenclatureElementDAOImpl extends AbstractJPADAO<NomenclatureElement, Long> implements
        NomenclatureElementDAO {

    public NomenclatureElementDAOImpl() {
        super();
    }

    @Override
    protected Class<NomenclatureElementImpl> getEntityClass() {
        return NomenclatureElementImpl.class;
    }

    @Override
    public Collection<NomenclatureElement> findAll(
            Nomenclature nomenclature, String nomenclatureValue) {
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                    + getEntityClass().getName()
                    + " r WHERE r.nomenclatureValue = :nomenclatureValue AND r.nomenclature = :nomenclature");
            query.setParameter("nomenclatureValue", nomenclatureValue);
            query.setParameter("nomenclature", nomenclature);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }
}
