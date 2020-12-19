package com.kqy.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800,GAME_HEIGHT =600;
    Tank tank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
//    Explode explode = new Explode(100,100,this);
//    Bullet b = new Bullet(200,200,Dir.DOWN);
    public TankFrame() throws HeadlessException {
        setVisible(true);
        setResizable(false);
        setTitle("tank war");
        setSize(GAME_WIDTH,GAME_HEIGHT);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量"+bulletList.size(),10,60);
        g.drawString("敌人的数量"+tankList.size(),10,80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);
        tank.paint(g);
        for (int i = 0; i < bulletList.size(); i ++){
            bulletList.get(i).paint(g);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for (int i = 0; i < bulletList.size(); i ++){
            for (int j = 0; j < tankList.size(); j++) {
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }



    }



    class MyKeyListener extends KeyAdapter{
        boolean bU = false;
        boolean bL = false;
        boolean bD = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:break;
            }
            setMainTank();
//            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        private void setMainTank() {
            if(!bL&!bU&!bD&!bR)tank.setMoving(false);
            else{
                tank.setMoving(true);
                if (bU) tank.setDir(Dir.UP);
                if (bL) tank.setDir(Dir.LEFT) ;
                if (bD) tank.setDir(Dir.DOWN) ;
                if (bR) tank.setDir(Dir.RIGHT) ;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                        tank.fire();
                        break;
                default:break;
            }
            setMainTank();
        }
    }
}
