/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "webresource")
@XmlRootElement
public class WebresourceImpl implements Webresource, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "webresource_id")
    protected Long id;
    @Column(name = "webresource_url")
    protected String url;
    @Column(name = "webresource_description")
    protected String description;
    @Column(name = "webresource_date_c")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date date;
    @Column(name = "webresource_priority")
    protected int priority;
    @OneToMany(mappedBy = "webresource", cascade = CascadeType.ALL)
    protected List<WebarchiveImpl> webarchiveList = new ArrayList<WebarchiveImpl>();

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
    public List<WebarchiveImpl> getWebarchiveList() {
        return webarchiveList;
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
    public void setWebarchiveList(List<? extends Webarchive> webarchiveList) {
        this.webarchiveList = (List<WebarchiveImpl>)webarchiveList;
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

    @Override
    public void addWebarchive(Webarchive webarchive) {
        webarchive.setWebresourceParent(this);
        this.webarchiveList.add((WebarchiveImpl)webarchive);
    }
}
