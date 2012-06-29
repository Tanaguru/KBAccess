package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureDAO extends GenericDAO<Nomenclature, Long> {

    /**
     *
     * @param code the code of the nomenclature to find
     * @return the nomenclature found, null otherwise
     */
    Nomenclature findByCode(String code);
}
