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
    @Column(name = "DESCRIPTION", nullable=true)
    protected String description = null;
    @Column(name = "LABEL", nullable = true)
    protected String label = null;
    @Column(name = "PRIORITY", nullable = false)
    protected int rank;

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

}
