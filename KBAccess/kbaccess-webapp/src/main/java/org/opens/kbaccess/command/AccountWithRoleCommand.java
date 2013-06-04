/*
 * Copyright (C) 2012-2016  Open-S Company
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

import org.opens.kbaccess.entity.authorization.Account;
import org.opens.kbaccess.entity.service.authorization.AccessLevelDataService;
import org.opens.kbaccess.utils.SHA1Hasher;

/**
 *
 * @author blebail
 */
public class AccountWithRoleCommand extends AccountCommand {
    
    public Long accessLevelId;
    public Long accountId;

    public AccountWithRoleCommand() {
    }
  
    public AccountWithRoleCommand(
            String email, 
            String firstName, 
            String lastName, 
            String url, 
            String password, 
            String passwordConfirmation, 
            Long accessLevelId,
            Long acccountId) {
       super(email, firstName, lastName, url, password, passwordConfirmation);
       
       this.accessLevelId = accessLevelId;
       this.accountId = acccountId;
    }
    
    public AccountWithRoleCommand(Account account) {
        super(account);
        this.accessLevelId = account.getAccessLevel().getId();
    }

    public void updateAccount(AccessLevelDataService accessLevelDataService, Account account, boolean passwordChanged) {   
        account.setAccessLevel(accessLevelDataService.read(this.accessLevelId));
        account.setEmail(this.email);
        account.setFirstName(this.firstName);
        account.setLastName(this.lastName);
        account.setUrl(this.url);
        
        if (passwordChanged) {
            account.setPassword(SHA1Hasher.getInstance().hashAsString(this.password));
        }
    }
    
    public Long getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(Long accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    } 
}
