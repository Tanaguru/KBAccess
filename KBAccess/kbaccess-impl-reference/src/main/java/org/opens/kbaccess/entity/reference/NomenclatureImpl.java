package org.opens.kbaccess.entity.reference;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "nomenclature")
@XmlRootElement
public class NomenclatureImpl implements Nomenclature, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_NOMENCLATURE")
    protected Long id;
    @Column(name = "CD_NOMENCLATURE", nullable = false)
    protected String code;
    @Column(name = "LABEL")
    protected String label;
    @Column(name = "DESCRIPTION")
    protected String description;
    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL)
    protected Collection<NomenclatureElementImpl> elementList = new HashSet<NomenclatureElementImpl>();
//    @ManyToOne
//    @JoinColumn(name = "Id_Nomenclature_Parent")
//    protected NomenclatureImpl parent;

    public NomenclatureImpl() {
        super();
    }

    public NomenclatureImpl(String code) {
        this();
        this.code = code;
    }

    public void addElement(NomenclatureElement element) {
        element.setNomenclature(this);
        elementList.add((NomenclatureElementImpl) element);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.kbaccess.entity.reference.NomenclatureElementImpl.class)})
    public Collection<NomenclatureElement> getElementList() {
        return (Collection) elementList;
    }

    public Long getId() {
        return id;
    }

//    public Collection<Integer> getIntegerValueList() {
//        Collection<Integer> values = new HashSet<Integer>();
//        for (NomenclatureElement element : elementList) {
////            NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
//            values.add(cssUnitElement.getCssShortValue());
//        }
//        return values;
//    }

    public String getLabel() {
        return label;
    }

    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<String>();
        for (NomenclatureElement element : elementList) {
            values.add(element.getLabel());
        }
        return values;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElementList(
            Collection<NomenclatureElement> elementList) {
        this.elementList = (Collection) elementList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Collection<Integer> getIntegerValueList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
