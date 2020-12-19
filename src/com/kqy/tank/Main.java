package com.kqy.tank;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String) PropertyMgr.props.get("initTankCount")) ;

        for(int i=0; i<initTankCount; i++) {
            tf.tankList.add(new Tank(50 + i*80, 100, Dir.DOWN,Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }


}
