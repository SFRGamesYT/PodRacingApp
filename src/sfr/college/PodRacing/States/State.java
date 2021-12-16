/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import java.awt.Color;
import java.awt.Graphics;
import sfr.college.PodRacing.Game;
import static sfr.college.PodRacing.Game.WIN_SIZE;
import sfr.college.PodRacing.Handler;
;

/**
 *
 * @author SR35477
 */
public class State {
    protected Handler handler;
    public boolean exitable = false;
    public boolean forward = false;
    public boolean back = false;
    protected long first;

    public void render(Graphics g){
        
    }
    
    public void tick(){
        
    }
    public State(Handler handler){
        this.handler = handler;
    }
    protected static void fillScreenColor(Graphics g,Color color){
        g.setColor(color);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
    }
}
