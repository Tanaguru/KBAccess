/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.subject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "webarchive")
@XmlRootElement
public class WebarchiveImpl implements Webarchive, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "webarchive_id")
    protected Long id;
    @Column(name = "webarchive_description")
    protected String description;
    @Column(name = "webarchive_url")
    protected String url;
    @ManyToOne
    @JoinColumn(name = "webarchive_id_webresource")
    protected WebresourceImpl webresource;
    @Column(name = "webarchive_priority")
    protected int priority;
    @Column(name = "webarchive_date_c")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date date;
    @Column(name = "webarchive_scope")
    protected String scope;
    // TODO: add mapping / entity / interface for languagetag
    // TODO: add mapping / entity / interface for nomenclature/nomenclature-element

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
        return priority;
    }

    @Override
    public void setRank(int priority) {
        this.priority = priority;
    }

    @XmlElementRef(type = org.opens.slurpmanager.entity.subject.WebresourceImpl.class)
    @Override
    public Webresource getWebresourceParent() {
        return webresource;
    }

    @Override
    public void setWebresourceParent(Webresource webresource) {
        this.webresource = (WebresourceImpl)webresource;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

}
