/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;

/**
 *
 * @author Sami
 */
public class UISlider extends Entity{
    private float percentage;
    private int min,max,range,x,y,size;
    private int min1,max1,range1;
    private Handler handler;
    private int now;
    private Rectangle bounds;

    public UISlider(Handler handler,float size, float x, float y,float per) {
        this.handler = handler;
        now = handler.getTime();
        this.size = Game.scaleToWindow(size);
        this.x = Game.scaleToWindow(x);
        this.y = Game.scaleToWindow(y);
        min = this.x;
        max = min+this.size;
        range = max-min;
        percentage = per;
        min1 = min;
        range1 = (int)(range*percentage);
        max1 = min+range1;
        bounds = new Rectangle(this.x,this.y,range,(int)(range/8));
    }
    public void tick(){
        if(handler.getMouseManager().leftPressed&&(handler.getTime()-now>=10)){
            if(bounds.contains(handler.getMouseManager().mX, handler.getMouseManager().mY)){
                percentage = ((float)(handler.getMouseManager().mX-min)/(float)(range));
                System.out.println((float)(handler.getMouseManager().mX-min));
                System.out.println(percentage);
            }
        }
        range1 = (int)(range*percentage);
        max1 = min+range1;
        
    }
    public void render(Graphics g){
        g.setColor(Game.BLUE2);
        g.fillRect(x,y,range, range/8);
        g.setColor(Game.BLUE1);
        g.fillRect(x,y,range1,range/8);
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
    
    
}
