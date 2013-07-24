package org.opens.kbaccess.entity.service.subject;

import java.util.ArrayList;
import java.util.Collection;
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

    private ResultDataService resultDataService;

    public TestcaseDataServiceImpl(){
        super();
    }
    
    @Override
    public Testcase createFromTest(Account account, Webarchive webarchive, Result result, ReferenceTest test, String description) {
        int rank = ((TestcaseDAO) entityDao).findMaxPriorityValueFromTable(); 
        Testcase tc = ((TestcaseFactory) entityFactory).createFromTest(account, webarchive, result, test, description, rank);
        return tc;
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
            Collection<ReferenceTest> referenceTestCollection,
            Result result) {
        return ((TestcaseDAO) entityDao).findAllFromUserSelection(
                    referenceTestCollection,
                    result);
    }

    @Override
    public Long getCount() {
        return ((TestcaseDAO) entityDao).count();
    }
    
    public ResultDataService getResultDataService() {
        return resultDataService;
    }

    public void setResultDataService(ResultDataService resultDataService) {
        this.resultDataService = resultDataService;
    }
}