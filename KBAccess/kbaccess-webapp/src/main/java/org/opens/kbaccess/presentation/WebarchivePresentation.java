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
package org.opens.kbaccess.presentation;

import java.util.Date;
import org.opens.kbaccess.entity.subject.Webarchive;

/**
 *
 * @author bcareil
 */
public class WebarchivePresentation {
    
    private String url;
    private String localUrl;
    private String scope;
    private String description;
    private Date creationDate;
    
    private String accountDisplayedName;
    private Long accountId;
    
    public WebarchivePresentation(Webarchive webarchive) {
        this.url = webarchive.getUrl();
        this.localUrl = webarchive.getLocalUrl();
        this.scope = webarchive.getScope();
        this.description = webarchive.getDescription();
        this.creationDate = webarchive.getCreationDate();
        this.accountDisplayedName = AccountPresentation.generateDisplayedName(webarchive.getAccount());
        this.accountId = webarchive.getAccount().getId();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
    
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAccountDisplayedName() {
        return accountDisplayedName;
    }

    public void setAccountDisplayedName(String accountDisplayedName) {
        this.accountDisplayedName = accountDisplayedName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
