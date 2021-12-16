/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static sfr.college.PodRacing.Game.WIN_SIZE;
import sfr.college.PodRacing.Handler;

/**
 *
 * @author SR35477
 */
public class VideoSettingsState extends StateExitable {

    public VideoSettingsState(Handler handler){
        super(handler);
        
    }
    
    public void render(Graphics g) {
       g.setColor(Color.CYAN);
       g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
       g.setColor(Color.RED);
       g.setFont(new Font("Arial",0,50));
       g.drawString("VIDEO SETTINGS",10, 100);
       super.render(g);
    }

    public void tick() {
        super.tick();
    }
}
