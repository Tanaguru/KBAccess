/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.service.authorization;

import org.opens.kbaccess.entity.authorization.AccessLevel;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author amchaar
 */
public interface AccessLevelDataService extends
        GenericDataService<AccessLevel, Long>{

    AccessLevel getByCode(String code);
}
