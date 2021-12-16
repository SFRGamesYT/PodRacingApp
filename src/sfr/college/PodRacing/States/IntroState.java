/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sfr.college.PodRacing.Entities.ImageEntity;
import sfr.college.PodRacing.Game;
import static sfr.college.PodRacing.Game.WIN_SIZE;
import static sfr.college.PodRacing.Game.scaleToWindow;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Assets;

/**
 *
 * @author Sami
 */
public class IntroState extends State{
    private long now;
    private boolean showText,hideText;
    private int alpha;
    private ImageEntity sfrtext,yeartext;
    public IntroState(Handler handler){
        super(handler);
        now = handler.getTime();
        showText = false;
        hideText = false;
        alpha = 255;
        sfrtext = new ImageEntity(handler,Assets.sfrtext,0.05f,0.5f,0.5f);
        yeartext = new ImageEntity(handler,Assets.yeartext,0.05f,0.5f,0.55f);
        Assets.main.play();

    }
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        sfrtext.render(g);
        yeartext.render(g);
        g.setColor(new Color(0,0,0,alpha));
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);

        
    }

    @Override
    public void tick() {
        if(handler.getTime()-now>=90&&hideText==false){
            showText = true;
        }
        if(showText&&handler.getTime()%2==0&&alpha>=5) alpha-=5;
        if(handler.getTime()-now>=300) hideText = true; showText = false;
        if(hideText&&handler.getTime()%2==0&&alpha<=250) alpha+=5;
        if(handler.getTime()-now>=500) forward = true;
        
    }
    
}
