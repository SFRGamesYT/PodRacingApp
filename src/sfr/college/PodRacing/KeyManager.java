/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author sr35477
 */
public class KeyManager implements KeyListener {
    public boolean keyPressed, q, left, right, up, down, back, esc, enter, zero, one, two, three, four, five, six, nine, f, shift, space;
    private final boolean[] keys;

    public KeyManager() {
        this.keys = new boolean[1024];
        keyPressed = false;
    }

    public void tick() {
        q = keys[KeyEvent.VK_Q];
        f = keys[KeyEvent.VK_F];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        back = keys[KeyEvent.VK_BACK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];
        zero = keys[KeyEvent.VK_0];
        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        three = keys[KeyEvent.VK_3];
        four = keys[KeyEvent.VK_4];
        five = keys[KeyEvent.VK_5];
        six = keys[KeyEvent.VK_6];
        nine = keys[KeyEvent.VK_9];
        shift = keys[KeyEvent.VK_SHIFT];
        space = keys[KeyEvent.VK_SPACE];


    }


    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        keyPressed = true;
        System.out.println("Key Has Been Pressed");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        keyPressed = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

}
