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

//the main menu of the game, user can play, go to settings or exit the game.
public class MenuState extends State {
    //title object containing an image of the title
    private final Title title;
    //button objects for play, settings and quit button
    private final Button play;
    private final Button settings;
    private final Button quit;
    //background object containing an image of the racetrack
    private final TitleBackground background;
    //stores the name of the button that has been pressed, so the state manager knows
    //what the next state needs to be
    private String menuChoice;
    //number of frames up to now
    private final int now;

    //constructor
    public MenuState(Handler handler) {
        super(handler);
        //menuChoice is set to an empty string to begin with
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

    //inherited render method
    @Override
    public void render(Graphics g) {
        //render background
        background.render(g);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        //render rest
        title.render(g);
        play.render(g);
        settings.render(g);
        quit.render(g);
    }

    //inherited tick method
    @Override
    public void tick() {
        //resets menuChoice to an empty string, otherwise user won't be able to return to the
        //main menu
        menuChoice = "";
        //update different game objects
        title.tick();
        play.tick();
        settings.tick();
        quit.tick();
        background.tick();
        //add a small delay before user can start pressing buttons,
        // to avoid skipping through the menu state
        if (handler.getTime() - now > 5)
            //if play button is pressed
            if (play.getPressed()) {
                menuChoice = "PLAY";
            //if settings button is pressed
            } else if (settings.getPressed()) {
                menuChoice = "SETTINGS";
            //if quit button is pressed
            } else if (quit.getPressed()) {
                menuChoice = "QUIT";
            }
        //if String menuChoice is not empty, move to the next state
        forward = !(menuChoice.equals(""));

    }


}
