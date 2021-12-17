/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Entities.*;
import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;
import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;

/**
 * @author SR35477
 */
public class GameState extends State {
    private final float DEFAULT_FOV = 1.5f;
    String podSelected;
    Button pauseButton;
    GameBackground bg;
    Vehicle vehicleSelected;
    MiniMap miniMap;
    boolean devMode;
    private int mapX;
    private int mapY;
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
        bg = new GameBackground(handler, 1, 0.5f, 0.5f, fov);
        miniMap = new MiniMap(handler);
        mapX = (int) ((WIN_SIZE) - (vehicleSelected.getCamX() + WIN_SIZE_HALF));
        mapY = (int) ((WIN_SIZE) - (vehicleSelected.getCamY() + WIN_SIZE_HALF));


    }

    @Override
    public void render(Graphics g) {
        bg.render(g, vehicleSelected);
        pauseButton.render(g);
        vehicleSelected.render(g);
        miniMap.render(g);
        g.setColor(Color.CYAN);
        if (handler.devMode) {

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
        mapX = (int) ((WIN_SIZE) - (vehicleSelected.getCamX() + WIN_SIZE_HALF));
        mapY = (int) ((WIN_SIZE) - (vehicleSelected.getCamY() + WIN_SIZE_HALF));

    }

    public GameState getGameState() {
        return this;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
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
