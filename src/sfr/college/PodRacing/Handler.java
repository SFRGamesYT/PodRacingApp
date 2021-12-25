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

	/**getTime
	 * Returns the private variable time's value
	 * @return the value of the private variable time
	 */
    public int getTime() {
        return game.getTime();
    }

	/**getKeyManager
	 * Returns the private variable keyManager's value
	 * @return the value of the private variable keyManager
	 */
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

	/**getMouseManager
	 * Returns the private variable mouseManager's value
	 * @return the value of the private variable mouseManager
	 */
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

	/**getGame
	 * Returns the private variable game's value
	 * @return the value of the private variable game
	 */

    public Game getGame() {
        return game;
    }

	/**setGame
	 * Sets the private variable game to the provided value
	 * @param game value to store into game
	 */
    public void setGame(Game game) {
        this.game = game;
    }

	/**getStateManager
	 * Returns the private variable stateManager's value
	 * @return the value of the private variable stateManager
	 */
    public StateManager getStateManager() {
        return game.sm;
    }

	/**getGameState
	 * Returns the private variable gameState's value
	 * @return the value of the private variable gameState
	 */
    public GameState getGameState() {
        return this.getStateManager().getGame();
    }

	/**setInvert
	 * Sets the private variable invert to the provided value
	 * @param b value to store into invert
	 */
    public void setInvert(boolean b) {
        invertControls = b;
    }

	/**getFOV
	 * Returns the private variable FOV's value
	 * @return the value of the private variable fOV
	 */
    public float getFOV() {
        return getGameState().getBG().getFOV();
    }

	/**getCollisionHull
	 * Returns the private variable collisionHull's value
	 * @return the value of the private variable collisionHull
	 */
	public Vehicle getCurrentVehicle(){
		return getGameState().getVehicleSelected();
	}



}
