/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.dao.subject;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.slurpmanager.entity.subject.WebresourceImpl;
import org.opens.slurpmanager.entity.subject.Webresource;

/**
 *
 * @author jkowalczyk
 */
public class WebresourceDAOImpl extends AbstractJPADAO<Webresource, Long> implements
        WebresourceDAO {

    public WebresourceDAOImpl() {
        super();
    }

    @Override
    protected Class<WebresourceImpl> getEntityClass() {
        return WebresourceImpl.class;
    }

    public Webresource retrieveByUrl(String url) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n" + " WHERE n.url = :url");
        query.setParameter("url", url);

        try {
            return (Webresource) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

}
