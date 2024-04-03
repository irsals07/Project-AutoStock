import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;


class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Car> garage;
    private Rectangle button;


    public DrawPanel() {
        button = new Rectangle(147, 380, 160, 26);
        this.addMouseListener(this);
        garage = Car.buildGarage();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        for(int i = 0; i<garage.size(); i++){
            Car c = garage.get(i);
            g.drawImage(c.getImage(), x, y, null);
            x+=300;
            if(i==2||i==5){
                y+=100;
                x=50;
            }
        }

        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());

    }


    public void mousePressed(MouseEvent e) {



    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}