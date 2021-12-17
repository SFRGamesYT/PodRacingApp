/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;
import sfr.college.PodRacing.Sound;
import sfr.college.PodRacing.gfx.Animation;
import sfr.college.PodRacing.util.MathUtils;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE_HALF;

/**
 * @author Sami
 */
public class Vehicle extends AnimImageEntity {
    public static final float startX = 0.0625f;
    public static final float startY = 0.75f;
    protected final double MAX_SPEED, STEER_SP, ACCELERATION;
    protected double sp;
    protected double direction;//in degrees
    protected double tempDirection;
    protected float camX, camY;
    protected int engineDelta;
    protected Animation left, right, idle;
    protected Sound engineSound;
    protected Rectangle hitBox;
    protected Rectangle collisionHull;
    protected boolean isColliding;
    private boolean drifting;

    public Vehicle(Handler handler, Animation animation, float cx, float cy, double max, double steer, double acc, Sound sound) {
        super(handler, animation, 0.25f, 0.5f, 0.8f, cx, cy);

        camX = -Game.scaleToWindow(startX - 0.5f);
        camY = -Game.scaleToWindow(startY - 0.5f);
        MAX_SPEED = max;
        STEER_SP = steer;
        ACCELERATION = acc;
        sp = 0;
        direction = 0;
        idle = animation;
        engineSound = sound;
        engineDelta = 15;
        hitBox = new Rectangle(x2, y2 + h2 - w2, w2, w2);
        isColliding = false;


    }

    public void tick() {


        if (handler.getKeyManager().six) direction = 0;
        if (sp > 0) {
            if (handler.getTime() % engineDelta == 0) {
                engineSound.play();
            }
        }
        this.setAnim(idle);
        if (sp >= MAX_SPEED) {
            sp = MAX_SPEED;
        }
        if (handler.getKeyManager().space) {
            if (!drifting) {
                tempDirection = direction;
                drifting = true;
            }
            camY += (Math.cos(tempDirection)) * (sp);
            camX += (Math.sin(tempDirection)) * (sp);
        } else {
            drifting = false;
            camY += (Math.cos(direction)) * sp;
            camX += (Math.sin(direction)) * sp;
        }


        if (handler.getFOV() >= 10)
            if (handler.getKeyManager().up && !(drifting && (handler.getKeyManager().left || handler.getKeyManager().right))) {
                if (handler.getTime() % 3 == 0) {
                    super.x2 += Math.pow(-1, handler.getTime()) * Game.scaleToWindow(0.003f);
                }
                if (handler.getTime() % 10 == 0) {
                    engineDelta--;
                }
                if (sp >= ACCELERATION) {
                    sp += ACCELERATION;
                } else {
                    sp += MathUtils.approachLinear(ACCELERATION, sp, ACCELERATION / 10);
                }
            } else {
                if (handler.getTime() % 10 == 0) {
                    engineDelta++;
                }
                super.x2 = WIN_SIZE_HALF - super.w2 / 2;
                if (sp > 0) {
                    if (drifting) {
                        sp -= ACCELERATION / 2;
                    } else {
                        sp -= (ACCELERATION);
                    }
                }

            }
        if (engineDelta < 3) engineDelta = 3;
        if (engineDelta > 15) engineDelta = 15;
        if (sp < 0) sp = 0;
        if (!handler.invertControls) {
            if (handler.getKeyManager().left) {
                direction += STEER_SP;
                this.setAnim(left);
                if (drifting) {

                    tempDirection += STEER_SP / 20;
                }
            }
            if (handler.getKeyManager().right) {
                direction -= STEER_SP;
                this.setAnim(right);
                if (drifting) {

                    tempDirection -= STEER_SP / 20;
                }
            }
        } else {
            if (handler.getKeyManager().left) {
                direction -= STEER_SP;
                this.setAnim(right);
                if (drifting) {

                    tempDirection -= STEER_SP / 20;
                }
            }
            if (handler.getKeyManager().right) {
                direction += STEER_SP;
                this.setAnim(left);
                if (drifting) {

                    tempDirection += STEER_SP / 20;
                }
            }
        }
        try {
            collisionHull.setLocation(handler.getGameState().getMapX() - w2 / (2 * 10), handler.getGameState().getMapY() + h2 / (10));
        } catch (NullPointerException e) {
            collisionHull = new Rectangle(handler.getGameState().getMapX() - w2 / (2 * 10), handler.getGameState().getMapY() + h2 / (10), w2 / 10, w2 / 10);
        }

        super.tick();


    }

    public void render(Graphics g) {

        super.render(g);

    }

    public boolean isColliding(Rectangle rec) {
        // collision detected!
        return collisionHull.intersects(rec);
    }

    public float getCamX() {
        return camX;
    }

    public float getCamY() {
        return camY;
    }

    public double getDirection() {
        return direction;
    }

    public Rectangle getCollisionHull() {
        return collisionHull;
    }


}
