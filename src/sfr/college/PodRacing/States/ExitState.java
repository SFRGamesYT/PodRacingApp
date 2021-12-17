/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Handler;

import java.awt.*;

/**
 * @author SR35477
 */
public class ExitState extends State {
    long first;

    public ExitState(Handler handler) {
        super(handler);
        first = System.currentTimeMillis();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {
        System.exit(0);

    }

}
