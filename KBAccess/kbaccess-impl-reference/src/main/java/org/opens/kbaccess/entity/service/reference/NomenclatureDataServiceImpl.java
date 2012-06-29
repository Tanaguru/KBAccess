package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.dao.reference.NomenclatureDAO;
import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class NomenclatureDataServiceImpl extends AbstractGenericDataService<Nomenclature, Long> implements
        NomenclatureDataService {

    public NomenclatureDataServiceImpl() {
        super();
    }

    @Override
    public Nomenclature getByCode(String code) {
        return ((NomenclatureDAO) entityDao).findByCode(code);
    }

    @Override
    public Nomenclature read(Long key) {
        Nomenclature entity = super.read(key);
        // fetch elements
        entity.getElementList().size();
        return entity;
    }
}
