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
    @Column(name = "LABEL", nullable=false)
    protected String label;
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description;
    @Column(name = "URL", nullable=true)
    protected String url;
    @Column(name = "PRIORITY", nullable=false)
    protected int rank;
    @ManyToOne
    @JoinColumn(name = "ID_CRITERION", nullable = false)
    protected CriterionImpl criterion;

    public TestImpl() {
        super();
    }

    public TestImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.CriterionImpl.class)
    public Criterion getCriterion() {
        return this.criterion;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl) criterion;
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
