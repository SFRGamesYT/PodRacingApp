package sfr.college.PodRacing;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Sami
 */
public class Assets {
    //static instances for each asset used
    public static Preferences settings;
    public static Image titleBg, titleText1, icon,
            choosevehicletext, sfrtext, yeartext, gogtitle, minimap, bigrock,scores;
    public static Image[] pressKey, pod0, pod1, pod2, playbutton,
            quitbutton, settingsbutton, backbutton, pod1button,
            pod0button, pod2button, pauseButton, applyButton, bg, pod0left, pod0right,
            pod1left, pod1right, pod2left, pod2right, pod3, pod3button, pod3left, pod3right,
            checkboxfalse, checkboxtrue;
    public static Sound main, beep, beep1, beep2, anakinnoise, sebulbanoise, gasganonoise, bruh, engine1;
    public static ArrayList<Sound> music, fx;
    public static Font pixel;

    public Assets() {
    }

    public static void init(Handler h) {
        //specifing each file 
        settings = Preferences.userRoot();
        float soundVolume, musicVolume;
        boolean muteMusic, muteSound, invertControls;
        soundVolume = settings.getFloat("soundVolume", 1.0f);
        musicVolume = settings.getFloat("musicVolume", 1.0f);
        muteMusic = settings.getBoolean("muteMusic", false);
        muteSound = settings.getBoolean("muteSound", false);
        invertControls = settings.getBoolean("invertControls", false);
        h.setInvert(invertControls);
        main = new Sound("focus.wav", h);
        main.setVolume(musicVolume);
        if (muteMusic) main.mute();
        beep = new Sound("select.wav", h);
        beep1 = new Sound("beep.wav", h);
        beep2 = new Sound("beep2.wav", h);
        anakinnoise = new Sound("anakinnoise.wav", h);
        sebulbanoise = new Sound("sebulbanoise.wav", h);
        gasganonoise = new Sound("gasganonoise.wav", h);
        bruh = new Sound("bruh.wav", h);
        engine1 = new Sound("engine1.wav", h);
        music = new ArrayList<>();
        fx = new ArrayList<>();
        music.add(main);
        fx.add(beep);
        fx.add(beep1);
        fx.add(beep2);
        fx.add(anakinnoise);
        fx.add(sebulbanoise);
        fx.add(gasganonoise);
        fx.add(engine1);
        for (Sound fx : fx) {
            fx.setVolume(soundVolume);
            if (muteSound) fx.mute();
        }

        titleBg = ResourceManager.getImage("pseudoracetrack.png");
        titleText1 = ResourceManager.getImage("podracingtext.png");
        icon = ResourceManager.getImage("icon.png");
        choosevehicletext = ResourceManager.getImage("choosevehicle.png");
        sfrtext = ResourceManager.getImage("sfr.png");
        yeartext = ResourceManager.getImage("2021.png");
        gogtitle = ResourceManager.getImage("gogtitle.png");
        minimap = ResourceManager.getImage("minimap.png");
        bigrock = ResourceManager.getImage("rock.png");
        scores = ResourceManager.getImage("scores.png");


        pressKey = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("presskeyanim.png"), 2);
        pod0 = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod0.png"), 4);
        pod1 = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod1.png"), 4);
        pod2 = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod2.png"), 4);
        pod3 = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod3.png"), 4);
        playbutton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("playbutton.png"), 2);
        quitbutton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("quitbutton.png"), 2);
        settingsbutton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("settingsbutton.png"), 2);
        backbutton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("backbutton.png"), 2);
        pod1button = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod1button.png"), 2);
        pod0button = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod0button.png"), 2);
        pod2button = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod2button.png"), 2);
        pod3button = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod3button.png"), 2);
        pauseButton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pause.png"), 2);
        applyButton = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("applybutton.png"), 2);
        bg = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("racetrackraw.png"), 2);
        pod0left = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod0left.png"), 4);
        pod0right = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod0right.png"), 4);
        pod1left = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod1left.png"), 4);
        pod1right = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod1right.png"), 4);
        pod2left = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod2left.png"), 4);
        pod2right = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod2right.png"), 4);
        pod3left = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod3left.png"), 4);
        pod3right = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("pod3right.png"), 4);
        checkboxfalse = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("checkboxfalse.png"), 2);
        checkboxtrue = ResourceManager.getFrames((BufferedImage) ResourceManager.getImage("checkboxtrue.png"), 2);


        pixel = ResourceManager.getFont("pixel.ttf").deriveFont((float) Game.scaleToWindow(0.05f));
    }

}
