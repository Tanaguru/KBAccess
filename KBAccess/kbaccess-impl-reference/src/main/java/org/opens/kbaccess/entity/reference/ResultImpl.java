/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.kbaccess.entity.reference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "result")
@XmlRootElement
public class ResultImpl implements Result{

    @Id
    @GeneratedValue
    @Column(name = "ID_RESULT", nullable=false)
    protected Long id;
    @Column(name = "CD_RESULT", nullable=false)
    protected String code;
    @Column(name = "LABEL", nullable = true)
    protected String label = null;
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description = null;
    @Column(name = "RANK", nullable = false)
    protected int rank;

    public ResultImpl() {
    }

    public ResultImpl(Long id, String code, int rank) {
        this.id = id;
        this.code = code;
        this.rank = rank;
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
    public String getLabel() {
        return label;
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
    public void setLabel(String label) {
        this.label = label;
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
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ResultImpl other = (ResultImpl) obj;
        
        return this.getCode().equals(other.getCode());
    }
    
    @Override
    public String toString() {
        return code;
    }
}
