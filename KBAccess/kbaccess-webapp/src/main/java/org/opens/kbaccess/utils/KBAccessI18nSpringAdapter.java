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

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.displaytag.localization.I18nSpringAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * This class overrides the display tag I18nSpringAdapter to get the Locale.
 *
 * @author blebail
 */
public class KBAccessI18nSpringAdapter extends I18nSpringAdapter {

    /**
     * Default constructor
     */
    public KBAccessI18nSpringAdapter() {
        super();
    }

    /**
     * 
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

}