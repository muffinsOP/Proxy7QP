/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import data.Constants;
import org.osbot.rs07.api.Quests;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

/**
 *
 * @author Islah
 */
public class CookQuest extends Task {

    public static Area cookArea = new Area(3206, 3217, 3212, 3212);
    //3208,3213
    Position cookPos = new Position(3208, 3213, 0);

    public CookQuest(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return !cookArea.contains(s.myPlayer().getPosition()) && !s.getInventory().contains("Copper ore") && s.getInventory().contains("Egg");
    }

    @Override
    public void process() throws InterruptedException {

        NPC cook = s.getNpcs().closest("Cook");

        if (!cookArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(cookPos);
        }

        if (cookArea.contains(s.myPlayer()) && !s.getInventory().contains("Egg")) {
            s.stop();
        }

        if ((cook != null && cook.isVisible()) && (!s.getDialogues().inDialogue())) {
            cook.interact(new String[]{"Talk-to"});
        }
        new ConditionalSleep(10000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue("What's wrong?",
                    "I'm always happy to help a cook in distress.", "Actually, I know where to find this stuff");
        }

    }

    @Override
    public String status() {
        return "Doing Cook's Assistant...";
    }

}
