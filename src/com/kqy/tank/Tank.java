package com.kqy.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir;
    private static final int speed = 5;
    private boolean moving = false;
    private static int WIDTH = 30, HEIGHT = 30;
    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics){
        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x,y,WIDTH,HEIGHT);
        graphics.setColor(c);
        move();
    }

    public void fire(){
        tf.b = new Bullet(this.x,this.y,this.dir);
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
}
