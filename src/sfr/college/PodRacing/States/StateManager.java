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
    private AudioSettingsState audioSettings;
    private VideoSettingsState videoSettings;
    private GameSettingsState gameSettings;
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
                if(intro.skip){
                    game = new GameState(handler,"Anakin's Podracer");
                    states.push(game);
                }else {
                    title = new TitleState(handler);
                    states.push(title);
                }
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
                game = new GameState(handler, choosePod.getPodSelected());
                states.push(game);
            } else if (currentState.equals(game)) {
                gamePause = new GameStatePaused(handler, game);
                states.push(gamePause);
                count = 0;
            } else if (currentState.equals(gamePause)) {
                menu = new MenuState(handler);
                states.clear();
                states.push(menu);

            } else if (currentState.equals(settings)) {
                switch (settings.getSettingsChoice()) {
                    case 1:
                        videoSettings = new VideoSettingsState(handler);
                        states.push(videoSettings);
                        break;
                    case 2:
                        audioSettings = new AudioSettingsState(handler);
                        states.push(audioSettings);
                        break;
                    case 3:
                        gameSettings = new GameSettingsState(handler);
                        states.push(gameSettings);
                        break;
                }
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

    public GameState getGame() {
        return game;
    }

}