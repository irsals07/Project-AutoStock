import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Car {
    private int topSpeed;
    private int accelaration;
    private String num;
    private String imageFileName;
    private String backImageFileName;
    private boolean show;
    private BufferedImage image;
    private Rectangle carBox;
    private boolean highlight;

    public Car(String num){
        this.topSpeed = topSpeed;
        this.accelaration = accelaration;
        this.imageFileName = "images/car_" + num +".png";
        this.show = true;
        this.image = readImage();
        this.carBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.highlight = false;
    }
    public String getImageFileName() {
        return imageFileName;
    }
    public void flipHighlight() {
        highlight = !highlight;
    }
    public boolean getHighlight() {
        return highlight;
    }
    public void setRectangleLocation(int x, int y) {
        carBox.setLocation(x, y);
    }
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage readImage() {
        try {
            BufferedImage image;
            if (show) {
                image = ImageIO.read(new File(imageFileName));
            }
            else {
                image = ImageIO.read(new File(backImageFileName));
            }
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<Car> buildGarage(){
        ArrayList<Car> garage = new ArrayList<Car>();
        String[] nums = {"1","2","3","4", "5","6","7","8"};
        for(String n :nums){
            Car c = new Car(n);
            garage.add(c);
        }
    return garage;
    }


}
