package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.ReferenceTestImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceTestFactoryImpl implements ReferenceTestFactory {

    public ReferenceTestFactoryImpl() {
        super();
    }

    @Override
    public ReferenceTest create() {
        return new ReferenceTestImpl();
    }
}
