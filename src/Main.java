import javax.swing.*;
import java.awt.*;

public class Main {
    static JProgressBar bar;
    static JLabel label;
    static ImageIcon wallpaper = new ImageIcon("D:\\Project\\Java\\Inteliji\\BricksBreaker\\src\\saa.png");
    static ImageIcon ballIcon = new ImageIcon("D:\\Project\\Java\\Inteliji\\BricksBreaker\\src\\ball.png");
    public static void main(String[] args) {
        bar = new JProgressBar();


        label = new JLabel();
        label.setIcon(wallpaper);

        JFrame frame = new JFrame();

        frame.setIconImage(ballIcon.getImage());
        frame.setSize(700,600);
        frame.setResizable(false);
        frame.setTitle("Bricks Breaker");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //to appear window in centre
        frame.setVisible(true);

        frame.add(label);
        progressBar();
        frame.dispose();
        new directToGameplay();
    }
    private static void progressBar() {
        bar.setBounds(0, 557, 684, 10);
        bar.setBackground(new Color(20,20,20,1));
        bar.setForeground(new Color(255, 0, 0));
        //bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setFont(new Font("MV Boli", Font.PLAIN,7));
        label.add(bar);
        fill();
    }

    public static void fill() {
        int counter =0;
        while(counter <= 100) {
            bar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            counter += 1;
        }
        label.setVisible(false);
    }
}
