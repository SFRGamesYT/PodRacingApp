package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Entities.PressKeyPrompt;
import sfr.college.PodRacing.Entities.Title;
import sfr.college.PodRacing.Entities.TitleBackground;
import sfr.college.PodRacing.Handler;

import java.awt.*;

//the image of the racetrack is faded in from black, then the game's title is shown.
//then the user is prompted to press any key to continue
public class TitleState extends State {
    //object that contains image of racetrack for the background.
    private final TitleBackground bg;
    //object that contains image of title.
    private  Title title;
    //object that contains image of the prompt to press any key to start.
    private  PressKeyPrompt pressKey;

    //constructor
    public TitleState(Handler handler) {
        super(handler);
        this.bg = new TitleBackground(handler, true);
        this.title = new Title(handler,1.5d,0.5d,0d,true);
        this.pressKey = new PressKeyPrompt(handler,0.4f,0.5f,0.7f);

    }

    //inherited render method
    @Override
    public void render(Graphics g) {
        //render background.
        bg.render(g);
        //if background has finished fading in, render the title.
        if (bg.isDone()) {
            title.render(g);
        }
        //if title has finished its animation, render the press key prompt.
        if (title.isdone()) {
            pressKey.render(g);
            if (handler.getKeyManager().keyPressed) {
                forward = true;
            }
        }
    }

    //inherited tick method
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