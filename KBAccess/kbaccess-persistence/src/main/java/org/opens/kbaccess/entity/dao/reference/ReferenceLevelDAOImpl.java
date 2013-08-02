package org.opens.kbaccess.entity.dao.reference;

import org.opens.kbaccess.entity.reference.ReferenceLevel;
import org.opens.kbaccess.entity.reference.ReferenceLevelImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

public class ReferenceLevelDAOImpl extends AbstractJPADAO<ReferenceLevel, Long> implements
        ReferenceLevelDAO {

    public ReferenceLevelDAOImpl() {
        super();
    }

    @Override
    protected Class<ReferenceLevelImpl> getEntityClass() {
        return ReferenceLevelImpl.class;
    }
}
