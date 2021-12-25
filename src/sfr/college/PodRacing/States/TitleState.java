package sfr.college.PodRacing.States;


import sfr.college.PodRacing.Entities.PressKeyPrompt;
import sfr.college.PodRacing.Entities.Title;
import sfr.college.PodRacing.Entities.TitleBackground;
import sfr.college.PodRacing.Handler;

import java.awt.*;


/**
 * @author Sami
 */
public class TitleState extends State {
    private final TitleBackground bg;
    int count = 0;
    private  Title title;
    private  PressKeyPrompt pressKey;


    public TitleState(Handler handler) {
        super(handler);
        this.bg = new TitleBackground(handler, true);
        this.title = new Title(handler,0.6d,0.5d,0d,true);
        this.pressKey = new PressKeyPrompt(handler,0.4f,0.5f,0.7f);

    }

    @Override
    public void render(Graphics g) {
        bg.render(g);
        if (bg.isDone()) {
            title.render(g);
        }

        if (title.isdone()) {
            pressKey.render(g);
            if (handler.getKeyManager().keyPressed) {
                forward = true;
            }
        }
    }





    @Override
    public void tick() {
        bg.tick();
        if (bg.isDone()) {
                title.tick();
        }
        if (title.isdone()) {
           bg.setAlpha(50);
            pressKey.tick();
        }
    }
}