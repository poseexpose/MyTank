package com.kqy.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Dir dir;
    private static final int speed = 10;
    private static int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private TankFrame tf = null;
    private boolean living = true;
    private Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
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
        if(!living)tf.bulletList.remove(this);
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

        //update rect
        rect.x = this.x;
        rect.y = this.y;
        if(x < 0 || y < 0 || x > tf.getWidth() || y > tf.getHeight()) living = false;
    }

    public void collideWith(Tank tank){
        if (this.group==tank.getGroup())return;

//        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY= tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die(){
        this.living = false;
    }


}
