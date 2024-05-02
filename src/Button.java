import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button {
    private String buttonName;
    private Rectangle button;
    private boolean visible;

    private String imageFileName;
    private BufferedImage image;
    private int x;
    private int y;
    private Rectangle cardBox;
    private double width;
    private double height;
    private boolean clickable;

    public Button(String name, String b, int x, int y) {
        this.imageFileName = b;
        this.x = x;
        this.y = y;
        this.image = readImage();
        this.buttonName = name;
        this.visible = true;
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.clickable = clickable;
    }
    public void setRectangleLocation(int x, int y) {
        cardBox.setLocation(x, y);
    }

    public void setClickable(boolean b){
        clickable = b;
    }


    public String getButtonName() {
        return buttonName;
    }

    public Rectangle getButton() {
        return button;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean b){
        visible = b;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void draw(Graphics g)
    {
        if (visible){
            g.drawImage(image, getX(), getY(), (int)width, (int)height, null);

        }
}

    public boolean contains(Point clicked) {
        if(clickable){
            if(clicked.x > getX() && clicked.x < getX()+ width && clicked.y > getY() && clicked.y < getY()+height){
                return true;
            }
        }
        return false;
    }
    public void scale(double s){
        width = image.getWidth()*s;
        height =image.getHeight()*s;
    }
}
