import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GameLogic {
  private int roundNumber, humanWins, computerWins;
  private List<String> commands;
  private Scanner s;
  private Random rand;

  public GameLogic() {
    this.commands = new ArrayList<String>();
    setComputerChoices();
    this.rand = new Random();
    this.s = new Scanner(System.in);
    this.roundNumber = 0;
    this.humanWins = 0;
    this.computerWins = 0;
  }

  public int getRoundNumber() {
    return this.roundNumber;
  }
  public int getHumanWins() {
    return this.humanWins;
  }

  public int getComputerWins() {
    return this.computerWins;
  }

  public void setComputerChoices() {
    this.commands.add("ROCK");
    this.commands.add("PAPER");
    this.commands.add("SCISSORS");
    this.commands.add("LIZARD");
    this.commands.add("SPOCK");
  }

  public List<String> getCommands() {
    return this.commands;
  }

  public void play() {
    System.out.println("Welcome to the Arnold Clark's game!");
    String s = "";
    for (int i = 0; i < this.getCommands().size(); i++) {
      s = s + this.getCommands().get(i) + " ";
    }
    String humanCommand;
    do {
      System.out.println("Please choose your selection from the following: " + s);
      System.out.println("Or type 'quit' to exit");
      humanCommand = this.s.nextLine().toUpperCase();
      if (humanCommand.equals("QUIT")) {
        break;
      }
      String aiCommand = getAIChoice();
      Selection selection = new Selection(humanCommand, aiCommand);
      decide_winner(selection);
      printSummary();
    } while (!(humanCommand.equals("QUIT")));
    System.out.println("Final score");
    printSummary();
  }

  public String getAIChoice() {
    int ai = this.rand.nextInt(5);
    String aiCommand = this.commands.get(0);
    return aiCommand;
  }

  public String decide_winner(Selection selection) {
    this.roundNumber++;
    String result = "This round ended in a draw!";
    String humanCommand = selection.getHuman();
    String aiCommand = selection.getComputer().toUpperCase();
    switch (humanCommand) {
      case "ROCK":
      if (aiCommand.equals("PAPER")) {
        result = "Paper wraps Rock - AI wins this round";
        this.computerWins++;
      } else if (aiCommand.equals("LIZARD")) {
        result = "Rock crushes Lizard - Human wins this round";
        this.humanWins++;
      } else if (aiCommand.equals("SPOCK")) {
        result = "Spock vaporizes Rock - AI wins this round";
        this.computerWins++;
      } else if (aiCommand.equals("SCISSORS")) {
        result = "Rock crushes Scissors - Human wins this round";
        this.humanWins++;
      }
      break;
      case "PAPER":
      if (aiCommand.equals("SCISSORS")) {
        result = "Scissors cut Papers - AI wins this round";
        this.computerWins++;
      } else if (aiCommand.equals("ROCK")) {
        result = "Paper wraps Rock - Human wins this round";
        this.humanWins++;
      } else if (aiCommand.equals("LIZARD")) {
        result = "Lizard eats Paper - AI wins this round";
        this.computerWins++;
      } else if (aiCommand.equals("SPOCK")) {
        result = "Paper disproves Spock - Human wins this round";
        this.humanWins++;
      }
      break;
      case "SCISSORS":
      if (aiCommand.equals("PAPER")) {
        result = "Scissors cut Paper - Human wins this round";
        this.humanWins++;

      } else if (aiCommand.equals("SPOCK")) {
        result = "Spock smashes Scissors - AI wins this round";
        this.computerWins++;

      } else if (aiCommand.equals("LIZARD")) {
        result = "Scissors decapitates Lizard - Human wins this round";
        this.humanWins++;

      } else if (aiCommand.equals("ROCK")) {
        result = "Rock crushes Scissors - AI wins this round";
        this.computerWins++;

      }
      break;
      case "LIZARD":
      if (aiCommand.equals("ROCK")) {
        result = "Rock crushes Lizard - AI wins this round";
        this.computerWins++;

      } else if (aiCommand.equals("SPOCK")) {
        result = "Lizard poisons Spock - Human wins this round";
        this.humanWins++;

      } else if (aiCommand.equals("SCISSORS")) {
        result = "Scissors decapitates Lizard - AI wins this round";
        this.computerWins++;

      } else if (aiCommand.equals("PAPER")) {
        result = "Lizard eats Paper - Human wins this round";
        this.humanWins++;

      }
      break;
      case "SPOCK":
      if (aiCommand.equals("LIZARD")) {
        result = "Lizard poisons Spock - AI wins this round";
        this.computerWins++;

      } else if (aiCommand.equals("SCISSORS")) {
        result = "Spock smashes Scissors - Human wins this round";
        this.humanWins++;

      } else if (aiCommand.equals("PAPER")) {
        result = "Paper disproves Spock - AI wins this round";
        this.computerWins++;

      } else if (aiCommand.equals("ROCK")) {
        result = "Spock vaporizes Rock - Human wins this round";
        this.humanWins++;

      }
      break;
    }
    System.out.println(result);
    return result;
  }

  public void printSummary() {
    System.out.println("Rounds played: " + this.roundNumber);
    System.out.println("Human wins: " + this.humanWins);
    System.out.println("AI wins: " + this.computerWins);
  }
}
