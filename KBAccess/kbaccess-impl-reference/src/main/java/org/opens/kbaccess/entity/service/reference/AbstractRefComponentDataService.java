package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.opens.kbaccess.entity.reference.RefComponent;
import org.opens.kbaccess.entity.reference.Reference;

/**
 *
 * @author blebail
 */
public abstract class AbstractRefComponentDataService<E extends RefComponent, K extends Serializable> 
        extends AbstractRefBaseDataService<E, K> implements RefComponentDataService<E, K> {
    
    protected ReferenceDataService referenceDataService;
    
    /*
    * This map stores all the E typed entities (At the moment only ReferenceLevel, ReferenceDepth, ReferenceInfo and ReferenceTest) :
    * - by reference and then by code
    * internMapByDepth.get(reference).get(code) allows to find the component of a given reference and a given code
    */
    protected Map<Reference, Map<String, E>> internMap;
    
    public AbstractRefComponentDataService() {
        internMap = new TreeMap<Reference, Map<String, E>>();
    }

    @Override
    public Reference getReferenceOf(String refComponentCode) {
        /* 
         * We split the componentCode based on the '-' character and get the first part which is the code of the reference
         */
        String referenceCode = refComponentCode.split("-")[0];
        return referenceDataService.getByCode(referenceCode);
    }
    
    @Override
    public Reference getReferenceOf(RefComponent refComponent) {
        return getReferenceOf(refComponent.getCode());
    }
    
    @Override
    protected void initMap() {
        if (internMap.isEmpty()) {
            Collection<E> referenceComponentCollection = entityDao.findAll();
            
            for (E referenceComponent : referenceComponentCollection) {
                Reference reference = getReferenceOf(referenceComponent.getCode());
                
                if (internMap.get(reference) == null) {
                    internMap.put(reference, new TreeMap<String, E>());
                }
                
                internMap.get(reference).put(referenceComponent.getCode(), referenceComponent);
            }
        }
    }
    
    @Override
    public E getByCode(String code) {
        Reference reference = getReferenceOf(code);
        
        if (reference == null) {
            return null;
        }
        
        return internMap.get(reference).get(code);
    }

    @Override
    public Long getCount() {
        long count = 0L;
        
        for (Map<String, E> map : internMap.values()) {
            count += map.size();
        }
        
        return count;
    }
    
    @Override
    public Collection<E> getAllByReference(Reference reference) {
        return internMap.get(reference).values();
    }

    @Override
    public Collection<? extends E> findAll() {
        List<E> refComponentCollection = new ArrayList<E>();
        
        for (Map<String, E> map : internMap.values()) {
            for (Map.Entry<String, E> entry : map.entrySet()) {
                refComponentCollection.add(entry.getValue());
            }
        }
        
        return refComponentCollection; 
    }
    
    /*
     * Accessors
     */
    public ReferenceDataService getReferenceDataService() {
        return referenceDataService;
    }

    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }
    
    @Override
    public Map<Reference, Map<String, E>> getInternMap() {
        return internMap;
    }
}

