/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import java.util.Set;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 *
 * @author blebail
 */
public interface Reference extends RefBase, Reorderable {

    public String getDescription();
    
    public void setDescription(String description);

    public String getLabel();

    public void setLabel(String label);

    public String getUrl();

    public void setUrl(String url);

    public String getCountry();

    public void setCountry(String country);

    public int getInfoMaxDepth();

    public void setInfoMaxDepth(int infoMaxDepth);

    public int getTestMaxDepth();

    public void setTestMaxDepth(int testMaxDepth);
    
    public Set<? extends ReferenceTest> getReferenceTestSet();

    public void setReferenceTestSet(Set<? extends ReferenceTest> referenceTestSet);
}
