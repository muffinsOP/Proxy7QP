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
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

/**
 *
 * @author Islah
 */
public class DoricsQuest extends Task {

    public static Area doricArea = new Area(2949, 3447, 2953, 3455);

    Position doricPos = new Position(2951, 3451, 0);

    public DoricsQuest(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return s.getTabs().getQuests().isComplete(Quests.Quest.ROMEO_JULIET) && s.getInventory().contains("Iron ore");
    }

    @Override
    public void process() throws InterruptedException {

        NPC doric = s.getNpcs().closest("Doric");

        if (!doricArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(doricPos);
        }

        if ((doric != null && doric.isVisible()) && (!s.getDialogues().inDialogue())) {
            doric.interact(new String[]{"Talk-to"});
        }
        new ConditionalSleep(10000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue("I wanted to use your anvils.",
                    "Yes, I will get you the materials.",
                    "Certainly, I'll be right back!");
        }

    }

    @Override
    public String status() {
        return "Doing Doric's Quest....";
    }

}
