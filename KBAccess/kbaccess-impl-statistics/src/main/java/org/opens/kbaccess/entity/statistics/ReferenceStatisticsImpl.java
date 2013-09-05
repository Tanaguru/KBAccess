/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.statistics;

import org.opens.kbaccess.entity.reference.Reference;

/**
 *
 * @author blebail
 */
public class ReferenceStatisticsImpl implements ReferenceStatistics {
    
    private Reference reference;
    private int coveragePercentage;

    public ReferenceStatisticsImpl() {
    }

    public ReferenceStatisticsImpl(
            Reference reference,
            int coveragePercentage) {
        this.reference = reference;
        this.coveragePercentage = coveragePercentage;
    }

    @Override
    public Reference getReference() {
        return this.reference;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }
    
    @Override
    public int getCoveragePercentage() {
        return this.coveragePercentage;
    }

    @Override
    public void setCoveragePercentage(int coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }
    
    @Override
    public String toString() {
        return "ReferenceStatisticsImpl{" + "code=" + reference.getCode() + ", coveragePercentage=" + coveragePercentage + "}";
    }
}
