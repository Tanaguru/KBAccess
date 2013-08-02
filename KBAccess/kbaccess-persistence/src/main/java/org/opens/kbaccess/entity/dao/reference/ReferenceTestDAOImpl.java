package org.opens.kbaccess.entity.dao.reference;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
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

    @Override
    public List<ReferenceTest> findAll() {
        List<ReferenceTest> referenceTestList = new ArrayList<ReferenceTest>();
        
        // Fully fetch all the ReferenceTest and their relations
        for (ReferenceTest referenceTest : super.findAll()) {
            Hibernate.initialize(referenceTest);
            Hibernate.initialize(referenceTest.getChildren());
            Hibernate.initialize(referenceTest.getParents());
            
            referenceTestList.add(referenceTest);
        }
        
        return referenceTestList;
    }
}
