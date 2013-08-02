package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceLevelImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceLevelFactoryImpl implements ReferenceLevelFactory {

    public ReferenceLevelFactoryImpl() {
        super();
    }

    @Override
    public ReferenceLevel create() {
        return new ReferenceLevelImpl();
    }
}
