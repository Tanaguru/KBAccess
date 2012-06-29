package org.opens.kbaccess.entity.dao.reference;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.TestImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class TestDAOImpl extends AbstractJPADAO<Test, Long> implements TestDAO {

    public TestDAOImpl() {
        super();
    }

    @Override
    protected Class<TestImpl> getEntityClass() {
        return TestImpl.class;
    }

    @Override
    public List<Test> findAll(Reference reference) {
        try {
            Query query = entityManager.createQuery("SELECT t FROM "
                    + getEntityClass().getName() + " t"
                    + " WHERE t.criterion.reference = :reference");
            query.setParameter("reference", reference);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Test> findAll(Criterion criterion) {
        try {
            Query query = entityManager.createQuery("SELECT t FROM "
                    + getEntityClass().getName() + " t"
                    + " WHERE t.criterion.criterion = :criterion");
            query.setParameter("criterion", criterion);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Test> findAllByCode(String[] codeArray) {
        if (codeArray.length == 0) {
            return new ArrayList<Test>();
        }

        // TODO: SQL injection proof & JQL complient
        StringBuilder stringBuilder = new StringBuilder("SELECT t FROM "
                + getEntityClass().getName() + " t" + " WHERE t.code IN (");

        boolean first = true;
        for (String code : codeArray) {
            if (!first) {
                stringBuilder.append(',');
            } else {
                first = false;
            }
            stringBuilder.append('\'').append(code).append('\'');
        }
        stringBuilder.append(')');
        try {
            Query query = entityManager.createQuery(stringBuilder.toString());
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public Test findByCode(String code) {
        try {
            Query query = entityManager.createQuery("SELECT t FROM "
                    + getEntityClass().getName() + " t" + " WHERE t.code = :code");
            query.setParameter("code", code);
            return (Test) query.getSingleResult();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

}
