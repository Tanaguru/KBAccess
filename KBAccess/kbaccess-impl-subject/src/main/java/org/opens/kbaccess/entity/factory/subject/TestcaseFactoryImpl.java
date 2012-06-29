package org.opens.kbaccess.entity.factory.subject;

import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.TestcaseImpl;

/**
 * 
 * @author jkowalczyk
 */
public class TestcaseFactoryImpl implements TestcaseFactory {

    public TestcaseFactoryImpl() {
        super();
    }

    @Override
    public Testcase create() {
        return new TestcaseImpl();
    }
}
