package gameImages;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public enum Img{
    BlueLockeddoor,
    Chip,
    Exitlock,
    Exittile,
    Freetile,
    GreenLockeddoor,
    Infotile,
    RedLockeddoor,
    Walltile,
    YellowLockeddoor,
    Bluekey,
    Greenkey,
    Yellowkey,
    Redkey;

    public final BufferedImage image;
    Img(){image=loadImage(this.name());}
    static private BufferedImage loadImage(String name){
        URL imagePath = Img.class.getResource(name+".png");
        try{return ImageIO.read(imagePath);}
        catch(IOException e) { throw new Error(e); }
    }
}