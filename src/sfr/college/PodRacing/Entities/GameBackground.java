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
import static sfr.college.PodRacing.Game.scaleToWindow;


/**
 * @author Sami
 */
public class GameBackground extends AnimImageEntity {

    protected float fov;
    protected MapCollisionHulls mch;
    protected HitBox finishLine;


    public GameBackground(Handler handler, float fov) {
        super(handler, new Animation(300, Assets.bg), 1, 0.5f, 0.5f);
        this.fov = fov;
        mch = new MapCollisionHulls();
        finishLine = new HitBox(7,185,27,187);
    }

    public void tick() {
        super.tick();


    }

    public void render(Graphics g) {
        boolean doCamera = true;
        g.setColor(Game.SAND);
        g.fillRect(0,0,WIN_SIZE,WIN_SIZE);
        Graphics2D g2d = (Graphics2D)g;
        Vehicle v = handler.getCurrentVehicle();
        AffineTransform OLDat = g2d.getTransform();

        double width = WIN_SIZE;
        double height =WIN_SIZE;

        double zoomWidth = width * fov;
        double zoomHeight = height * fov;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;
        if(doCamera) {
            AffineTransform at = g2d.getTransform();
            at.rotate(Math.PI * 2 - v.getAngle(), v.getBoundsOnScreen().getPosCentre().x, v.getBoundsOnScreen().getPosCentre().y);
            at.translate(anchorx, anchory);
            at.scale(fov, fov);
            at.translate(-v.getHitBox().getPosCentre().x + scaleToWindow(0.5), -v.getHitBox().getPosCentre().y + scaleToWindow(0.516));

            g2d.setTransform(at);
        }

        if(handler.devMode){
            g2d.setColor(Color.blue);
            mch.render(g2d);
            g.setColor(Color.pink);
            finishLine.draw(g2d);
            g.setColor(Color.green);
            if(v.isColliding())g2d.setColor(Color.red);
            handler.getCurrentVehicle().getHitBox().draw(g2d);
            g2d.setColor(Color.orange);

        }else{
            super.render(g2d);
        }
        g2d.setTransform(OLDat);
       //
      //  g2d.setColor(Color.GREEN);
       // handler.getCurrentVehicle().hitBox.getVelocity().getNormalized().getMultiplied(Game.scaleToWindow(0.05)).renderFrom(handler.getCurrentVehicle().hitBox.getPosCentre(),g2d);
    //    handler.getCurrentVehicle().getHitBox().draw(g2d);
     //   g2d.setColor(Color.orange);
     //   new HitBox(handler.getCurrentVehicle().getHitBox().getContactPoint(),this.handler.getCurrentVehicle().getHitBox().getSize().getDivided(4),new Vector2D(0.5f,0.5f),false).draw(g2d);






    }

    public float getFOV() {
        return fov;
    }

    public HitBox getFinishLine() {
        return finishLine;
    }
}
