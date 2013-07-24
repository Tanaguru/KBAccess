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

public interface ReferenceInfo extends RefComponentWithDepth, Reorderable {

    public String getLabel();

    public void setLabel(String label);

    public Set<? extends ReferenceInfo> getChildren();

    public void setChildren(Set<? extends ReferenceInfo> childrenReferenceInfoSet);

    public ReferenceInfo getParent();

    public void setParent(ReferenceInfo parentReferenceInfo);

    public Set<? extends ReferenceTest> getReferenceTestSet();

    public void setReferenceTestSet(Set<? extends ReferenceTest> referenceTestSet);
}
