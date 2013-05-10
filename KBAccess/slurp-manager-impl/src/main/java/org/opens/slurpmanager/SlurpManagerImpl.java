package org.opens.slurpmanager;

import java.util.List;

import org.opens.slurpmanager.exception.WebarchiveCreationException;
import org.opens.slurpmanager.handler.WebarchiveHandler;
import org.opens.slurpmanager.scope.CrawlScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class SlurpManagerImpl implements SlurpManager{

    private static final String APPLICATION_CONTEXT_FILE_PATH = "file:///etc/slurp-manager/context/context.xml";

    private WebarchiveHandler waHandler;
    private boolean isInitialized = false;

    public SlurpManagerImpl(){
    }
    
    public String create(String url, CrawlScope scope, String description)
            throws WebarchiveCreationException{
        initializeSpringContext();
        return waHandler.create(url, scope, description);
    }

    public List<String> retrieveAll() {
        initializeSpringContext();
        return waHandler.retrieveAll();
    }

    public String retrieve(String url) {
        initializeSpringContext();
        return waHandler.retrieve(url);
    }

    public void update(String url) {
        initializeSpringContext();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(String url) {
        initializeSpringContext();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initializeSpringContext(){
        if(!isInitialized) {
            ApplicationContext springApplicationContext =
                    new FileSystemXmlApplicationContext(APPLICATION_CONTEXT_FILE_PATH);
            BeanFactory springBeanFactory = springApplicationContext;
            waHandler = (WebarchiveHandler) springBeanFactory.getBean("webarchiveHandler");
            isInitialized = true;
        }
    }

}

