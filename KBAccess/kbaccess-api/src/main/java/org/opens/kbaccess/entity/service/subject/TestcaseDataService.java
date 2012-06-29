/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.service.subject;

import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Level;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author jkowalczyk
 */
public interface TestcaseDataService
        extends GenericDataService<Testcase, Long> {


    /**
     * Finds a testcase from an Id
     * @param id
     * @return the testcase corresponding to the id
     */
    Testcase getById(Long id);

    /**
     *
     * @return
     */
    int getMaxPriorityFromTable();

    /**
     *
     * @param account
     * @return All the testcases created by a user or null if there are none.
     */
    List<Testcase> getAllFromAccount(Account account);

    /**
     *
     * @return an empty instance of testcase
     */
    Testcase createTestcase();

    /**
     *
     * @param account
     * @param webarchive
     * @param result
     * @param test
     * @param description
     * @return an initialized instance of testcase
     */
    Testcase createTestcase(
            Account account,
            Webarchive webarchive,
            Result result,
            Test test,
            String description);


    /**
     *
     * @param account
     * @return the last five testcases created by a user or an empty list if
     *         there are no results
     */
    List<Testcase> getLastTestcasesFromAccount(Account account, int nbOfTestcases);

    /**
     *
     * @param account
     * @return the last five created testcases or an empty list if
     *         there a no results.
     */
    List<Testcase> getLastTestcases(int nbOfTestcases);

    /**
     *
     * @param reference
     * @param criterion
     * @param theme
     * @param test
     * @param level
     * @param result
     * @return The list of testcases corresponding to the search, an empty list
     *         if there are no results.
     */
    public List<Testcase> getAllFromUserSelection (
            Reference reference,
            Criterion criterion,
            Theme theme,
            Test test,
            Level level,
            Result result);

}
