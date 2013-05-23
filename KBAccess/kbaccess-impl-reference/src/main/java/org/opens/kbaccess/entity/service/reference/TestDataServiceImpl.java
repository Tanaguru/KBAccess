package org.opens.kbaccess.entity.service.reference;

import java.util.List;
import org.opens.kbaccess.entity.dao.reference.TestDAO;
import org.opens.kbaccess.entity.reference.Criterion;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class TestDataServiceImpl extends AbstractGenericDataService<Test, Long>
        implements TestDataService {

    public TestDataServiceImpl() {
        super();
    }

    @Override
    public List<Test> getTestListFromReference(Reference reference) {
        return ((TestDAO) entityDao).findAll(reference);
    }

    @Override
    public List<Test> getTestListFromCriterion(Criterion criterion) {
        return ((TestDAO) entityDao).findAll(criterion);
    }

    @Override
    public List<Test> getAllByCode(String[] codeArray) {
        return ((TestDAO) entityDao).findAllByCode(codeArray);
    }

    @Override
    public Test getByCode(String code) {
        return ((TestDAO) entityDao).findByCode(code);
    }
    
    @Override
    public Test getByLabelAndReferenceCode(String label, String referenceCode) {
        return ((TestDAO) entityDao).findByLabelAndReferenceCode(label, referenceCode);
    }

}
