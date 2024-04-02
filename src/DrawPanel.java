import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;


class DrawPanel extends JPanel implements MouseListener {


    private Rectangle button;


    public DrawPanel() {
        button = new Rectangle(147, 380, 160, 26);
        this.addMouseListener(this);
    }


    protected void paintComponent(Graphics g) {

    }


    public void mousePressed(MouseEvent e) {



    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}