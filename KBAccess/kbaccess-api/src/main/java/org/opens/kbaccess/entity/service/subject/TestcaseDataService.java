/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.service.subject;

import java.util.Collection;
import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.reference.*;
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
     * Create a fully initialized testcase.
     * 
     * @param account
     * @param webarchive
     * @param result
     * @param referenceTest
     * @param description
     * @return 
     */
    Testcase createFromTest(
            Account account,
            Webarchive webarchive,
            Result result,
            ReferenceTest referenceTest,
            String description
            );
    
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
     * @param referenceTestSet
     * @param result
     * @return The list of testcases corresponding to the search, an empty list
     *         if there are no results.
     */
    List<Testcase> getAllFromUserSelection (
            Collection<ReferenceTest> referenceTestCollection,
            Result result);

    /**
     * 
     * @return The number of Testcase in database
     */
    Long getCount();
    
}
