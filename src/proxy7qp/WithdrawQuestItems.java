/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import data.Constants;
import java.util.ArrayList;
import org.osbot.rs07.api.map.constants.Banks;
import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;
import org.osbot.rs07.script.Script;

/**
 *
 * @author Islah
 */
public class WithdrawQuestItems extends Task {
    
    
    


    public WithdrawQuestItems(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
       return !s.getBank().isOpen() && !s.getInventory().contains(Constants.items) && Banks.GRAND_EXCHANGE.contains(s.myPosition());
    }

    @Override
    public void process() throws InterruptedException {
        s.getBank().open();
        sleep(random(800,1000));
        s.getBank().withdrawAll("Copper ore");
        sleep(random(800,1000));
        s.getBank().withdrawAll("Iron ore");
        sleep(random(800,1000));
        s.getBank().withdrawAll("Clay");
        sleep(random(800,1000));
        s.getBank().withdrawAll("Bucket of milk");
        sleep(random(800,1000));
        s.getBank().withdrawAll("Pot of flour");
        sleep(random(800,1000));
        s.getBank().withdrawAll("Egg");
        sleep(random(800,1000));
        s.getBank().close();
        
    }

    @Override
    public String status() {
        return "Grabbing Quest Items....";
    }
    
}
