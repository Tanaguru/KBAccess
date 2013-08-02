package org.opens.kbaccess.entity.dao.reference;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.opens.kbaccess.entity.reference.ReferenceInfoImpl;
import org.opens.kbaccess.entity.reference.ReferenceInfo;
import org.opens.kbaccess.entity.reference.ReferenceTest;
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
    
    @Override
    public List<ReferenceInfo> findAll() {
        List<ReferenceInfo> referenceInfoList = new ArrayList<ReferenceInfo>();
        
        // Fully fetch all the ReferenceInfo and their relations
        for (ReferenceInfo referenceInfo : super.findAll()) {
            Hibernate.initialize(referenceInfo);
            Hibernate.initialize(referenceInfo.getChildren());
            Hibernate.initialize(referenceInfo.getReferenceTestSet());
            
            for (ReferenceTest referenceTest : referenceInfo.getReferenceTestSet()) {
                Hibernate.initialize(referenceTest.getChildren());
                Hibernate.initialize(referenceTest.getParents());
            }
            
            referenceInfoList.add(referenceInfo);
        }
        
        return referenceInfoList;
    }
}
