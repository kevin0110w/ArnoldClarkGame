/**
This program can be ran through the command line/terminal
First compile the program javac *.java
Followed by java Main --c or java Main --gui for the CLI or GUI version respectively
**/
import javax.swing.SwingUtilities;

/**
Author - Kevin Wu
**/
public class Main {
  public static void main(String[] args) {
    for (String arg : args) {
      if (arg.equals("--c")) {
        GameLogic logic = new GameLogic();
        logic.play();
      } else if (arg.equals("--gui")) {
        View gui = new View();
        SwingUtilities.invokeLater(gui);
      }
    }
  }
}
