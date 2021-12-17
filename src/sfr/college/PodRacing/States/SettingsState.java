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
import sfr.college.PodRacing.Sound;

import java.awt.*;
import java.util.prefs.BackingStoreException;

/**
 * @author SR35477
 */
public class SettingsState extends StateExitable {

    private final int settingsChoice;
    private int count = 0;
    private final TitleBackground background;
    private final CoolBlueText text;
    private final CoolBlueText musicLbl;
    private final CoolBlueText soundLbl;
    private final CoolBlueText muteMusicLbl;
    private final CoolBlueText muteSoundLbl;
    private final CoolBlueText invertLbl;
    private float musicVolume, soundVolume;
    private boolean muteMusic, muteSound, invertControls;
    private final CheckBox musicBox;
    private final CheckBox fxBox;
    private final CheckBox invertBox;
    private final UISlider soundSlider;
    private final UISlider musicSlider;
    private final Button applyButton;

    public SettingsState(Handler handler) {
        super(handler);
        background = new TitleBackground(handler, false);
        settingsChoice = -1;
        text = new CoolBlueText(handler, "SETTINGS", 0.25f, 0.1f);
        soundLbl = new CoolBlueText(handler, "Sound Effects Volume:", 0.1f, 0.25f);
        musicLbl = new CoolBlueText(handler, "Music Volume:", 0.1f, 0.4f);
        muteSoundLbl = new CoolBlueText(handler, "Mute Sound Effects:", 0.1f, 0.55f);
        muteMusicLbl = new CoolBlueText(handler, "Mute Music:", 0.1f, 0.60f);
        invertLbl = new CoolBlueText(handler, "Invert Controls:", 0.1f, 0.65f);
        text.setSize(0.1f);
        muteSoundLbl.setSize(0.03f);
        muteMusicLbl.setSize(0.03f);
        invertLbl.setSize(0.03f);
        soundVolume = Assets.settings.getFloat("soundVolume", 1.0f);
        musicVolume = Assets.settings.getFloat("musicVolume", 1.0f);
        soundSlider = new UISlider(handler, 0.3f, 0.1f, 0.3f, soundVolume);
        musicSlider = new UISlider(handler, 0.3f, 0.1f, 0.45f, musicVolume);
        applyButton = new Button(handler, Assets.applyButton, 0.2f, 0.88f, 0.77f, false);
        fxBox = new CheckBox(handler, 0.03f, 0.48f, 0.542f);
        fxBox.setOn(Assets.settings.getBoolean("muteSound", false));
        musicBox = new CheckBox(handler, 0.03f, 0.32f, 0.592f);
        musicBox.setOn(Assets.settings.getBoolean("muteMusic", false));
        invertBox = new CheckBox(handler, 0.03f, 0.4f, 0.642f);
        invertBox.setOn(Assets.settings.getBoolean("invertControls", false));
    }

    public int getSettingsChoice() {
        return settingsChoice;
    }

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

    @Override
    public void tick() {
        if (settingsChoice != -1) forward = true;
        super.tick();
        background.tick();
        background.setAlpha(180);
        soundSlider.tick();
        musicSlider.tick();
        if (back == true) System.out.println("BACK " + back);
        applyButton.tick();
        musicVolume = musicSlider.getPercentage();
        soundVolume = soundSlider.getPercentage();
        if (applyButton.getPressed()) {
            count++;
            if (count == 1) Assets.beep.play();
            for (Sound music : Assets.music) {
                music.setVolume(musicVolume);
            }
            Assets.settings.putFloat("musicVolume", musicVolume);
            for (Sound fx : Assets.fx) {
                fx.setVolume(soundVolume);
            }
            Assets.settings.putFloat("soundVolume", soundVolume);
            try {
                Assets.settings.flush();
            } catch (BackingStoreException ex) {
                ex.printStackTrace();
            }
        } else {
            count = 0;
        }
        fxBox.tick();
        musicBox.tick();
        invertBox.tick();
        Assets.settings.putBoolean("muteMusic", musicBox.isOn());
        Assets.settings.putBoolean("muteSound", fxBox.isOn());
        Assets.settings.putBoolean("invertControls", invertBox.isOn());
        try {
            Assets.settings.flush();
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
        if (musicBox.isOn()) {
            Assets.main.mute();
        } else {
            Assets.main.setVolume(musicVolume);
        }

        for (Sound fx : Assets.fx) {
            if (fxBox.isOn()) {
                fx.mute();
            } else {
                fx.setVolume(soundVolume);
            }
        }
        handler.setInvert(invertBox.isOn());
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public boolean isMuteMusic() {
        return muteMusic;
    }

    public void setMuteMusic(boolean muteMusic) {
        this.muteMusic = muteMusic;
    }

    public boolean isMuteSound() {
        return muteSound;
    }

    public void setMuteSound(boolean muteSound) {
        this.muteSound = muteSound;
    }

    public boolean isInvertControls() {
        return invertControls;
    }

    public void setInvertControls(boolean invertControls) {
        this.invertControls = invertControls;
    }


}
