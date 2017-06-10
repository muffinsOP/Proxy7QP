/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import data.Constants;
import org.osbot.rs07.api.map.constants.Banks;
import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;
import org.osbot.rs07.script.Script;

/**
 *
 * @author Islah
 */
public class WalkToGE extends Task {

    public WalkToGE(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return !Banks.GRAND_EXCHANGE.contains(s.myPlayer().getPosition());
    }

    @Override
    public void process() throws InterruptedException {

        if (!s.getInventory().onlyContains(Constants.items)) {
            s.getWalking().webWalk((Banks.GRAND_EXCHANGE));
        }
    }

    @Override
    public String status() {
        return "Walking to GE.....";
    }

}
