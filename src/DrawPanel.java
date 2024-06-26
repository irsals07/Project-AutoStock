import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;


class DrawPanel extends JPanel implements MouseListener {
    int count = 0;
    public Car winner;
    private KeyHandler keyHandler = new KeyHandler();;
    private ArrayList<Car> garage;
    private ArrayList<Pin> pins;
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

    private int pin = 0;
    private int frames = 0;
    private int selectedCarX = 190;
    private int selectedCarY = 200;
    private int opponentOverall = (int)(Math.random()*91)+1;

    private int car1X = 0;
    private int car2X = 0;

    private long startTime = 0;
    private long endTime = 0;
    private double sec;




    public DrawPanel() {
        button = new Rectangle(200, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
        pins = Pin.showPin();
        gameState = 1;
        b = new Button("PLAY", "image_directory/buttons/button1.png", 420, 380);
        next = new Button("next", "image_directory/buttons/right.png", 800, 250);
        back = new Button("left", "image_directory/buttons/left.png", 100, 250);
        select = new Button("select", "image_directory/buttons/select.png", 750, 450);
        play = new Button("play", "image_directory/buttons/button1.png", 700, 420);
//       keyHandler = new KeyHandler();
        addKeyListener(this.keyHandler);
        stop1 = new Button("stop1", "image_directory/buttons/stop.png", 550, 100);
        stop1.scale(0.8);
        stop2 = new Button("stop2", "image_directory/buttons/stop.png", 550, 160);
        stop2.scale(0.8);
        stop3 = new Button("stop3", "image_directory/buttons/stop.png", 550, 220);
        stop3.scale(0.8);
        stop4 = new Button("stop4", "image_directory/buttons/stop.png", 550, 280);
        stop4.scale(0.8);
        stop5 = new Button("stop5", "image_directory/buttons/stop.png", 550, 340);
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

        track = new Background("image_directory/game_images/track.jpg");

        this.setFocusable(true);

        opponentCar = garage.get((int)(Math.random()*8));



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
        else if(gameState == 5){
            try {
                winnerScreen(g);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.addKeyListener(keyHandler);
    }
    protected void menuScreen(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        Background bg = new Background("image_directory/images/bg1.png");
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
        Background bg = new Background("image_directory/images/bg1.png");
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


        for(int i = 0; i<pins.size(); i++){
            pins.get(i).scale(.8);
        }
        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.drawString("Frames: " + frames, 1500, 700);
        if(shift(finalOverall)){
            g.drawRect(1600, 820, 250, 30);
            g.setColor(Color.green);
            g.fillRect(1600, 820, 250, 30);
        }
        else{
            g.drawRect(1600, 850, 200, 30);
            g.setColor(Color.red);
            g.fillRect(1600, 850, 200, 30);
        }

        g.drawString("Your Overall: " + finalOverall, 1500, 750);
        g.drawString("Opponent Overall: " + opponentOverall, 1500, 800);


        //SHIFTING PROCESS
        if(keyHandler.shift == true && shift(finalOverall)){
            selectedCarY = selectedCarY - 5;
            miniY++;
            pin=0;
        }
        else if(keyHandler.shift == true && !shift(finalOverall)){
            selectedCarY = selectedCarY + 3;
            miniY--;
        }
//opponent shift
        if(shift(opponentOverall)){
            selectedCarY = selectedCarY +2;
            miniY2++;
        }

        if(keyHandler.go == true){
            track.setTrackY(track.trackY+10);
            track.drawTrack(g);
            miniY--;
            miniY2--;
            if(frames ==1){
                startTime = System.currentTimeMillis();
            }
            if(frames == 300){
                endTime = System.currentTimeMillis();
            }


            frames++;
            if(frames==360){
                sec = (endTime - startTime) / 1000F;
                //time adjustments
//                if((selectedCarY - opponentCar.getY()) > 1000){
//                    sec+=1;
//                }
//                else if((selectedCarY - opponentCar.getY()) > 700){
//                    sec+=.7;
//                }
//                else if((selectedCarY - opponentCar.getY()) > 500){
//                    sec+=.5;
//                }
//                else if((selectedCarY - opponentCar.getY()) > 700){
//                    sec+=.2;
//                }

                String t = "\nTime: " + sec + " seconds | ";
                if(selectedCar.getY() < opponentCar.getY()){
                    winner = selectedCar;
                    t+="Player";
                }
                else{
                    winner = opponentCar;
                    t+="Opponent";
                }
                try {
                    Files.write(Paths.get("src/leaderboard"), t.getBytes(), StandardOpenOption.APPEND);
                }catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
                gameState = 5;



            }
            //System.out.println(pin);
            if(frames%10 == 0){
                pin++;
                Pin.drawX -= 8;
                Pin.drawY -= 3;
            }
            if(frames % 200 == 0){
                pin = 0;
                Pin.drawX = 1490;
                Pin.drawY = 170;
            }
        }
        if(keyHandler.go == false){
            track.drawTrack(g);

        }
        selectedCar.setTopWidth(245);
        selectedCar.setTopHeight(349.3);
        selectedCar.setY(selectedCarY);
        selectedCar.setX(selectedCarX);
        selectedCar.drawTopView(g);
        opponentCar.setTopWidth(245);
        opponentCar.setTopHeight(349.3);
        opponentCar.setY(200);
        opponentCar.setX(530);
        opponentCar.drawTopView(g);



        pins.get(pin).draw(g);
        //System.out.println("X: " + pins.get(pin).getX() + "Y: " + pins.get(pin).getY());



        g.setColor(Color.gray);
        g.drawRect(930, 100, 50, 350);
        g.fillRect(930,100, 50, 350);
        g.setColor(Color.red);
        g.drawRect(930, miniY, 25, 25);
        g.fillRect(930, miniY, 25, 25);
        g.setColor(Color.green);
        g.drawRect(955,  miniY2, 25, 25);
        g.fillRect(955, miniY2, 25, 25);

        if(gameState!=4){
            removeAll();
        }
    }
    protected void winnerScreen(Graphics g) throws IOException {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        g.drawRect(1250, 130, 600, 700);
        Background bg = new Background("image_directory/game_images/winner.jpg");
        g.drawImage(bg.getImage(), x,y, bg.getImage().getWidth(), bg.getImage().getHeight(), null);

        if(winner == selectedCar){
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawString("You Win!", 1000, 85);
        }
        else {
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawString("You Lost!", 1000, 85);
        }

        //Display leaderboard
        int ly = 140;
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("LeaderBoard", 1450, 120);
        g.drawString("____________", 1500, 125);
        File f = null;
        try {
            f = new File("src/leaderboard");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                g.drawString(currentLine, 1300, ly+=35);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }



        g.drawImage(winner.getImage(), 300, 380, null);
        if(gameState!=5){
            removeAll();
        }
    }

    public boolean shift(int finalOverall){
        if(finalOverall <= 20){
            return ((frames > 250 && frames<300));
        }
        else if(finalOverall > 20 && finalOverall <= 50){
            return (frames>200 && frames < 250) || (frames>350 && frames< 400);
        }
        else if(finalOverall > 51 && finalOverall <= 70){
            return (frames>150 && frames < 200) || (frames>250 && frames<300) || (frames>350 && frames<400);
        }
        else if(finalOverall > 70 && finalOverall < 90){
            return (frames>50 && frames<100) || (frames>150 && frames<200) ||(frames>250 && frames<300) ||(frames>350 && frames<400);
        }
        else{
            return true;
        }
    }
    public String shiftTimes(int finalOverall){
        if(finalOverall <= 20){
            return "250 - 300 frames";
        }
        else if(finalOverall > 20 && finalOverall <= 50){
            return "200-250 frames";
        }
        else if(finalOverall > 51 && finalOverall <= 70){
            return "150-200 frames or 250-300 frames";
        }
        else if(finalOverall > 70 && finalOverall < 90){
            return "50-100, 150-200, or 250-300 frames";
        }
        else{
            return "whenever";
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

