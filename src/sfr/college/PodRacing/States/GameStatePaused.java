/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Handler;

import java.awt.*;


//when the user paused the game
public class GameStatePaused extends StateExitable {
    //the game state before the game was paused
    private final GameState game;
    //quit button
    private final Button quit;

//constructor
    public GameStatePaused(Handler handler, GameState game) {
        super(handler);
        this.game = game;
        super.backButton = new Button(handler, Assets.backbutton, 0.2f, 0.5f, 0.3f, false);
        quit = new Button(handler, Assets.quitbutton, 0.2f, 0.5f, 0.45f, false);
    }

    //inherited render method
    @Override
    public void render(Graphics g) {
        //render the last frame of the game state before it was paused, to be the background
        game.render(g);
        //make background darker
        fillScreenColor(g, new Color(0, 0, 0, 150));
        //render buttons
        super.render(g);
        quit.render(g);
    }

    //inherited tick method
    @Override
    public void tick() {
        //update buttons
        super.tick();
        quit.tick();
        //return to main menu
        if (quit.getPressed()) forward = true;

    }

}
