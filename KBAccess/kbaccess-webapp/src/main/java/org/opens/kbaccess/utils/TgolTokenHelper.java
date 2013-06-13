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
package org.opens.kbaccess.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.opens.kbaccess.entity.authorization.Account;
import org.owasp.esapi.crypto.CryptoToken;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author jkowalczyk
 */
public final class TgolTokenHelper {
    
    private static String ESAPI_RESOURCE_NAME = "org.owasp.esapi.resources";
        public void setEsapiPropertyValue(String esapiPropertyValue) {
        System.setProperty(ESAPI_RESOURCE_NAME, esapiPropertyValue);
    }

    private int tokenDurationValidity = 3600;
    public void setTokenDurationValidity(int tokenDurationValidity) {
        this.tokenDurationValidity = tokenDurationValidity;
    }
    
    /**
     * The unique instance of TgolTokenHelper
     */
    private static TgolTokenHelper tokenHelper = null;

    /**
     * Private constructor
     */
    private TgolTokenHelper() {}

    /**
     *
     * @return
     */
    public static synchronized TgolTokenHelper getInstance() {
        if (tokenHelper == null) {
            tokenHelper = new TgolTokenHelper();
        }
        
        return tokenHelper;
    }

    /**
     *
     */
//    private void setSystemProperty() {
//        if (esapiPropertyName != null && esapiPropertyValue != null) {
//            System.setProperty(ESAPI_RESOURCE_NAME, esapiPropertyValue);
//        }
//    }

    /**
     * 
     * @param account
     * @return
     */
    public String getTokenUser(Account account, boolean hasExpiration) {
        CryptoToken cryptoToken = null;
        try {
            cryptoToken = new CryptoToken();
            cryptoToken.setUserAccountName(account.getEmail());
            
            // Reset password token has expiration
            // But activation token doesn't
            if (hasExpiration) {
                cryptoToken.setExpiration(tokenDurationValidity);
            }
            String token = cryptoToken.getToken();
            
            return token;
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        } catch (ValidationException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        }
    }
    
    /**
     * 
     * @param token
     * @return 
     */
    public String getUserEmailFromToken(String token) {
        CryptoToken cryptoToken;
        try {
            cryptoToken = new CryptoToken(token);
            return cryptoToken.getUserAccountName();
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return null;
        }
    }
    
    /**
     * 
     * @param account
     * @param token
     * @return
     */
    public boolean checkUserToken(String token) {
        CryptoToken cryptoToken = null;
        try {
            cryptoToken = new CryptoToken(token);
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return false;
        }
        
        if (Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate())) {
            Logger.getLogger(this.getClass()).info(
                    "Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate() " 
                    + cryptoToken.getExpirationDate());
            return false;
        }

        return true;
    }
}