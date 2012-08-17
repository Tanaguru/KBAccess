package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "criterion")
@XmlRootElement
public class CriterionImpl implements Criterion, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CRITERION", nullable = false)
    protected Long id;
    @Column(name = "CD_CRITERION", nullable = false)
    protected String code;
    @Column(name = "DESCRIPTION", nullable = true)
    protected String description = null;
    @Column(name = "LABEL", nullable = true)
    protected String label = null;
    @Column(name = "URL", nullable = true)
    protected String url = null;
    @Column(name = "PRIORITY", nullable = false)
    protected int priority;
    @ManyToOne
    @JoinColumn(name = "ID_REFERENCE", nullable = false)
    protected ReferenceImpl reference;
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL)
    protected List<TestImpl> testList = new ArrayList<TestImpl>();
    @ManyToOne
    @JoinColumn(name = "ID_THEME", nullable = false)
    protected ThemeImpl theme;
    @ManyToOne
    @JoinColumn(name = "ID_LEVEL", nullable = false)
    protected LevelImpl level;

    public CriterionImpl() {
        super();
    }

    public CriterionImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public void addTest(Test test) {
        test.setCriterion(this);
        this.testList.add((TestImpl) test);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getRank() {
        return priority;
    }

    @Override
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.ReferenceImpl.class)
    public Reference getReference() {
        return reference;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.TestImpl.class)
    public List<Test> getTestList() {
        return (List) testList;
    }

    @Override
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.ThemeImpl.class)
    public Theme getTheme() {
        return theme;
    }

    @Override
    public Level getLevel() {
        return level;
    }    

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setRank(int rank) {
        this.priority = rank;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = (ReferenceImpl) reference;
    }

    @Override
    public void setTestList(List<Test> testList) {
        this.testList = (List) testList;
    }

    @Override
    public void setTheme(Theme theme) {
        this.theme = (ThemeImpl) theme;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setLevel(Level level) {
        this.level = (LevelImpl) level;
    }
    
}
