/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.command;

import org.opens.kbaccess.command.utils.ACommand;
import org.opens.kbaccess.entity.subject.Testcase;

/**
 *
 * @author blebail
 */
public class DeleteTestcaseCommand extends ACommand {
    private long id;

    public DeleteTestcaseCommand() {
    }
    
    public DeleteTestcaseCommand(Testcase testcase) {
        this.id = testcase.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    } 
}
