/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing;

import sfr.college.PodRacing.Entities.Vehicle;
import sfr.college.PodRacing.States.GameState;
import sfr.college.PodRacing.States.StateManager;

/**
 * @author sr35477
 */
public class Handler {
    public boolean invertControls;
    public boolean devMode = false;
    public boolean hasSound;
	private Game game;

	/**Handler
	 * Easy way for different classes to access commonly used variables and methods
	 * 
	 * @param game main game instance
	 */
    public Handler(Game game) {
        this.game = game;

    }


    public int getTime() {
        return game.getTime();
    }


    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }



    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }


    public StateManager getStateManager() {
        return game.sm;
    }


    public GameState getGameState() {
        return this.getStateManager().getGame();
    }

    public void setInvert(boolean b) {
        invertControls = b;
    }


    public float getFOV() {
        return getGameState().getBG().getFOV();
    }


	public Vehicle getCurrentVehicle(){
		return getGameState().getVehicleSelected();
	}



}
