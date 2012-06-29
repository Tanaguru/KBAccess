package org.opens.kbaccess.entity.dao.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.kbaccess.entity.reference.NomenclatureElement;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureElementDAO extends
        GenericDAO<NomenclatureElement, Long> {

    /**
     * 
     * @param nomenclature
     * @param nomenclatureValue
     * @return 
     * TODO : document
     */
    Collection<NomenclatureElement> findAll(Nomenclature nomenclature,
            String nomenclatureValue);
}
