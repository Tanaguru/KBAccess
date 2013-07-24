package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.ReferenceTest;
import org.opens.kbaccess.entity.reference.ReferenceTestImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ReferenceTestDAOImpl extends AbstractJPADAO<ReferenceTest, Long> implements
        ReferenceTestDAO {

    public ReferenceTestDAOImpl() {
        super();
    }

    @Override
    protected Class<ReferenceTestImpl> getEntityClass() {
        return ReferenceTestImpl.class;
    }
}
