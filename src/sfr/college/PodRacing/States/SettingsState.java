/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Entities.*;
import sfr.college.PodRacing.Handler;

import java.awt.*;
import java.util.prefs.BackingStoreException;

//Objective 6
//where the user can tweak and toggle different values and aspects of the game
//every state that extends StateExitable will already have a back button to go to the previous state
public class SettingsState extends StateExitable {

    //counts how many frames have passed since the apply button has been pressed
    private int count = 0;
    //background object containing an image of the racetrack
    private final TitleBackground background;
    //stores the custom game text for each label in the settings
    private final GameText text;
    private final GameText musicLbl;
    private final GameText soundLbl;
    private final GameText muteMusicLbl;
    private final GameText muteSoundLbl;
    private final GameText invertLbl;
    //stores the new volume of the music/sounds
    private float musicVolume, soundVolume;
    //check boxes to control different booleans
    private final CheckBox musicBox;
    private final CheckBox fxBox;
    private final CheckBox invertBox;
    //sliders to control different float values
    private final UISlider soundSlider;
    private final UISlider musicSlider;
    //apply button: applies user changes, reloads assets
    private final Button applyButton;

    //constructor
    public SettingsState(Handler handler) {
        super(handler);
        background = new TitleBackground(handler, false);

        text = new GameText(handler, "SETTINGS", 0.25f, 0.1f);
        soundLbl = new GameText(handler, "Sound Effects Volume:", 0.1f, 0.25f);
        musicLbl = new GameText(handler, "Music Volume:", 0.1f, 0.4f);
        muteSoundLbl = new GameText(handler, "Mute Sound Effects:", 0.1f, 0.55f);
        muteMusicLbl = new GameText(handler, "Mute Music:", 0.1f, 0.60f);
        invertLbl = new GameText(handler, "Invert Controls:", 0.1f, 0.65f);
        text.setSize(0.1f);
        muteSoundLbl.setSize(0.03f);
        muteMusicLbl.setSize(0.03f);
        invertLbl.setSize(0.03f);
        soundVolume = Assets.settings.getFloat("soundVolume", 1.0f);
        musicVolume = Assets.settings.getFloat("musicVolume", 1.0f);
        soundSlider = new UISlider(handler, 0.3f, 0.1f, 0.3f, soundVolume);
        musicSlider = new UISlider(handler, 0.3f, 0.1f, 0.45f, musicVolume);
        applyButton = new Button(handler, Assets.applyButton, 0.2f, 0.85f, 0.75f, false);
        fxBox = new CheckBox(handler, 0.03f, 0.48f, 0.542f);
        fxBox.setOn(Assets.settings.getBoolean("muteSound", false));
        musicBox = new CheckBox(handler, 0.03f, 0.32f, 0.592f);
        musicBox.setOn(Assets.settings.getBoolean("muteMusic", false));
        invertBox = new CheckBox(handler, 0.03f, 0.4f, 0.642f);
        invertBox.setOn(Assets.settings.getBoolean("invertControls", false));
    }


    //inherited render method
    @Override
    public void render(Graphics g) {
        background.render(g);
        super.render(g);

        text.render(g);
        musicLbl.render(g);
        musicSlider.render(g);
        soundLbl.render(g);
        soundSlider.render(g);
        muteSoundLbl.render(g);
        muteMusicLbl.render(g);
        applyButton.render(g);
        invertLbl.render(g);
        fxBox.render(g);
        musicBox.render(g);
        invertBox.render(g);
    }

    //inherited tick method
    @Override
    public void tick() {
        super.tick();
        background.tick();
        //set background to be slightly darker so text is more visible
        background.setAlpha(180);
        //update sliders, buttons and check boxes - 6.1
        soundSlider.tick();
        musicSlider.tick();
        applyButton.tick();
        fxBox.tick();
        musicBox.tick();
        invertBox.tick();
        //if the apply button is pressed - 6.2
        if (applyButton.getPressed()) {
            //count how many frames has passed since apply button is pressed
            count++;
            //only on the first frame
            if (count == 1) {
                //play beep noise
                Assets.beep.play();
                //set new volumes relative to the sliders position - 6.3
                musicVolume = musicSlider.getPercentage();
                soundVolume = soundSlider.getPercentage();
                //store new values in users ROM so saved changed are not lost - 6.4
                Assets.settings.putFloat("musicVolume", musicVolume);
                Assets.settings.putFloat("soundVolume", soundVolume);
                Assets.settings.putBoolean("muteMusic", musicBox.isOn());
                Assets.settings.putBoolean("muteSound", fxBox.isOn());
                Assets.settings.putBoolean("invertControls", invertBox.isOn());
                try {
                    Assets.settings.flush();
                } catch (BackingStoreException ex) {
                    ex.printStackTrace();
                }
                //reload game assets with new settings
                Assets.init(handler);
            }
        } else {
            //if apply button is not pressed, reset count variable to 0
            count = 0;
        }
    }

}
