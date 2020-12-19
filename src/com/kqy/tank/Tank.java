package com.kqy.tank;

import sun.security.acl.GroupImpl;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;
    private static final int speed = 10;
    private boolean moving = true;
    private Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.goodTankD.getWidth(), HEIGHT =ResourceMgr.goodTankD.getHeight();
    private TankFrame tf = null;
    private Random random = new Random();
    public Rectangle rect = new Rectangle();
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
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
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
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU , x , y ,null);
                break;
            case LEFT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL , x , y ,null);
                break;
            case DOWN:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD , x , y ,null);
                break;
            case RIGHT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR , x , y ,null);
                break;
            default:break;
        }
        move();
    }

    public void fire(){
        int bX = x + WIDTH/2 - ResourceMgr.bulletD.getWidth()/2;
        int bY= y + HEIGHT/2 - ResourceMgr.bulletD.getHeight()/2;
        tf.bulletList.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
        for (int i = 0; i < tf.bulletList.size(); i++) {
            tf.bulletList.get(i).collideWith(this);
        }
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

        if (this.group == Group.BAD && random.nextInt(100) > 95)this.fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95)this.randomDir();

        boundsCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }


    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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
