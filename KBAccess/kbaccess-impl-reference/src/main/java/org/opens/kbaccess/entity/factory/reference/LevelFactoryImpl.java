package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Level;
import org.opens.kbaccess.entity.reference.LevelImpl;

/**
 * 
 * @author jkowalczyk
 */
public class LevelFactoryImpl implements LevelFactory {

    public LevelFactoryImpl() {
        super();
    }

    @Override
    public Level create() {
        return new LevelImpl();
    }
}
