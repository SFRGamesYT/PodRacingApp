/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static sfr.college.PodRacing.Game.WIN_SIZE;


/**
 * @author Sami
 */
public class GameBackground extends AnimImageEntity {

    protected float fov;



    public GameBackground(Handler handler, float s, float x, float y, float fov) {
        super(handler, new Animation(300, Assets.bg), s, x, y);
        this.fov = fov;
    }

    public void tick() {

        super.tick();


    }

    public void render(Graphics g) {
        Vehicle v = handler.getCurrentVehicle();
        Graphics2D g2d = (Graphics2D) g;
        double width = WIN_SIZE;
        double height = WIN_SIZE;

        double zoomWidth = width * fov;
        double zoomHeight = height * fov;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;
        AffineTransform OLDat = new AffineTransform();
        AffineTransform at = new AffineTransform();
        //at.rotate(2*Math.PI-v.getAngle(), WIN_SIZE_HALF,WIN_SIZE_HALF);
        at.translate(anchorx, anchory);
        at.scale(fov, fov);
       // at.translate(-WIN_SIZE*(v.getHitBox().getPosCentre().x/256.0d)+WIN_SIZE_HALF, -WIN_SIZE*(v.getHitBox().getPosCentre().y/256.0d)+WIN_SIZE_HALF);

        g2d.setTransform(at);


        super.render(g2d);
        if (handler.devMode) {
            g.setColor(Color.MAGENTA);
            int count = 0;
            while (true) {
                try {
                    g.setColor(Color.MAGENTA);
                    MapCollisionHulls.hitBoxes[count].getScaled().render(g);

                    if (v.getHitBox().RectVsRect(MapCollisionHulls.hitBoxes[count])){
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.yellow);
                    }

                    v.getHitBox().getScaled().render(g);
                    g.drawLine((int)v.getHitBox().getScaled().getPosCentre().x,(int)v.getHitBox().getScaled().getPosCentre().y,(int)v.getHitBox().getScaled().getPosCentre().getAdded(v.getDirection().getMultiplied(Game.scaleToWindow(0.1f))).x,(int)v.getHitBox().getScaled().getPosCentre().getAdded(v.getDirection().getMultiplied(Game.scaleToWindow(0.1f)*-1)).y);
                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    break;
                }
                count++;

            }
        }
        g2d.setTransform(OLDat);
    }

    public float getFOV() {
        return fov;
    }
}
