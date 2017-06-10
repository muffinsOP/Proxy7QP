/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy7qp;

import java.awt.Graphics2D;
import java.util.ArrayList;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

/**
 *
 * @author Islah
 */
@ScriptManifest(author = "Muffins", name = "7QP Getter", version = 1.0, logo = "", info = "Aquires 7 Quest Points")

public class Main extends Script {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private long startTime;
    public String current;

    @Override
    public void onStart() {
        
        tasks.add(new WalkToGE(this));
        tasks.add(new BankAtGE(this));
        tasks.add(new BuySupplies(this));
        tasks.add(new WithdrawQuestItems(this));
        tasks.add(new doRomeo(this));
        tasks.add(new DoricsQuest(this));
        tasks.add(new CookQuest(this));
        startTime = System.currentTimeMillis();
        

    }

    @Override
    public int onLoop() throws InterruptedException {
        

        tasks.forEach(tasks -> {

            current = tasks.status();
            tasks.run();

        }
        );
        return 150;
    }

    @Override
    public void onPaint(Graphics2D g) {
        String newstatus = current.toString();
        long timePassed = System.currentTimeMillis() - startTime;
        int seconds = (int) (timePassed / 1000) % 60;
        int minutes = (int) ((timePassed / (1000 * 60)) % 60);
        int hours = (int) ((timePassed / (1000 * 60 * 60)));

        if (hours > 99) {
            g.drawString((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":"
                    + (seconds < 10 ? "0" : "") + seconds, 450, 10);
            g.drawString("Status: " + newstatus, 400, 60);

        } else {
            g.drawString((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":"
                    + (seconds < 10 ? "0" : "") + seconds, 450, 10);
            g.drawString("Status: " + newstatus, 400, 60);
        }

    }

}
