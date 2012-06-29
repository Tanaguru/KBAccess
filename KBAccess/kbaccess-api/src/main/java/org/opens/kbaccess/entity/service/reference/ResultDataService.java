package org.opens.kbaccess.entity.service.reference;

import org.opens.kbaccess.entity.reference.Result;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ResultDataService extends GenericDataService<Result, Long> {

    Result getByCode(String code);
}
