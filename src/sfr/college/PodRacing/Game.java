/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.image.BufferStrategy;
import static java.lang.Math.signum;
import sfr.college.PodRacing.Display.Screen;
import sfr.college.PodRacing.States.*;


/**
 * 
 * @author Sami
 */
//main class to handle game
public class Game implements Runnable{
    
    
    
    private final Screen screen;
    private Thread thread;
    private String title;
    
    private Handler handler;
    private final KeyManager km;
    private final MouseManager mm;

    private int time; //starts at 0, increments each frame
    
    private static final Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIN_SIZE = getAppropriateDimensions(window);
    public static final int WIN_SIZE_HALF = (int)WIN_SIZE/2;
    public static final int WIN_SIZE_QUARTER = (int)WIN_SIZE_HALF/2;
    public static final int WIN_SIZE_8TH = (int)WIN_SIZE_QUARTER/2;
    public static final Color SAND = new Color(255,178,127);
    public static final Color BLUE0 = new Color(124,211,255);
    public static final Color BLUE1 = new Color(0,169,255);
    public static final Color BLUE2 = new Color(0,96,145);
    boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    public StateManager sm;
    private boolean hasSound;

    /*
    public static void wait(int ms){
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    */
    public static int getAppropriateDimensions(Dimension dimension){
		int x = 0;
		int alpha;
		int width = (int)dimension.getWidth();
		int height =(int)dimension.getHeight();
		alpha = width-height;
		if(signum(alpha)==1){
			x = height;
		}else{
			x = width;
		}


		return (int) (0.87f*x);

    }
    
    public static int scaleToWindow(float percent){
        return (int) (percent * WIN_SIZE);
    }
    
    
    public Game(String title,boolean hasSound){
        time = 0;
        this.screen = new Screen(WIN_SIZE,title);
        km = new KeyManager();
        mm = new MouseManager();
        this.hasSound = hasSound;
    }
    
    private void init(){
        handler = new Handler(this);
        handler.hasSound = hasSound;
        Assets.init(handler);
        screen.getFrame().setIconImage(Assets.icon);
        screen.getFrame().getContentPane().setBackground(SAND);
        screen.getFrame().addKeyListener(km);
        screen.getCanvas().addKeyListener(km);
        screen.getFrame().addMouseListener(mm);
        screen.getCanvas().addMouseListener(mm);
        screen.getFrame().addMouseMotionListener(mm);
        screen.getCanvas().addMouseMotionListener(mm);

        sm = new StateManager(handler);

       
    }

    public KeyManager getKeyManager() {
        return km;
    }
    public MouseManager getMouseManager(){
        return mm;
    }
    
    private void tick(){
        time++;
        
        sm.tick();
        sm.getState().tick();
        km.tick();
        handler.devMode = km.f;

    }

    public int getTime() {
        return time;
    }
    
    
    
    private void render(){
        bs = screen.getCanvas().getBufferStrategy();
	if(bs == null){
            screen.getCanvas().createBufferStrategy(3);
            bs = screen.getCanvas().getBufferStrategy();
            return;
	}
	g = bs.getDrawGraphics();
	//Clear Screen
	g.clearRect(0, 0, WIN_SIZE,WIN_SIZE);
	//Draw Here!
        sm.getState().render(g);

        bs.show();
        g.dispose();
    }
    
    public void run() {
        init();
        final int FPS = 60;
        final double timePerFrame = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running){
            //calculate delta time
            now = System.nanoTime();
            delta += (now - lastTime) / timePerFrame;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
		tick();
		render();
		ticks++;
		delta--;
            }
            if(timer>=1000000000){
                if(handler.getKeyManager().f)System.out.println("FPS: "+ticks);
                ticks = 0;
                timer = 0;
            }
                    
        }
        stop();
        
        
        
    }
    
    public synchronized void stop(){
        if(!running){
            return;
        }
        running=false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }


}
