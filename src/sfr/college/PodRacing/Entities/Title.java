/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.MathUtils;

import java.awt.*;

import static sfr.college.PodRacing.Game.scaleToWindow;

/**
 * @author SFRGa
 */
public class Title extends ImageEntity {
    float yMove = 1;

    boolean animated;
    float velocity;

    public Title(Handler handler, float s, float x, float y, Boolean anim) {
        super(handler, Assets.titleText1, s, x, y);
        this.animated = anim;
        velocity = 0;

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void tick() {
        super.tick();


        if (animated) {
            if (!isdone()) {
                if (!handler.getKeyManager().keyPressed) {
                    velocity = MathUtils.Approach(80, velocity, 20);
                    yMove = velocity;
                }
            }
            if (this.isdone()) {
                yMove = 0;
            }
            y2 += yMove;
        }


    }

    public boolean isdone() {
        if (animated) return !(y2 <= scaleToWindow(0.3f));
        return true;
    }

    public void skipAnim() {
        y2 = scaleToWindow(0.3f);

    }

}
