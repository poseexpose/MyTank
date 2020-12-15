package com.kqy.tank;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 800,GAME_HEIGHT =600;
    Tank tank = new Tank(200,200,Dir.DOWN,this);
    Bullet b = new Bullet(200,200,Dir.DOWN);
    public TankFrame() throws HeadlessException {
        setVisible(true);
        setResizable(false);
        setTitle("tank war");
        setSize(800,600);

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
        tank.paint(g);
        b.paint(g);
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
