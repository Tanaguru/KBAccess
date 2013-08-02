package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceInfoImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceInfoFactoryImpl implements ReferenceInfoFactory {

    public ReferenceInfoFactoryImpl() {
        super();
    }

    @Override
    public ReferenceInfo create() {
        return new ReferenceInfoImpl();
    }
}
