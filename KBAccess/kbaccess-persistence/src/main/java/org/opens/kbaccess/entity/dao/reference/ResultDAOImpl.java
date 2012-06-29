package org.opens.kbaccess.entity.dao.reference;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ResultDAOImpl extends AbstractJPADAO<Result, Long> implements
        ResultDAO {

    public ResultDAOImpl() {
        super();
    }

    @Override
    protected Class<ResultImpl> getEntityClass() {
        return ResultImpl.class;
    }

    @Override
    public Result findByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT n FROM "
                    + getEntityClass().getName() + " n" + " WHERE n.code = :code");
            query.setParameter("code", code);
            return (Result) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }
}
