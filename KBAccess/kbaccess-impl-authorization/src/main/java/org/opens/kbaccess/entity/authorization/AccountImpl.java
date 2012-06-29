package org.opens.kbaccess.entity.authorization;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "account")
@XmlRootElement
public class AccountImpl implements Account, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ACCOUNT")
    protected Long id;
    @Column(name = "EMAIL")
    protected String email;
    @Column(name = "PASSWORD")
    protected String password;
    @OneToOne
    @JoinColumn(name = "access_level_ID_ACCESS_LEVEL")
    protected AccessLevelImpl accessLevel;
    @Column(name = "NAME")
    protected String lastName;
    @Column(name = "FIRSTNAME")
    protected String firstName;
    @Column(name = "URL")
    protected String url;
    @Column(name = "ACTIVATED")
    protected boolean activated;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DATE_INSCRIPTION")
    protected Date dateInscription;
    @Column(name = "AUTH_CODE", nullable=true)
    protected String authCode;



    @Override
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    @Override
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = (AccessLevelImpl)accessLevel;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = StringEscapeUtils.escapeHtml(firstName);
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = StringEscapeUtils.escapeHtml(name);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = StringEscapeUtils.escapeHtml(password);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
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
    public Date getSubscriptionDate() {
        return dateInscription;
    }

    @Override
    public void setSubscriptionDate(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String getAuthCode() {
        return authCode;
    }

      @Override
    public void setAuthCode(String authCode) {
        this.authCode=authCode;
    }

    @Override
    public int getRank() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRank(int rank) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

