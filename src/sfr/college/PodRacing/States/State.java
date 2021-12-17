/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;

/**
 * @author SR35477
 */
public class State {
    public boolean exitable = false;
    public boolean forward = false;
    public boolean back = false;
    protected Handler handler;
    protected long first;

    public State(Handler handler) {
        this.handler = handler;
    }

    protected static void fillScreenColor(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
    }

    public void render(Graphics g) {

    }

    public void tick() {

    }
}
