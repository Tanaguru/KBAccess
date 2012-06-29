package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.kbaccess.entity.reference.NomenclatureImpl;

/**
 * 
 * @author jkowalczyk
 */
public class NomenclatureFactoryImpl implements NomenclatureFactory {

    public NomenclatureFactoryImpl() {
        super();
    }

    @Override
    public Nomenclature create() {
        return new NomenclatureImpl();
    }

    @Override
    public Nomenclature create(String code) {
        return new NomenclatureImpl(code);
    }
}
