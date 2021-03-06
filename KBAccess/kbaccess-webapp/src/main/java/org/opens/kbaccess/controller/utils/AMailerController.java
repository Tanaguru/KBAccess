/*
 * KBAccess - Collaborative database of accessibility examples
 * Copyright (C) 2012-2016  Open-S Company
 *
 * This file is part of KBAccess.
 *
 * KBAccess is free software: you can redistribute it and/or modify
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
package org.opens.kbaccess.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.opens.kbaccess.controller.GuestController;
import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.subject.Testcase;
import org.opens.kbaccess.entity.subject.Webarchive;
import org.opens.kbaccess.presentation.TestcasePresentation;
import org.opens.kbaccess.presentation.factory.TestcasePresentationFactory;
import org.opens.kbaccess.utils.MailingServiceProperties;
import org.opens.kbaccess.utils.TgolTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bcareil
 */
public class AMailerController extends AController {
    
    private static final String AUTH_TOKEN_KEY = "${authToken}";
    private static final String EMAIL_KEY = "${email}";
    private static final String FULL_NAME_KEY = "${fullName}";
    private static final String ID_KEY = "${id}";    
    private static final String LOCAL_URL_KEY = "${localUrl}";
    private static final String URL_KEY = "${url}";
    private static final String PASSWORD_KEY = "${password}";
    
    private static final String SUBSCRIPTION_NOTIFICATION_SUBJECT =
            "[KBAccess] A new account has been created";
    private static final String TESTCASE_CREATION_NOTIFICATION_SUBJECT =
            "[KBAccess] A new testcase has been created";
    private static final String WEBARCHIVE_CREATION_NOTIFICATION_SUBJECT =
            "[KBAccess] A new webarchive has been created";
    
    @Autowired
    private MailingServiceProperties mailingServiceProperties;
    @Autowired
    private TestcasePresentationFactory testcasePresentationFactory;

    
    /*
     * Private members
     */
    private String[] splitMessageBody(String body) {
        String[] subjectAndMessage = body.split("\r?\n\r?\n", 2);

        if (subjectAndMessage.length != 2) {
            LogFactory.getLog(AMailerController.class).error("Invalid email body : " + body);
            return new String[] {body, ""};
        }
        return subjectAndMessage;
    }            
    
    /*
     * Public members
     */
    
    /**
     * Send a mail to the specified recipients.
     * 
     * @param subject The mail's subject
     * @param message The mail's body
     * @param recipients The adressee
     * @return true if the send succeed,
     *         false otherwise
     */
    public boolean sendMail(String subject, String message, String[] recipients) {
        Session session;
        MimeMessage mimeMessage;
        Properties properties;

        // sanity check
        if (recipients.length == 0) {
            return true;
        }
        // set-up session
        properties = new Properties();
        properties.put("mail.smtp.host", mailingServiceProperties.getSmtpHost());
        session = Session.getDefaultInstance(properties);
        //session.setDebug(true);
        try {
            Address from;
            Address[] to = new InternetAddress[recipients.length];
            
            // set sender
            from = new InternetAddress(mailingServiceProperties.getDefaultReturnAddress());
            // initialize recipients list
            for (int i = 0; i < to.length; ++i) {
                to[i] = new InternetAddress(recipients[i]);
            }
            // create message
            mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(from);
            mimeMessage.setFrom(from);
            mimeMessage.setReplyTo(new Address[] { from });
            mimeMessage.setRecipients(Message.RecipientType.TO, to);
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message, "utf-8");
            // send it
            Transport.send(mimeMessage);
        } catch (MessagingException ex) {
            LogFactory.getLog(GuestController.class).error("Unable to send email", ex);
            return false;
        }
        return true;
    }
    
    public boolean sendAuthToken(String lang, Account account) {
        String message;
        String subject;
        String[] subjectAndMessage;
        
        // Encode token
        String token = account.getAuthCode();
        
        try {
            token = URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
        }
        
        // Construct email
        message = mailingServiceProperties.getAuthTokenEmailBody(lang);
        if (message == null) {
            return false;
        }
        subjectAndMessage = splitMessageBody(message);
        subject = subjectAndMessage[0];
        
        // We replace the token key
        message = subjectAndMessage[1].replace(AUTH_TOKEN_KEY, token);

        return sendMail(subject, message, new String[] {account.getEmail()});
    }
    
    public boolean sendNewPasswordToken(String lang, Account account) {
        String message;
        String subject;
        String[] subjectAndMessage;
        
        // create message
      
        Logger.getLogger(AMailerController.class).debug(account.toString());
        String token = TgolTokenHelper.getInstance().getTokenUser(account, true);
        
        try {
            token = URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
        }
        
         // Construct email
        message = mailingServiceProperties.getLostPasswordEmailBody(lang);
        if (message == null) {
            return false;
        }
        subjectAndMessage = splitMessageBody(message);
        subject = subjectAndMessage[0];
        
        // We replace the token key
        message = subjectAndMessage[1].replace(AUTH_TOKEN_KEY, token);
        
        return sendMail(subject, message, new String[] {account.getEmail()});
    }
    
    public boolean sendSubsciptionNotification(Account account) {
        StringBuilder message;
        String[] recipients;
        String subject;
        
        message = new StringBuilder();
        recipients = mailingServiceProperties.getSubscriptionNotificationMailingList();
        subject = SUBSCRIPTION_NOTIFICATION_SUBJECT;
        // create message
      
        Logger.getLogger(AMailerController.class).debug(account.toString());
        
        message.append(
                "Hello,\n\n" +
                "A new account has been created on KBAccess :\n" +
                "* email : ").append(account.getEmail()).append("\n" +
                "* first name : ").append(account.getFirstName()).append("\n" +
                "* last name : ").append(account.getLastName()).append("\n" +
                "* url : ").append(account.getUrl()).append("\n" +
                "* access level : ").append(account.getAccessLevel().getLabel()).append("\n" +
                "* creation date : ").append(account.getSubscriptionDate()).append("\n\n" +
                "If you receive this email, it means you are on the KBAccess " +
                "subscrition notification mailing list. To unsubscribe, modify " +
                "the KBAccess configuration and restart the service.\n\n" +
                "Regards,\n" +
                "-- \n" +
                "The KBAccess Team\n"
                );
        // send it*/
        return sendMail(subject, message.toString(), recipients);
    }
    
    /**
     * 
     * @param newTestcase
     * @return 
     */
    public boolean sendTestcaseCreationNotification(Testcase newTestcase) {
        StringBuilder message;
        String[] recipients;
        String subject;
        
        message = new StringBuilder();
        recipients = mailingServiceProperties.getTestcaseCreationNotificationMailingList();
        subject = TESTCASE_CREATION_NOTIFICATION_SUBJECT;
        TestcasePresentation testcasePresentation = testcasePresentationFactory.create(newTestcase);
        Account account = accountDataService.read(testcasePresentation.getAccountId());
        
        // create message
        message.append(
                "Hello,\n\n" +
                "A new testcase has been created on KBAccess :\n" +
                "* url : ").append("http://www.kbaccess.org/example/details/" + testcasePresentation.getTestcaseId() + 
                "/exemple-accessibilite-" + testcasePresentation.getTestLabel() + 
                "-" + testcasePresentation.getReferenceLabel().replace(" ", "") + 
                "-" + testcasePresentation.getResultCode() + ".html\n" + 
                "* id : ").append(testcasePresentation.getTestcaseId().toString()).append("\n" +
                "* test : ").append(testcasePresentation.getTestLabel()).append("\n" +
                "* description : ").append(testcasePresentation.getDescription()).append("\n" +
                "* account : (").append(account.getId()).append(") ")
                .append(account.getEmail()).append("\n" +
                "* webarchive : ").append(testcasePresentation.getWebarchiveOriginalUrl()).append(" "
                ).append(testcasePresentation.getWebarchiveCreationDate()).append("\n\n" +
                "If you receive this email, it means you are in the KBAccess " +
                "testcase creation notification mailing list. To unsubscribe, modify " +
                "the KBAccess configuration and restart the service.\n\n" +
                "Regards,\n" +
                "-- \n" +
                "The KBAccess Team\n"
                );
        // send it
        return sendMail(subject, message.toString(), recipients);
    }
    
    /**
     * 
     * @param webarchive
     * @return 
     */
    public boolean sendWebarchiveCreationNotification(Webarchive webarchive) {
        StringBuilder message;
        String[] recipients;
        String subject;
        
        message = new StringBuilder();
        recipients = mailingServiceProperties.getWebarchiveCreationNotificationMailingList();
        subject = WEBARCHIVE_CREATION_NOTIFICATION_SUBJECT;
        // create message
        message.append(
                "Hello,\n\n" +
                "A new webarchive has been created on KBAccess :\n" +
                "* id : ").append(webarchive.getId().toString()).append("\n" +
                "* url : ").append(webarchive.getUrl()).append("\n" +
                "* local url : ").append(webarchive.getLocalUrl()).append("\n" +
                "* description : ").append(webarchive.getDescription()).append("\n" +
                "* creation date : ").append(webarchive.getCreationDate()).append("\n" +
                "* account : (").append(webarchive.getAccount().getId()).append(") ")
                .append(webarchive.getAccount().getEmail()).append("\n" +
                "\n" +
                "If you receive this email, it means you are in the KBAccess " +
                "webarchive creation notification mailing list. To unsubscribe, modify " +
                "the KBAccess configuration and restart the service.\n\n" +
                "Regards,\n" +
                "-- \n" +
                "The KBAccess Team\n"
                );
        // send it
        return sendMail(subject, message.toString(), recipients);
    }

    /*
     * Accessors
     */
    
    public MailingServiceProperties getMailingServiceProperties() {
        return mailingServiceProperties;
    }

    public void setMailingServiceProperties(MailingServiceProperties mailingServiceProperties) {
        this.mailingServiceProperties = mailingServiceProperties;
    }

    public TestcasePresentationFactory getTestcasePresentationFactory() {
        return testcasePresentationFactory;
    }

    public void setTestcasePresentationFactory(TestcasePresentationFactory testcasePresentationFactory) {
        this.testcasePresentationFactory = testcasePresentationFactory;
    }
}
