/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Game;
import static sfr.college.PodRacing.Game.*;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Assets;


/**
 *
 * @author sr35477



/**
 *
 * @author sr35477
 */
public class GameStatePaused extends StateExitable {
    int now;
    private final GameState game;
    private final Button quit;

    public GameStatePaused(Handler handler,GameState game){
        super(handler);
        this.game = game;
        super.backButton = new Button(handler,Assets.backbutton,0.35f,0.5f,0.4f,false);
        quit = new Button(handler,Assets.quitbutton,0.35f,0.5f,0.6f,false);
    }

    @Override
    public void render(Graphics g){
       game.render(g);
       fillScreenColor(g, new Color(0,0,0,150));
       super.render(g);
       quit.render(g);
    }
    @Override
    public void tick(){

        super.tick();
        quit.tick();
        if(quit.getPressed())forward = true;
        if(back == true)System.out.println("BACK "+back);
    }
   
    
    
}
