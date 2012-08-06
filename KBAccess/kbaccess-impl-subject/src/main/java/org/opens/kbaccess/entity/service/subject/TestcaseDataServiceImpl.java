package org.opens.kbaccess.entity.service.subject;

import java.util.ArrayList;
import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.subject.TestcaseDAO;
import org.opens.kbaccess.entity.reference.*;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class TestcaseDataServiceImpl extends AbstractGenericDataService<Testcase, Long>
        implements TestcaseDataService {

    public TestcaseDataServiceImpl(){
        super();
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

}