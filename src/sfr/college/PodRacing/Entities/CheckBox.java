/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import java.awt.Graphics;
import java.awt.Image;
import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Handler;

/**
 *
 * @author Sami
 */
public class CheckBox extends Button{
    private boolean on;
    private int count = 0;
    public CheckBox(Handler handler, float s, float x, float y) {
        super(handler, Assets.checkboxfalse, s, x, y, false);
        on = false;
    }
    public void tick(){
        super.tick();
        if(pressed){
            count++;
            if(count==1){
            on = !on;
            }
        }else{
            count = 0;
        }
        if(on){
            setNormal(Assets.checkboxtrue[0]);
            setFlash(Assets.checkboxtrue[1]);
        }else{
            setNormal(Assets.checkboxfalse[0]);
            setFlash(Assets.checkboxfalse[1]);
        }

    }
    public void render(Graphics g){
        super.render(g);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
    
    
}
