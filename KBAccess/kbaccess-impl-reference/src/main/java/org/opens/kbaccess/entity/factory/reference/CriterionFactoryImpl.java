package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.CriterionImpl;

/**
 * 
 * @author jkowalczyk
 */
public class CriterionFactoryImpl implements CriterionFactory {

    public CriterionFactoryImpl() {
        super();
    }

    @Override
    public Criterion create() {
        return new CriterionImpl();
    }
}
