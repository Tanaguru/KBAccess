package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;
import java.util.List;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Theme extends Entity, Reorderable {

    /**
     * 
     * @param criterion
     */
    void addCriterion(Criterion criterion);

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criteria of this theme
     */
    List<Criterion> getCriterionList();

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the label
     */
    String getLabel();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param criteria
     *            the criteria to set
     */
    void setCriterionList(List<Criterion> criteria);

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param label
     *            the label to set
     */
    void setLabel(String label);
}
