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


    public DrawPanel() {
        button = new Rectangle(200, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
        gameState = 1;
    }


    protected void paintComponent(Graphics g) {
        if(gameState == 1){
            menuScreen(g);
        }
        else{
            garageScreen(g);
        }
    }
    protected void menuScreen(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        Background bg = new Background("images/bg1.png");
        g.drawImage(bg.getImage(), x,y, (int)bg.getImage().getWidth(), bg.getImage().getHeight(), null);

//        g.setFont(new Font("Courier New", Font.BOLD, 20));
//        g.drawString("GET NEW CARDS", 150, 400);
//        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        b = new Button("PLAY", "buttons/button1.png", 450, 380);
        b.draw(g);
    }
    protected void garageScreen(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        Background bg = new Background("images/bg2.png");
        g.drawImage(bg.getImage(), x,y, bg.getImage().getWidth(), bg.getImage().getHeight(), null);


    }


    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();
        if(e.getButton() == 1){
            if(b.contains(clicked)){
                gameState = 2;
            }
        }


    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}