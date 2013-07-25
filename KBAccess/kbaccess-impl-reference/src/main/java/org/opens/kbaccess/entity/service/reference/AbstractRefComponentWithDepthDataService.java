package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import org.opens.kbaccess.entity.reference.RefComponentWithDepth;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceDepth;

/**
 *
 * @author blebail
 */
public abstract class AbstractRefComponentWithDepthDataService<E extends RefComponentWithDepth, K extends Serializable> 
        extends AbstractRefComponentDataService<E, K> implements RefComponentWithDepthDataService<E, K> {
    /*
     * This map stores all the E typed entities (At the moment only ReferenceInfo or ReferenceTest) :
     * - by Reference and then by ReferenceDepth
     */
    protected Map<Reference, Map<ReferenceDepth, Set<E>>> internMapByDepth;
    
    public AbstractRefComponentWithDepthDataService() {
        super();
        internMapByDepth = new TreeMap<Reference, Map<ReferenceDepth, Set<E>>>();
    }
    
    @Override
    protected void initMap() {
        // Build internMap
        super.initMap();
        
        // Build internMapByDepth from internMap
        if (internMapByDepth.isEmpty()) {
            for (Map.Entry<Reference, Map<String, E>> entry : internMap.entrySet()) {

                Reference reference = entry.getKey();
                internMapByDepth.put(reference, new TreeMap<ReferenceDepth, Set<E>>());


                for (E referenceComponent : entry.getValue().values()) {
                    ReferenceDepth referenceComponentDepth = referenceComponent.getReferenceDepth();

                    // Build set of components for referenceComponent depth                
                    if (internMapByDepth.get(reference).get(referenceComponentDepth) == null) {
                        // New depth, thus new set
                        internMapByDepth.get(reference).put(referenceComponentDepth, new TreeSet<E>());
                    }

                    internMapByDepth.get(reference).get(referenceComponentDepth).add(referenceComponent);
                }
            }
        }
    }
    
    @Override
    public Collection<E> getAllByReferenceAndReferenceDepth(Reference reference, ReferenceDepth referenceDepth) {
        if (reference == null || referenceDepth == null) {
            return new ArrayList<E>();
        }
        
        return internMapByDepth.get(reference).get(referenceDepth);
    }

    @Override
    public Collection<ReferenceDepth> getReferenceDepthsByReference(Reference reference) {
        List<ReferenceDepth> referenceDepths = new ArrayList<ReferenceDepth>();
        
        if (reference != null) {
            referenceDepths.addAll(internMapByDepth.get(reference).keySet());
        }
        
        return referenceDepths;
    }
    
    @Override
    public Collection<? extends E> findAll() {
        List<E> refComponentCollection = new ArrayList<E>();
        
        for (Map<ReferenceDepth, Set<E>> map : internMapByDepth.values()) {
            for (Map.Entry<ReferenceDepth, Set<E>> entry : map.entrySet()) {
                refComponentCollection.addAll(entry.getValue());
            }
        }
        
        return refComponentCollection; 
    }
    
    /*
     * Accessors
     */
    @Override
    public Map<Reference, Map<ReferenceDepth, Set<E>>> getInternMapByDepth() {
        return internMapByDepth;
    }
}

