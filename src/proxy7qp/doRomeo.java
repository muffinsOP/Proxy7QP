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
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import static org.osbot.rs07.script.MethodProvider.random;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

/**
 *
 * @author Islah
 */
public class doRomeo extends Task {

    public String romeo = "Romeo";
    public String rjStartOption = "Perhaps I could help to find her for you?";
    public String rjAccept = "Yes, ok, I'll let her know";
    public String juliet = "Juliet";
    public String father = "Father Lawrence";
    public String apothecary = "Apothecary";
    public String cadava = "Cadava bush";
    public String potion = "Cadava potion";
    public Position dukePos = new Position(3209, 3222, 1);
    public Position sedridorPos = new Position(3105, 9570, 0);
    public Position auburyPos = new Position(3253, 3401, 0);
    public Position berryPos = new Position(3273, 3371, 0);
    public Position romeoPos = new Position(3212, 3428, 0);
    public Position julietPos = new Position(3158, 3425, 1);
    public Position fatherPos = new Position(3254, 3484, 0);
    public Position apotPos = new Position(3196, 3404, 0);
    public Area berryArea = new Area(new Position(3265, 3364, 0), new Position(3281, 3375, 0));
    public Area romeoArea = new Area(new Position(3208, 3421, 0), new Position(3220, 3434, 0));
    public Area julietArea = new Area(new Position(3153, 3427, 1), new Position(3164, 3435, 1));
    public Area fatherArea = new Area(new Position(3252, 3478, 0), new Position(3259, 3488, 0));
    public Area apotArea = new Area(new Position(3189, 3399, 0), new Position(3198, 3406, 0));
    public int rjConfig = 144;
    private boolean rjCompleted;

    public doRomeo(Script s) {
        super(s);
    }

    @Override
    public boolean verify() {
        return s.getInventory().contains("Egg") && !s.getTabs().getQuests().isComplete(Quests.Quest.ROMEO_JULIET) && !s.getInventory().contains(item -> item.isNote());
    }

    @Override
    public void process() throws InterruptedException {
        State currentState = getState();
        switch (currentState) {
            case IDLE:
                break;
            case RJNOTSTARTED:
                runningToRomeo();
                startRomeoQuest();
                break;
            case RJSTARTED:
                runningToJuliet();
                talkToJuliet();
                break;
            case RJLETTER:
                runningToRomeo();
                talkToRomeo();
                break;
            case RJTOFATHER:
                runningToFather();
                talkToFather();
                break;
            case RJTOAPOTHECARY:
                runningToBerries();
                pickBerries();
                runningToApot();
                talkToApot();
                break;
            case RJTOJULIET:
                talkToApot();
                runningToJuliet();
                talkToJuliet();
                break;
            case RJTOROMEO:
                runningToRomeo();
                talkToRomeo();
                break;
            case RJCOMPLETED:
                closeQuest();
                this.rjCompleted = true;
                break;
        }

    }

    private void runningToRomeo() {
        if (!romeoArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(romeoPos);
        }
    }

    private void runningToJuliet() {
        if (!julietArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(julietPos);
        }else if(!julietArea.contains(s.myPlayer()) && s.getInventory().contains("Cadava potion")){
            s.getWalking().webWalk(julietPos);
        }
    }

    private void runningToFather() {
        if (!fatherArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(fatherPos);
        }
    }

    private void runningToBerries() {
        if (!berryArea.contains(s.myPlayer())) {
            if (!s.getInventory().contains(new String[]{"Cadava berries"})) {
                s.getWalking().webWalk(berryPos);
            }
        }
    }

    private void runningToApot() {
        if (!apotArea.contains(s.myPlayer())) {
            s.getWalking().webWalk(apotPos);
        }
    }

    private void startRomeoQuest()
            throws InterruptedException {
        NPC romeo = s.getNpcs().closest("Romeo");
        if (romeo != null) {
            romeo.interact(new String[]{"Talk-to"});
            new ConditionalSleep(5000) {
                public boolean condition()
                        throws InterruptedException {
                    return !s.myPlayer().isMoving();
                }
            }.sleep();
            if (s.getDialogues().inDialogue()) {
                if (s.getDialogues().isPendingContinuation()) {
                    s.getDialogues().completeDialogue(rjStartOption);
                }
                s.getDialogues().completeDialogue(rjAccept);
            }
        }
    }

    private void talkToJuliet()
            throws InterruptedException {
        NPC juliet = s.getNpcs().closest("Juliet");
        if ((juliet != null) && (!s.getDialogues().inDialogue())) {
            juliet.interact(new String[]{"Talk-to"});
        }
        new ConditionalSleep(10000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue();
        }
    }

    private void talkToRomeo()throws InterruptedException {
        NPC romeo = s.getNpcs().closest("Romeo");
        if ((romeo != null) && (!s.getDialogues().inDialogue())) {
            romeo.interact(new String[]{"Talk-to"});
        }
        new ConditionalSleep(15000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue();
        }
        new ConditionalSleep(15000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue();
        }
        new ConditionalSleep(15000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue();
        }
    }

    private void talkToFather()
            throws InterruptedException {
        NPC father = s.getNpcs().closest("Father Lawrence");
        if (father != null) {
            father.interact(new String[]{"Talk-to"});
            new ConditionalSleep(5000) {
                public boolean condition()
                        throws InterruptedException {
                    return s.getDialogues().inDialogue();
                }
            }.sleep();
            if (s.getDialogues().inDialogue()) {
                s.getDialogues().completeDialogue(new String[0]);
            }
        }
    }

    private void pickBerries()
            throws InterruptedException {
        if (!s.getInventory().contains(new String[]{cadava})) {
            RS2Object berries = s.getObjects().closest(new String[]{cadava});
            if (berries != null) {
                berries.interact(new String[]{"Pick-from"});
                new ConditionalSleep(5000) {
                    public boolean condition()
                            throws InterruptedException {
                        return s.getInventory().contains(new String[]{cadava});
                    }
                }.sleep();
            }
        }
    }

    private void talkToApot()
            throws InterruptedException {
        NPC apot = s.getNpcs().closest(new String[]{apothecary});
        if ((apot != null) && (!s.getDialogues().inDialogue())) {
            apot.interact(new String[]{"Talk-to"});
            new ConditionalSleep(5000) {
                public boolean condition()
                        throws InterruptedException {
                    return s.getDialogues().inDialogue();
                }
            }.sleep();
        }
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue();
        }
        if ((apot != null) && (!s.getDialogues().inDialogue())) {
            apot.interact(new String[]{"Talk-to"});
            new ConditionalSleep(5000) {
                public boolean condition()
                        throws InterruptedException {
                    return s.getDialogues().inDialogue();
                }
            }.sleep();
        }
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue("Talk about something else", "Talk about Romeo & Juliet", "Ok thanks.");
        }
        new ConditionalSleep(5000) {
            public boolean condition()
                    throws InterruptedException {
                return s.getDialogues().inDialogue();
            }
        }.sleep();
        if (s.getDialogues().inDialogue()) {
            s.getDialogues().completeDialogue("Talk about something else", "Talk about Romeo & Juliet", "Ok thanks.");
        }

    }

    public State getState() {

        if ((!rjCompleted)) {
            switch (s.getConfigs().get(rjConfig)) {
                case 0:
                    return State.RJNOTSTARTED;
                case 10:
                    return State.RJSTARTED;
                case 20:
                    return State.RJLETTER;
                case 30:
                    return State.RJTOFATHER;
                case 40:
                    return State.RJTOAPOTHECARY;
                case 50:
                    return State.RJTOJULIET;
                case 60:
                    return State.RJTOROMEO;
                case 100:
                    return State.RJCOMPLETED;
            }
        }
        return State.IDLE;
    }

    public static enum State {
        IDLE, RJNOTSTARTED, RJSTARTED, RJLETTER, RJTOFATHER, RJTOAPOTHECARY, RJTOJULIET, RJTOROMEO, RJCOMPLETED;

        private State() {
        }
    }

    private void closeQuest() {
        RS2Widget close = s.getWidgets().get(277, 15);
        if (close != null) {
            close.interact(new String[]{"Close"});
        }
    }

    @Override
    public String status() {
        return "Doing Romeo/Juliet";
    }
}
