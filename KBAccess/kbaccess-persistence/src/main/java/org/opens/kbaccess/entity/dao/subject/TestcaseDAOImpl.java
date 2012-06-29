package org.opens.kbaccess.entity.dao.subject;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class TestcaseDAOImpl extends AbstractJPADAO<Testcase, Long>
        implements TestcaseDAO {

    public TestcaseDAOImpl() {
        super();
    }

    @Override
    protected Class<TestcaseImpl> getEntityClass() {
        return TestcaseImpl.class;
    }

    private Query selectTestcases(String where) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT tc FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" tc ");
        sb.append(where);
        return entityManager.createQuery(sb.toString());
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
    public List<Testcase> findAllFromAccount(Account account) {
        try {
            Query query = selectTestcases("WHERE tc.account = :account"
                    + " ORDER BY tc.test asc, tc.result asc, tc.date asc");
            query.setParameter("account", account);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Testcase> findLastTestcasesFromAccount(
            Account account,
            int nbOfTestcases) {
        try {
            Query query = selectTestcases("WHERE tc.account = :account"
                    + " ORDER BY tc.date desc");
            query.setParameter("account", account);
            query.setMaxResults(nbOfTestcases);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Testcase> findLastTestcases(int nbOfTestcases) {
        try {
            Query query = selectTestcases("ORDER BY tc.date desc");
            query.setMaxResults(nbOfTestcases);
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    @Override
    public List<Testcase> findAll() {
        try {
            Query query = selectTestcases("ORDER BY tc.test asc, tc.result asc, tc.date asc");
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }

    private void addTable(StringBuilder sb, String tableName, String tableAlias, int thTable) {
        if (thTable > 0) {
            sb.append(" , ");
        }
        sb.append(tableName);
        sb.append(" ").append(tableAlias);
    }
    
    private void addWhereClause(StringBuilder sb, String where, int thClause) {
        if (thClause > 0) {
            sb.append(" AND ");
        }
        sb.append(where);
    }
    
    @Override
    public List<Testcase> findAllFromUserSelection(
            Reference reference,
            Criterion criterion,
            Theme theme,
            Test test,
            Level level,
            Result result) {

        Query query;
        StringBuilder request = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();
        // the Testcase table will be added necessarly at the end of the process
        int nbTableAdded = 1;
        int nbWhereClauseAdded = 0;
        boolean hasReference = (reference != null);
        boolean hasTheme = (theme != null);
        boolean hasCriterion = (criterion != null);
        boolean hasLevel = (level != null);
        boolean hasTest = (test != null);
        boolean hasResult = (result != null);
        
        // create request
        if (hasReference) {
            addTable(request, ReferenceImpl.class.getName(), "r", nbTableAdded++);
            addWhereClause(whereClause,  "r = :reference AND r = c.reference", nbWhereClauseAdded++);
        }
        if (hasTheme) {
            addTable(request, ThemeImpl.class.getName(), "th", nbTableAdded++);
            addWhereClause(whereClause,  "th = :theme AND th = c.theme", nbWhereClauseAdded++);
        }
        if (hasCriterion) {
            addTable(request, CriterionImpl.class.getName(), "c", nbTableAdded++);
            addWhereClause(whereClause,  "c = :criterion AND c = t.criterion", nbWhereClauseAdded++);
        } else {
            if (hasReference || hasTheme) {
                addTable(request, CriterionImpl.class.getName(), "c", nbTableAdded++);
                addWhereClause(whereClause,  "c = t.criterion", nbWhereClauseAdded++);
            }
        }
        if (hasLevel) {
            addTable(request, LevelImpl.class.getName(), "l", nbTableAdded++);
            addWhereClause(whereClause,  "l = :level AND l = t.level", nbWhereClauseAdded++);
        }
        if (hasTest) {
            addTable(request, TestImpl.class.getName(), "t", nbTableAdded++);
            addWhereClause(whereClause,  "t = :test AND t = tc.test", nbWhereClauseAdded++);
        } else {
            if (hasLevel || hasCriterion || hasReference || hasTheme) {
                addTable(request, TestImpl.class.getName(), "t", nbTableAdded++);
                addWhereClause(whereClause,  "t = tc.test", nbWhereClauseAdded++);
            }
        }
        if (hasResult) {
            addTable(request, ResultImpl.class.getName(), "res", nbTableAdded++);
            addWhereClause(whereClause,  "res = :result AND res = tc.result", nbWhereClauseAdded++);
        }

        // process request
        if (nbWhereClauseAdded == 0) {
            // if there are no research criteria, return all the testcases.
            return findAll();
        }
        // at this point, request contains the tables, we now add the where
        // clause and the order by statement before creating the query
        request.append(" WHERE ").append(whereClause);
        request.append(" ORDER BY tc.test asc, tc.result asc, tc.date asc");
        query = selectTestcases(request.toString());
        if (hasReference) {
            query.setParameter("reference", reference);
        }
        if (hasCriterion) {
            query.setParameter("criterion", criterion);
        }
        if (hasTheme) {
            query.setParameter("theme", theme);
        }
        if (hasLevel) {
            query.setParameter("level", level);
        }
        if (hasResult) {
            query.setParameter("result", result);
        }
        if (hasTest) {
            query.setParameter("test", test);
        }
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            // In case of query with no result, return null
            return null;
        }
    }
}
