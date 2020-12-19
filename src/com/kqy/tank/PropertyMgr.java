package com.kqy.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (props.isEmpty()) return null;
       return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(props.get("initTankCount"));
    }
}
