/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

/**
 * @author SFRGa
 */
public class ImageEntity extends Entity{

    protected Image img;
    protected Vector2D imgSize;
    protected HitBox boundsOnScreen;



    public ImageEntity(Handler handler, Image img, double s, double x, double y) {
        this.handler = handler;
        this.img=img;
        imgSize = new Vector2D();
        imgSize.set(img.getWidth(null),img.getHeight(null));
        boundsOnScreen = new HitBox(new Vector2D(Game.scaleToWindow(x),Game.scaleToWindow(y)),imgSize.getNormalized().getMultiplied(Math.sqrt(2*Game.scaleToWindow(s)*Game.scaleToWindow(s))),new Vector2D(0.5d,0.5d),false);
    }
    public ImageEntity(Handler handler, Image img, double s, double x, double y,double ox, double oy) {
        this.handler = handler;
        this.img=img;
        imgSize = new Vector2D();
        imgSize.set(img.getWidth(null),img.getHeight(null));
        boundsOnScreen = new HitBox(new Vector2D(Game.scaleToWindow(x),Game.scaleToWindow(y)),imgSize.getNormalized().getMultiplied(Math.sqrt(2*Game.scaleToWindow(s)*Game.scaleToWindow(s))),new Vector2D(ox,oy),false);
    }


    public Vector2D getImgSize() {
        return imgSize;
    }

    public void setImgSize(Vector2D imgSize) {
        this.imgSize = imgSize;
    }

    public HitBox getBoundsOnScreen() {
        return boundsOnScreen;
    }

    public void setBoundsOnScreen(HitBox boundsOnScreen) {
        this.boundsOnScreen = boundsOnScreen;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)boundsOnScreen.getPos().x, (int)boundsOnScreen.getPos().y, (int)boundsOnScreen.getSize().x, (int)boundsOnScreen.getSize().y, null);
    }

    @Override
    public void tick() {
        boundsOnScreen.tick();
    }


}


