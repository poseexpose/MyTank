package com.kqy.tank;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Frame tf = new TankFrame();
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }


}
