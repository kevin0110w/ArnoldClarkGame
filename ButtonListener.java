import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ButtonListener implements ActionListener {
  private GameLogic logic;
  private JTextField score, output;
  private JTextArea round;
  private JButton rock, paper, scissors, lizard, spock;

public ButtonListener(GameLogic logic, JTextArea round, JTextField score, JTextField output, JButton rock, JButton paper, JButton scissors, JButton lizard, JButton spock) {
  this.logic = logic;
  this.round = round;
  this.score = score;
  this.output = output;
  this.rock = rock;
  this.paper = paper;
  this.scissors = scissors;
  this.lizard = lizard;
  this.spock = spock;
  }

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
    }
    Selection selection = new Selection(humanChoice, logic.getAIChoice());
    String result = logic.decide_winner(selection);
    updateResult(result);
    updateScore();
  }

  public void updateResult(String result) {
    output.setText(result);
  }

  public void updateScore() {
    score.setText("" + logic.getHumanWins() + ":" + logic.getComputerWins());
    round.setText("Round No: " + logic.getRoundNumber());
  }
}
