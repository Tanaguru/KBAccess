package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.ReferenceDepth;
import org.opens.kbaccess.entity.reference.ReferenceDepthImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ReferenceDepthDAOImpl extends AbstractJPADAO<ReferenceDepth, Long> implements
        ReferenceDepthDAO {

    public ReferenceDepthDAOImpl() {
        super();
    }

    @Override
    protected Class<ReferenceDepthImpl> getEntityClass() {
        return ReferenceDepthImpl.class;
    }
}
