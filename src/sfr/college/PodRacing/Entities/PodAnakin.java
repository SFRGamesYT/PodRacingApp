/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;

import java.awt.*;


/**
 * @author Sami
 */
public class PodAnakin extends Vehicle {
    public static String label = "Anakin's Podracer";

    public PodAnakin(Handler handler) {
        super(handler, new Animation(50, Assets.pod0), 0.5f, 0.85f, Game.WIN_SIZE*0.005f, 0.03d, Game.WIN_SIZE*0.00001f, Assets.engine1);
        left = new Animation(50, Assets.pod0left);
        right = new Animation(50, Assets.pod0right);
    }

    public void tick() {
        super.tick();

    }

    public void render(Graphics g) {
        super.render(g);
    }
}
