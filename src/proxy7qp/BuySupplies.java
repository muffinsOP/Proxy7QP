/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import org.osbot.rs07.api.map.constants.Banks;
import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;
import org.osbot.rs07.script.Script;

/**
 *
 * @author Islah
 */
public class BuySupplies extends Task {

    private final String ironOre = "Iron ore";
    private final int ironOrePrice = 350;
    private final int ironOreAmount = 2;
    private final String clay = "Clay";
    private final int clayPrice = 350;
    private final int clayAmount = 6;
    private final String copperOre = "Copper ore";
    private final int copperOrePrice = 200;
    private final int copperOreAmount = 4;
    private final String bucketOfMilk = "Bucket of milk";
    private final int bucketOfMilkPrice = 500;
    private final int bucketOfMilkAmount = 1;
    private final String potOfFlour = "Pot of flour";
    private final int potOfFlourPrice = 500;
    private final int potOfFlourAmount = 1;
    private final String egg = "Egg";
    private final int eggPrice = 500;
    private final int eggAmount = 1;

    public BuySupplies(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return s.getInventory().onlyContains("Coins") && Banks.GRAND_EXCHANGE.contains(s.myPlayer().getPosition());
    }

    @Override
    public void process() throws InterruptedException {
        GrandExchange GE = new GrandExchange(s);
        if (!s.getGrandExchange().isOpen()) { //Checks if ge is open
            GE.openGE(); //open ge randomly using booth or npc
        } else if (!s.getInventory().contains(ironOre)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(ironOre, ironOrePrice, ironOreAmount); //creates a buy offer with specified params
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if (!s.getInventory().contains(clay)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(clay, clayPrice, clayAmount);
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if (!s.getInventory().contains(copperOre)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(copperOre, copperOrePrice, copperOreAmount);
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if (!s.getInventory().contains(bucketOfMilk)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(bucketOfMilk, bucketOfMilkPrice, bucketOfMilkAmount);
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if (!s.getInventory().contains(potOfFlour)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(potOfFlour, potOfFlourPrice, potOfFlourAmount);
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if (!s.getInventory().contains(egg)) {
            sleep(random(600, 1000));
            GE.createBuyOffer(egg, eggPrice, eggAmount);
            sleep(random(1500, 3000));
            GE.collectItems(false);
        } else if(s.getInventory().contains(item -> item.isNote())) {
            s.getBank().open();
            sleep(random(600, 1000));
            s.getBank().depositAll();
            sleep(random(600, 900));
            s.getBank().close();
        }
    }

    @Override
    public String status() {
        return "Buying Supplies....";
    }

}
