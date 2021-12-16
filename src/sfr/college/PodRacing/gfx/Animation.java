/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.gfx;

import java.awt.Image;

/**
 *
 * @author sr35477
 */
public class Animation {
    
	private int speed, index;
	private long lastTime, timer;
	private Image[] frames;
	
	public Animation(int speed, Image[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public Image getCurrentFrame(){
		return frames[index];
	}
}
