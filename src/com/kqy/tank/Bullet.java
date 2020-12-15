package com.kqy.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Dir dir;
    private static final int speed = 1;
    private static int WIDTH = 30, HEIGHT = 30;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics graphics){
        Color c = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x,y,WIDTH,HEIGHT);
        graphics.setColor(c);
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
    }




}
