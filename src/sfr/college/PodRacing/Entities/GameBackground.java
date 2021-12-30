/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static sfr.college.PodRacing.Game.*;


/**
 * @author Sami
 */
public class GameBackground extends AnimImageEntity {

    protected float fov;
    protected MapCollisionHulls mch;


    public GameBackground(Handler handler, float fov) {
        super(handler, new Animation(300, Assets.bg), 1, 0.5f, 0.5f);
        this.fov = fov;
        mch = new MapCollisionHulls();
    }

    public void tick() {
        super.tick();


    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Vehicle v = handler.getCurrentVehicle();
        AffineTransform OLDat = g2d.getTransform();

        double width = WIN_SIZE;
        double height =WIN_SIZE;

        double zoomWidth = width * fov;
        double zoomHeight = height * fov;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;

        AffineTransform at = g2d.getTransform();
        at.rotate(Math.PI*2-v.getAngle(),v.getBoundsOnScreen().getPosCentre().x,v.getBoundsOnScreen().getPosCentre().y);
        at.translate(anchorx, anchory);
        at.scale(fov,fov);
        at.translate(-v.getHitBox().getPosCentre().x+Game.scaleToWindow(0.5), -v.getHitBox().getPosCentre().y+Game.scaleToWindow(0.535));

        g2d.setTransform(at);

        super.render(g2d);
        if(handler.devMode){
            g2d.setColor(Color.magenta);
            mch.render(g2d);
            if(v.isColliding())g2d.setColor(Color.red);
            handler.getCurrentVehicle().getHitBox().draw(g2d);
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
}
