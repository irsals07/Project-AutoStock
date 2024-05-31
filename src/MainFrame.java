import javax.swing.JFrame;
import java.security.Key;

public class MainFrame extends JFrame implements Runnable {

    private DrawPanel p;
    int FPS = 60;
    private Thread windowThread;
    KeyHandler kh = new KeyHandler();

    public MainFrame(String display) {
        super(display);
        int frameWidth = 1500;
        int frameHeight = 600;
        p = new DrawPanel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(600, 100);
        this.setVisible(true);
        this.addKeyListener(kh);
        startThread();

    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (windowThread != null) {


            update();
            p.repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime< 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(kh.go == true){

        }
    }



}