/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

/**
 * @author SFRGa
 */
public class Title extends ImageEntity {
    boolean animated;


    public Title(Handler handler, double s, double x, double y, Boolean anim) {
        super(handler, Assets.titleText1, s, x, y);
        this.animated = anim;
        if(animated){
            boundsOnScreen.setAcceleration(new Vector2D(0,0));
        }
    }



    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        if(animated) {
            if (00.12d * boundsOnScreen.getElapsedFrames() >= Math.PI * 2) {
                boundsOnScreen.getVelocity().setZero();
                boundsOnScreen.getAcceleration().setZero();
                System.out.println(boundsOnScreen.getPos().x);
            } else {
                boundsOnScreen.getAcceleration().set(0, Math.sin(00.12d * boundsOnScreen.getElapsedFrames()));
            }
            if(isdone()&&Assets.main.getClip().getMicrosecondPosition()<23900 * 1000L){
                Assets.main.skip(23900);
            }
        }
    }

    public boolean isdone() {
        if (animated) return (boundsOnScreen.getPos().y >= 195.8);
        return true;
    }



}
