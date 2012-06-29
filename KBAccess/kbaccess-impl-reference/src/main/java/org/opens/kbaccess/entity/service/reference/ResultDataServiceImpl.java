package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.dao.reference.ResultDAO;
import org.opens.kbaccess.entity.reference.Result;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class ResultDataServiceImpl 
        extends AbstractGenericDataService<Result, Long>
        implements ResultDataService {

    public ResultDataServiceImpl() {
        super();
    }

    @Override
    public Result getByCode(String code) {
        return ((ResultDAO) entityDao).findByCode(code);
    }
}
