/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.scaleToWindow;

/**
 * @author SFRGa
 */
public class ImageEntity extends Entity {

    protected Image img;
    protected int w1, h1, w2, h2;//image dimensions and dimensions relative to window
    protected int x1, x2, y1, y2;
    protected Boolean wide, square;
    protected Rectangle boundsOnScreen;
    protected float centreX, centreY;
    protected float size, ar;//aspect ratio

    public ImageEntity(Handler handler, Image img, float s, float x, float y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.img = img;
        this.size = s;
        this.w1 = img.getWidth(null);
        this.h1 = img.getHeight(null);
        wide = w1 > h1;
        square = w1 == h1;
        if (wide) {
            ar = (float) h1 / w1;
            w2 = scaleToWindow(size);
            h2 = (int) (w2 * ar);
        } else if (square) {
            ar = 1;
            w2 = scaleToWindow(size);
            h2 = (int) (w2 * ar);
        } else {
            ar = (float) w1 / h1;
            h2 = scaleToWindow(size);
            w2 = (int) (h2 * ar);
        }

        x1 = scaleToWindow(this.x);
        y1 = scaleToWindow(this.y);
        x2 = x1 - (w2 / 2);
        y2 = y1 - (h2 / 2);
        boundsOnScreen = new Rectangle(x2, y2, w2, h2);

    }

    public ImageEntity(Handler handler, Image img, float s, float x, float y, float cx, float cy) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.img = img;
        this.size = s;
        this.w1 = img.getWidth(null);
        this.h1 = img.getHeight(null);
        wide = w1 > h1;
        square = w1 == h1;
        if (wide) {
            ar = (float) h1 / w1;
            w2 = scaleToWindow(size);
            h2 = (int) (w2 * ar);
        } else if (square) {
            ar = 1;
            w2 = scaleToWindow(size);
            h2 = (int) (w2 * ar);
        } else {
            ar = (float) w1 / h1;
            h2 = scaleToWindow(size);
            w2 = (int) (h2 * ar);
        }

        x1 = scaleToWindow(this.x);
        y1 = scaleToWindow(this.y);
        x2 = x1 - (int) (w2 * cx);
        y2 = y1 - (int) (h2 * cy);
        boundsOnScreen = new Rectangle(x2, y2, w2, h2);

    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x2, y2, w2, h2, null);
    }

    @Override
    public void tick() {
    }


}
