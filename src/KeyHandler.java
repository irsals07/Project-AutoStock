import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener{

    public static boolean go;
    public static boolean shift;



    /**
     * Handle the key typed event from the text field.saw
     *

    /** Handle the key-pressed event from the text field. */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            shift = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode() + " " + KeyEvent.VK_W);
        if (e.getKeyCode() == KeyEvent.VK_W) {
            go = true;
            //System.out.println("hello");
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            go = false;
        }
    }


}
