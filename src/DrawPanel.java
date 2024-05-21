import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


class DrawPanel extends JPanel implements MouseListener {
    int count = 0;    private KeyHandler keyHandler = new KeyHandler();;
    private ArrayList<Car> garage;
    private Rectangle button;
    private Button mainButton;
    private int gameState;
    private Button b;
    private Button c;
    private Button next;
    private Button back;
    private Button select;
    private Button play;
    private int currentCar = 0;
    private Car selectedCar;
    private Car opponentCar;

    private Rectangle miniPlayer;
    private Rectangle miniOpponent;
    private Rectangle miniTrack;

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

    private Background track;

    private int miniY =450;
    private int miniY2 = 450;





    public DrawPanel() {
        button = new Rectangle(200, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
        gameState = 1;
        b = new Button("PLAY", "buttons/button1.png", 420, 380);
        next = new Button("next", "buttons/right.png", 800, 250);
        back = new Button("left", "buttons/left.png", 100, 250);
        select = new Button("select", "buttons/select.png", 750, 450);
        play = new Button("play", "buttons/button1.png", 700, 420);
//       keyHandler = new KeyHandler();
        addKeyListener(this.keyHandler);
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
        finalOverall = (finalAcceleration+finalAero+finalSpeed+finalEngine)/4;
        show1 = false;
        show2 = false;
        show3 = false;
        show4 = false;
        show5 = false;

        track = new Background("game_images/track.jpg");

        this.setFocusable(true);

        opponentCar = garage.get((int)(Math.random()*9));

    }


    protected void paintComponent(Graphics g) {
        if(gameState == 1){
            menuScreen(g);
            b.setClickable(true);
        }
        else if(gameState==2){
            garageScreen(g);
            b.setClickable(false);
            select.setClickable(true);
            next.setClickable(true);
            back.setClickable(true);
        }
        else if(gameState == 3){
            statsScreen(g);
            select.setClickable(false);
            next.setClickable(false);
            back.setClickable(false);
            stop1.setClickable(true);
            stop2.setClickable(true);
            stop3.setClickable(true);
            stop4.setClickable(true);
            play.setClickable(true);
        }
        else if(gameState == 4){
            raceScreen(g);
        }
        this.addKeyListener(keyHandler);
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
        Background bg = new Background("images/bg1.png");
        g.drawImage(bg.getImage(), x,y, bg.getImage().getWidth(), bg.getImage().getHeight(), null);

        //Display Buttons
        next.draw(g);
        back.draw(g);
        select.draw(g);
        Car c = garage.get(currentCar);
        g.drawImage(c.getImage(), 300, 350, null);
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


        g.drawImage(selectedCar.getImage(), 100, 180, null);

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

            //setSpeed
            selectedCar.setAcceleration(finalAcceleration);
        }
        if(show2){
            g.drawRect(700, 160, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 160, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Speed: " + finalSpeed, 700, 190);

            //setSpeed
            selectedCar.setTopSpeed(finalSpeed);
        }
        if(show3){
            g.drawRect(700, 220, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 220, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Engine: " + finalEngine, 700, 250);

            //set Engine
            selectedCar.setEngine(engine);
        }
        if(show4){
            g.drawRect(700, 280, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 280, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Aerodynamics: " + finalAero, 700, 310);

            //set aero
            selectedCar.setTopSpeed(finalAero);
        }
        if(show5){
            g.drawRect(700, 340, 200, 40);
            g.setColor(Color.white);
            g.fillRect(700, 340, 200, 40);
            g.setColor(Color.black);
            g.drawString(" Overall: " + finalOverall, 700, 370);
            play.setClickable(true);
            play.draw(g);

            //setSpeed
            selectedCar.setOverall(finalOverall);

        }


        // Shift time deciding
            // Overall: 0-10 ---> 12s
            // Overall: 11-20 ---> 11s
            // Overall: 21-30 ---> 10s
            // Overall: 31-40 ---> 9s
            // Overall: 41-50 ---> 8s
            // Overall: 51-60 ---> 7s
            // Overall: 61-70 ---> 6s
            // Overall: 71-80 ---> 5s
            // Overall: 81-90 ---> 4s
            // Overall: 91-100 ---> 3s
        if(overall > 0 && overall < 10){
            selectedCar.setShiftTime(12);
        }
        else if(overall > 11 && overall < 20){
            selectedCar.setShiftTime(11);
        }
        else if(overall > 21 && overall < 30){
            selectedCar.setShiftTime(10);
        }
        else if(overall > 31 && overall < 40){
            selectedCar.setShiftTime(9);
        }
        else if(overall > 41 && overall < 50){
            selectedCar.setShiftTime(8);
        }
        else if(overall > 51 && overall < 60){
            selectedCar.setShiftTime(7);
        }
        else if(overall > 61 && overall < 70){
            selectedCar.setShiftTime(6);
        }
        else if(overall > 71 && overall < 80){
            selectedCar.setShiftTime(5);
        }
        else if(overall > 81 && overall < 90){
            selectedCar.setShiftTime(4);
        }
        else if(overall > 91 && overall < 100){
            selectedCar.setShiftTime(3);
        }

        if(show1==true &&show2==true &&show3==true &&show4==true){
            stop5.setVisible(true);
            stop5.setClickable(true);
        }

        if(gameState!=3){
            removeAll();
        }
    }
    protected void raceScreen(Graphics g){
        super.paintComponent(g);

        if(keyHandler.go == true){
            track.setTrackY(track.trackY+10);
            track.drawTrack(g);
            miniY--;
            miniY2--;
        }
        if(keyHandler.go == false){
            track.drawTrack(g);
        }
        selectedCar.setTopWidth(245);
        selectedCar.setTopHeight(349.3);
        selectedCar.drawTopView(g);
        selectedCar.setY(200);
        selectedCar.setX(230);
        opponentCar.setTopWidth(245);
        opponentCar.setTopHeight(349.3);
        opponentCar.drawTopView(g);
        opponentCar.setY(200);
        opponentCar.setX(530);




        g.setColor(Color.gray);
        g.drawRect(900, 100, 50, 350);
        g.fillRect(900,100, 50, 350);
        g.setColor(Color.red);
        g.drawRect(900, miniY, 25, 25);
        g.fillRect(900, miniY, 25, 25);
        g.setColor(Color.green);
        g.drawRect(925,  miniY2, 25, 25);
        g.fillRect(925, miniY2, 25, 25);

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
            }
            if(stop5.contains(clicked)){
                show5 = true;
            }
            if(play.contains(clicked)){
                gameState = 4;
            }
        }
    }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

}

