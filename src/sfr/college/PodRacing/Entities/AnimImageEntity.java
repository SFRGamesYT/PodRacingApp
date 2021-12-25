/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;

import java.awt.*;



public class AnimImageEntity extends ImageEntity {
    private Animation anim;

    public AnimImageEntity(Handler handler, Animation anim, float s, float x, float y) {
        super(handler, anim.getCurrentFrame(), s, x, y);
        this.anim = anim;
    }


    public void tick() {
        anim.tick();
        super.img = anim.getCurrentFrame();
        super.tick();
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void setAnim(Animation a) {
        this.anim = a;
    }

}
