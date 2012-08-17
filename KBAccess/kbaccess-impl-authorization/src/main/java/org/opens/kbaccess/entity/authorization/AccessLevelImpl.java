/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.authorization;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amchaar
 */
@Entity()
@Table(name = "access_level")
@XmlRootElement
public class AccessLevelImpl implements AccessLevel, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ACCESS_LEVEL")
    protected Long id;
    @Column(name = "CD_ACCESS_LEVEL")
    protected String accessLevel;
    @Column(name = "LABEL")
    protected String label;
    @Column(name = "DESCRIPTION")
    protected String description;
    @Column(name = "PRIORITY")
    protected int priority;

    @Override
    public String getCdAccessLevel() {
        return accessLevel;
    }

    @Override
    public void setCdAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description ;
    }

    @Override
    public int getPriority() {
        return priority ;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority ;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id ;
    }

    @Override
    public int getRank() {
        return getPriority();
    }

    @Override
    public void setRank(int rank) {
        setPriority(rank);
    }

    @Override
    public AccessLevelEnumType getAccessLevelEnumType() {
        return AccessLevelEnumType.getEnumFromString(getCdAccessLevel());
    }

}
