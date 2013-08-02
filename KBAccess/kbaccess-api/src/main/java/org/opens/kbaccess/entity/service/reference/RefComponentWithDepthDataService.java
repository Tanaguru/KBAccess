package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceDepth;
import org.opens.kbaccess.entity.reference.RefComponentWithDepth;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface RefComponentWithDepthDataService<E extends RefComponentWithDepth, K extends Serializable>
		extends RefComponentDataService<E, K> {
    
    /**
     * 
     * @param id
     * @return 
     */
    public E findById(Long id);
    
    /**
     * 
     * @param reference
     * @param referenceDepth
     * @return the collection of components for the corresponding reference and reference depth
     */
     Collection<E> getAllByReferenceAndReferenceDepth(Reference reference, ReferenceDepth referenceDepth);
     
    /**
      * Only a getter because the map is loaded at the initialization of the application
      * The data it stores are read only and should not be changed
      * 
      * @return 
      */
     public Map<Reference, Map<ReferenceDepth, Set<E>>> getInternMapByDepth();
     
     /**
      * Return all ReferenceDepths of a reference
      * 
      * @param reference the reference
      * @return 
      */
     public Collection<ReferenceDepth> getReferenceDepthsByReference(Reference reference);
}
