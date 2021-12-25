package sfr.college.PodRacing;


import sfr.college.PodRacing.Display.Screen;
import sfr.college.PodRacing.States.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static java.lang.Math.signum;


/**
 * @author Sami
 */
//main class to handle game
public class Game implements Runnable {


    public static final Color SAND = new Color(255, 178, 127);
    public static final Color BLUE0 = new Color(124, 211, 255);
    public static final Color BLUE1 = new Color(0, 169, 255);
    public static final Color BLUE2 = new Color(0, 96, 145);
    private static final Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIN_SIZE = getAppropriateDimensions(window);
    public static final double mapScale = WIN_SIZE/256d;
    public static final int WIN_SIZE_HALF = WIN_SIZE / 2;
    public static final int WIN_SIZE_QUARTER = WIN_SIZE_HALF / 2;
    public static final int WIN_SIZE_8TH = WIN_SIZE_QUARTER / 2;
    private final Screen screen;
    private final KeyManager km;
    private final MouseManager mm;
    private final boolean hasSound;
    public StateManager sm;
    boolean running = false;
    private Thread thread;
    private Handler handler;
    private int time; //starts at 0, increments each frame

	/**Game
	 * Class to handler main game loop
	 * 
	 * @param title title for game window
	 * @param hasSound use sound assets or not
	 */
    public Game(String title, boolean hasSound) {
        time = 0;
        this.screen = new Screen(WIN_SIZE, title);
        km = new KeyManager();
        mm = new MouseManager();
        this.hasSound = hasSound;
    }

	/**getAppropriateDimensions
	 * Returns the private variable appropriateDimensions value
	 * @return the value of the private variable appropriateDimensions
	 */
    public static int getAppropriateDimensions(Dimension dimension) {
        int alpha;
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        alpha = width - height;
        if (signum(alpha) == 1) {
            return (int) (height * 0.9f);
        } else {
            return (int) (width * 0.8f);
        }
    }

	/**scaleToWindow
	 * takes a float value between 0.0-1.0 and converts it to an integer value relating to the percentage on the players screen
	 *
     * @param percent percentage relative to screen
     */
    public static int scaleToWindow(double percent) {
        return (int) (percent * WIN_SIZE);
    }

	/**init
	 * initializes any other variables
	 * 
	 */
    private void init() {
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

	/**getKeyManager
	 * Returns the private variable keyManager's value
	 * @return the value of the private variable keyManager
	 */
    public KeyManager getKeyManager() {
        return km;
    }

	/**getMouseManager
	 * Returns the private variable mouseManager's value
	 * @return the value of the private variable mouseManager
	 */
    public MouseManager getMouseManager() {
        return mm;
    }

	/**tick
	 * updates all values and variables
	 * 
	 */
    private void tick() {
        time++;

        sm.tick();
        sm.getState().tick();
        km.tick();
        handler.devMode = km.f;

    }

	/**getTime
	 * Returns the private variable time's value
	 * @return the value of the private variable time
	 */
    public int getTime() {
        return time;
    }


	/**render
	 * where objects are rendered to the screen
	 * 
	 */
    private void render() {
        BufferStrategy bs = screen.getCanvas().getBufferStrategy();
        if (bs == null) {
            screen.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, WIN_SIZE, WIN_SIZE);
        //Draw Here!
        sm.getState().render(g);

        bs.show();
        g.dispose();
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void run() {
        init();
        final int FPS = 60;
        final double timePerFrame = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            //calculate delta time
            now = System.nanoTime();
            delta += (now - lastTime) / timePerFrame;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                if (handler.getKeyManager().f) System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }

        }
        stop();


    }

	/**stop the games thread
	 * 
	 */
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

	/**start the game
	 * 
	 */
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }


}
