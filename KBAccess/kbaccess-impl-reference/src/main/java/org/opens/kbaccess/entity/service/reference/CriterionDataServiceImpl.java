package org.opens.kbaccess.entity.service.reference;

import java.util.List;
import org.opens.kbaccess.entity.dao.reference.CriterionDAO;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class CriterionDataServiceImpl extends AbstractGenericDataService<Criterion, Long> implements
        CriterionDataService {

    public CriterionDataServiceImpl() {
        super();
    }

    // FIXME : are the tests of the criterion allways use ?
    @Override
    public Criterion read(Long key) {
        Criterion entity = super.read(key);
        // fetch tests
        entity.getTestList().size();
        return entity;
    }

    @Override
    public Criterion getByCode(String code) {
        return ((CriterionDAO) entityDao).findByCode(code);
    }

    @Override
    public List<Criterion> getCriterionListFromReference(Reference ref) {
        return (List<Criterion>)((CriterionDAO) entityDao).
                findAllFromReference(ref);
    }

    @Override
    public List<Criterion> getCriterionListFromTheme(Theme theme) {
        return (List<Criterion>)((CriterionDAO) entityDao).
                findAllFromTheme(theme);
    }
}
