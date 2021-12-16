/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing;

import java.awt.Rectangle;
import sfr.college.PodRacing.States.GameState;
import sfr.college.PodRacing.States.StateManager;

/**
 *
 * @author sr35477
 */
public class Handler {
    private Game game;
    public boolean invertControls;
    public boolean devMode = false;
    public boolean hasSound;
    public boolean isPlayerCollidng;
    public Handler(Game game) {
        this.game = game;

    }
    
    public int getTime(){
        return game.getTime();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }
    public StateManager getStateManager(){
        return game.sm;
    }
    public GameState getGameState(){
        return this.getStateManager().getGame();
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public void setInvert(boolean b){
        invertControls = b;
    }
    public float getFOV(){
        return getGameState().getBG().getFOV();
    }
    public Rectangle getCollisionHull(){
        return getGameState().getVehicleSelected().getCollisionHull();
    }


    
    
    
}
