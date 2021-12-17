/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.util;

import java.awt.*;

/**
 * @author sr35477
 */
public class MathUtils {
    public static final double PI = Math.PI;
    public static final double PI_HALF = PI / 2;
    public static final double TWO_PI = 2 * PI;

    public static float Approach(float goal, float current, float delta) {
        float difference = goal - current;

        if (difference > 0) return current + (difference / delta);
        return goal;
    }


    public static double approachLinear(double goal, double current, double delta) {
        double difference = goal - current;
        if (difference > delta) {
            return current + delta;
        }
        if (difference < -delta) {
            return current - delta;
        }
        return goal;
    }

    public static float parseFloat(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    public static boolean parseBoolean(String text) {
        return Boolean.parseBoolean(text);

    }

    public static float sqrt(float x) {
        return (float) Math.sqrt(x);
    }

    public static boolean pointVsRect(Point p, Rectangle rect) {
        return (p.x >= rect.x && p.y >= rect.y && p.x < rect.x + rect.width && p.y < rect.y + rect.height);
    }

    public boolean rectVsRect(Rectangle r1, Rectangle r2) {
        return (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y);
    }

}
