package org.opens.kbaccess.entity.dao.subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
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

    /*
     * Utilities
     */
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

    private Query selectTestcases(String where) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT tc FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" tc ");
        sb.append(where);
        LogFactory.getLog(TestcaseDAOImpl.class).info(sb.toString());
        return entityManager.createQuery(sb.toString());
    }
    
    /*
     * Implementation
     */
    public TestcaseDAOImpl() {
        super();
    }

    @Override
    protected Class<TestcaseImpl> getEntityClass() {
        return TestcaseImpl.class;
    }
    
    @Override
    public int findMaxPriorityValueFromTable() {
        try {
            Query query = entityManager.createQuery("SELECT MAX(t.rank) FROM "
                    + getEntityClass().getName() + " t");
            return (Integer) (query.getSingleResult());
        } catch (NoResultException e) {
            // In case of query with no result, return 0
            return 0;
        }
    }

    @Override
    public List<Testcase> findAllFromAccount(Account account) {
        List<Testcase> result;
        
        Query query = selectTestcases("WHERE tc.account = :account"
                + " ORDER BY tc.creationDate desc");
        query.setParameter("account", account);

        result = query.getResultList();

        return result;
    }

    @Override
    public List<Testcase> findLastTestcasesFromAccount(
            Account account,
            int nbOfTestcases) {
            
        Query query = selectTestcases("WHERE tc.account = :account"
                + " ORDER BY tc.creationDate desc");
        query.setParameter("account", account);
        query.setMaxResults(nbOfTestcases);
        return query.getResultList();
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
        
        return result;
    }

    @Override
    public List<Testcase> findAll() {
        Query query = selectTestcases("ORDER BY tc.referenceTest asc, tc.result asc, tc.creationDate asc");
        return query.getResultList();
    }

    @Override
    public List<Testcase> findAllFromUserSelection(
            Collection<ReferenceTest> referenceTestCollection,
            Result result) {

        List<Testcase> testcases = null;
        boolean hasResult = (result != null);
        boolean hasReferenceTest = (referenceTestCollection != null && !referenceTestCollection.isEmpty());          
          
        Logger.getLogger(this.getClass().getName()).debug("Colection<referenceTest> of query : " + referenceTestCollection);
                
        //Query
        int nbTableAdded = 1;
        int nbWhereClauseAdded = 0;
        Query query;
        StringBuilder request = new StringBuilder();
        StringBuilder whereClause = new StringBuilder();

        if (hasReferenceTest) {
            addWhereClause(whereClause, "tc.referenceTest in (?1)", nbWhereClauseAdded++);
        }

        if (hasResult) {
            addTable(request, ResultImpl.class.getName(), "res", nbTableAdded++);
            addWhereClause(whereClause, "tc.result = res AND res = :result", nbWhereClauseAdded++);
        }

        if (nbWhereClauseAdded == 0) {
            // if there are no research parameters, return all the testcases.
            return findAll();
        }
        
        // at this point, request contains the tables, we now add the where
        // clause and the order by statement before creating the query
        request.append(" WHERE ").append(whereClause) ;
        request.append(" ORDER BY tc.referenceTest asc, tc.result asc, tc.creationDate asc");
        query = selectTestcases(request.toString());
        
        if (hasReferenceTest) {
            query.setParameter("1", referenceTestCollection);
        }
        
        if (hasResult) {
            query.setParameter("result", result);
        }
        
        // Get the result list of the request
        testcases = query.getResultList();
        
        return ((testcases == null) ? new ArrayList<Testcase>() : testcases);
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery(
                "SELECT COUNT(*) FROM " + getEntityClass().getName()
                );
        
        
        return (Long) query.getSingleResult();
    }
}
