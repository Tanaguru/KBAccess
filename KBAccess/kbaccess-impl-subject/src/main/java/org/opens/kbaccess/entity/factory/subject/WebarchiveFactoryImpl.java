package org.opens.kbaccess.entity.factory.subject;

import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.entity.subject.WebarchiveImpl;

/**
 * 
 * @author jkowalczyk
 */
public class WebarchiveFactoryImpl implements WebarchiveFactory {

    public WebarchiveFactoryImpl() {
        super();
    }

    @Override
    public Webarchive create() {
        return new WebarchiveImpl();
    }
}
