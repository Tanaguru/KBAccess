package org.opens.kbaccess.entity.service.reference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.opens.kbaccess.entity.reference.RefComponent;
import org.opens.kbaccess.entity.reference.Reference;

/**
 * 
 * @author blebail
 * @version 1.0.0
 */
public interface RefComponentDataService<E extends RefComponent, K extends Serializable> 
    extends RefBaseDataService<E, K> {

     /**
      * 
      * @param reference
      * @param referenceDepth
      * @return 
      */
     Collection<E> getAllByReference(Reference reference);
     
     /**
      * 
      * @param code
      * @return 
      */
     public Reference getReferenceOf(String refComponentCode);

     /**
      * 
      * @param code
      * @return 
      */
     public Reference getReferenceOf(RefComponent refComponent);
     
     /**
      * Only a getter because the map is loaded at the initialization of the application
      * The data it stores are read only and should not be changed
      * 
      * @return 
      */
     public Map<Reference, Map<String, E>> getInternMap();
}
