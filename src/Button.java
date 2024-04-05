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

    public Button(String name, String b, int x, int y) {
        this.imageFileName = b;
        this.x = x;
        this.y = y;
        this.image = readImage();
        this.buttonName = name;
        this.visible = true;
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
            g.drawImage(image, x,y, image.getWidth(), image.getHeight(), null);

        }
}

    public boolean contains(Point clicked) {
        return true;
    }
}
