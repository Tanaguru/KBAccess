package org.opens.kbaccess.entity.service.reference;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.opens.kbaccess.entity.reference.Reference;

/**
 * 
 * @author blebail
 */
public class ReferenceDataServiceImpl extends AbstractRefBaseDataService<Reference, Long> implements
        ReferenceDataService {
    
    protected Map<String, Reference> internMap;
    
    public ReferenceDataServiceImpl() {
        super();
        internMap = new LinkedHashMap<String, Reference>();
    }
    
    @Override
    protected void initMap() {
        if (internMap.isEmpty()) {
            Collection<Reference> referenceCollection = entityDao.findAll();
            for (Reference referenceItem : referenceCollection) {
                internMap.put(referenceItem.getCode(), referenceItem);
            }
        }
    }

    @Override
    public Reference getByCode(String code) {
        return internMap.get(code);
    }

    @Override
    public Long getCount() {
        return (long)internMap.size();
    }
    
    @Override
    public Collection<Reference> findAll() {
        return internMap.values();
    }
    
    /*
     * Accessors
     */
    @Override
    public Map<String, Reference> getInternMap() {
        return internMap;
    }
}
