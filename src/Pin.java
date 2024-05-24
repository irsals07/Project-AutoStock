import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import java.awt.*;

public class Pin {

    private int width;
    private int height;
    private String imageFileName;
    private String topImageName;

    private boolean show;
    private BufferedImage image;

    private int x;
    private int y;
    public Pin(String num){
        this.imageFileName = "pinm_movement/" + num +".png";
        this.show = true;
        this.image = readImage();
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage readImage() {
        try {
            BufferedImage image = null;
            if (show) {
                image = ImageIO.read(new File(imageFileName));
            }

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
        g.drawImage(image, getX(), getY(), (int)width, (int)height, null);
    }
    public static ArrayList<Pin> showPin(){
        ArrayList<Pin> showPin = new ArrayList<Pin>();
        String[] nums = {"0", "3","6","9","12", "15","18","21","24", "27","30","33","36", "39","42","45","48"};
        for(String n :nums){
            Pin c = new Pin(n);
            showPin.add(c);
        }
        return showPin;
    }

}
