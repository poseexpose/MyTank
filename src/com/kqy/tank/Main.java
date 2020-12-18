package com.kqy.tank;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for(int i=0; i<5; i++) {
            tf.tankList.add(new Tank(50 + i*80, 100, Dir.DOWN, tf));
        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }


}
