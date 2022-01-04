/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.ImageEntity;
import sfr.college.PodRacing.Handler;

import java.awt.*;
//window size
import static sfr.college.PodRacing.Game.WIN_SIZE;

//First thing that the user sees when the game is launched
//"sfr 2021" is rendered to the screen
public class IntroState extends State {
    //number of frames up to now
    private final long now;
    //controls whether text is shown to the screen or not
    private boolean showText, hideText;
    //controls the opacity / alpha value of a color
    private int alpha;
    //image of 'sfr' text
    private final ImageEntity sfrText;
    //image of '2021' text
    private final ImageEntity yearText;
    //whether this intro state should be skipped.
    private boolean skip;

    //constructor
    public IntroState(Handler handler) {
        super(handler);
        //'now' is set to the number of frames since the start of the program;
        now = handler.getTime();
        //no text should be shown yet so both booleans are set to false
        showText = false;
        hideText = false;
        //set alpha to 255 - completely opaque
        alpha = 255;
        //initialize images
        sfrText = new ImageEntity(handler, Assets.sfrtext, 0.05f, 0.5f, 0.5f);
        yearText = new ImageEntity(handler, Assets.yeartext, 0.05f, 0.5f, 0.55f);
        //start playing main game music
        Assets.main.play();
    }

    //inherited render method
    @Override
    public void render(Graphics g) {
        //draws a black background
        g.setColor(Color.black);
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
        //draw text
        sfrText.render(g);
        yearText.render(g);
        //draws a black screen over the text at a certain opacity to achieve a fade in transition
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, WIN_SIZE, WIN_SIZE);
    }

    //inherited tick method
    @Override
    public void tick() {
        if(handler.getKeyManager().nine){
            skip = true;
            forward = skip;
        }
        //start showing the text if 90 frames has passed
        if (handler.getTime() - now >= 90 && hideText == false) {
            showText = true;
        }
        /*
        if we are showing the text, alpha value is decremented by 5 every 2 frames,
        to achieve a fade in transition;
         */
        if (showText && handler.getTime() % 2 == 0 && alpha >= 5) alpha -= 5;
        //if 300 frames has passed, we need to start hiding the text
        if (handler.getTime() - now >= 300) {
            hideText = true;
            showText = false;
        }
        /*
        if we are hiding the text, alpha value is incremented by 5 every 2 frames,
        to achieve a fade out transition;
         */
        if (hideText && handler.getTime() % 2 == 0 && alpha <= 250) alpha += 5;
        //if 500 frames has passed, move on to the next game state
        if (handler.getTime() - now >= 500) forward = true;

    }

}
