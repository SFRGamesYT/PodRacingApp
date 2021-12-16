/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;


/**
 *
 * @author Sami
 */
public class CoolBlueText extends Entity{
    private Font font;
    private final Color blue0;
    private final Color blue1;
    private final Color blue2;
    private String text;
    public CoolBlueText(Handler handler,String text,float x,float y){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.text = text;
        font = Assets.pixel;
        //setSize();
        blue0 = Game.BLUE0;
        blue1 = Game.BLUE1;
        blue2 = Game.BLUE2;
    }
    @Override
    public void tick(){
        
    }
    @Override
    public void render(Graphics g){
       g.setColor(blue0);
       g.setFont(font);
       g.drawString(text, Game.scaleToWindow(x), Game.scaleToWindow(y));
       g.setColor(blue2);
       g.drawString(text, Game.scaleToWindow(x), Game.scaleToWindow(y+0.006f));
       g.setColor(blue1);
       g.drawString(text, Game.scaleToWindow(x), Game.scaleToWindow(y+0.003f));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setSize(){
        if(!"".equals(this.text))this.font = font.deriveFont((float)Game.scaleToWindow(0.06f)*(18f/(float)text.length()));//*((float)text.length()/18f));
    }
    public void setSize(float size){
        this.font = font.deriveFont((float)Game.scaleToWindow(size));
    }
    
}
