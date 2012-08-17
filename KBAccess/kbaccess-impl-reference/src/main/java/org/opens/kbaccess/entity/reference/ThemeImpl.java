package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "theme")
@XmlRootElement
public class ThemeImpl implements Theme, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_THEME")
    protected Long id;
    @Column(name = "CD_THEME")
    protected String code;
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    protected List<CriterionImpl> criterionList;
    @Column(name = "LABEL", nullable = false)
    protected String label;
    @Column(name = "DESCRIPTION")
    protected String description;
    @Column(name = "PRIORITY")
    protected int rank;

    public ThemeImpl() {
        super();
        criterionList = new ArrayList<CriterionImpl>();
    }

    public ThemeImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public void addCriterion(Criterion criterion) {
        criterion.setTheme(this);
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
}
