package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "reference_level")
@XmlRootElement
public class ReferenceLevelImpl extends AbstractRefComponent implements ReferenceLevel, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_REFERENCE_LEVEL")
    protected Long id;
    @Column(name = "CD_REFERENCE_LEVEL")
    protected String code;
    @Column(name = "LABEL")
    protected String label;
    @Column(name = "DESCRIPTION")
    protected String description;
    @Column(name = "RANK")
    protected int rank;

    public ReferenceLevelImpl() {
        super();
    }

    public ReferenceLevelImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
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
        return rank;
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
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final ReferenceLevelImpl other = (ReferenceLevelImpl) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  code;
    }
}
