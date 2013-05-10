package org.opens.kbaccess.entity.factory.subject;

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface WebarchiveFactory extends GenericFactory<Webarchive> {
    
    /**
     * Return an initialized instance of Webarchive, only the localUrl is
     * left unset.
     * 
     * The rank will be set to the maximum rank in the DB plus one.
     * The scope will be set to "page".
     * 
     * @param account The webarchive's account
     * @param url The webarchive's url
     * @param description The webarchie's description
     * @param rank The rank of the webarchive
     * @return 
     */
    Webarchive create(Account account, String url, String description, int rank);
    
}
