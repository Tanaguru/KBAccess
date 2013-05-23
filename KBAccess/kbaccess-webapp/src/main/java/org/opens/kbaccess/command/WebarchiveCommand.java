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
package org.opens.kbaccess.command;
import org.opens.kbaccess.command.utils.ACommand;

/**
 *
 * @author bcareil
 */
public class WebarchiveCommand extends ACommand {
    private String url;
    private String descriptionNewWebarchive;
    private String scope;

    public WebarchiveCommand() {
    }

    public WebarchiveCommand(String url, String descriptionNewWebarchive, String scope) {
        this.url = url;
        this.descriptionNewWebarchive = descriptionNewWebarchive;
        this.scope = scope;
    }

    public String getDescriptionNewWebarchive() {
        return descriptionNewWebarchive;
    }

    public void setDescriptionNewWebarchive(String descriptionNewWebarchive) {
        this.descriptionNewWebarchive = descriptionNewWebarchive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
}
