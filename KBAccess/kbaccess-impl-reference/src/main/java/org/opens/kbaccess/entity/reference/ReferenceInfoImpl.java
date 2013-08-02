/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author blebail
 */
@Entity
@Table(name = "reference_info")
@XmlRootElement
public class ReferenceInfoImpl extends AbstractRefComponent implements ReferenceInfo, Serializable, Comparable<ReferenceInfo> {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_REFERENCE_INFO")
    private Long id;
    @Column(name = "CD_REFERENCE_INFO")
    private String code;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "RANK")
    private Integer rank;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<ReferenceInfoImpl> children;
    
    @JoinColumn(name = "ID_REFERENCE_DEPTH", referencedColumnName = "ID_REFERENCE_DEPTH")
    @ManyToOne(optional = false)
    private ReferenceDepthImpl referenceDepth;
    
    @JoinColumn(name = "ID_REFERENCE_INFO_PARENT", referencedColumnName = "ID_REFERENCE_INFO", nullable = true)
    @ManyToOne(optional = true)
    private ReferenceInfoImpl parent;
    
    @OneToMany(mappedBy = "referenceInfo")
    private Set<ReferenceTestImpl> referenceTestSet;

    public ReferenceInfoImpl() {
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
    public int getRank() {
        return this.rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
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
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceInfo> getChildren() {
        return children;
    }

    @Override
    public void setChildren(Set<? extends ReferenceInfo> children) {
        this.children = (Set<ReferenceInfoImpl>)children;
    }

    @Override
    public ReferenceInfo getParent() {
        return parent;
    }

    @Override
    public void setParent(ReferenceInfo parent) {
        this.parent = (ReferenceInfoImpl)parent;
    }

    @Override
    public ReferenceDepth getReferenceDepth() {
        return referenceDepth;
    }

    @Override
    public void setReferenceDepth(ReferenceDepth idReferenceDepth) {
        this.referenceDepth = (ReferenceDepthImpl)idReferenceDepth;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceTest> getReferenceTestSet() {
        return referenceTestSet;
    }

    @Override
    public void setReferenceTestSet(Set<? extends ReferenceTest> referenceTestSet) {
        this.referenceTestSet = (Set<ReferenceTestImpl>)referenceTestSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final ReferenceInfoImpl other = (ReferenceInfoImpl) obj;
        
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
    public int compareTo(ReferenceInfo referenceInfo) {
        return this.getCode().compareTo(referenceInfo.getCode());
    }
}
