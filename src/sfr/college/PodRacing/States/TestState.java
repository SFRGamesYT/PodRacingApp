package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Entities.HitBox;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

public class TestState extends State{
    private HitBox r0,r1;
    private Vector2D mousePos,playerToMouse;
    private int clickTime;
    public TestState(Handler handler){
        super(handler);
        r1 = new HitBox(new Vector2D(Game.WIN_SIZE_HALF,Game.WIN_SIZE_HALF),new Vector2D(Game.WIN_SIZE_QUARTER,Game.WIN_SIZE_8TH),new Vector2D(0.5f,0.5f),false);
        r0 = new HitBox(new Vector2D(Game.WIN_SIZE_8TH,Game.WIN_SIZE_8TH),new Vector2D(Game.scaleToWindow(0.1f),Game.scaleToWindow(0.1f)),new Vector2D(0.5f,0.5f),false);
        mousePos = new Vector2D(handler.getMouseManager().mX,handler.getMouseManager().mY);
        playerToMouse = new Vector2D();
        playerToMouse = mousePos.getSubtracted(r0.getPosCentre());
        clickTime= 0;

    }
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,Game.WIN_SIZE,Game.WIN_SIZE);
        g.setColor(Color.cyan);
        if(r0.DynamicRectVsRect(r1))g.setColor(Color.yellow);
        r1.draw((Graphics2D) g);
        g.setColor(Color.green);
        r0.draw((Graphics2D) g);
        g.setColor(Color.magenta);
        playerToMouse.renderFrom(r0.getPosCentre(),(Graphics2D) g);
        if(r0.RayVsRect(playerToMouse,r1)){
            g.setColor(Color.red);
            new HitBox(r0.getContactPoint(),r0.getSize().getDivided(10),new Vector2D(0.5,0.5),false).fill((Graphics2D) g);
        }

    }
    public void tick(){

        mousePos = new Vector2D(handler.getMouseManager().mX,handler.getMouseManager().mY);
        playerToMouse = mousePos.getSubtracted(r0.getPosCentre());
        if(handler.getMouseManager().leftPressed){
            clickTime++;
        }else{
            clickTime = 0;
        }
        r0.setVelocity(playerToMouse.getNormalized().getMultiplied(clickTime/10));
        if(r0.DynamicRectVsRect(r1)){
            Vector2D collisionRes = r0.getContactNormal().getMultiplied(r0.getVelocity().getAbs()).getMultiplied(1-r0.getT_hit_near());
            r0.getVelocity().add(collisionRes);
        }



        r0.tick();
    }
}
