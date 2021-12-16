package sfr.college.PodRacing;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sami
 */
public class ResourceManager {
    static ResourceManager rl = new ResourceManager();

    
    public static Image getImage(String fileName){
        try {
            return ImageIO.read(rl.getClass().getResource("png/"+fileName));
        } catch (IOException ex) {

        }
        return null;
    }
     public static Image[] getFrames(BufferedImage img,int amountOfFrames){
            Image[] frames = new Image[amountOfFrames];
            int myWidth = (int)(img.getWidth(null)/amountOfFrames);
            for(int i = 0;i < frames.length;i++){
                frames[i] = img.getSubimage(0+(i*myWidth), 0, myWidth, img.getHeight(null));
            }
            return frames;
    }
     public static Font getFont(String fileName){
        InputStream is = rl.getClass().getResourceAsStream("fonts/"+fileName);
        try {
            return Font.createFont(Font.TRUETYPE_FONT,is);
        } catch (FontFormatException | IOException ex) {
            return null;
        }
     }
     public static String getTextFromFile(String fileName){
         String data = "";
         InputStream in = rl.getClass().getResourceAsStream("config/"+fileName);
        try (Scanner x = new Scanner(in)) {
            while(x.hasNextLine()){
                data+=x.nextLine()+"\n";
            }
        }
        return data;
     }
        
}
