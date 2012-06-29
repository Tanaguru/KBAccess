/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.factory.authorization;

import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.kbaccess.entity.authorization.AccessLevelImpl;

/**
 *
 * @author amchaar
 */
public class AccessLevelFactoryImpl implements AccessLevelFactory {

    public AccessLevelFactoryImpl() {
        super();
    }

    @Override
    public AccessLevel create() {
        return new AccessLevelImpl();
    }
}
