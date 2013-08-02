/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.statistics;

/**
 *
 * @author blebail
 */
public class ReferenceTestStatisticsImpl implements ReferenceTestStatistics {
    
    private Long id;
    private String code;
    private String label;
    private String referenceCode;
    private Long referenceId;
    private String referenceLabel;
    private Long testcaseCount;

    public ReferenceTestStatisticsImpl() {
    }

    public ReferenceTestStatisticsImpl(
            Long id,
            String code,
            String label,
            String referenceCode,
            Long referenceId,
            String referenceLabel,
            Long testcaseCount) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.referenceCode = referenceCode;
        this.referenceId = referenceId;
        this.referenceLabel = referenceLabel;
        this.testcaseCount = testcaseCount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getReferenceCode() {
        return referenceCode;
    }

    @Override
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    @Override
    public Long getReferenceId() {
        return referenceId;
    }

    @Override
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public String getReferenceLabel() {
        return referenceLabel;
    }

    @Override
    public void setReferenceLabel(String referenceLabel) {
        this.referenceLabel = referenceLabel;
    }

    @Override
    public Long getTestcaseCount() {
        return testcaseCount;
    }

    @Override
    public void setTestcaseCount(Long testcaseCount) {
        this.testcaseCount = testcaseCount;
    }

    @Override
    public String toString() {
        return "ReferenceTestStatisticsImpl{" + "code=" + code + ", label=" + label + ", referenceCode=" + referenceCode + ", referenceLabel=" + referenceLabel + ", testcaseCount=" + testcaseCount + '}';
    }
}
