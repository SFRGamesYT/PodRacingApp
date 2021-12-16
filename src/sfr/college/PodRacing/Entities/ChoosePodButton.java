/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import java.awt.Graphics;
import sfr.college.PodRacing.Handler;
import java.awt.Image;
import sfr.college.PodRacing.Assets;

/**
 *
 * @author Sami
 */
public class ChoosePodButton extends Button{
    private String selected;
    private int count = 0;
    
    public ChoosePodButton(Handler handler, Image[] img, float s, float x, float y, String lbl) {
        super(handler, img, s, x, y,false, lbl);
        selected = "";
        

    }
    public void tick(){
        super.tick();
        
        if(pressed){
            count++;
            if(count==1){
               
                if(label.equals(PodAnakin.label)){
                    Assets.anakinnoise.play();
                }else if(label.equals(PodSebulba.label)){
                    Assets.sebulbanoise.play();
                }else if(label.equals(PodGasgano.label)){
                    Assets.gasganonoise.play();
                }
            }
        
            selected = label;
        }else{
            selected = "";
            count = 0;
        }
        if(!isMouseOnButton()){
            selected = "";
        }
        
    }
    public void render(Graphics g){
        super.render(g);
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
   
    
}
