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
@Table(name = "reference")
@XmlRootElement
public class ReferenceImpl implements Reference, Serializable, Comparable<Reference> {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REFERENCE")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CD_REFERENCE")
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
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @Column(name = "INFO_MAX_DEPTH")
    private int infoMaxDepth;
    @Basic(optional = false)
    @Column(name = "TEST_MAX_DEPTH")
    private int testMaxDepth;
    
    @OneToMany(mappedBy = "reference")
    private Set<ReferenceTestImpl> referenceTestSet;

    public ReferenceImpl() {
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
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int getInfoMaxDepth() {
        return infoMaxDepth;
    }

    @Override
    public void setInfoMaxDepth(int infoMaxDepth) {
        this.infoMaxDepth = infoMaxDepth;
    }

    @Override
    public int getTestMaxDepth() {
        return testMaxDepth;
    }

    @Override
    public void setTestMaxDepth(int testMaxDepth) {
        this.testMaxDepth = testMaxDepth;
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
        hash = 41 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final ReferenceImpl other = (ReferenceImpl) obj;
 
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
    public int compareTo(Reference reference) {
        return this.getCode().compareTo(reference.getCode());
    }
}
