package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.ReferenceImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ReferenceDAOImpl extends AbstractJPADAO<Reference, Long> implements
        ReferenceDAO {

    public ReferenceDAOImpl() {
        super();
    }

    @Override
    protected Class<ReferenceImpl> getEntityClass() {
        return ReferenceImpl.class;
    }
}
