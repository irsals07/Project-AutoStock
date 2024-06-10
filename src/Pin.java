import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import java.awt.*;

public class Pin {

    public static int drawX = 1490;
    public static int drawY = 170;
    private double width;
    private double height;
    private double width2;
    private double height2;
    private String imageFileName;
    private String dialName;

    private boolean show;
    private BufferedImage image;
    private BufferedImage dial;

    private int x;
    private int y;
    public Pin(String num){
        this.imageFileName = "image_directory/pin_movement/" + num +".png";
        this.dialName = "image_directory/game_images/dial.png";

        this.show = true;
        this.image = readImage();
        this.dial = readDial();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.width2 = dial.getWidth();
        this.height2 = dial.getHeight();
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth2() {
        return width2;
    }

    public double getHeight2() {
        return height2;
    }

    public BufferedImage readDial() {
        try {
            BufferedImage dial = null;
            if (show) {
                dial = ImageIO.read(new File(dialName));
            }

            return dial;
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
        g.drawImage(dial, 1300, getY(), (int)width2, (int)height2, null);
        g.drawImage(image, drawX, drawY, (int)width, (int)height, null);
    }
    public static ArrayList<Pin> showPin(){
        ArrayList<Pin> showPin = new ArrayList<Pin>();
        String[] nums = {"0", "3","6","9","12", "15","18","21","24", "27","30","33","36", "39","42","45","48", "51","54","57","60"};
        for(String n :nums){
            Pin c = new Pin(n);
            showPin.add(c);
        }
        return showPin;
    }

    public void scale(double s){
        width = image.getWidth()*s;
        height =image.getHeight()*s;

    }
    public void dialScale(double s){
        width2 = dial.getWidth()*s;
        height2 = dial.getHeight()*s;
    }




}
