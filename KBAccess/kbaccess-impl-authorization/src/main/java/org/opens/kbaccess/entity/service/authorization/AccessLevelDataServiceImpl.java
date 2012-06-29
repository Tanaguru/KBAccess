/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.service.authorization;

import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 *
 * @author amchaar
 */
public class AccessLevelDataServiceImpl extends 
        AbstractGenericDataService<AccessLevel, Long>
        implements AccessLevelDataService {

    public AccessLevelDataServiceImpl(){
        super();
    }
}
