package org.opens.kbaccess.entity.factory.subject;

import java.util.Date;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.subject.WebarchiveDataService;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.entity.subject.WebarchiveImpl;

/**
 * 
 * @author jkowalczyk
 */
public class WebarchiveFactoryImpl implements WebarchiveFactory {

    private WebarchiveDataService webarchiveDataService;
    
    public WebarchiveFactoryImpl() {
        super();
    }

    @Override
    public Webarchive create() {
        return new WebarchiveImpl();
    }

    public Webarchive create(Account account, String url, String description) {
        Webarchive ofthejedi = create();
        
        ofthejedi.setAccount(account);
        ofthejedi.setCreationDate(new Date());
        ofthejedi.setDescription(description);
        ofthejedi.setRank(webarchiveDataService.getMaxPriorityFromTable() + 1);
        ofthejedi.setScope("page");
        ofthejedi.setUrl(url);
        return ofthejedi;
    }

    public WebarchiveDataService getWebarchiveDataService() {
        return webarchiveDataService;
    }

    public void setWebarchiveDataService(WebarchiveDataService webarchiveDataService) {
        this.webarchiveDataService = webarchiveDataService;
    }
}
