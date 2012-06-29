package org.opens.kbaccess.entity.service.reference;

import java.util.List;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface CriterionDataService extends
		GenericDataService<Criterion, Long> {

    Criterion getByCode(String code);

    List<Criterion> getCriterionListFromReference(Reference ref);

    List<Criterion> getCriterionListFromTheme(Theme theme);
}
