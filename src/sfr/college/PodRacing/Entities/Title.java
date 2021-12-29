/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;

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
            if (0.16 * boundsOnScreen.getElapsedFrames() >= Math.PI * 2) {
                boundsOnScreen.getVelocity().setZero();
                boundsOnScreen.getAcceleration().setZero();
                System.out.println(boundsOnScreen.getPos().x);
            } else {
                boundsOnScreen.getAcceleration().set(0, Math.sin(0.16 * boundsOnScreen.getElapsedFrames()));
            }
            if(isdone()&&Assets.main.getClip().getMicrosecondPosition()<23900 * 1000L){
                Assets.main.skip(23900);
            }
            if(boundsOnScreen.getSize().x> Game.scaleToWindow(0.9))boundsOnScreen.getSize().multiply(0.96f);
        }
    }

    public boolean isdone() {
        if (animated) return (0.35 * boundsOnScreen.getElapsedFrames() >= Math.PI * 2);
        return true;
    }



}
