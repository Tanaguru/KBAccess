package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.dao.reference.LevelDAO;
import org.opens.kbaccess.entity.reference.Level;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class LevelDataServiceImpl extends AbstractGenericDataService<Level, Long>
        implements LevelDataService {

    public LevelDataServiceImpl() {
        super();
    }

    @Override
    public Level getByCode(String code) {
        return ((LevelDAO) entityDao).findByCode(code);
    }
}
