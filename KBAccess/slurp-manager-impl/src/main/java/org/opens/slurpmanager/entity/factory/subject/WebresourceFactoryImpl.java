/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.slurpmanager.entity.factory.subject;

import org.opens.slurpmanager.entity.subject.WebresourceImpl;
import org.opens.slurpmanager.entity.factory.subject.WebresourceFactory;
import org.opens.slurpmanager.entity.subject.Webresource;

/**
 *
 * @author jkowalczyk
 */
public class WebresourceFactoryImpl implements WebresourceFactory{

    public WebresourceFactoryImpl(){
        super();
    }

    @Override
    public Webresource create() {
        return new WebresourceImpl();
    }

}
