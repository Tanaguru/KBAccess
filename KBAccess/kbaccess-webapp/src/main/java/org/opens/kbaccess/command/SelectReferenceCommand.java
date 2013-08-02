/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.command;

import org.opens.kbaccess.command.utils.ACommand;

/**
 *
 * @author blebail
 */
public class SelectReferenceCommand extends ACommand {
    
    private Long idReference;
    
    public SelectReferenceCommand() {
    }
    
    /*
     * Accessors
     */
    public Long getIdReference() {
        return idReference;
    }

    public void setIdReference(Long idReference) {
        this.idReference = idReference;
    }
}
