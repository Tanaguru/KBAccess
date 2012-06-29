package org.opens.kbaccess.entity.subject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringEscapeUtils;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.authorization.AccountImpl;

@Entity
@Table(name = "webarchive")
@XmlRootElement
public class WebarchiveImpl implements Webarchive, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_WEBARCHIVE", nullable=true)
    protected Long id;
    @Column(name = "URL", nullable=true)
    protected String url;
    @Column(name = "LOCAL_URL", nullable=true)
    protected String localUrl;
    @OneToOne
    @JoinColumn(name = "account_ID_ACCOUNT", nullable = false)
    protected AccountImpl account;
    @Column(name = "SCOPE",  nullable=true)
    protected String scope;
    @Column(name = "DESCRIPTION", nullable=false)
    protected String description;
    @Column(name = "PRIORITY", nullable=true)
    protected int rank;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DATE_C", nullable=true)
    protected Date date;

    public WebarchiveImpl() {
        super();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getLocalUrl() {
        return localUrl;
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
    public Account getAccount() {
        return account;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setUrl(String url) {
        //this.url = url;
        this.url = StringEscapeUtils.escapeHtml(url) ;
    }

    @Override
    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setDescription(String description) {
        //this.description = description;
        this.description = StringEscapeUtils.escapeHtml(description);
    }

    @Override
    public void setAccount(Account account) {
        this.account = (AccountImpl)account;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
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
