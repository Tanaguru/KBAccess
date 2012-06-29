package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceFactoryImpl implements ReferenceFactory {

    public ReferenceFactoryImpl() {
        super();
    }

    @Override
    public Reference create() {
        return new ReferenceImpl();
    }
}
