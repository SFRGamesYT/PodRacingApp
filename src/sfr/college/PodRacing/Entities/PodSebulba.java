/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;

import java.awt.*;

/**
 * @author sr35477
 */
public class PodSebulba extends Vehicle {
    public static String label = "Sebulba's Podracer";

    public PodSebulba(Handler handler) {
        super(handler, new Animation(50, Assets.pod1), 0.5f, 0.8f, 3f, 0.022d, 0.01d, Assets.engine1);
        left = new Animation(50, Assets.pod1left);
        right = new Animation(50, Assets.pod1right);
    }

    public void tick() {
        super.tick();

    }

    public void render(Graphics g) {
        super.render(g);
    }
}
