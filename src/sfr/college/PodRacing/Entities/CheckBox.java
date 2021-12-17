package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;

import java.awt.*;

/**
 * @author Sami
 */
public class CheckBox extends Button {
    private boolean on;
    private int count = 0;

    public CheckBox(Handler handler, float s, float x, float y) {
        super(handler, Assets.checkboxfalse, s, x, y, false);
        on = false;
    }

    public void tick() {
        super.tick();
        if (pressed) {
            count++;
            if (count == 1) {
                on = !on;
            }
        } else {
            count = 0;
        }
        if (on) {
            setNormal(Assets.checkboxtrue[0]);
            setFlash(Assets.checkboxtrue[1]);
        } else {
            setNormal(Assets.checkboxfalse[0]);
            setFlash(Assets.checkboxfalse[1]);
        }

    }

    public void render(Graphics g) {
        super.render(g);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }


}
