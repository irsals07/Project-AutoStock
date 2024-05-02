import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Background {
    private String bg;
    private String imageFileName;
    private String backImageFileName;
    private int x;
    private int y;

    private BufferedImage image;
    private boolean show;
    public Background(String bg){
        this.imageFileName = bg;
        this.image = readImage();
        this.show = true;
        this.x = x;
        this.y = y;
    }


    public Background(String bg, int x, int y){
        this.imageFileName = bg;
        this.image = readImage();
        this.show = true;
        this.x = x;
        this.y = y;
    }
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage readImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g)
    {
            g.drawImage(image, getX(), getY(), (int)image.getWidth(), (int)image.getHeight(), null);

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
