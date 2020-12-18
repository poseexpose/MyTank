package com.kqy.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;
    private static final int speed = 5;
    private boolean moving = true;
    public static int WIDTH = ResourceMgr.tankD.getWidth(), HEIGHT =ResourceMgr.tankD.getHeight();
    private TankFrame tf = null;
    private Random random = new Random();
    private Group group = Group.BAD;
    private boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }



    public Tank(int x, int y, Dir dir, Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics graphics){
        /*Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x,y,WIDTH,HEIGHT);
        graphics.setColor(c);*/
        if (!living){
            tf.tankList.remove(this);
        }

        switch (dir){
            case UP:
                graphics.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            default:break;
        }
        move();
    }

    public void fire(){
        int bX = x + WIDTH/2 - ResourceMgr.bulletD.getWidth()/2;
        int bY= y + HEIGHT/2 - ResourceMgr.bulletD.getHeight()/2;
        tf.bulletList.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
    }

    public void move() {
        if(!moving)return;
        switch (dir){
            case UP:
                y-=speed;
                break;
            case LEFT:
                x-=speed;
                break;
            case DOWN:
                y+=speed;
                break;
            case RIGHT:
                x+=speed;
                break;
            default:break;
        }
    }


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public static int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void die() {
        this.living = false;
    }
}
