import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

    public static String Rock = "Rock";
    public static String Scissors = "Scissors";
    public static String Paper = "Paper";

    private JFrame frame;

    private JLabel topLabel, secondLabel;

    private JLabel rockLabel, paperLabel, scissorsLabel, chooseLabel, computerLabel, waitLabel;

    private JButton playButton, startBtn;

    private JRadioButton rockButton, paperButton, scissorsButton;

    private JPanel homePanel, gamePanel;

    private CardLayout cardLayout;

    private Container container;

    public static String selectedImage = "Rock";

    private App() throws IOException {
        frame = new JFrame();
        frame.setTitle("Rock, Paper & Scissor Game");
        frame.setSize(500, 500);

        // create layout
        cardLayout = new CardLayout();

        // container
        container = frame.getContentPane();

        // set layout on container
        container.setLayout(cardLayout);

        // create homePanel and gamePanel
        homePanel = new JPanel();
        gamePanel = new JPanel();

        // set bound on panels
        homePanel.setBounds(0, 0, 500, 500);
        gamePanel.setBounds(0, 0, 500, 500);
        homePanel.setLayout(null);
        gamePanel.setLayout(null);

        // set up home panel
        setUpHomePanel();

        // set up game panel
        setUpGamePanel();

        container.add(homePanel);
        container.add(gamePanel);
        cardLayout.first(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setUpGamePanel() throws IOException {

        // top level
        topLabel = new JLabel("WelCome to Rock, Paper & Scissor Game.");
        topLabel.setBounds(80, 10, 500, 40);
        topLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topLabel.setForeground(Color.decode("#990000"));
        gamePanel.add(topLabel);

        // second Level
        secondLabel = new JLabel("You Choose.");
        secondLabel.setBounds(180, 50, 150, 40);
        secondLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        secondLabel.setForeground(Color.decode("#009900"));
        gamePanel.add(secondLabel);

        // show choose image
        chooseLabel = new JLabel(getImage(App.selectedImage));
        chooseLabel.setBounds(180, 100, 100, 100);
        gamePanel.add(chooseLabel);

        // create start btn
        startBtn = new JButton("Start");
        startBtn.setBounds(180, 220, 100, 40);
        gamePanel.add(startBtn);

        // create wait label
        waitLabel = new JLabel("Please Wait....");
        waitLabel.setBounds(160, 220, 280, 40);
        gamePanel.add(waitLabel);
        waitLabel.setBackground(Color.BLUE);
        waitLabel.setVisible(false);

        // create Random Number
        Random randomNum = new Random();

        // create choose array
        String choose[] = { "Rock", "Paper", "Scissors" };

        computerLabel = new JLabel(getImage(choose[0]));
        computerLabel.setBounds(180, 270, 100, 100);
        computerLabel.setVisible(false);
        gamePanel.add(computerLabel);
        Runnable visibilityRun =  new Runnable() {
            @Override
            public void run() {
                waitLabel.setVisible(true);
                startBtn.setVisible(false);
            }
        };
        // set action on start btn
        startBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              
                visibilityRun.run();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        int computerSelectedImage = randomNum.nextInt(3);
                        // create computer choose image
                        waitLabel.setText("Computer is Choose " + choose[computerSelectedImage]);
                        try {
                            computerLabel.setIcon(getImage(choose[computerSelectedImage]));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        computerLabel.setVisible(true);
                        Thread winningShow = new Thread(() -> checkGame(choose[computerSelectedImage]));

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        winningShow.start();
                    }

                };

                Thread thread = new Thread(runnable);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                thread.start();

            }

        });

    }

    private void checkGame(String str) {

        if (str.equals("Rock") && selectedImage.equals("Paper")) {
            JOptionPane.showMessageDialog(frame, "You are win", "Massage", JOptionPane.INFORMATION_MESSAGE);
        } else if (str.equals("Rock") && selectedImage.equals("Scissors")) {
            JOptionPane.showMessageDialog(frame, "Your Lose the game", "Massage", JOptionPane.INFORMATION_MESSAGE);
        } else if (str.equals("Scissors") && selectedImage.equals("Rock")) {
            JOptionPane.showMessageDialog(frame, "You are win", "Massage", JOptionPane.INFORMATION_MESSAGE);
        } else if (str.equals("Paper") && selectedImage.equals("Scissors")) {
            JOptionPane.showMessageDialog(frame, "You Lose the game", "Massage", JOptionPane.INFORMATION_MESSAGE);
        } else if (str.equals("Scissors") && selectedImage.equals("Paper")) {
            JOptionPane.showMessageDialog(frame, "You Lose the game", "Massage", JOptionPane.INFORMATION_MESSAGE);
        } else if (str.equals(selectedImage)) {
            JOptionPane.showMessageDialog(frame, "Match is tie", "Massage", JOptionPane.INFORMATION_MESSAGE);
        }
        cardLayout.previous(container);
        startBtn.setVisible(true);
        waitLabel.setVisible(false);

    }

    private ImageIcon getImage(String str) throws IOException {
        if (str.equals(App.Rock)) {
            File file = new File("icons/RockIcon.jpg");
            BufferedImage rockImage = ImageIO.read(file);
            return new ImageIcon(rockImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        } else if (str.equals(App.Paper)) {
            File file = new File("icons/PaperIcon.jpg");
            BufferedImage rockImage = ImageIO.read(file);
            return new ImageIcon(rockImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        } else {
            File file = new File("icons/Scissors.jpg");
            BufferedImage rockImage = ImageIO.read(file);
            return new ImageIcon(rockImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        }
    }

    private void setUpHomePanel() throws IOException {
        // top level
        topLabel = new JLabel("WelCome to Rock, Paper & Scissor Game.");
        topLabel.setBounds(80, 10, 500, 40);
        topLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topLabel.setForeground(Color.decode("#990000"));
        homePanel.add(topLabel);

        // second Level
        secondLabel = new JLabel("Choose one.");
        secondLabel.setBounds(180, 50, 150, 40);
        secondLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        secondLabel.setForeground(Color.decode("#009900"));
        homePanel.add(secondLabel);

        // load rock file
        rockLabel = new JLabel(getImage("Rock"));
        rockLabel.setBounds(80, 100, 100, 100);
        rockButton = new JRadioButton("Rock");
        rockButton.setBounds(100, 210, 100, 50);
        rockButton.setName(App.Rock);
        rockButton.addActionListener(e -> updateRadioButton(0, App.Rock));
        homePanel.add(rockButton);
        homePanel.add(rockLabel);

        // load paper file
        paperLabel = new JLabel(getImage("Paper"));
        paperLabel.setBounds(200, 100, 100, 100);
        paperButton = new JRadioButton("Paper");
        paperButton.setName(App.Paper);
        paperButton.setBounds(220, 210, 100, 50);
        paperButton.addActionListener(e -> updateRadioButton(1, App.Paper));
        homePanel.add(paperButton);
        homePanel.add(paperLabel);

        // load scissors file
        scissorsLabel = new JLabel(getImage("Scissors"));
        scissorsLabel.setBounds(320, 100, 100, 100);
        scissorsButton = new JRadioButton("Scissors");
        scissorsButton.setName(App.Scissors);
        scissorsButton.setBounds(340, 210, 100, 50);
        scissorsButton.addActionListener(e -> updateRadioButton(2, App.Scissors));
        homePanel.add(scissorsButton);
        homePanel.add(scissorsLabel);

        // play button
        playButton = new JButton("Play Game");
        playButton.setBounds(210, 270, 100, 40);
        homePanel.add(playButton);

        // set action on play button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(container);
                try {
                    chooseLabel.setIcon(getImage(selectedImage));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                computerLabel.setVisible(false);
            }
        });
    }

    private void updateRadioButton(int i, String item) {
        App.selectedImage = item;
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
