package org.opens.kbaccess.entity.factory.subject;

import org.opens.kbaccess.entity.service.subject.TestResultDataServiceImpl;
import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.service.reference.ResultDataService;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.entity.subject.TestResultImpl;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 * 
 * @author jkowalczyk
 */
public class TestcaseFactoryImpl implements TestcaseFactory {

    private TestcaseDataService testcaseDataService;
    private ResultDataService resultDataService;
    private TestResultDataServiceImpl testResultDataService;
    
    public TestcaseFactoryImpl() {
        super();
    }
    
    /*
     * Utility methods
     */

    private Result computeResultFromTestResult(Result result) {
        if ("failed".equals(result.getCode())) {
            return result;
        } else {
            return resultDataService.getByCode("undetermined");
        }
    }
    
    /*
     * Implementation
     */
    
    @Override
    public Testcase create() {
        return new TestcaseImpl();
    }
    
    @Override
    public Testcase createFromTest(
            Account account,
            Webarchive webarchive,
            Result result,
            Test test,
            String description
            ) {
        Testcase tc = createFromCriterion(account, webarchive, result, test.getCriterion(), description);

        tc.addTestResult(testResultDataService.getByTestResult(test, result));
        return tc;
    }

    @Override
    public Testcase createFromCriterion(
            Account account,
            Webarchive webarchive,
            Result result,
            Criterion criterion,
            String description
            ) {
        Testcase tc = create();
        
        tc.setWebarchive(webarchive);
        tc.setAccount(account);
        tc.setResult(computeResultFromTestResult(result));
        tc.setCriterion(criterion);
        tc.setDescription(description);
        tc.setCreationDate(new Date());
        tc.setRank(testcaseDataService.getMaxPriorityFromTable() + 1);
        return tc;
    }

    /*
     * Data service accessors
     */
    
    public TestcaseDataService getTestcaseDataService() {
        return testcaseDataService;
    }

    public void setTestcaseDataService(TestcaseDataService testcaseDataService) {
        this.testcaseDataService = testcaseDataService;
    }

    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }

    public TestResultDataServiceImpl getTestResultDataService() {
        return testResultDataService;
    }

    public void setTestResultDataService(TestResultDataServiceImpl testResultDataService) {
        this.testResultDataService = testResultDataService;
    }

}
