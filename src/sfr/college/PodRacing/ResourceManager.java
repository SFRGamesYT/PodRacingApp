package sfr.college.PodRacing;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Sami
 */
public class ResourceManager {
    static ResourceManager rl = new ResourceManager();


    public static Image getImage(String fileName) {
        try {
            final BufferedImage read = ImageIO.read(Objects.requireNonNull(rl.getClass().getResource("png/" + fileName)));
            return read;
        } catch (IOException ex) {

        }
        return null;
    }

    public static Image[] getFrames(BufferedImage img, int amountOfFrames) {
        Image[] frames = new Image[amountOfFrames];
        int myWidth = img.getWidth(null) / amountOfFrames;
        for (int i = 0; i < frames.length; i++) {
            frames[i] = img.getSubimage(0 + (i * myWidth), 0, myWidth, img.getHeight(null));
        }
        return frames;
    }

    public static Font getFont(String fileName) {
        InputStream is = rl.getClass().getResourceAsStream("fonts/" + fileName);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            return null;
        }
    }

    public static String getTextFromFile(String fileName) {
        String data = "";
        InputStream in = rl.getClass().getResourceAsStream("config/" + fileName);
        try (Scanner x = new Scanner(in)) {
            while (x.hasNextLine()) {
                data += x.nextLine() + "\n";
            }
        }
        return data;
    }

}
