/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author sr35477
 */
public class HitBox{
    private Vector2D pos, size, posCentre, velocity, origin, acceleration;
    private Rectangle2D rectangle2D;
    private HitBox[] contact;
    private int elapsedFrames;


    public HitBox(Vector2D pos, Vector2D size) {

        elapsedFrames = 0;
        this.posCentre = pos;
        this.origin = posCentre;
        this.size = size;
        this.pos = new Vector2D();
        this.pos = this.posCentre.getSubtracted(this.size.getMultiplied(0.5d));
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        rectangle2D = new Rectangle2D.Double();
        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);
        setNames();
    }

    public HitBox(Vector2D pos, Vector2D size, Vector2D origin) {
        elapsedFrames = 0;
        this.posCentre = pos;
        this.size = size;
        this.pos = this.posCentre.getSubtracted(this.size.getMultiplied(0.5d));
        this.origin = origin;
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        rectangle2D = new Rectangle2D.Double();
        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);
        setNames();
    }


    public HitBox(int x1, int y1, int x2, int y2) {//for racetrack hitboxes
        elapsedFrames = 0;
        pos = new Vector2D(x1, y1);
        this.size = new Vector2D(x2, y2).getSubtracted(pos);
        this.posCentre = pos.getAdded(size.getMultiplied(0.5d));
        this.origin = posCentre;
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        contact = new HitBox[4];
        rectangle2D = new Rectangle2D.Double();
        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);
        setNames();
    }

    private void setNames() {
        pos.setName("pos");
        size.setName("size");
        posCentre.setName("posCentre");
        velocity.setName("velocity");
        origin.setName("origin");
        acceleration.setName("acceleration");
    }

    public HitBox getScaled() {
        return new HitBox(this.pos.getMultiplied(Game.mapScale).getAdded(this.size.getMultiplied(2.5d)), this.size.getMultiplied(Game.mapScale));
    }

    public void render(Graphics g) {
        g.fillRect((int) pos.x, (int) pos.y, (int) size.x, (int) size.y);
    }


    public void tick() {
        pos.add(velocity);
        this.posCentre = pos.getAdded(size.getMultiplied(0.5d));
        velocity.add(acceleration);
        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);

        elapsedFrames++;
    }

    public boolean PointVsRect(Vector2D p) {
        return (p.x >= pos.x && p.y >= pos.y && p.x < pos.x + size.x && p.y < pos.y + size.y);
    }

    public boolean RectVsRect(HitBox r) {
        return this.rectangle2D.intersects(r.getRectangle2D());
    }

    public boolean RayVsRect(Vector2D ray_origin, Vector2D ray_dir, HitBox target, Vector2D contact_point, Vector2D contact_normal, double t_hit_near) {
        contact_normal.setZero();
        contact_point.setZero();
        Vector2D invDir = new Vector2D(1.0d / ray_dir.x, 1.0d / ray_dir.y);

        // Calculate intersections with rectangle bounding axes
        Vector2D t_near = (target.pos.getSubtracted(ray_origin)).getMultiplied(invDir);
        Vector2D t_far = (target.pos.getAdded(target.size).getSubtracted(ray_origin)).getMultiplied(invDir);

        if (Double.isNaN(t_far.y) || Double.isNaN(t_far.x)) return false;
        if (Double.isNaN(t_near.y) || Double.isNaN(t_near.x)) return false;

        // Sort distances
        if (t_near.x > t_far.x) {
            double temp = t_near.x;
            t_near.x = t_far.x;
            t_far.x = temp;
        }
        if (t_near.y > t_far.y) {
            double temp = t_near.y;
            t_near.y = t_far.y;
            t_far.y = temp;
        }

        // Early rejection
        if (t_near.x > t_far.y || t_near.y > t_far.x) return false;

        // Closest 'time' will be the first contact
        t_hit_near = max(t_near.x, t_near.y);

        // Furthest 'time' is contact on opposite side of target
        double t_hit_far = min(t_far.x, t_far.y);

        // Reject if ray direction is pointing away from object
        if (t_hit_far < 0) return false;

        // Contact point of collision from parametric line equation
        contact_point = ray_origin.getAdded(ray_dir.getMultiplied(t_hit_near));

        if (t_near.x > t_near.y)
            if (invDir.x < 0)
                contact_normal.set(1, 0);
            else
                contact_normal.set(-1, 0);
        else if (t_near.x < t_near.y)
            if (invDir.y < 0)
                contact_normal.set(0, 1);
            else
                contact_normal.set(0, -1);

        return true;
    }

    public boolean DynamicRectVsRect(HitBox r_dynamic, HitBox r_static, double fTimeStep, Vector2D contact_point, Vector2D contact_normal, double contact_time) {
        // Check if dynamic rectangle is actually moving - we assume rectangles are NOT in collision to start
        if (r_dynamic.velocity.x == 0 && r_dynamic.velocity.x == 0)
            return false;

        // Expand target rectangle by source dimensions
        HitBox expanded_target = new HitBox(r_static.pos.getSubtracted(r_dynamic.size.getDivided(2)), r_static.size.getAdded(r_dynamic.size));


        if (RayVsRect(r_dynamic.pos.getAdded(r_dynamic.size.getDivided(2)), r_dynamic.velocity.getMultiplied(fTimeStep), expanded_target, contact_point, contact_normal, contact_time)) {
            return (contact_time >= 0.0f && contact_time < 1.0f);
        } else return false;
    }

    boolean ResolveDynamicRectVsRect(HitBox r_dynamic, double fTimeStep, HitBox r_static, Vector2D contact_point, Vector2D contact_normal) {
        ;
        double contact_time = 0.0f;
        if (DynamicRectVsRect(r_dynamic, r_static, fTimeStep, contact_point, contact_normal, contact_time)) {
            if (contact_normal.y > 0) r_dynamic.contact[0] = r_static;
            else r_dynamic.contact[0] = null;
            if (contact_normal.x < 0) r_dynamic.contact[1] = r_static;
            else r_dynamic.contact[1] = null;
            if (contact_normal.y < 0) r_dynamic.contact[2] = r_static;
            else r_dynamic.contact[2] = null;
            if (contact_normal.x > 0) r_dynamic.contact[3] = r_static;
            else r_dynamic.contact[3] = null;

            r_dynamic.velocity.add(contact_normal.getMultiplied(r_dynamic.velocity.getAbs().getMultiplied(1 - contact_time)));
            return true;
        }

        return false;
    }


    public Vector2D getPos() {
        return pos;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public Vector2D getSize() {
        return size;
    }

    public void setSize(Vector2D size) {
        this.size = size;
    }

    public Vector2D getPosCentre() {
        return posCentre;
    }

    public void setPosCentre(Vector2D posCentre) {
        this.posCentre = posCentre;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration.set(acceleration);
    }

    public Vector2D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2D origin) {
        this.origin = origin;
    }

    public HitBox[] getContact() {
        return contact;
    }

    public void setContact(HitBox[] contact) {
        this.contact = contact;
    }

    public int getElapsedFrames() {
        return elapsedFrames;
    }

    public Rectangle2D getRectangle2D() {
        return rectangle2D;
    }

    public void setRectangle2D(Rectangle2D rectangle2D) {
        this.rectangle2D = rectangle2D;
    }

    public void setElapsedFrames(int elapsedFrames) {
        this.elapsedFrames = elapsedFrames;
    }
}