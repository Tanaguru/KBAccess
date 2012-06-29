package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Test;
import org.opens.kbaccess.entity.reference.TestImpl;

/**
 * 
 * @author jkowalczyk
 */
public class TestFactoryImpl implements TestFactory {

    public TestFactoryImpl() {
        super();
    }

    @Override
    public Test create() {
        return new TestImpl();
    }
}
