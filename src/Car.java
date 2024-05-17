import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Car {
    private int topSpeed;
    private String num;
    private String imageFileName;
    private String topImageName;

    private boolean show;
    private BufferedImage image;
    private BufferedImage topImage;
    private Rectangle carBox;
    private boolean highlight;
    private int acceleration;
    private int engine;
    private int aero;
    private int shiftTime;
    private int overall;
    private int x;
    private int y;
    private double width;
    private double height;
    private double topWidth;
    private double topHeight;

    public Car(String num){
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.imageFileName = "images/car_" + num +".png";
        this.topImageName = "cars/top_car" + num + ".png";
        this.show = true;
        this.image = readImage();
        this.topImage = readTopImage();
        this.carBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.highlight = false;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.topWidth = topImage.getWidth();
        this.topHeight = topImage.getHeight();
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
    public BufferedImage readTopImage() {
        try {
            BufferedImage topImage = null;
            if (show) {
                topImage = ImageIO.read(new File(topImageName));
            }

            return topImage;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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



    public void draw(Graphics g)
    {
        g.drawImage(image, getX(), getY(), (int)width, (int)height, null);
    }

    public void drawTopView(Graphics g){
        g.drawImage(topImage, getX(), getY(), (int)topWidth, (int)topHeight, null);
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

    public void scale(double s){
        width = image.getWidth()*s;
        height =image.getHeight()*s;
    }
    public void topScale(double s){
        topWidth = topImage.getWidth()*s;
        topHeight = topImage.getHeight()*s;
    }

    public void setTopWidth(double topWidth) {
        this.topWidth = topWidth;
    }

    public void setTopHeight(double topHeight) {
        this.topHeight = topHeight;
    }

    public double getTopWidth() {
        return topWidth;
    }

    public double getTopHeight() {
        return topHeight;
    }
}
