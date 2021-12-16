/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import java.awt.Graphics;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.Assets;

/**
 *
 * @author sr35477
 */
public class PodGasgano extends Vehicle {
    public static String label = "Gasgano's Ord Pedrovia";
    public PodGasgano(Handler handler) {
        super(handler, new Animation(50,Assets.pod2), 0.5f, 0.8f, 2.8f, 0.025d, 0.006d,Assets.engine1);
        left = new Animation(50,Assets.pod2left);
        right = new Animation(50,Assets.pod2right);
    }
    public void tick(){
        super.tick();
    }
    public void render(Graphics g){
        super.render(g);
    }
}
