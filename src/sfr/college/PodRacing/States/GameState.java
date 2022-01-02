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

/**
 * @author SR35477
 */
public class GameState extends State {

    private String podSelected;
    private Button pauseButton,backButton;
    private GameBackground bg;
    private Vehicle vehicleSelected;
    private MiniMap miniMap;
    private boolean devMode;
    private final float fov;//Field Of View
    private GameText lapCounter, lap1,lap2,lap3;
    private long lap1Time,lap2Time,lap3Time,now;
    private boolean finished,showScores;
    private Results scores;



    public GameState(Handler handler, String podSelected,int DEFAULT_FOV) {
        super(handler);
        fov = DEFAULT_FOV;
        bg = new GameBackground(handler, fov);
        this.podSelected = podSelected;
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



    @Override
    public void render(Graphics g) {
        bg.render(g);



        g.setColor(Color.blue);
        if (handler.devMode&&!finished) {
            g.drawString("speed: "+ MathUtils.round(vehicleSelected.getHitBox().getVelocity().getLength(),2), Game.scaleToWindow(0.75),Game.scaleToWindow(0.3f));
            g.drawString("pos"+vehicleSelected.getHitBox().getPosCentre().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.4f));
            g.drawString("velocity"+vehicleSelected.getHitBox().getVelocity().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.2f));
            g.drawString("ElapsedFrames: "+ vehicleSelected.getHitBox().getElapsedFrames(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.9f));
            g.drawString("direction"+vehicleSelected.getDirection().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.5f));

        }else{
            miniMap.render(g);
           if(!finished){
               pauseButton.render(g);
               vehicleSelected.render(g);
           }

            lapCounter.render(g);
            lap1.render(g);
            lap2.render(g);
            lap3.render(g);
        }
        if(showScores){
            scores.render(g);
            backButton.render(g);
        }


    }

    @Override
    public void tick() {
        forward = false;
        if(finished&&System.currentTimeMillis()-now>1000){
            showScores = true;

        }
        if(showScores)backButton.tick();
        if(!finished)pauseButton.tick();
        forward = pauseButton.getPressed();
        if(showScores)forward = backButton.getPressed();
        vehicleSelected.tick();
        bg.tick();
        miniMap.tick();

        devMode = handler.getKeyManager().f;
        if(!finished)lapCounter.setText("Lap: "+ vehicleSelected.getLap() +"/3");
        if(finished)vehicleSelected.setHasControls(false);
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
        if(vehicleSelected.getLap()>3&&!finished){
            lapCounter.setSize(0.16f);
            lapCounter.setX(0.21f);
            lapCounter.setY(0.5f);
            lapCounter.setText("Finish!");
            lap1.setText("");
            lap2.setText("");
            lap3.setText("");
            this.finished = true;
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
