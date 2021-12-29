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
        super.render(g2d);
        g2d.setColor(Color.magenta);
        mch.render(g2d);
        g2d.setColor(Color.GREEN);
        handler.getCurrentVehicle().hitBox.getVelocity().getNormalized().getMultiplied(Game.scaleToWindow(0.05)).renderFrom(handler.getCurrentVehicle().hitBox.getPosCentre(),g2d);
        handler.getCurrentVehicle().getHitBox().draw(g2d);
        g2d.setColor(Color.orange);
        new HitBox(handler.getCurrentVehicle().getHitBox().getContactPoint(),this.handler.getCurrentVehicle().getHitBox().getSize().getDivided(4),new Vector2D(0.5f,0.5f),false).draw(g2d);






    }

    public float getFOV() {
        return fov;
    }
}
