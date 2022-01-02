/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Entities.Title;
import sfr.college.PodRacing.Entities.TitleBackground;
import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;

/**
 * @author SR35477
 */
public class MenuState extends State {
    private final Title title;
    private final Button play;
    private final Button settings;
    private final Button quit;
    private final TitleBackground background;
    private String menuChoice;
    private final int now;

    public MenuState(Handler handler) {
        super(handler);

        menuChoice = "";
        title = new Title(handler, 0.6f, 0.5f, 0.1f, false);
        play = new Button(handler, Assets.playbutton, 0.3f, 0.5f, 0.3f, false);
        settings = new Button(handler, Assets.settingsbutton, 0.3f, 0.5f, 0.5f, false);
        quit = new Button(handler, Assets.quitbutton, 0.3f, 0.5f, 0.7f, false);
        background = new TitleBackground(handler, false);
        now = handler.getTime();
    }

    public String getMenuChoice() {
        return menuChoice;
    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        title.render(g);
        play.render(g);
        settings.render(g);
        quit.render(g);
    }

    @Override
    public void tick() {
        menuChoice = "";
        title.tick();
        play.tick();
        settings.tick();
        quit.tick();
        background.tick();
        if (handler.getTime() - now > 5)
            if (play.getPressed()) {
                menuChoice = "PLAY";
            } else if (settings.getPressed()) {
                menuChoice = "SETTINGS";
            } else if (quit.getPressed()) {
                menuChoice = "QUIT";
            }
        forward = !(menuChoice.equals(""));

    }


}
