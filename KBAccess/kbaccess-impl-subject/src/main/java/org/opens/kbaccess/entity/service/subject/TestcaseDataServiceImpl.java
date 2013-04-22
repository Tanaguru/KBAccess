package org.opens.kbaccess.entity.service.subject;

import java.util.ArrayList;
import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.subject.TestcaseDAO;
import org.opens.kbaccess.entity.factory.subject.TestcaseFactory;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class TestcaseDataServiceImpl extends AbstractGenericDataService<Testcase, Long>
        implements TestcaseDataService {

    private TestResultDataService testResultDataService;
    private ResultDataService resultDataService;

    /*
     * Utilities
     */
    private Result computeResultFromTestResult(Result result) {
        if ("failed".equals(result.getCode())) {
            return result;
        } else {
            return resultDataService.getByCode("undetermined");
        }
    }
    
    public static String computeTitleFromCriterionAndUrl(Criterion criterion, Webarchive webarchive, Result result) {
        return (criterion.getCode() + " " + result.getLabel() + " sur le site " + webarchive.getUrl());
    }
      
    /*
     * Implementation
     */
    public TestcaseDataServiceImpl(){
        super();
    }
    
    @Override
    public Testcase createFromTest(Account account, Webarchive webarchive, Result result, Test test, String description) {
        int rank = ((TestcaseDAO) entityDao).findMaxPriorityValueFromTable(); 
        String title = computeTitleFromCriterionAndUrl(test.getCriterion(), webarchive, result);
        Testcase tc = ((TestcaseFactory) entityFactory).createFromCriterion(account, title, webarchive, result, test.getCriterion(), description, rank);

        tc.addTestResult(testResultDataService.getByTestResult(test, result));
        return tc;
    }
     
    @Override
    public Testcase createFromCriterion(Account account, Webarchive webarchive, Result result, Criterion criterion, String description) {
        int rank = ((TestcaseDAO) entityDao).findMaxPriorityValueFromTable();
        String title = computeTitleFromCriterionAndUrl(criterion, webarchive, result);
        
        result = computeResultFromTestResult(result);
        return ((TestcaseFactory) entityFactory).createFromCriterion(account, title, webarchive, result, criterion, description, rank);
    }

    @Override
    public Testcase read(Long id, boolean fetch) {
        return ((TestcaseDAO) entityDao).read(id, fetch);
    }
    
    @Override
    public List<Testcase> findAll() {
        return (List<Testcase>)((TestcaseDAO) entityDao).findAll();
    }

    @Override
    public List<Testcase> getAllFromAccount(Account account) {
        return ((TestcaseDAO) entityDao).findAllFromAccount(account);
    }

    @Override
    public int getMaxPriorityFromTable() {
        return ((TestcaseDAO) entityDao).findMaxPriorityValueFromTable();
    }

    @Override
    public List<Testcase> getLastTestcasesFromAccount(Account account, int nbOfTestcases) {
        List<Testcase> tcList = ((TestcaseDAO) entityDao).
                findLastTestcasesFromAccount(account, nbOfTestcases);
        if (tcList != null) {
            return tcList;
        } else {
            return new ArrayList<Testcase>();
        }
    }

    @Override
    public List<Testcase> getLastTestcases(int nbOfTestcases) {
        List<Testcase> tcList =
                ((TestcaseDAO) entityDao).findLastTestcases(nbOfTestcases);
        if (tcList != null) {
            return tcList;
        } else {
            return new ArrayList<Testcase>();
        }
    }

    @Override
    public List<Testcase> getAllFromUserSelection (
            Reference reference,
            Criterion criterion,
            Theme theme,
            Test test,
            Level level,
            Result result) {
        List<Testcase> tcList =
                ((TestcaseDAO) entityDao).findAllFromUserSelection(
                    reference,
                    criterion,
                    theme,
                    test,
                    level,
                    result);
        if (tcList != null) {
            return tcList;
        } else {
            return new ArrayList<Testcase>();
        }
    }

    @Override
    public Long getCount() {
        return ((TestcaseDAO) entityDao).count();
    }

    public TestResultDataService getTestResultDataService() {
        return testResultDataService;
    }

    public void setTestResultDataService(TestResultDataService testResultDataService) {
        this.testResultDataService = testResultDataService;
    }
    
    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }
}