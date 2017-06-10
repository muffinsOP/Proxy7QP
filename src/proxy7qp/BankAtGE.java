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
public class BankAtGE extends Task {

    public BankAtGE(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return Banks.GRAND_EXCHANGE.contains(s.myPlayer().getPosition()) && !s.getInventory().isEmptyExcept(Constants.items);
    }

    @Override
    public void process() throws InterruptedException {
        if (Banks.GRAND_EXCHANGE.contains(s.myPlayer())) {
            s.getBank().open();
            sleep(random(600, 1500));
            s.getBank().depositAllExcept("Coins");
            sleep(random(600, 1500));
            s.getBank().close();
        }
    }

    @Override
    public String status() {
        return "Banking...";
    }
}


