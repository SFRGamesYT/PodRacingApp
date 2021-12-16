/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import static sfr.college.PodRacing.Game.WIN_SIZE;
import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;
import static sfr.college.PodRacing.Game.scaleToWindow;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.util.MathUtils;
import sfr.college.PodRacing.util.RenderUtil;


/**
 *
 * @author Sami
 */
public class GameBackground extends AnimImageEntity{
    protected float fov;
    protected final int now = handler.getTime();
    private int t;
    protected MapCollisionHulls hulls;
    public GameBackground(Handler handler, float s, float x, float y,float fov) {
        super(handler, new Animation(300,Assets.bg), s, x, y);
        this.fov = fov;
        hulls = new MapCollisionHulls();
        t = 0;
    }
    public void tick(){

        super.tick();
        if (fov<=10)t = 0+handler.getTime()-now;
        fov = (float) Math.pow(1.01f,2*t);
    }
    public void render(Graphics g,Vehicle v){

        Graphics2D g2d = (Graphics2D)g;
        double width = WIN_SIZE;
        double height =WIN_SIZE;

        double zoomWidth = width * fov;
        double zoomHeight = height * fov;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;
        AffineTransform OLDat = new AffineTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(v.getDirection(),WIN_SIZE_HALF,scaleToWindow(0.8f));
        at.translate(anchorx, anchory);
        at.scale(fov,fov);
        at.translate(v.getCamX(), v.getCamY());

        g2d.setTransform(at);





        super.render(g2d);
        if(handler.devMode){
            g.setColor(Color.MAGENTA);
            int count = 0;
            while (true) {
                try {
                    g.setColor(Color.MAGENTA);
                    RenderUtil.fillRect(g, hulls.hitBoxes[count]);
    
                    if(MapCollisionHulls.isColliidingWith(handler.getCollisionHull())){
                        g.setColor(Color.red);
                    }else{
                        g.setColor(Color.yellow);
                    }
                        
                    RenderUtil.fillRect(g, handler.getCollisionHull());
                }catch(java.lang.ArrayIndexOutOfBoundsException e){
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
