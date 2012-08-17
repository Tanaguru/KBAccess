package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "reference")
@XmlRootElement
public class ReferenceImpl implements Reference, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_REFERENCE", nullable=false)
    protected Long id;
    @Column(name = "CD_REFERENCE", nullable=false)
    protected String code;
    @Column(name = "CD_CRITERION_LEVEL_NAME", nullable=false)
    protected String codeCriterion;
    @Column(name = "CD_TEST_LEVEL_NAME", nullable=false)
    protected String codeTest;
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description;
    @Column(name = "LABEL", nullable = false)
    protected String label;
    @Column(name = "PRIORITY", nullable=false)
    protected int rank;
    @Column(name = "URL", nullable=true)
    protected String url;
    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    protected List<CriterionImpl> criterionList = new ArrayList<CriterionImpl>();

    public ReferenceImpl() {
        super();
    }

    public ReferenceImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public void addCriterion(Criterion criterion) {
        criterion.setReference(this);
        this.criterionList.add((CriterionImpl) criterion);
    }

    public String getCode() {
        return code;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.kbaccess.entity.reference.CriterionImpl.class)
    public List<Criterion> getCriterionList() {
        return (List) criterionList;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getRank() {
        return rank;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = (List) criterionList;
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
