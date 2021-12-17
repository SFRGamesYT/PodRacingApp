/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Handler;

import java.awt.*;

/**
 * @author SR35477
 */
public abstract class Entity {
    protected float x, y;
    protected Handler handler;

    public abstract void render(Graphics g);

    public abstract void tick();
}
