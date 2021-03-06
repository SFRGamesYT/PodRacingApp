/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;

import java.util.Stack;

/**
 * @author SR35477
 */
public class StateManager {
    private final Stack<State> states;
    private final IntroState intro;
    private TitleState title;
    private MenuState menu;
    private ChooseState choosePod;
    private SettingsState settings;
    private ExitState exit;
    private GameState game;
    private GameStatePaused gamePause;
    private State currentState = null;
    private final Handler handler;

    private int count;

    public StateManager(Handler handler) {
        this.handler = handler;
        states = new Stack<>();
        intro = new IntroState(handler);
        states.push(intro);
        currentState = states.lastElement();
        count = 0;
    }

    public State getState() {
        return currentState;
    }

    public void tick() {

        currentState = states.lastElement();
        if (states.contains(menu) && currentState != menu) menu.tick();
        changeState(currentState.back, currentState.forward);
    }

    public void changeState(Boolean back, Boolean forward) {
        if (forward && !back) {
            if (!currentState.equals(intro)) Assets.beep.play();
            if (currentState.equals(intro)) {
                    title = new TitleState(handler);
                    states.push(title);

            } else if (currentState.equals(title)) {
                menu = new MenuState(handler);
                states.push(menu);
            } else if (currentState.equals(menu)) {
                switch (menu.getMenuChoice()) {
                    case "PLAY":
                        choosePod = new ChooseState(handler);
                        states.push(choosePod);
                        break;
                    case "SETTINGS":
                        settings = new SettingsState(handler);
                        states.push(settings);
                        break;
                    case "QUIT":
                        exit = new ExitState(handler);
                        states.push(exit);
                        break;
                }
            } else if (currentState.equals(choosePod)) {
                game = new GameState(handler, choosePod.getPodSelected(),12);
                states.push(game);
            } else if (currentState.equals(game)) {
                gamePause = new GameStatePaused(handler, game);
                states.push(gamePause);
                count = 0;
            } else if (currentState.equals(gamePause)) {
                menu = new MenuState(handler);
                states.clear();
                Assets.init(handler);
                states.push(menu);

            } else if (currentState.equals(settings)) {

            }
        } else {
            if (currentState.equals(gamePause) && count < 5) {
                game.tick();
                count++;
            }
            if (currentState.exitable && back) {
                Assets.beep.play();
                states.pop();
            }
        }
    }

    public Stack<State> getStates() {
        return states;
    }

    public IntroState getIntro() {
        return intro;
    }

    public TitleState getTitle() {
        return title;
    }

    public void setTitle(TitleState title) {
        this.title = title;
    }

    public MenuState getMenu() {
        return menu;
    }

    public void setMenu(MenuState menu) {
        this.menu = menu;
    }

    public ChooseState getChoosePod() {
        return choosePod;
    }

    public void setChoosePod(ChooseState choosePod) {
        this.choosePod = choosePod;
    }

    public SettingsState getSettings() {
        return settings;
    }

    public void setSettings(SettingsState settings) {
        this.settings = settings;
    }

    public ExitState getExit() {
        return exit;
    }

    public void setExit(ExitState exit) {
        this.exit = exit;
    }

    public void setGame(GameState game) {
        this.game = game;
    }

    public GameStatePaused getGamePause() {
        return gamePause;
    }

    public void setGamePause(GameStatePaused gamePause) {
        this.gamePause = gamePause;
    }


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GameState getGame() {
        return game;
    }

}