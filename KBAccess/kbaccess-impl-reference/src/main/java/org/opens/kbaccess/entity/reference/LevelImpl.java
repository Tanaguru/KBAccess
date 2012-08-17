package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "level")
@XmlRootElement
public class LevelImpl implements Level, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_LEVEL")
    protected Long id;
    @Column(name = "CD_LEVEL")
    protected String code;
    @Column(name = "LABEL")
    protected String label;
    @Column(name = "DESCRIPTION")
    protected String description;
    @Column(name = "PRIORITY")
    protected int rank;

    public LevelImpl() {
        super();
    }

    public LevelImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public String getCode() {
        return code;
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
