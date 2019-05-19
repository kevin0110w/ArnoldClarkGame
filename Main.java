import javax.swing.SwingUtilities;
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
