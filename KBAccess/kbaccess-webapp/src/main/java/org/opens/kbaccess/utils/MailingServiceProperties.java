/*
 * URLManager - URL Indexer
 * Copyright (C) 2008-2012  Open-S Company
 *
 * This file is part of URLManager.
 *
 * URLManager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.kbaccess.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bcareil
 */
public class MailingServiceProperties {
    private static final Pattern lostPasswordBodyFilePattern = Pattern.compile("^lost-password(_([a-z]{2,3}))?$");
    private static final Pattern authTokenBodyFilePattern = Pattern.compile("^auth-token(_([a-z]{2,3}))?$");
    
    private String smtpHost;
    private String defaultReturnAddress;
    private String emailTemplateDirectoryName;
    private String[] subscriptionNotificationMailingList;
    private String[] testcaseCreationNotificationMailingList;
    private String[] webarchiveCreationNotificationMailingList;
    private Map<String, String> lostPasswordEmailBody;
    private Map<String, String> authTokenEmailBody;

    public MailingServiceProperties() {
    }

    /*
     * private methods
     */
    
    private String getFileContent(File file) {
        try {
            return new Scanner(new FileInputStream(file), "UTF-8").useDelimiter("\\A").next();
        } catch (NoSuchElementException ex) {
            return "";
        } catch (FileNotFoundException ex) {
            return "";
        }
    }
    
    private void loadEmailBodyHandleFile(Map<String, String> emailBodyByLang, File file, Pattern pattern) {
        Matcher matcher = pattern.matcher(file.getName());
        
        if (matcher.matches()) {
            // if the filename match the pattern
            if (matcher.group(2) == null) {
                // and the _lang is not present, add it as the default body
                LogFactory.getLog(MailingServiceProperties.class).info("Adding file " + file.getName() + " for the default language");
                emailBodyByLang.put("default", getFileContent(file));
            } else {
                // otherwise, add it as the body to use for the specified language
                LogFactory.getLog(MailingServiceProperties.class).info("Adding file " + file.getName() + " for the " + matcher.group(2) + " language");
                emailBodyByLang.put(matcher.group(2), getFileContent(file));
            }
        }        
    }

    /**
     * Load the email body from the given emailTemplateDirectory.
     */
    private synchronized void loadEmailBody() {
        if (lostPasswordEmailBody == null) {
            File emailTemplateDirectory = new File(emailTemplateDirectoryName);
            
            // attempt to open the email template directory
            // we create the hashmap here, so we are sure this method
            // is just called once. Even in case of error.
            authTokenEmailBody = new HashMap<String, String>();
            lostPasswordEmailBody = new HashMap<String, String>();
            if (emailTemplateDirectory.isDirectory() == false) {
                // if we do not found the directory, log it and return,
                // the application has to be restart before the directory
                // is evaluated again.
                LogFactory.getLog(MailingServiceProperties.class).error("Unable to open the email template directory (" + emailTemplateDirectoryName + ")");
                return ;
            }
            // list file in the directory
            for (File file : emailTemplateDirectory.listFiles()) {
                // for each real file
                if (file.isFile()) {
                    if (file.getName().startsWith("lost-password")) {
                        // look for thous begining with "lost-password" and add their content
                        loadEmailBodyHandleFile(lostPasswordEmailBody, file, lostPasswordBodyFilePattern);
                    } else if (file.getName().startsWith("auth-token")) {
                        // or begining with "auth-token" and add their content
                        loadEmailBodyHandleFile(authTokenEmailBody, file, authTokenBodyFilePattern);
                    } // ignore others
                }
            }
        }
    }

    /**
     * @see getLostPasswordEmailBody and getAuthTokenEmailBody
     */
    private String getEmailBody(Map<String, String> bodyByLang, String lang) {        
        if (lang != null && bodyByLang.containsKey(lang)) {
            return bodyByLang.get(lang);
        } else if (bodyByLang.containsKey("default")) {
            return bodyByLang.get("default");
        } else {
            return null;
        }        
    }
    
    /*
     * utility methods
     */
    
    /**
     * Return the body of the email to send with the new password for the
     * given language.
     * 
     * If the email body does not exist for the given language, a default
     * one is returned. If the default one does not exist, null is returned.
     * @param lang
     * @return The email body in the given language
     *         or in the default language
     *         or null
     */
    public String getLostPasswordEmailBody(String lang) {
        loadEmailBody();
        return getEmailBody(lostPasswordEmailBody, lang);
    }
    
    /**
     * Return the body of the email to send with the auth token for the
     * given language.
     * 
     * If the email body is not available for the given language, a default
     * one is returned. If the default one is not available either, null is
     * returned.
     * @param lang
     * @return The email body in the given language
     *         or in the default language
     *         or null
     */
    public String getAuthTokenEmailBody(String lang) {
        loadEmailBody();
        return getEmailBody(authTokenEmailBody, lang);
    }

    /*
     * Accessors
     */
    
    /**
     * The default address to reply to.
     * 
     * @return 
     */
    public String getDefaultReturnAddress() {
        return defaultReturnAddress;
    }

    public void setDefaultReturnAddress(String defaultReturnAddress) {
        this.defaultReturnAddress = defaultReturnAddress;
    }

    /**
     * The smtp host to use to forward the mail.
     * 
     * @return 
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    /**
     * The directory containg the email templates.
     * 
     * @return 
     */
    public String getEmailTemplateDirectoryName() {
        return emailTemplateDirectoryName;
    }

    public void setEmailTemplateDirectoryName(String emailTemplateDirectoryName) {
        this.emailTemplateDirectoryName = emailTemplateDirectoryName;
    }

    /**
     * The list of email addresses to send the subscription notifications to.
     * 
     * @return 
     */
    public String[] getSubscriptionNotificationMailingList() {
        return subscriptionNotificationMailingList;
    }

    public void setSubscriptionNotificationMailingList(String[] subscriptionNotificationMailingList) {
        this.subscriptionNotificationMailingList = subscriptionNotificationMailingList;
    }

    public String[] getTestcaseCreationNotificationMailingList() {
        return testcaseCreationNotificationMailingList;
    }

    public void setTestcaseCreationNotificationMailingList(String[] testcaseCreationNotificationMailingList) {
        this.testcaseCreationNotificationMailingList = testcaseCreationNotificationMailingList;
    }

    public String[] getWebarchiveCreationNotificationMailingList() {
        return webarchiveCreationNotificationMailingList;
    }

    public void setWebarchiveCreationNotificationMailingList(String[] webarchiveCreationNotificationMailingList) {
        this.webarchiveCreationNotificationMailingList = webarchiveCreationNotificationMailingList;
    }

}
