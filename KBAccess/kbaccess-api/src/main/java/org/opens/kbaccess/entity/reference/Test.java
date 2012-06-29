package org.opens.kbaccess.entity.reference;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Test extends Entity, Reorderable {

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criterion
     */
    Criterion getCriterion();

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
     * @return the URL of the page describing the test
     */
    String getUrl();

    /**
     *
     * @return the level
     */
    Level getLevel();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param criterion
     *            the criterion to set
     */
    void setCriterion(Criterion criterion);

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
     *            the url of the page describing the test
     */
    void setUrl(String url);

    /**
     *
     * @param level
     *            the level to set
     */
    void setLevel(Level level);
}
