/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;

import java.awt.*;

/**
 * @author sr35477
 */
public class PodGasgano extends Vehicle {
    public static String label = "Gasgano's Ord Pedrovia";

    public PodGasgano(Handler handler) {
        super(handler, new Animation(50, Assets.pod2), 0.5f, 0.5f, Game.WIN_SIZE*0.007f, 0.025d, Game.WIN_SIZE*0.00002d, Assets.engine1);
        left = new Animation(50, Assets.pod2left);
        right = new Animation(50, Assets.pod2right);
    }

    public void tick() {
        super.tick();
    }

    public void render(Graphics g) {
        super.render(g);
    }
}
