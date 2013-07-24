/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author blebail
 */
@Entity
@Table(name = "reference_test")
@XmlRootElement
public class ReferenceTestImpl extends AbstractRefComponent implements ReferenceTest, Serializable, Comparable<ReferenceTest> {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REFERENCE_TEST")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CD_REFERENCE_TEST")
    private String code;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LABEL")
    private String label;
    @Basic(optional = false)
    @Column(name = "RANK")
    private int rank;
    @Column(name = "URL")
    private String url;
    
    @JoinTable(name = "reference_test_reference_test", joinColumns = {
        @JoinColumn(name = "ID_REFERENCE_TEST_1", referencedColumnName = "ID_REFERENCE_TEST")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_REFERENCE_TEST_2", referencedColumnName = "ID_REFERENCE_TEST")})
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<ReferenceTestImpl> parents;
    
    @ManyToMany(mappedBy = "parents", fetch=FetchType.EAGER)
    private Set<ReferenceTestImpl> children;
    
    @JoinColumn(name = "ID_REFERENCE_INFO", referencedColumnName = "ID_REFERENCE_INFO")
    @ManyToOne
    private ReferenceInfoImpl referenceInfo;
    
    @JoinColumn(name = "ID_REFERENCE_DEPTH", referencedColumnName = "ID_REFERENCE_DEPTH")
    @ManyToOne(optional = false)
    private ReferenceDepthImpl referenceDepth;
    
    @JoinColumn(name = "ID_REFERENCE", referencedColumnName = "ID_REFERENCE")
    @ManyToOne
    private ReferenceImpl reference;
    
    @JoinColumn(name = "ID_REFERENCE_LEVEL", referencedColumnName = "ID_REFERENCE_LEVEL")
    @ManyToOne
    private ReferenceLevelImpl referenceLevel;
    
    
    public ReferenceTestImpl() {
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public int getRank() {
        return rank;
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
        this.url = url;
    }
    
    @Override
    public ReferenceLevel getReferenceLevel() {
        return this.referenceLevel;
    }

    @Override
    public void setReferenceLevel(ReferenceLevel referenceLevel) {
        this.referenceLevel = (ReferenceLevelImpl)referenceLevel;
    }

    @Override
    public ReferenceInfo getReferenceInfo() {
        return this.referenceInfo;
    }

    @Override
    public void setReferenceInfo(ReferenceInfo referenceInfo) {
        this.referenceInfo = (ReferenceInfoImpl)referenceInfo;
    }

    @Override
    public ReferenceDepth getReferenceDepth() {
        return this.referenceDepth;
    }

    @Override
    public void setReferenceDepth(ReferenceDepth referenceDepth) {
        this.referenceDepth = (ReferenceDepthImpl)referenceDepth;
    }

    @Override
    public Reference getReference() {
        return this.reference;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = (ReferenceImpl)reference;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceTest> getParents() {
        return parents;
    }

    @Override
    public void setParents(Set<? extends ReferenceTest> parents) {
        this.parents = (Set<ReferenceTestImpl>)parents;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceTest> getChildren() {
        return children;
    }

    @Override
    public void setChildren(Set<? extends ReferenceTest> children) {
        this.children = (Set<ReferenceTestImpl>)children;
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
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReferenceTestImpl other = (ReferenceTestImpl) obj;

        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return code;
    }

    @Override
    public int compareTo(ReferenceTest referenceTest) {
        return this.getCode().compareTo(referenceTest.getCode());
    }
}
