package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.dao.reference.ReferenceDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class ReferenceDataServiceImpl extends AbstractGenericDataService<Reference, Long> implements
        ReferenceDataService {

    public ReferenceDataServiceImpl() {
        super();
    }

    @Override
    public Reference read(Long key) {
        Reference entity = super.read(key);
        // fetch criteria
        entity.getCriterionList().size();
        return entity;
    }

    @Override
    public Reference getByCode(String code) {
        return ((ReferenceDAO) entityDao).findByCode(code);
    }

    @Override
    public Long getCount() {
        return ((ReferenceDAO) entityDao).count();
    }
    
}
