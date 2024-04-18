import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;


class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Car> garage;
    private Rectangle button;
    private Button mainButton;
    private int gameState;
    private Button b;
    private Button c;
    private Button next;
    private Button back;
    private Button select;
    private int currentCar = 0;
    private Car selectedCar;




    public DrawPanel() {
        button = new Rectangle(200, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
        gameState = 1;
        b = new Button("PLAY", "buttons/button1.png", 450, 380);
        next = new Button("next", "buttons/right.png", 800, 250);
        back = new Button("left", "buttons/left.png", 100, 250);
        select = new Button("select", "buttons/select.png", 450, 450);
    }


    protected void paintComponent(Graphics g) {
        if(gameState == 1){
            menuScreen(g);
        }
        else if(gameState==2){
            garageScreen(g);
        }
        else if(gameState == 3){
            statsScreen(g);
        }
    }
    protected void menuScreen(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        Background bg = new Background("images/bg1.png");
        g.drawImage(bg.getImage(), x,y, (int)bg.getImage().getWidth(), bg.getImage().getHeight(), null);
        b.draw(g);
        b.setRectangleLocation(b.getX(), b.getY());
        if(gameState!=1){
            removeAll();
        }
    }
    protected void garageScreen(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        Background bg = new Background("images/bg2.png");
        g.drawImage(bg.getImage(), x,y, bg.getImage().getWidth(), bg.getImage().getHeight(), null);

        //Display Buttons
        next.draw(g);
        back.draw(g);
        select.draw(g);
        Car c = garage.get(currentCar);
        g.drawImage(c.getImage(), 400, 250, null);
        if(gameState!=2){
            removeAll();
        }
    }
    protected void statsScreen(Graphics g){
        super.paintComponent(g);
        int x = 0; int y = 0;

        int acceleration = (int)(Math.random()*100);
        int topSpeed = (int)(Math.random()*100);
        int engine = (int)(Math.random()*100);
        int aero = (int)(Math.random()*100);
        int grip = (int)(Math.random()*100);
        int overall = (int)(acceleration+topSpeed+engine+aero+grip)/5;


        g.drawImage(selectedCar.getImage(), 150, 250, null);

        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.drawString("Your Stats", 700, 60);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("Acceleration: " + Integer.toString(acceleration), 700, 120);
        g.drawString("Top Speed: " + Integer.toString(topSpeed), 700, 180);
        g.drawString("Engine: " + Integer.toString(engine), 700, 240);
        g.drawString("Aerodynamics: " + Integer.toString(aero), 700, 300);
        g.drawString("Overall: " + Integer.toString(overall), 700, 360);

    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if(e.getButton() == 1){
            if(b.contains(clicked)){
                gameState = 2;
            }
            if(next.contains(clicked)){
                if(currentCar==7){
                    currentCar=-1;
                }
                currentCar++;

            }
            if(back.contains(clicked)){
                if(currentCar==0){
                    currentCar=8;
                }
                currentCar--;
            }
            if(select.contains(clicked)){
                selectedCar = garage.get(currentCar);
                gameState=3;
            }
        }


    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}