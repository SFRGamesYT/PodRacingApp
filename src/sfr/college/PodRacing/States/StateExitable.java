/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import java.awt.Graphics;
import sfr.college.PodRacing.Entities.Button;
import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Assets;
/**
 *
 * @author sr35477
 */
public class StateExitable extends State {
    protected Button backButton;
    int now;
    public StateExitable(Handler handler){
        super(handler);
        backButton = new Button(handler,Assets.backbutton,0.2f,0.88f,0.9f,false);
        exitable = true;
        back = false;
        forward = false;
        now = handler.getTime();

    }
    @Override
    public void render(Graphics g) {
        backButton.render(g);
    }

    @Override
    public void tick() {
        backButton.tick();
        if(backButton.getPressed()){
            if(handler.getTime()-now>=10){
                back = true;
            }
        }
        
    }
    
}
