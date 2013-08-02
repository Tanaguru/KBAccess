package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import org.opens.kbaccess.entity.reference.RefBase;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author blebail
 */
public abstract class AbstractRefBaseDataService<E extends RefBase, K extends Serializable> 
        extends AbstractGenericDataService<E, K> implements RefBaseDataService<E, K> {

    protected abstract void initMap();
    
    @Override
    public void setEntityDao(GenericDAO<E, K> entityDao) {
        super.setEntityDao(entityDao);
        initMap();
    }
}

