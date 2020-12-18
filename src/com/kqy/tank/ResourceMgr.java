package com.kqy.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankU,tankL,tankD,tankR;
    public static BufferedImage bulletU,bulletL,bulletD,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankL =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankD =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankR =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));

            bulletU =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletL =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletD =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            bulletR =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
