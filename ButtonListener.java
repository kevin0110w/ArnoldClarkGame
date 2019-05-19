/**
This class is responsible for acting as the 'controller' for the user interface version of the game
When a button on the gui is clicked, this an instance of this class will check the source and coordinate the correct actions between the model (GameLogic)
and the view (View)
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
* author - Kevin Wu
*/

public class ButtonListener implements ActionListener {
  private GameLogic logic;
  private JTextField score, output, round;
  private JButton rock, paper, scissors, lizard, spock, reset, quit;

public ButtonListener(GameLogic logic, JButton reset, JTextField round, JButton quit, JTextField score, JTextField output, JButton rock, JButton paper, JButton scissors, JButton lizard, JButton spock) {
  this.logic = logic;
  this.reset = reset;
  this.round = round;
  this.quit = quit;
  this.score = score;
  this.output = output;
  this.rock = rock;
  this.paper = paper;
  this.scissors = scissors;
  this.lizard = lizard;
  this.spock = spock;
  }
  /**
  Work out which button was pressed and perform the desired action
  **/
  @Override
  public void actionPerformed(ActionEvent e) {
    String humanChoice = "";
    if (e.getSource() == this.rock) {
      humanChoice = "ROCK";
    } else if (e.getSource() == this.paper) {
      humanChoice = "PAPER";
    } else if (e.getSource() == this.scissors) {
      humanChoice = "SCISSORS";
    } else if (e.getSource() == this.lizard) {
      humanChoice = "LIZARD";
    } else if (e.getSource() == this.spock) {
      humanChoice = "SPOCK";
    } else if (e.getSource() == this.reset) {
      logic.reset();  // reset the scores in the model
      updateScore(); // display the resetted scores in the gui
      clearOutput(); // clear the output field
      return;
    } else if (e.getSource() == this.quit) {
      System.exit(0); // terminate the program
    }
    Selection selection = new Selection(humanChoice, logic.getAIChoice()); // create a new instance of the selection class
    String result = logic.decide_winner(selection); // work out the winner by using the formula in the model
    updateResult(result); // update the results in the gui
    updateScore(); // update the results in the gui
  }
  /**
  This method is responsible for displaying the correct output of a round in the JTextField, as determined by logic.decide_winner();
  @Param result : the String that was returned by logic.decide_winner() stating the outcome of the round
  **/
  public void updateResult(String result) {
    output.setText(result);
  }

/**
This method is responsible for updating the round number and score JTextFields
**/
  public void updateScore() {
    score.setText("" + logic.getHumanWins() + ":" + logic.getComputerWins());
    round.setText("Round No: " + logic.getRoundNumber());
  }
/**
This method is responsible for clearing the JTextField used to show the outcome of a roundNumber
**/
  public void clearOutput() {
    output.setText("");
  }
}
