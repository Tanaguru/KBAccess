package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Theme;
import org.opens.kbaccess.entity.reference.ThemeImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ThemeFactoryImpl implements ThemeFactory {

    public ThemeFactoryImpl() {
        super();
    }

    @Override
    public Theme create() {
        return new ThemeImpl();
    }
}
