package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "nomenclature_element")
@XmlRootElement
public class NomenclatureElementImpl implements NomenclatureElement,
        Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_NOMENCLATURE_ELEMENT")
    protected Long id;
    @Column(name = "CD_NOMENCLATURE_ELEMENT")
    protected String code;
    @Column(name = "LABEL", nullable = false)
    protected String label;
    @ManyToOne
    @JoinColumn(name = "ID_NOMENCLATURE", nullable = false)
    protected NomenclatureImpl nomenclature;

    public NomenclatureElementImpl() {
        super();
    }

    public NomenclatureElementImpl(String code, String label) {
        super();
        this.code = code;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    @XmlTransient
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = (NomenclatureImpl) nomenclature;
    }

    public void setLabel(String value) {
        this.label = value;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
