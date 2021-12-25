/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;

/**
 * @author Sami
 */
public class TitleBackground extends ImageEntity {
    private int alpha = 255;
    private final boolean animated;

    public TitleBackground(Handler handler, boolean anim) {
        super(handler, Assets.titleBg, 1, 0.5f, 0.5f);
        this.animated = anim;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
    }

    @Override
    public void tick() {
        int time = handler.getTime();
        super.tick();
        if (animated) {
            if (time % 5 == 0 && !isDone()) {
                alpha--;
            }
            if (handler.getKeyManager().keyPressed) {
                skipAnim();
            }
        } else {
            skipAnim();
        }
    }

    public Boolean isDone() {
        return alpha <= 72;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void skipAnim() {
        alpha = 72;
    }

}
