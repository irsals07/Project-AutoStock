import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Background {
    private String bg;
    private String imageFileName;
    private String backImageFileName;

    private BufferedImage image;
    private boolean show;

    public Background(String bg){
        this.imageFileName = bg;
        this.image = readImage();
        this.show = true;
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

}
