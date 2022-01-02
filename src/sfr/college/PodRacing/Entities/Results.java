package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.States.GameState;
import sfr.college.PodRacing.util.MathUtils;

import java.awt.*;

public class Results extends ImageEntity{
    private GameState game;
    private GameText title,lap1Time,lap2Time,lap3Time,meanTime;
    public Results(Handler handler, double s, double x, double y, GameState game) {
        super(handler, Assets.scores, s, x, y);
        this.game = game;
        title = new GameText(handler,"Results:",0.26f,0.21f);
        title.setSize(0.09f);
        lap1Time = new GameText(handler,"Lap 1 Time = "+ MathUtils.convertMillis(game.getLap1Time()),0.24f,0.3f);
        lap1Time.setSize(0.03f);
        lap2Time = new GameText(handler,"Lap 2 Time = "+ MathUtils.convertMillis(game.getLap2Time()),0.24f,0.35f);
        lap2Time.setSize(0.03f);
        lap3Time = new GameText(handler,"Lap 3 Time = "+ MathUtils.convertMillis(game.getLap3Time()),0.24f,0.4f);
        lap3Time.setSize(0.03f);
        meanTime = new GameText(handler,"Mean Lap Time = "+ MathUtils.convertMillis((game.getLap1Time()+game.getLap2Time()+game.getLap3Time())/3),0.24f,0.5f);
        meanTime.setSize(0.03f);
    }
    public void tick(){

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        title.render(g);
        lap1Time.render(g);
        lap2Time.render(g);
        lap3Time.render(g);
        meanTime.render(g);
    }
}
