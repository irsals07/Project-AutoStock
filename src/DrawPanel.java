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

    private Button stop1;
    private Button stop2;
    private Button stop3;
    private Button stop4;
    private Button stop5;

    private int finalAcceleration;
    private int finalEngine;
    private int finalAero;
    private int finalSpeed;
    private int finalOverall;
    private int finalShiftTime;
    private boolean show1;
    private boolean show2;
    private boolean show3;
    private boolean show4;
    private boolean show5;





    public DrawPanel() {
        button = new Rectangle(200, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
        gameState = 1;
        b = new Button("PLAY", "buttons/button1.png", 450, 380);
        next = new Button("next", "buttons/right.png", 800, 250);
        back = new Button("left", "buttons/left.png", 100, 250);
        select = new Button("select", "buttons/select.png", 450, 450);

        stop1 = new Button("stop1", "buttons/stop.png", 550, 100);
        stop1.scale(0.8);
        stop2 = new Button("stop2", "buttons/stop.png", 550, 160);
        stop2.scale(0.8);
        stop3 = new Button("stop3", "buttons/stop.png", 550, 220);
        stop3.scale(0.8);
        stop4 = new Button("stop4", "buttons/stop.png", 550, 280);
        stop4.scale(0.8);
        stop5 = new Button("stop5", "buttons/stop.png", 550, 340);
        stop5.scale(0.8);
        stop5.setVisible(false);

        finalAcceleration = (int)(Math.random()*100);
        finalAero = (int)(Math.random()*100);
        finalEngine = (int)(Math.random()*100);
        finalSpeed = (int)(Math.random()*100);
        finalOverall = (finalAcceleration+finalAero+finalSpeed+finalEngine)/5;
        show1 = false;
        show2 = false;
        show3 = false;
        show4 = false;
        show5 = false;
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
        g.setColor(Color.black);

        int acceleration = (int)(Math.random()*100);
        int topSpeed = (int)(Math.random()*100);
        int engine = (int)(Math.random()*100);
        int aero = (int)(Math.random()*100);
        int grip = (int)(Math.random()*100);
        int overall = (acceleration+topSpeed+engine+aero+grip)/5;


        g.drawImage(selectedCar.getImage(), 150, 250, null);

        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.drawString("Your Stats", 700, 60);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("Acceleration: " + acceleration, 700, 120);
        g.drawString("Top Speed: " + (topSpeed), 700, 180);
        g.drawString("Engine: " + (engine), 700, 240);
        g.drawString("Aerodynamics: " + (aero), 700, 300);
        g.drawString("Overall: " + (overall), 700, 360);




        stop1.draw(g);
        stop2.draw(g);
        stop3.draw(g);
        stop4.draw(g);
        stop5.draw(g);


        if(show1){
            g.drawRect(700, 100, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 100, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Acceleration: " + finalAcceleration, 700, 130);
        }
        if(show2){
            g.drawRect(700, 160, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 160, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Speed: " + finalSpeed, 700, 190);
        }
        if(show3){
            g.drawRect(700, 220, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 220, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Engine: " + finalEngine, 700, 250);
        }
        if(show4){
            g.drawRect(700, 280, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 280, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Aerodynamics: " + finalAero, 700, 310);
        }
        if(show5){
            g.drawRect(700, 340, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 340, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Overall: " + finalOverall, 700, 370);
        }


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

            if(stop1.contains(clicked)){
                show1 = true;
            }
            if(stop2.contains(clicked)){
                show2 = true;
            }
            if(stop3.contains(clicked)){
                show3 = true;
            }
            if(stop4.contains(clicked)){
                show4 = true;
                stop5.setVisible(true);
            }
            if(stop5.contains(clicked)){
                show5 = true;

            }



        }


    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}