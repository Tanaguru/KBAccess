package org.opens.kbaccess.entity.factory.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 * 
 * @author jkowalczyk
 */
public class TestcaseFactoryImpl implements TestcaseFactory {
    
    public TestcaseFactoryImpl() {
        super();
    }
    
    /*
     * Implementation
     */
    
    @Override
    public Testcase create() {
        return new TestcaseImpl();
    }

    @Override
    public Testcase createFromCriterion(
            Account account,
            Webarchive webarchive,
            Result result,
            Criterion criterion,
            String description,
            int rank
            ) {
        Testcase tc = create();
        
        tc.setWebarchive(webarchive);
        tc.setAccount(account);
        tc.setResult(result);
        tc.setCriterion(criterion);
        tc.setDescription(description);
        tc.setCreationDate(new Date());
        tc.setRank(rank);
        return tc;
    }

}
