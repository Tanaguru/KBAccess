package org.opens.kbaccess.entity.factory.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.service.subject.TestcaseDataService;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 * 
 * @author jkowalczyk
 */
public class TestcaseFactoryImpl implements TestcaseFactory {

    private TestcaseDataService testcaseDataService;
    
    public TestcaseFactoryImpl() {
        super();
    }

    @Override
    public Testcase create() {
        return new TestcaseImpl();
    }
    
    @Override
    public Testcase create(
            Account account,
            Webarchive webarchive,
            Result result,
            Test test,
            String description
            ) {
        Testcase tc = create();
        
        tc.setWebarchive(webarchive);
        tc.setAccount(account);
        tc.setResult(result);
        tc.setTest(test);
        tc.setDescription(description);
        tc.setCdTestcase(test.getCode() + '-' + result.getLabel());
        tc.setDateC(new Date());
        tc.setRank(testcaseDataService.getMaxPriorityFromTable() + 1);
        return tc;
    }

    public TestcaseDataService getTestcaseDataService() {
        return testcaseDataService;
    }

    public void setTestcaseDataService(TestcaseDataService testcaseDataService) {
        this.testcaseDataService = testcaseDataService;
    }

}
