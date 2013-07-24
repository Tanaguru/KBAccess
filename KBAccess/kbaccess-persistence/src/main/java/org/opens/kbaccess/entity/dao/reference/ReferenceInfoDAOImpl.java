package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceInfoImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ReferenceInfoDAOImpl extends AbstractJPADAO<ReferenceInfo, Long> implements
        ReferenceInfoDAO {
    
    public ReferenceInfoDAOImpl() {
        super();
    }

    @Override
    protected Class<ReferenceInfoImpl> getEntityClass() {
        return ReferenceInfoImpl.class;
    }
}
