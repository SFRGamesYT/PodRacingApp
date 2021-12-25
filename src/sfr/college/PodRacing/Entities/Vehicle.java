/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Sound;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;

/**
 * @author Sami
 */
public class Vehicle extends AnimImageEntity {
    public static Vector2D start;
    protected HitBox hitBox, hitBoxLarge;
    protected final double MAX_SPEED, STEER_SP;
    protected double angle;
    protected double jerk;
    protected Vector2D direction;//in degrees
    protected Vector2D tempDirection;
    protected int engineDelta;
    protected Animation left, right, idle;
    protected Sound engineSound;
    private boolean drifting;

    public Vehicle(Handler handler, Animation animation, float originX, float originY, double max_speed, double steer_speed, double jerk, Sound sound) {
        super(handler, animation, 0.25f, 0.5f, 0.8f);
        start = new Vector2D(0.0625f,0.75f);
        hitBoxLarge = new HitBox(start.getMultiplied(256),boundsOnScreen.getSize().getMultiplied(0.025d),new Vector2D(originX,originY));
        hitBox = new HitBox(hitBoxLarge.getPos().getAdded(hitBoxLarge.getSize().getMultiplied(hitBoxLarge.getOrigin())),new Vector2D (hitBoxLarge.getSize().x/2,hitBoxLarge.getSize().x/2));
        MAX_SPEED = max_speed;
        STEER_SP = steer_speed;
        this.jerk = jerk;
        angle = 0;
        direction = new Vector2D();
        direction.set(Math.sin(angle),-Math.cos(angle));
        idle = animation;
        engineSound = sound;
        engineDelta = 15;
    }

    public void tick() {
        super.tick();

        hitBox.setVelocity(direction);

        hitBox.setVelocity(hitBox.getVelocity().getMultiplied(hitBox.getAcceleration()));

        if (handler.getKeyManager().six) direction.set(0,1);
        if (hitBox.getVelocity().getLength() > 0) {
            if (handler.getTime() % engineDelta == 0) {
                engineSound.play();
            }
        }
        this.setAnim(idle);

        if (handler.getKeyManager().space) {
            if (!drifting) {
                tempDirection = direction;
                drifting = true;
            }
           direction = tempDirection;
        } else {
            drifting = false;
            direction.set(Math.sin(angle),-Math.cos(angle));
        }

        //forward
       // if (hitBoxLarge.getVelocity().getLength() < MAX_SPEED)
            if (handler.getKeyManager().up && !(drifting && (handler.getKeyManager().left || handler.getKeyManager().right))) {
                //pod shake
             //   if (handler.getTime() % 3 == 0) {
              //      boundsOnScreen.getPos().x += Math.pow(-1, handler.getTime()) * Game.scaleToWindow(0.003f);
              //  }
                //speed up engine noise
                if (handler.getTime() % 10 == 0) {
                    engineDelta--;
                }
                //accelerate

                hitBox.getAcceleration().add(jerk, jerk);

            } else {
                if (handler.getTime() % 10 == 0) {
                    engineDelta++;
                }
                boundsOnScreen.getPos().x = WIN_SIZE_HALF;
                if (hitBox.getVelocity().getLength() > 0&&hitBox.getAcceleration().x>0&&hitBox.getAcceleration().y>0) {
                    if (drifting) {

                        hitBox.getAcceleration().subtract(jerk/2,jerk/2);
                    } else {

                        hitBox.getAcceleration().subtract(jerk,jerk);
                    }
                }

            }
        if (engineDelta < 3) engineDelta = 3;
        if (engineDelta > 15) engineDelta = 15;
        if (jerk<0) jerk = 0;
        if(hitBox.getAcceleration().x<0&&hitBox.getAcceleration().y<0)hitBox.getAcceleration().setZero();hitBoxLarge.getAcceleration().setZero();
        if (handler.invertControls) {
            if (handler.getKeyManager().left) {
                angle += STEER_SP;
                this.setAnim(left);

            }
            if (handler.getKeyManager().right) {
                angle -= STEER_SP;
                this.setAnim(right);
            }
        } else {
            if (handler.getKeyManager().left) {
                angle -= STEER_SP;;
                this.setAnim(right);
            }
            if (handler.getKeyManager().right) {
                angle += STEER_SP;
                this.setAnim(left);
            }
        }

        hitBox.tick();



    }

    public void render(Graphics g) {

        super.render(g);


    }

    public static Vector2D getStart() {
        return start;
    }

    public static void setStart(Vector2D start) {
        Vehicle.start = start;
    }


    public HitBox getHitBoxLarge() {
        return hitBoxLarge;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBoxLarge = hitBox;
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

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public Vector2D getTempDirection() {
        return tempDirection;
    }

    public void setTempDirection(Vector2D tempDirection) {
        this.tempDirection = tempDirection;
    }

    public int getEngineDelta() {
        return engineDelta;
    }

    public void setEngineDelta(int engineDelta) {
        this.engineDelta = engineDelta;
    }

    public Animation getLeft() {
        return left;
    }

    public void setLeft(Animation left) {
        this.left = left;
    }

    public Sound getEngineSound() {
        return engineSound;
    }

    public void setEngineSound(Sound engineSound) {
        this.engineSound = engineSound;
    }

    public boolean isDrifting() {
        return drifting;
    }

    public void setDrifting(boolean drifting) {
        this.drifting = drifting;
    }

    public double getAngle() {
        return angle;
    }

    public HitBox getCollisionHull() {
        return boundsOnScreen;
    }



}
