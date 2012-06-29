package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureDataService extends
        GenericDataService<Nomenclature, Long> {

    /**
     *
     * @param code
     *            the code to find from
     * @return the nomenclature found
     */
    Nomenclature getByCode(String code);
}
