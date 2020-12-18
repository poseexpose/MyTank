package com.kqy.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Dir dir;
    private static final int speed = 10;
    private static int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private TankFrame tf = null;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics){
        if(!live)tf.bulletList.remove(this);
        /*Color c = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x,y,WIDTH,HEIGHT);
        graphics.setColor(c);*/
        switch (dir){
            case UP:
                graphics.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            default:break;
        }
        move();
    }

    private void move() {
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
        if(x < 0 || y < 0 || x > tf.getWidth() || y > tf.getHeight()) live = false;
    }




}
