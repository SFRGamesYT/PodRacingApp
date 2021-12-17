/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;

/**
 * @author SR35477
 */
public class AudioSettingsState extends StateExitable {
    public AudioSettingsState(Handler handler) {
        super(handler);
    }

    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", 0, 50));
        g.drawString("AUDIO SETTINGS", 10, 100);
        super.render(g);
    }

    public void tick() {
        super.tick();

    }

}
