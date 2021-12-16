/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;
import java.awt.Graphics;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.Assets;

/**
 *
 * @author SFRGa
 */
public class PressKeyPrompt extends AnimImageEntity {
    public PressKeyPrompt(Handler handler,float s, float x, float y) {
        super(handler,new Animation(500,Assets.pressKey),s,x,y);
    }
    @Override
    public void render(Graphics g){
        super.render(g);
    }    
    
    @Override
    public void tick(){
        super.tick();
    }
}
