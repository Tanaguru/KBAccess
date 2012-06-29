package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "test")
@XmlRootElement
public class TestImpl implements Test, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_TEST", nullable=false)
    protected Long id;
    @Column(name = "CD_TEST", nullable=false)
    protected String code;
    @ManyToOne
    @JoinColumn(name = "criterion_ID_CRITERION", nullable=false)
    protected CriterionImpl criterion;
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description;
    @Column(name = "LABEL", nullable=false)
    protected String label;
    @ManyToOne
    @JoinColumn(name = "level_ID_LEVEL", nullable=false)
    protected LevelImpl level;
    @Column(name = "PRIORITY", nullable=false)
    protected int rank;
    @Column(name = "URL", nullable=true)
    protected String url;

    public TestImpl() {
        super();
    }

    public TestImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    @XmlElementRef(type = org.opens.kbaccess.entity.reference.CriterionImpl.class)
    public Criterion getCriterion() {
        return this.criterion;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    @XmlElementRef(type = org.opens.kbaccess.entity.reference.LevelImpl.class)
    public Level getLevel() {
        return this.level;
    }

    public int getRank() {
        return rank;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl) criterion;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLevel(Level priority) {
        this.level = (LevelImpl) priority;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url =  url;
    }

}
