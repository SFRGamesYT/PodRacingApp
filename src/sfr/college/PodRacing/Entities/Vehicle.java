/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;
import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Sound;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;
import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;

/**
 * @author Sami
 */
public class Vehicle extends AnimImageEntity {
    public static Vector2D startPos;
    protected HitBox hitBox;
    protected final double MAX_SPEED, STEER_SP;
    protected double angle;
    protected double jerk;
    protected Vector2D direction;//in degrees
    protected Vector2D tempDirection;
    protected Vector2D collisonResponse;
    protected int engineDelta;
    protected Animation left, right, idle;
    protected Sound engineSound;
    private boolean drifting;
    protected MapCollisionHulls mch;
    private boolean colliding;
    private double friction;
    private HitBox finishLine;
    private long timeTaken,now,lastFrameTime;
    private byte lap;
    private long startTime;
    private boolean start;
    private boolean hasControls;



    public Vehicle(Handler handler, Animation animation, float originX, float originY, double max_speed, double steer_speed, double jerk, Sound sound) {
        super(handler, animation, 0.25f, 0.5f, 0.7f,originX,originY);
        startPos = new Vector2D(0.0625f,0.75f);
        hitBox = new HitBox(startPos.getMultiplied(WIN_SIZE),new Vector2D (Game.scaleToWindow(0.025f),Game.scaleToWindow(0.025)),new Vector2D(0.5d,0.5d),true);
        MAX_SPEED = max_speed;
        STEER_SP = steer_speed;
        this.jerk = jerk;
        angle = 0;
        direction = new Vector2D();
        direction.set(Math.sin(angle),-Math.cos(angle));
        idle = animation;
        engineSound = sound;
        engineDelta = 15;
        mch = new MapCollisionHulls();
        colliding = false;
        friction = 0;
        finishLine  = new HitBox(7,185,27,187);
        lap = 0;
        start = false;

        timeTaken = 0;
        hasControls = false;
        startTime = System.currentTimeMillis();

    }

    public void tick() {
        now = System.currentTimeMillis();
        if(System.currentTimeMillis()-startTime>1000)hasControls = true;
        super.tick();

        if(start)timeTaken+=now-lastFrameTime;

        if(drifting){
            hitBox.setVelocity(tempDirection.getMultiplied(hitBox.getAcceleration()));
        }else{
            hitBox.setVelocity(direction.getMultiplied(hitBox.getAcceleration()));
        }
        byte count = 0;
        for(HitBox x: mch.getHitBoxes()) {
            if(hitBox.DynamicRectVsRect(x)){
                colliding = true;
                count++;
                Vector2D collisionRes = hitBox.getContactNormal().getMultiplied(hitBox.getVelocity().getAbs()).getMultiplied(1 - hitBox.getT_hit_near());
                hitBox.getVelocity().add(collisionRes);

            }else if(count == 0){
                colliding = false;
            }


        }
        if(hitBox.DynamicRectVsRect(finishLine)&&hitBox.getContactNormal().equals(new Vector2D(0,1))){
            if(lap == 0)start = true;
            lap++;
            Assets.beep.play();
        }else if(hitBox.DynamicRectVsRect(finishLine)&&hitBox.getContactNormal().equals(new Vector2D(0,-1))){
            Vector2D collisionRes = hitBox.getContactNormal().getMultiplied(hitBox.getVelocity().getAbs()).getMultiplied(1 - hitBox.getT_hit_near());
            hitBox.getVelocity().add(collisionRes);
        }






        if (hitBox.getVelocity().getLength() > 0) {
            if (handler.getTime() % engineDelta == 0) {
                engineSound.play();
            }
        }
        this.setAnim(idle);
//
        if (handler.getKeyManager().space&&hasControls) {
            if (!drifting) {
                tempDirection = direction;
                drifting = true;
            }

        } else {
            drifting = false;
            direction.set(Math.sin(angle),-Math.cos(angle));
        }

        //forward
        if (hitBox.getVelocity().getLength() <= MAX_SPEED&&(Math.abs(hitBox.getAcceleration().x)<=MAX_SPEED||Math.abs(hitBox.getAcceleration().y)<=MAX_SPEED)) {
            if (handler.getKeyManager().up && hasControls && !(drifting && (handler.getKeyManager().left || handler.getKeyManager().right))&&!colliding) {
                //pod shake
                if (handler.getTime() % 3 == 0) {
                    boundsOnScreen.getPos().x += Math.pow(-1, handler.getTime()) * Game.scaleToWindow(0.004f);
                }
                //speed up engine noise
                if (handler.getTime() % 10 == 0) {
                    engineDelta--;
                }
                //accelerate
                if(colliding) {

                }else{
                    hitBox.getAcceleration().add(jerk/2,jerk/2);
                }

            } else {
                if (handler.getTime() % 10 == 0) {
                    engineDelta++;
                }
                boundsOnScreen.getPosCentre().x = WIN_SIZE_HALF;
                if (hitBox.getVelocity().getLength() > 0 && hitBox.getAcceleration().getLength() > 0) {
                    if (drifting) {

                        hitBox.getAcceleration().subtract(jerk / 2, jerk / 2);
                    } else {

                        hitBox.getAcceleration().subtract(jerk, jerk);
                    }
                }

            }
        }
        if(hitBox.getVelocity().getLength()>=MAX_SPEED){
            hitBox.getAcceleration().subtract(jerk,jerk);
        }
        if (engineDelta < 3) engineDelta = 3;
        if (engineDelta > 15) engineDelta = 15;
        if(hitBox.getAcceleration().x<0&&hitBox.getAcceleration().y<0)hitBox.getAcceleration().setZero();
        if(hasControls) {
            if (handler.invertControls) {
                if (handler.getKeyManager().left) {
                    angle += STEER_SP;
                    this.setAnim(right);

                }
                if (handler.getKeyManager().right) {
                    angle -= STEER_SP;
                    this.setAnim(left);
                }
            } else {
                if (handler.getKeyManager().left) {
                    angle -= STEER_SP;
                    ;
                    this.setAnim(left);
                }
                if (handler.getKeyManager().right) {
                    angle += STEER_SP;
                    this.setAnim(right);
                }
            }
        }

        hitBox.tick();


        lastFrameTime = now;

    }

    public void render(Graphics g) {

        super.render(g);


    }

    public static Vector2D getStart() {
        return startPos;
    }

    public static void setStart(Vector2D start) {
        Vehicle.startPos = start;
    }


    public boolean isColliding() {
        return colliding;
    }


    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public double getMAX_SPEED() {
        return MAX_SPEED;
    }

    public double getSTEER_SP() {
        return STEER_SP;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getJerk() {
        return jerk;
    }

    public void setJerk(double jerk) {
        this.jerk = jerk;
    }

    public Vector2D getDirection() {
        direction.setName("direction");return direction.getMultiplied(new Vector2D(1,-1));
    }



    public double getAngle() {
        return angle;
    }



    public long getTimeTaken() {
        return timeTaken;
    }



    public byte getLap() {
        return lap;
    }


    public boolean isHasControls() {
        return hasControls;
    }

    public void setHasControls(boolean hasControls) {
        this.hasControls = hasControls;
    }
}
