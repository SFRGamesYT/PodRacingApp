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
public class GameSettingsState extends StateExitable {

    public GameSettingsState(Handler handler) {
        super(handler);
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", 0, 50));
        g.drawString("GAME SETTINGS", 10, 100);
        super.render(g);
    }

    public void tick() {
        super.tick();
    }
}
