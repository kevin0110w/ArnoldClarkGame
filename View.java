/**
This class is responsible for the view for the GUI version of the Game
It creates an instance of the GameLogic class which is passed as a parameter to the controller class to coordinate events
Author - kevin Wu
**/

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
  /**
  This method is responsible for laying out the components on the GUI and creating an ActionListener
  @Param user interface components are added to the JFrame object component container
  **/
  public void setup(Container container) {
    container.setLayout(new GridLayout(5,1)); // 5 rows, 1 column layout
    JPanel roundPanel = new JPanel(); // panel used to hold the reset and quit buttons and textfield displaying the round number
    JButton reset = new JButton("Reset"); // reset button
    JTextField round = new JTextField(10); // textfield displaying round number
    round.setHorizontalAlignment(JTextField.CENTER); // centre the text in the JTextField
    round.setText("Round No");
    round.setEditable(false); // make the JTextField uneditable
    JButton quit = new JButton("Quit"); // quit button
    roundPanel.add(reset);
    roundPanel.add(round);
    roundPanel.add(quit);
    JPanel scorePanel = new JPanel(); // panel used to hold the scores
    JLabel humLabel = new JLabel("Human"); // label for human
    JTextField score = new JTextField(10); // textfield displaying the scores
    score.setHorizontalAlignment(JTextField.CENTER); // centre the score text
    score.setText("" + logic.getHumanWins() + ":" + logic.getComputerWins()); // display the scores by using the getters from the model, gamelogic class
    score.setEditable(false); // make the textfield uneditable
    JLabel aiLabel = new JLabel("AI"); // label for the AI
    scorePanel.add(humLabel);
    scorePanel.add(score);
    scorePanel.add(aiLabel);
    JPanel outputPanel = new JPanel(); // new Jpanel to store the output message displaying the outcome of a round
    Font font1 = new Font("SansSerif", Font.BOLD, 15); // display the message in a larger, bolder format
    JTextField output = new JTextField(50); // text field to display the output
    output.setFont(font1);
    output.setHorizontalAlignment(JTextField.CENTER); // centre the output
    output.setEditable(false); // make the textfield uneditable
    outputPanel.add(output);
    JPanel buttonPanel = new JPanel(); // panel to store the playable options
    JButton rock = new JButton("Rock"); // buttons for rock, scissors, paper, lizard and spock
    JButton scissors = new JButton("Scissors");
    JButton paper = new JButton("Paper");
    JButton lizard = new JButton("Lizard");
    JButton spock = new JButton("Spock");
    buttonPanel.add(rock);
    buttonPanel.add(paper);
    buttonPanel.add(scissors);
    buttonPanel.add(lizard);
    buttonPanel.add(spock);
    ButtonListener listener = new ButtonListener(logic, reset, round, quit, score, output, rock, paper, scissors, lizard, spock); // create an instance of the
    // the buttonlistener class and pass a list of components that are clickable or are to be updated, along with an instance of the GameLogic class
    rock.addActionListener(listener); // add the listener to the buttons
    paper.addActionListener(listener);
    scissors.addActionListener(listener);
    lizard.addActionListener(listener);
    spock.addActionListener(listener);
    reset.addActionListener(listener);
    quit.addActionListener(listener);
    container.add(roundPanel);
    container.add(scorePanel);
    container.add(outputPanel);
    container.add(buttonPanel);
  }

  public JFrame getFrame() {
    return frame;
  }
}
