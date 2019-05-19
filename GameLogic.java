/**
This class is responsible for the logic for both command line and gui versions of the game.
It keeps track of the scores and determining the winner for each round
Author - Kevin Wu
**/
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GameLogic {
  private int roundNumber, humanWins, computerWins;
  private List<String> commands;
  private Random rand;

  public GameLogic() {
    this.commands = new ArrayList<String>(); // hold the commands in an ArrayList
    setComputerChoices(); // populate the ArrayList with the possible options
    this.rand = new Random(); // create an instance of the random class, used to determine AI choice
    this.roundNumber = 0; // set initial rounds to 0
    this.humanWins = 0; // set initial human wins to 0
    this.computerWins = 0; // set initial AI wins to 0
  }

  /**
  This method is responsible for resetting the scores
  **/
  public void reset() {
    this.roundNumber = 0;
    this.humanWins = 0;
    this.computerWins = 0;
  }

  /**
  This method returns the round number
  **/
  public int getRoundNumber() {
    return this.roundNumber;
  }

  /**
  This method returns the number of human wins
  **/
  public int getHumanWins() {
    return this.humanWins;
  }

  /**
  This method returns the number of computer wins
  **/
  public int getComputerWins() {
    return this.computerWins;
  }

  /**
  Populates the variable used to store the possible commands
  **/
  public void setComputerChoices() {
    this.commands.add("ROCK");
    this.commands.add("PAPER");
    this.commands.add("SCISSORS");
    this.commands.add("LIZARD");
    this.commands.add("SPOCK");
  }

  /**
  Return the list of commands
  **/
  public List<String> getCommands() {
    return this.commands;
  }

  /**
  A method that loops the game endlessly until it is terminated via an input or closing the program
  **/
  public void play() {
    System.out.println("Welcome to the Arnold Clark's game!");
    String humanCommand;
    do {
      printSelection();  // print the selection choice
      humanCommand = getHumanChoice(); // read a valid human's input and assign it to a variable
      if (humanCommand.equals("QUIT")) { // terminate if the human enters quit
        break;
      }
      String aiCommand = getAIChoice(); // get a random choice and assign it to a variable
      Selection selection = new Selection(humanCommand, aiCommand); // create an instance of the Selection class to hold the choices for this round
      decide_winner(selection); // method to decide who wins
      printSummary(); // print the round scores to console
    } while (true); // keep looping unless the user enters quit and the program terminates early
    System.out.println();
    System.out.println("Final score");
    printSummary(); // print final scores
  }
  /**
  This method is responsible for printing the current round and the options a player can choose
  **/
  public void printSelection() {
    System.out.println("\nRound No: " + (this.roundNumber+1));
    System.out.println("Please choose a selection from the following: ");
    for (String command : this.commands) {
      System.out.println("  " + command);
    }
    System.out.println("Or type 'quit' to exit");
  }
  /**
  A method to get a human player's choices
  @Return a string command of the player's choice
  **/
  public String getHumanChoice() {
    Scanner reader = new Scanner(System.in);
    String humanCommand = reader.nextLine().toUpperCase();
    while (!notValidInput(humanCommand)) { // keep looping until user enters a valid input
      System.out.println("Invalid input");
      printSelection(); // print the selection for user again
      humanCommand = reader.nextLine().toUpperCase(); // read user input
    }
    return humanCommand;
  }
  /**
  This method determines whether user has entered a valid input
  @Return true if user enters rock, paper, scissors, lizard, spock or quit
  **/
  public boolean notValidInput(String humanCommand) {
    return ((humanCommand.equals("ROCK")) || (humanCommand.equals("PAPER")) || (humanCommand.equals("SCISSORS")) || (humanCommand.equals("LIZARD")) || (humanCommand.equals("SPOCK")) || (humanCommand.equals("QUIT")));
  }

  /**
  Get a random choice for the computer
  @Return a String variable for the command
  **/
  public String getAIChoice() {
    int ai = this.rand.nextInt(5); // get a random number between 0 and 4 inclusive
    String aiCommand = this.commands.get(ai); // get the choice from the list of commands stored in this.commands
    return aiCommand;
  }

  /**
  A switch statement to determine who is the winner if any
  @Param selection holds the human and AI choices for the round
  @Return the outcome - winner or draw
  **/
  public String decide_winner(Selection selection) {
    this.roundNumber++; // increment the round number
    String result = "This round ended in a draw!"; // assume the round ends in a draw
    String humanCommand = selection.getHuman(); // get the human choice
    switch (humanCommand) {
      // call the appropriate playedX method depending what the user chose
      case "ROCK":
        result = playedRock(selection, result);
      break;
      case "PAPER":
        result = playedPaper(selection, result);
      break;
      case "SCISSORS":
        result = playedScissors(selection, result);
      break;
      case "LIZARD":
        result = playedLizard(selection, result);
      break;
      case "SPOCK":
        result = playedSpock(selection, result);
      break;
    }
    System.out.println();
    System.out.println(result); // print the output for the CLI version
    return result; // return the output for the gui version
  }


  /**
  This method is used to determine who the winner is if the user played "Rock". It'll also increment the winner's score
  @Param selection - the human and ai choices for this round
  @Param result - the string that is iniatially set to represent a drawn game that will be overwritten if there is a winner
  @Return string - return the "result" string which shows the outcome of the round
  **/
  public String playedRock(Selection selection, String result) {
    String aiCommand = selection.getComputer();
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
    return result;
  }
  /**
  This method is used to determine who the winner is if the user played "Paper". It'll also increment the winner's score
  @Param selection - the human and ai choices for this round
  @Param result - the string that is iniatially set to represent a drawn game that will be overwritten if there is a winner
  @Return string - return the "result" string which shows the outcome of the round
  **/
  public String playedPaper(Selection selection, String result) {
    String aiCommand = selection.getComputer();
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
    return result;
  }
  /**
  This method is used to determine who the winner is if the user played "Scissors". It'll also increment the winner's score
  @Param selection - the human and ai choices for this round
  @Param result - the string that is iniatially set to represent a drawn game that will be overwritten if there is a winner
  @Return string - return the "result" string which shows the outcome of the round
  **/
  public String playedScissors(Selection selection, String result) {
    String aiCommand = selection.getComputer();
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
    return result;
  }
  /**
  This method is used to determine who the winner is if the user played "Lizard". It'll also increment the winner's score
  @Param selection - the human and ai choices for this round
  @Param result - the string that is iniatially set to represent a drawn game that will be overwritten if there is a winner
  @Return string - return the "result" string which shows the outcome of the round
  **/
  public String playedLizard(Selection selection, String result) {
    String aiCommand = selection.getComputer();
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
    return result;
  }
  /**
  This method is used to determine who the winner is if the user played "Spock". It'll also increment the winner's score
  @Param selection - the human and ai choices for this round
  @Param result - the string that is iniatially set to represent a drawn game that will be overwritten if there is a winner
  @Return string - return the "result" string which shows the outcome of the round
  **/
  public String playedSpock(Selection selection, String result) {
    String aiCommand = selection.getComputer();
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
    return result;
  }
  // print the round/game summary
  public void printSummary() {
    System.out.println("Rounds played: " + this.roundNumber);
    System.out.println("Human wins: " + this.humanWins);
    System.out.println("AI wins: " + this.computerWins);
  }
}
