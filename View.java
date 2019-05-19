import java.awt.*;
import javax.swing.*;
import java.awt.Font;

public class View implements Runnable {

  private JFrame frame;
  private GameLogic logic;

  public View() {
    logic = new GameLogic();
  }

  @Override
  public void run() {
    frame = new JFrame("Arnold Clark Game");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setup(frame.getContentPane());
    frame.pack();
    frame.setVisible(true);
  }

  public void setup(Container container) {
    container.setLayout(new GridLayout(4,1));
    JPanel topPanel1 = new JPanel();
    JTextArea round = new JTextArea();
    topPanel1.add(round);
    JPanel topPanel = new JPanel();
    JLabel humLabel = new JLabel("Human");
    JTextField score = new JTextField(10);
    score.setHorizontalAlignment(JTextField.CENTER);
    score.setText("" + logic.getHumanWins() + ":" + logic.getComputerWins());
    JLabel aiLabel = new JLabel("AI");
    topPanel.add(humLabel);
    topPanel.add(score);
    topPanel.add(aiLabel);
    JPanel messPanel = new JPanel();
    Font font1 = new Font("SansSerif", Font.BOLD, 15);
    JTextField output = new JTextField(50);
    output.setFont(font1);
    output.setHorizontalAlignment(JTextField.CENTER);
    messPanel.add(output);
    JPanel buttonPanel = new JPanel();
    JButton rock = new JButton("Rock");
    JButton scissors = new JButton("Scissors");
    JButton paper = new JButton("Paper");
    JButton lizard = new JButton("Lizard");
    JButton spock = new JButton("Spock");
    buttonPanel.add(rock);
    buttonPanel.add(paper);
    buttonPanel.add(scissors);
    buttonPanel.add(lizard);
    buttonPanel.add(spock);
    ButtonListener b = new ButtonListener(logic, round, score, output, rock, paper, scissors, lizard, spock);
    rock.addActionListener(b);
    paper.addActionListener(b);
    scissors.addActionListener(b);
    lizard.addActionListener(b);
    spock.addActionListener(b);
    container.add(topPanel1);
    container.add(topPanel);
    container.add(messPanel);
    container.add(buttonPanel);
  }

  public JFrame getFrame() {
    return frame;
  }
}
