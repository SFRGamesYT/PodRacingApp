/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

/**
 * @author Sami
 */
public class MiniMap extends ImageEntity {
    private Vector2D cursorPos;

    public MiniMap(Handler handler) {
        super(handler, Assets.minimap, 0.2f, 0.9f, 0.9f);
        cursorPos = new Vector2D();
        cursorPos.set(boundsOnScreen.getPos());
    }

    @Override
    public void tick() {
        super.tick();
        cursorPos.set(Vector2D.add(boundsOnScreen.getPos(),handler.getCurrentVehicle().getHitBox().getPos().getMultiplied(0.2)));
    }

    public void render(Graphics g) {
        super.render(g);
        drawPos(g);

    }



    public void drawPos(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval((int)cursorPos.x,(int)cursorPos.y, Game.scaleToWindow(0.01f), Game.scaleToWindow(0.01f));
    }

}
