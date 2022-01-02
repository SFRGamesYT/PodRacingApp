/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;
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
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static int floor(double x){
        return (int)Math.floor(x);
    }


    public static boolean parseBoolean(String text) {
        return Boolean.parseBoolean(text);

    }
    public static String convertMillis(long milliseconds){
        long minutes
                = TimeUnit.MILLISECONDS.toMinutes(milliseconds);

        // This method uses this formula seconds =
        // (milliseconds / 1000);
        long seconds
                = (TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                % 60);
        long ms = milliseconds - TimeUnit.SECONDS.toMillis(seconds) - TimeUnit.MINUTES.toMillis(minutes);
        ms/=10;

        // Print the answer
        return (minutes + ":"+ seconds + ":"+ms);
    }

    public static float sqrt(float x) {
        return (float) Math.sqrt(x);
    }




}
