/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;
import org.opens.tanaguru.sdk.entity.Reorderable;


/**
 *
 * @author blebail
 */
public interface ReferenceDepth extends RefComponent, Reorderable {

    public Integer getDepth();

    public void setDepth(Integer depth);
}
