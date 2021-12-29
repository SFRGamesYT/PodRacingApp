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
    private final float DEFAULT_FOV = 1;
    String podSelected;
    Button pauseButton;
    GameBackground bg;
    Vehicle vehicleSelected;
    MiniMap miniMap;
    boolean devMode;
    private final float fov;//Field Of View

    public GameState(Handler handler, String podSelected) {
        super(handler);
        this.podSelected = podSelected;
        if (podSelected.equals(PodAnakin.label)) {
            vehicleSelected = new PodAnakin(handler);
        } else if (podSelected.equals(PodSebulba.label)) {
            vehicleSelected = new PodSebulba(handler);
        } else if (podSelected.equals(PodGasgano.label)) {
            vehicleSelected = new PodGasgano(handler);
        }
        pauseButton = new Button(handler, Assets.pauseButton, 0.05f, 0.97f, 0.03f, false);
        fov = DEFAULT_FOV;
        bg = new GameBackground(handler, fov);
        miniMap = new MiniMap(handler);




    }

    @Override
    public void render(Graphics g) {
        bg.render(g);
        pauseButton.render(g);
        vehicleSelected.render(g);
        miniMap.render(g);
        g.setColor(Color.blue);
        if (handler.devMode) {
            g.drawString("speed: "+ MathUtils.round(vehicleSelected.getHitBox().getVelocity().getLength(),2), Game.scaleToWindow(0.75),Game.scaleToWindow(0.3f));
            g.drawString("pos"+vehicleSelected.getHitBox().getPosCentre().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.4f));
            g.drawString("velocity"+vehicleSelected.getHitBox().getVelocity().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.2f));
            g.drawString("ElapsedFrames: "+String.valueOf(vehicleSelected.getHitBox().getElapsedFrames()), Game.scaleToWindow(0.75),Game.scaleToWindow(0.9f));
            g.drawString("direction"+vehicleSelected.getDirection().toString(), Game.scaleToWindow(0.75),Game.scaleToWindow(0.5f));

        }

    }

    @Override
    public void tick() {
        forward = false;

        pauseButton.tick();
        forward = pauseButton.getPressed();
        vehicleSelected.tick();
        bg.tick();
        miniMap.tick();

        devMode = handler.getKeyManager().f;


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
}
