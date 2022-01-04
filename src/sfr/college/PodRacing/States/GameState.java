/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Entities.*;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.MathUtils;
import java.awt.*;

//the actual gameplay
public class GameState extends State {
    //buttons
    private Button pauseButton,backButton;
    //the racetrack that the vehicle drives on
    private GameBackground bg;
    //the vehicle selected in the choose state
    private Vehicle vehicleSelected;
    //game's minimap
    private MiniMap miniMap;
    //field of view
    private final float fov;
    //game text
    private GameText lapCounter, lap1,lap2,lap3;
    //stores the time at which the game is started, and when each lap is completed
    private long lap1Time,lap2Time,lap3Time,now;
    //stores if all laps are completed, and if the game results should be displayed
    private boolean finished,showScores;
    //object that is responsible for displaying a results window to the user
    private Results scores;

    //constructor
    public GameState(Handler handler, String podSelected,int DEFAULT_FOV) {
        super(handler);
        fov = DEFAULT_FOV;
        bg = new GameBackground(handler, fov);
        //sets selected vehicle to its relevant sub-class
        if (podSelected.equals(PodAnakin.label)) {
            vehicleSelected = new PodAnakin(handler);
        } else if (podSelected.equals(PodSebulba.label)) {
            vehicleSelected = new PodSebulba(handler);
        } else if (podSelected.equals(PodGasgano.label)) {
            vehicleSelected = new PodGasgano(handler);
        }
        pauseButton = new Button(handler, Assets.pauseButton, 0.05f, 0.97f, 0.03f, false);
        miniMap = new MiniMap(handler);
        lapCounter = new GameText(handler,"Lap: "+Byte.toString(vehicleSelected.getLap())+"/3",0.05f,0.95f);
        lap1 = new GameText(handler,"Lap 1: ",0.02f,0.05f);
        lap2 = new GameText(handler,"Lap 2: ",0.02f,0.09f);
        lap3 = new GameText(handler,"Lap 3: ",0.02f,0.13f);
        lap1.setSize(0.035f);
        lap2.setSize(0.035f);
        lap3.setSize(0.035f);
        finished = false;
        showScores = false;
    }

    //inherited render method
    @Override
    public void render(Graphics g) {
        //render racetrack
        bg.render(g);
        g.setColor(Color.blue);
        //debug mode, displays different values
        if (handler.devMode && !finished) {
            g.drawString("speed: " + MathUtils.round(vehicleSelected.getHitBox().getVelocity().getLength(), 2), Game.scaleToWindow(0.75), Game.scaleToWindow(0.3f));
            g.drawString(vehicleSelected.getHitBox().getPosCentre().toString(), Game.scaleToWindow(0.75), Game.scaleToWindow(0.4f));
            g.drawString("velocity" + vehicleSelected.getHitBox().getVelocity().toString(), Game.scaleToWindow(0.75), Game.scaleToWindow(0.2f));
            g.drawString("ElapsedFrames: " + vehicleSelected.getHitBox().getElapsedFrames(), Game.scaleToWindow(0.75), Game.scaleToWindow(0.9f));
            g.drawString("direction" + vehicleSelected.getDirection().toString(), Game.scaleToWindow(0.75), Game.scaleToWindow(0.5f));
        } else {
            //render ui
            miniMap.render(g);
            if (!finished) {
                pauseButton.render(g);
                vehicleSelected.render(g);
                lapCounter.render(g);
                lap1.render(g);
                lap2.render(g);
                lap3.render(g);
            }
            if (showScores) {
                scores.render(g);
                backButton.render(g);
            }
        }
    }
    @Override
    public void tick() {
        //don't move to next state
        forward = false;
        //if 1 second has passed since finishing the game, show the scores
        if(finished&&System.currentTimeMillis()-now>1000){
            showScores = true;
        }
        if(showScores)backButton.tick();
        if(!finished)pauseButton.tick();
        forward = pauseButton.getPressed();
        if(showScores)forward = backButton.getPressed();
        //update the vehicle, racetrack and minimap
        vehicleSelected.tick();
        bg.tick();
        miniMap.tick();

        //set current lap
        if(!finished)lapCounter.setText("Lap: "+ vehicleSelected.getLap() +"/3");
        //don't respond to key input if game is finished
        if(finished)vehicleSelected.setHasControls(false);
        //calculate lap times
        if(vehicleSelected.getLap()==1){
            lap1Time = vehicleSelected.getTimeTaken();
            lap1.setText("Lap 1: "+MathUtils.convertMillis(lap1Time));
        }
        if(vehicleSelected.getLap()==2){
            lap2Time = vehicleSelected.getTimeTaken()-lap1Time;
            lap2.setText("Lap 2: "+MathUtils.convertMillis(lap2Time));
        }
        if(vehicleSelected.getLap()==3){
            lap3Time = vehicleSelected.getTimeTaken()-lap2Time-lap1Time;
            lap3.setText("Lap 3: "+MathUtils.convertMillis(lap3Time));
        }
        //if finished
        if(vehicleSelected.getLap()>3&&!finished){
            //display "finished!" to the screen
            lapCounter.setSize(0.16f);
            lapCounter.setX(0.21f);
            lapCounter.setY(0.5f);
            lapCounter.setText("Finish!");
            //stop displaying game lap times
            lap1.setText("");
            lap2.setText("");
            lap3.setText("");
            //tell program that game is finished
            this.finished = true;
            //initiate results window
            scores = new Results(handler,0.7f,0.5f,0.5f,this);
            backButton = new Button(handler,Assets.backbutton,0.3f,0.5f,0.7f,false);
            now = System.currentTimeMillis();
        }
    }
    public GameState getGameState() {
        return this;
    }

    public float getFov() {
        return fov;
    }

    public Vehicle getVehicleSelected() {
        return vehicleSelected;
    }

    public GameBackground getBG() {
        return bg;
    }

    public long getLap1Time() {
        return lap1Time;
    }

    public long getLap2Time() {
        return lap2Time;
    }

    public long getLap3Time() {
        return lap3Time;
    }
}
