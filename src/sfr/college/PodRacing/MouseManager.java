/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author sr35477
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    public boolean leftPressed,rightPressed;
    public int mX,mY; // location of mouse

    public MouseManager() {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
            mX = e.getX();
            mY = e.getY();
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
            mX = e.getX();
            mY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
             mX = e.getX();
             mY = e.getY();
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
             mX = e.getX();
             mY = e.getY();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    
}
