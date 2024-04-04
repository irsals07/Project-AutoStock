import java.awt.*;

public class Button {
    private String buttonName;
    private Rectangle button;
    private boolean visible;

    public Button(String name) {
        this.buttonName = name;
        button = new Rectangle(250, 380, 160, 26);
        this.visible = true;
    }

    public void draw(Graphics g)
    {
        if (visible){
            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString(buttonName, 150, 400);
            g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());    }
}}
