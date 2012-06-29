package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.NomenclatureElement;
import org.opens.kbaccess.entity.reference.NomenclatureElementImpl;

/**
 * 
 * @author jkowalczyk
 */
public class NomenclatureElementFactoryImpl implements
        NomenclatureElementFactory {

    public NomenclatureElementFactoryImpl() {
        super();
    }
    @Override
    public NomenclatureElement create() {
        return new NomenclatureElementImpl();
    }
    
    @Override
    public NomenclatureElement create(String code, String label) {
        return new NomenclatureElementImpl(code, label);
    }
}
