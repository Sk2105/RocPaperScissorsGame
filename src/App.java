import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;



/**
 * 
 * date -> 22-01-2024 13:10:38
 * 
 * @version 1.0
 * @author Sachin Kumar
 * 
 */

public class App {
    private JFrame frame;

    private JLabel topLabel, secondLabel;

    private JLabel rockLabel, paperLabel, scissorsLabel;

    private File rockFile, paperFile, scissorsFile;

    private JButton playButton;

    private JRadioButton rockButton, paperButton, scissorsButton;

    private App() throws IOException {
        frame = new JFrame();
        frame.setTitle("Rock, Paper & Scissor Game");
        frame.setSize(500, 400);

        // top level
        topLabel = new JLabel("WelCome to Rock, Paper & Scissor Game.");
        topLabel.setBounds(80, 10, 500, 40);
        topLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topLabel.setForeground(Color.decode("#990000"));
        frame.add(topLabel);

        // second Level
        secondLabel = new JLabel("Choose one.");
        secondLabel.setBounds(180, 50, 150, 40);
        secondLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        secondLabel.setForeground(Color.decode("#009900"));
        frame.add(secondLabel);

        // load rock file
        rockFile = new File("icons/RockIcon.jpg");
        BufferedImage rockImage = ImageIO.read(rockFile);
        rockLabel = new JLabel(new ImageIcon(rockImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        rockLabel.setBounds(80, 100, 100, 100);
        rockButton = new JRadioButton("Rock");
        rockButton.setBounds(100, 210, 100, 50);
        rockButton.addActionListener(e -> updateRadioButton(0));
        frame.add(rockButton);
        frame.add(rockLabel);

        // load paper file
        paperFile = new File("icons/PaperIcon.jpg");
        BufferedImage pImage = ImageIO.read(paperFile);
        paperLabel = new JLabel(new ImageIcon(pImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        paperLabel.setBounds(200, 100, 100, 100);
        paperButton = new JRadioButton("Paper");
        paperButton.setBounds(220, 210, 100, 50);
        paperButton.addActionListener(e -> updateRadioButton(1));
        frame.add(paperButton);
        frame.add(paperLabel);

        // load scissors file
        scissorsFile = new File("icons/Scissors.jpg");
        BufferedImage sImage = ImageIO.read(scissorsFile);
        scissorsLabel = new JLabel(new ImageIcon(sImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        scissorsLabel.setBounds(320, 100, 100, 100);
        scissorsButton = new JRadioButton("Scissors");
        scissorsButton.setBounds(340, 210, 100, 50);
        scissorsButton.addActionListener(e -> updateRadioButton(2));
        frame.add(scissorsButton);
        frame.add(scissorsLabel);

        // play button
        playButton = new JButton("Play Game");
        playButton.setBounds(210, 270, 100, 40);
        frame.add(playButton);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateRadioButton(int i) {
        JRadioButton button[] = { rockButton, paperButton, scissorsButton };
        for (JRadioButton btn : button) {
            if (button[i] != btn) {
                btn.setSelected(false);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new App();

    }
}
