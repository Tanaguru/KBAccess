package org.opens.kbaccess.entity.reference;

import java.util.List;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Reference extends Entity, Reorderable {

    /**
     *
     * @param criterion
     *            the criterion to add
     */
    void addCriterion(Criterion criterion);

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criterion list
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
     * @return the url of the page describing the referential
     */
    String getUrl();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param criterionList
     *            the criterion list to set
     */
    void setCriterionList(List<Criterion> criterionList);

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

    /**
     *
     * @param url
     *          the url of the page describing the url
     */
    void setUrl(String url);
}
