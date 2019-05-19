/**
This class is responsible for holding the data of the players' selections per round
An instance is created for each round
Author - Kevin Wu
**/

public class Selection {
  private String human, computer;

  public Selection(String human, String computer) {
    this.human = human;
    this.computer =  computer;
  }
  /**
  Return the human's choice
  **/
  public String getHuman() {
    return this.human;
  }
  /**
  Return the AI's choice
  **/
  public String getComputer() {
    return this.computer;
  }
}
