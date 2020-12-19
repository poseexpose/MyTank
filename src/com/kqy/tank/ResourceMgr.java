package com.kqy.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage goodTankU,goodTankL,goodTankD,goodTankR;
    public static BufferedImage badTankU,badTankL,badTankD,badTankR;
    public static BufferedImage bulletU,bulletL,bulletD,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL =ImageUtil.rotateImage(goodTankU,-90);
            goodTankD =ImageUtil.rotateImage(goodTankU,180);
            goodTankR =ImageUtil.rotateImage(goodTankU,90);

            badTankU =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL =ImageUtil.rotateImage(badTankU,-90);
            badTankD =ImageUtil.rotateImage(badTankU,180);
            badTankR =ImageUtil.rotateImage(badTankU,90);

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
