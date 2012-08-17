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

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bcareil
 */
public class SHA1Hasher {
    
    private static SHA1Hasher instance;
    
    private MessageDigest messageDigest;
    
    public static synchronized SHA1Hasher getInstance() {
        if (instance == null) {
            instance = new SHA1Hasher();
        }
        return instance;
    }
    
    private SHA1Hasher() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            LogFactory.getLog(SHA1Hasher.class).error("Unable to create a message digest for sha1 algorithm", ex);
        }
    }
    
    public String byteArrayToHex(final byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        
        for (byte b : byteArray) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public byte[] hash(final byte[] byteArray) {
        return messageDigest.digest(byteArray);
    }
    
    public String hashAsString(String string) {
        return byteArrayToHex(hash(string.getBytes(Charset.forName("utf-8"))));        
    }
    
}
