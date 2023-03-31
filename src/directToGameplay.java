import javax.swing.*;

public class directToGameplay {
    static ImageIcon ballIcon = new ImageIcon("D:\\Project\\Java\\Inteliji\\BricksBreaker\\src\\ball.png");
    directToGameplay(){
        JFrame frame = new JFrame();

        GamePlay gamePlay = new GamePlay();

        frame.setIconImage(ballIcon.getImage());
        frame.setBounds(50,20,700,600);
        frame.setResizable(false);
        frame.setTitle("Bricks Breaker");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //to appear window in centre
        frame.setVisible(true);

//        frame.add(label);
//        progressBar();

        frame.add(gamePlay);
    }
}
