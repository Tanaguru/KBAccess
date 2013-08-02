package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.ReferenceDepth;
import org.opens.kbaccess.entity.reference.ReferenceDepthImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceDepthFactoryImpl implements ReferenceDepthFactory {

    public ReferenceDepthFactoryImpl() {
        super();
    }

    @Override
    public ReferenceDepth create() {
        return new ReferenceDepthImpl();
    }
}
