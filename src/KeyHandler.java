import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private boolean left = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Handle the key typed event from the text field.
     *

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A){
            left = true;
            System.out.println("hello");
        }

    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {

    }

    public boolean getLeft() {
        return left;
    }
}
