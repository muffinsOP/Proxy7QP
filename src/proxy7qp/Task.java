/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import org.osbot.rs07.script.Script;

/**
 *
 * @author Islah
 */

public abstract class Task {
    // The script instance
    protected Script s;
 
    public Task(Script s) {
        this.s = s;
    }
 
    /**
     * @return if this Task should execute.
     */
    public abstract boolean verify();
 
    /**
     * Executes this Task.
     *
     * @return sleep time after this task ends.
     */
    public abstract void process() throws InterruptedException;
 
    public void run() {
        if (verify())
            try {
                process();
            } catch (Exception e){
                s.log(e.getMessage());
            }
    }
 
    /**
     * @return a description of the current Task.
     */
    public abstract String status();
}

