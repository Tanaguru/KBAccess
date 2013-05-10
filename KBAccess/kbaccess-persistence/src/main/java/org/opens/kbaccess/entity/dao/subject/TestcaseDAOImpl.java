package org.opens.kbaccess.entity.dao.subject;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.subject.TestResultImpl;
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
        LogFactory.getLog(TestcaseDAOImpl.class).info(sb.toString());
        return entityManager.createQuery(sb.toString());
    }

    @Override
    public Testcase read(Long id, boolean fetch) {
        Testcase testcase = read(id);
        
        if (fetch) {
            testcase.getTestResults().size();
        }
        return testcase;
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
        List<Testcase> result;
        
        try {
            Query query = selectTestcases("WHERE tc.account = :account"
                    + " ORDER BY tc.criterion asc, tc.result asc, tc.creationDate asc");
            query.setParameter("account", account);
          
            result = query.getResultList();
           
            return result;
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
                    + " ORDER BY tc.creationDate desc");
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
        List<Testcase> result;
        Query query;
        
        query = selectTestcases(
                "ORDER BY tc.creationDate desc"
                );
        query.setMaxResults(nbOfTestcases);
        result = query.getResultList();
        // TODO : optional fetching of testResults
        // NOTE : While using a MaxResult, it may be better to
        //        use a loop, since hibernate may do the same.
        for (Testcase testcase : result) {
            testcase.getTestResults().size();
        }
        return result;
    }

    @Override
    public List<Testcase> findAll() {
        try {
            Query query = selectTestcases("ORDER BY tc.criterion asc, tc.result asc, tc.creationDate asc");
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
        List<Testcase> testcases;
        // the Testcase table will be added necessarly at the end of the process
        int nbTableAdded = 1;
        int nbWhereClauseAdded = 0;
        boolean hasReference = (reference != null);
        boolean hasTheme = (theme != null);
        boolean hasCriterion = (criterion != null);
        boolean hasLevel = (level != null);
        boolean hasTest = (test != null);
        boolean hasResult = (result != null);

        boolean isSelectionValid = (
                (!hasTest || (
                    // a test imply a criterion
                    (hasTest ^ hasCriterion || test.getCriterion().getId().equals(criterion.getId())) &&
                    // a test imply a reference
                    (hasTest ^ hasReference || test.getCriterion().getReference().getId().equals(reference.getId())) &&
                    // a test imply a theme
                    (hasTest ^ hasTheme || test.getCriterion().getTheme().getId().equals(theme.getId())) &&
                    // a test imply a level
                    (hasTest ^ hasLevel || test.getCriterion().getLevel().getId().equals(level.getId()))
                )) && (!hasCriterion || (
                    // a criterion imply a reference
                    (hasCriterion ^ hasReference || criterion.getReference().getId().equals(reference.getId())) &&
                    // a criterion imply a theme
                    (hasCriterion ^ hasTheme || criterion.getTheme().getId().equals(theme.getId())) &&
                    // a criterion imply a level
                    (hasCriterion ^ hasLevel || criterion.getLevel().getId().equals(level.getId()))
                )) && (!hasTheme || (
                    // a theme imply a reference
                    (hasTheme ^ hasReference || theme.getCriterionList().get(0).getReference().getId().equals(reference.getId()))
                ))
                // a level imply a reference
                // FIXME: we have no way to check that the given level is associated with some criterion of the given reference
                // && (hasLevel == false ||
                    //((hasLevel ^ hasReference || true 
                //))
                );
        
        
        // validate request
        if (!isSelectionValid) {
            Logger.getLogger(this.getClass()).debug("SelectionInValid");
            return null;
        }
        Logger.getLogger(this.getClass()).debug("SelectionValid");
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
            addWhereClause(whereClause,  "c = :criterion AND c = tc.criterion", nbWhereClauseAdded++);
        } else {
            if (hasReference || hasTheme || hasLevel) {
                addTable(request, CriterionImpl.class.getName(), "c", nbTableAdded++);
                addWhereClause(whereClause,  "c = tc.criterion", nbWhereClauseAdded++);
            }
        }
        if (hasLevel) {
            addTable(request, LevelImpl.class.getName(), "l", nbTableAdded++);
            addWhereClause(whereClause,  "l = :level AND l = c.level", nbWhereClauseAdded++);
        }
        if (hasTest) {
            addTable(request, TestImpl.class.getName(), "t", nbTableAdded++);
            addTable(request, TestResultImpl.class.getName(), "tres", nbTableAdded++);
            addWhereClause(whereClause,  "tres MEMBER OF tc.testResults AND t = :test AND t = tres.test", nbWhereClauseAdded++);
//        } else {
//            if (hasLevel || hasCriterion || hasReference || hasTheme) {
//                addTable(request, TestImpl.class.getName(), "t", nbTableAdded++);
//                addWhereClause(whereClause,  "t = tc.test", nbWhereClauseAdded++);
//            }
        }
        if (hasResult) {
            addTable(request, ResultImpl.class.getName(), "res", nbTableAdded++);
            addWhereClause(whereClause, "res = :result", nbWhereClauseAdded++);
            if (hasTest) {
                // The user is looking for a test with the given result
                // the table test_result (tres) should have been added previously
                addWhereClause(whereClause,  "tres MEMBER OF tc.testResults AND res = tres.result", nbWhereClauseAdded++);
            } else {
                // The user is looking for a testcase (so a criterion) with the given result
                addWhereClause(whereClause,  "res = tc.result", nbWhereClauseAdded++);                
            }
        }

        // process request
        if (nbWhereClauseAdded == 0) {
            // if there are no research criteria, return all the testcases.
            return findAll();
        }
        // at this point, request contains the tables, we now add the where
        // clause and the order by statement before creating the query
        request.append(" WHERE ").append(whereClause);
        request.append(" ORDER BY tc.criterion asc, tc.result asc, tc.creationDate asc");
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
//        System.out.println(query.toString());
//        Logger.getLogger(this.getClass()).debug(query.toString());
        testcases = query.getResultList();
        return testcases;
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery(
                "SELECT COUNT(*) FROM " + getEntityClass().getName()
                );
        
        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException ex) {
            LogFactory.getLog(TestcaseDAOImpl.class).error("Count of testcase failed", ex);
            return 0L;
        }
    }
}
