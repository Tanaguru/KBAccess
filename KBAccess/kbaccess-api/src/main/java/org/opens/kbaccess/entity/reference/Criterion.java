package org.opens.kbaccess.entity.reference;

import java.util.List;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface Criterion extends Entity, Reorderable {

    /**
     *
     * @param test A test to add
     */
    void addTest(Test test);

    /**
     *
     * @return The unique code of the criteria
     */
    String getCode();

    /**
     *
     * @return Its description
     */
    String getDescription();

    /**
     *
     * @return Its label, aka. its name
     */
    String getLabel();

    /**
     *
     * @return the url of the criterion documentation
     */
    String getUrl();

    /**
     *
     * @return Its reference
     */
    Reference getReference();

    /**
     *
     * @return The associated tests
     */
    List<Test> getTestList();

    /**
     *
     * @return the theme
     */
    Theme getTheme();
    
    /**
     * 
     * @return the level
     */
    Level getLevel();

    /**
     *
     * @param code The new unique code of the criterion
     */
    void setCode(String code);

    /**
     *
     * @param description The description to set
     */
    void setDescription(String description);

    /**
     *
     * @param label The label to set
     */
    void setLabel(String label);

    /**
     *
     * @param url The url of its documentation to set
     */
    void setUrl(String url);

    /**
     *
     * @param reference The reference to set
     */
    void setReference(Reference reference);

    /**
     *
     * @param tests The new list of tests of the criterion.
     */
    void setTestList(List<Test> tests);

    /**
     *
     * @param theme The theme to set
     */
    void setTheme(Theme theme);
    
    /**
     * 
     * @param level The level
     */
    void setLevel(Level level);
}
