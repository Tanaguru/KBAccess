/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author blebail
 */
@Entity
@Table(name = "reference_depth")
@XmlRootElement
public class ReferenceDepthImpl extends AbstractRefComponent implements ReferenceDepth, Serializable, Comparable<ReferenceDepth> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REFERENCE_DEPTH")
    private Long id;
    @Column(name = "CD_REFERENCE_DEPTH")
    private String code;
    @Column(name = "DEPTH")
    private Integer depth;
    @Column(name = "RANK")
    private Integer rank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceDepth")
    private Set<ReferenceInfoImpl> referenceInfoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceDepth")
    private Set<ReferenceTestImpl> referenceTestSet;

    public ReferenceDepthImpl() {
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
        return this.code;
    }

    @Override
    public void setLabel(String label) {
    }
    
    @Override
    public Integer getDepth() {
        return depth;
    }

    @Override
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceInfo> getReferenceInfoSet() {
        return referenceInfoSet;
    }

    @Override
    public void setReferenceInfoSet(Set<? extends ReferenceInfo> referenceInfoSet) {
        this.referenceInfoSet = (Set<ReferenceInfoImpl>)referenceInfoSet;
    }

    @XmlTransient
    @Override
    public Set<? extends ReferenceTest> getReferenceTestSet() {
        return referenceTestSet;
    }

    @Override
    public void setReferenceTestSet(Set<?extends ReferenceTest> referenceTestSet) {
        this.referenceTestSet = (Set<ReferenceTestImpl>)referenceTestSet;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final ReferenceDepthImpl other = (ReferenceDepthImpl) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(ReferenceDepth referenceDepth) {
        return (this.getDepth().compareTo(referenceDepth.getDepth()) * -1);
    }
}
