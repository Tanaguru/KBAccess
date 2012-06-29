package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Result;
import org.opens.kbaccess.entity.reference.ResultImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ResultFactoryImpl implements ResultFactory {

    public ResultFactoryImpl() {
        super();
    }

    @Override
    public Result create() {
        return new ResultImpl();
    }
}
