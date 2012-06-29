package org.opens.kbaccess.entity.dao.reference;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.kbaccess.entity.reference.NomenclatureImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class NomenclatureDAOImpl extends AbstractJPADAO<Nomenclature, Long>
        implements NomenclatureDAO {

    public NomenclatureDAOImpl() {
        super();
    }

    @Override
    protected Class<NomenclatureImpl> getEntityClass() {
        return NomenclatureImpl.class;
    }

    @Override
    public Nomenclature findByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT n FROM "
                    + getEntityClass().getName() + " n" + " WHERE n.code = :code");
            query.setParameter("code", code);
            return (Nomenclature) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }
}
