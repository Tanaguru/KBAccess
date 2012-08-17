package org.opens.kbaccess.entity.service.subject;

import java.util.List;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.dao.subject.WebarchiveDAO;
import org.opens.kbaccess.entity.factory.subject.WebarchiveFactory;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class WebarchiveDataServiceImpl extends AbstractGenericDataService<Webarchive, Long>
        implements WebarchiveDataService {

    public WebarchiveDataServiceImpl(){
        super();
    }

    @Override
    public List<Webarchive> findAll() {
        return (List<Webarchive>)((WebarchiveDAO) entityDao).findAll();
    }

    @Override
    public List<Webarchive> getAllFromUserAccount(Account account) {
        return ((WebarchiveDAO) entityDao).findAllFromAccount(account);
    }

    @Override
    public int getMaxPriorityFromTable() {
        return ((WebarchiveDAO) entityDao).findMaxPriorityValueFromTable();
    }

    @Override
    public Webarchive createWebarchive() {
        return ((WebarchiveFactory) entityFactory).create();
    }

    @Override
    public int deleteWebArchive(Webarchive webarchive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getCount() {
        return ((WebarchiveDAO) entityDao).count();
    }

}