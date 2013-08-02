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
public interface ReferenceTest extends RefComponentWithDepth, Reorderable {

    public String getDescription();

    public void setDescription(String description);

    public String getLabel();

    public void setLabel(String label);

    public String getUrl();
    
    public void setUrl(String url);
    
    public void setReference(Reference reference);

    public Reference getReference();
    
    public Set<? extends ReferenceTest> getParents();

    public void setParents(Set<? extends ReferenceTest> parents);

    public Set<? extends ReferenceTest> getChildren();

    public void setChildren(Set<? extends ReferenceTest> children);

    public ReferenceInfo getReferenceInfo();

    public void setReferenceInfo(ReferenceInfo referenceInfo);

    public ReferenceLevel getReferenceLevel();

    public void setReferenceLevel(ReferenceLevel referenceLevel);
}
