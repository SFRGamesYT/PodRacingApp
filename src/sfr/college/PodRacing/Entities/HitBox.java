/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Game;

import java.awt.*;

/**
 * @author sr35477
 */
public class HitBox extends Rectangle {
    Point p1, p2, p3, p4;

    public HitBox(int x1, int y1, int x2, int y2) {
        super(Game.scaleToWindow(x1 / 256f), Game.scaleToWindow(y1 / 256f), Game.scaleToWindow((x2 - x1) / 256f), Game.scaleToWindow((y2 - y1) / 256f));
        p1 = new Point(x, y);
        p2 = new Point(x + width, y);
        p3 = new Point(x, y + height);
        p4 = new Point(x + width, y + height);
    }

    public void render(Graphics g) {

    }
}
