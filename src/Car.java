import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Car {
    private int topSpeed;
    private String num;
    private String imageFileName;

    private boolean show;
    private BufferedImage image;
    private Rectangle carBox;
    private boolean highlight;
    private int acceleration;
    private int engine;
    private int aero;
    private int shiftTime;
    private int overall;

    public Car(String num){
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.imageFileName = "images/car_" + num +".png";
        this.show = true;
        this.image = readImage();
        this.carBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.highlight = false;
    }
    public String getImageFileName() {
        return imageFileName;
    }
    public void setRectangleLocation(int x, int y) {
        carBox.setLocation(x, y);
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

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public void setAero(int aero) {
        this.aero = aero;
    }

    public void setShiftTime(int shiftTime) {
        this.shiftTime = shiftTime;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }


    public int getTopSpeed() {
        return topSpeed;
    }

    public String getNum() {
        return num;
    }

    public boolean isShow() {
        return show;
    }

    public Rectangle getCarBox() {
        return carBox;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getEngine() {
        return engine;
    }

    public int getAero() {
        return aero;
    }

    public int getShiftTime() {
        return shiftTime;
    }

    public int getOverall() {
        return overall;
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
