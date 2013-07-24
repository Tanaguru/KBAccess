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
public interface ReferenceDepth extends RefComponent, Reorderable {

    public Integer getDepth();

    public void setDepth(Integer depth);

    public Set<? extends ReferenceInfo> getReferenceInfoSet();
    
    public void setReferenceInfoSet(Set<? extends ReferenceInfo> referenceInfoSet);

    public Set<? extends ReferenceTest> getReferenceTestSet();
    
    public void setReferenceTestSet(Set<? extends ReferenceTest> referenceTestSet);
}
