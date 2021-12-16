
package sfr.college.PodRacing.States;


import java.awt.Graphics;
import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.PressKeyPrompt;
import sfr.college.PodRacing.Entities.Title;
import sfr.college.PodRacing.Entities.TitleBackground;

import sfr.college.PodRacing.Handler;


/**
 *
 * @author Sami
 */
public class TitleState extends State{
    private final TitleBackground bg;
    private Title title;
    private PressKeyPrompt pressKey;
    int count = 0;



    public TitleState(Handler handler){
        super(handler);
        this.bg = new TitleBackground(handler,true);
        this.title = new Title(handler,0.8f,0.5f,0f,true);
        this.pressKey = new PressKeyPrompt(handler,0.25f,0.5f,0.8f);
        
    }
    @Override
    public void render(Graphics g) {
        bg.render(g);
        if(bg.isDone())title.render(g);
        if(title.isdone()){
            pressKey.render(g);
            if(handler.getKeyManager().keyPressed){
                forward = true;
            }
        }
        
        
        
    }

    @Override
    public void tick() {
        bg.tick();
        if(bg.isDone()){
            title.tick();
            count++;
            if(count==1)Assets.main.skip(23700);

        }
        if(title.isdone()){
            bg.setAlpha(50);
            pressKey.tick();
        }
        
        
        
    }   
    
}
