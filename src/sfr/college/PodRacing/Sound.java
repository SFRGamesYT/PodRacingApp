/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Sami
 */
public class Sound {
/**
 * Static file paths for each sound.
 */


/**
 * Audio input stream for this sound.
 */
private AudioInputStream audioInputStream;

/**
 * Audio clip for this sound.
 */
private Clip clip;
private Handler handler;
/* -- Constructor -- */

/**
 * Creates a new sound at the specified file path.
 * 
 * @param fileName File path to sound file
 */
public Sound(String fileName,Handler handler) {
    // Get the audio from the file
    this.handler = handler;
    if(this.handler.hasSound){
        try {
            // Convert the file path string to a URL
            URL sound = getClass().getResource("wav/"+fileName);
            System.out.println(sound);

            // Get audio input stream from the file
            audioInputStream = AudioSystem.getAudioInputStream(sound);

            // Get clip resource
            clip = AudioSystem.getClip();

            // Open clip from audio input stream
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}

/* -- Method -- */

/**
 * Play the sound.
 */
public void play() {
    if(this.handler.hasSound){
        // Stop clip if it's already running
        if (clip.isRunning()){
            stop();
        }

        // Rewind clip to beginning
        clip.setFramePosition(0);

        // Play clip
        clip.start();
    }
}

public void skip(int ms){
    if(this.handler.hasSound){
        if(clip.isRunning()){
            clip.setMicrosecondPosition(ms*1000);
        }
    }
}
/**
 * Stop the sound.
 */
public void stop() {
    if(this.handler.hasSound){
        clip.stop();
        clip.close();
    }
}

public void setVolume(float amount){
    if(this.handler.hasSound){
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = volume.getMaximum() +80f;
        volume.setValue((amount*range)-80f);
    }
}

public void mute(){
    if(this.handler.hasSound){
        setVolume(-80f);
    }
}

}