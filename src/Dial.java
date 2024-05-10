import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dial {
    private String imageFileName;
    private BufferedImage image;
    private int x;
    private int y;
    private Rectangle cardBox;
    private double width;
    private double height;

    public Dial(String b, int x, int y, int width, int height) {
        this.imageFileName = b;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = readImage();
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.height = image.getHeight();
        this.width = image.getWidth();


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
            g.drawImage(image, x, y, (int)width, (int)height, null);
    }
    public BufferedImage rotate(BufferedImage img)
    {

        // Getting Dimensions of image
        int width = img.getWidth();
        int height = img.getHeight();

        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();

        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(90), width / 2,
                height / 2);
        g2.drawImage(img, null, 0, 0);

        // Return rotated buffer image
        return newImage;
    }


}
