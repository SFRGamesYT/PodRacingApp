/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.util.MathUtils;
import sfr.college.PodRacing.util.Vector2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author sr35477
 */
public class HitBox{
    private Vector2D pos, size, posCentre, velocity, origin, acceleration,contactPoint,contactNormal;
    private Rectangle2D rectangle2D;
    private HitBox[] contact;
    private int elapsedFrames;
    private boolean belongsToVehicle;
    private double t_hit_near;


    public HitBox(Vector2D pos, Vector2D size, Vector2D origin,boolean belongsToVehicle) {
        this.belongsToVehicle = belongsToVehicle;
        elapsedFrames = 0;
        this.posCentre = pos;
        this.origin = origin;
        this.size = size;
        this.pos = new Vector2D();
        this.pos = this.posCentre.getSubtracted(this.size.getMultiplied(this.origin));
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        rectangle2D = new Rectangle2D.Double();
        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);
        contactPoint = new Vector2D();
        contactNormal = new Vector2D();
        setNames();
    }



    public HitBox(int x1, int y1, int x2, int y2) {//for racetrack hitboxes
        this.belongsToVehicle = false;
        elapsedFrames = 0;
        pos = new Vector2D(Game.WIN_SIZE*(x1/256d), Game.WIN_SIZE*(y1/256d));
        this.size = new Vector2D(Game.WIN_SIZE*(x2/256d), Game.WIN_SIZE*(y2/256d)).getSubtracted(pos);
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
    public HitBox getTranslated(Vector2D x,boolean belongsToVehicle){
        return new HitBox(this.posCentre.getAdded(x),this.size,this.origin,belongsToVehicle);
    }



    public void draw(Graphics2D g) {
        g.drawRect(MathUtils.floor(pos.x),MathUtils.floor(pos.y),MathUtils.floor(size.x),MathUtils.floor(size.y));
    }
    public void fill(Graphics2D g) {
        g.fillRect(MathUtils.floor(pos.x),MathUtils.floor(pos.y),MathUtils.floor(size.x),MathUtils.floor(size.y));
    }


    public void tick() {
        if(!belongsToVehicle)velocity.add(acceleration);
        this.posCentre.add(velocity);
        this.pos = this.posCentre.getSubtracted(this.size.getMultiplied(this.origin));

        rectangle2D.setRect(this.pos.x,this.pos.y,this.size.x,this.size.y);

        elapsedFrames++;
    }

    public boolean PointVsRect(Vector2D p) {
        return (p.x >= pos.x && p.y >= pos.y && p.x < pos.x + size.x && p.y < pos.y + size.y);
    }

    public boolean RectVsRect(HitBox r) {
        return this.rectangle2D.intersects(r.getRectangle2D());
    }

    public boolean RayVsRect( Vector2D ray_dir,HitBox target){
        //calculate percentages along ray where ray intersects edge of hitbox
        Vector2D t_near = (target.pos.getSubtracted(this.posCentre)).getDivided(ray_dir);
        Vector2D t_far = (target.pos.getAdded(target.size).getSubtracted(this.posCentre)).getDivided(ray_dir);
        //sort intersections
        if(t_near.x > t_far.x){
            double tempNearX = t_near.x;
            t_near.x = t_far.x;
            t_far.x = tempNearX;
        }
        if(t_near.y > t_far.y){
            double tempNearY = t_near.y;
            t_near.y = t_far.y;
            t_far.y = tempNearY;
        }
        //for an intersection, near x is always less than far y, and near y is always less that far x
        //so return false if this isn't the case
        if(t_near.x > t_far.y || t_near.y > t_far.x) return  false;

        //work out actual percentage along the ray where collision
        double t_hit_near = Double.max(t_near.x,t_near.y);
        double t_hit_far  = Double.min(t_far.x, t_far.y);

        //we don't want to test collision for the ray in the opposite direction.
        if(t_hit_far < 0)return false;

        //calculate point of contact using line equation
        this.contactPoint = this.posCentre.getAdded(ray_dir.getMultiplied(t_hit_near));

        //calculate contact normal
        if(t_near.x > t_near.y){
            if(ray_dir.x < 0){
                this.contactNormal = new Vector2D(1,0);
            }else{
                this.contactNormal = new Vector2D(-1,0);
            }
        }else if(t_near.x < t_near.y){
            if(ray_dir.y < 0){
                this.contactNormal = new Vector2D(0,1);
            }else{
                this.contactNormal = new Vector2D(0,-1);
            }
        }

        if(t_hit_near>1)return false;
        this.t_hit_near = t_hit_near;
        return true;




    }

    public boolean DynamicRectVsRect(HitBox target){
        //if hitbox is not moving
        if(this.getVelocity().getLength()==0){
            return false;
        }
        HitBox expandedTarget = new HitBox(target.posCentre,target.size.getAdded(this.size),new Vector2D(0.5f,0.5f),false);
        if(this.RayVsRect(this.velocity,expandedTarget)){
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

    public Vector2D getContactPoint() {
        return contactPoint;
    }

    public Vector2D getContactNormal() {
        return contactNormal;
    }

    public boolean isBelongsToVehicle() {
        return belongsToVehicle;
    }

    public double getT_hit_near() {
        return t_hit_near;
    }
}