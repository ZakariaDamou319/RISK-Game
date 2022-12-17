import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private String name;
    protected boolean isTurn;
    private boolean isCPU;
    public ArrayList<Territory> playerTerritories;

    /**
     * Constructs a new player with a String name, boolean isTurn, and an ArrayList of player territories.
     * @param name
     */
    public Player(String name) {
        this.name = name;
        isTurn = false;
        isCPU = false;
        this.playerTerritories = new ArrayList<Territory>();
    }

    /**
     * Gets name of player.
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * Adds a territory to the player's list of owned territories.
     * @param terr
     */
    public void addTerritories(Territory terr) {

        playerTerritories.add(terr);
    }


    /**
     * Prints out the player's name and territory details.
     * @return
     */
    public String toString() {
        String description = "Player: " + name + "\n";
        description += "Territories: " + "\n";

        for (int i = 0; i < playerTerritories.size(); i++) {
            description += playerTerritories.get(i).getName();
            description += " Armies: " + playerTerritories.get(i).getNumberOfArmies(); //TEST

            if (i < (playerTerritories.size()-1)) {
                description += "\n";
            } else {
                description += ".\n";
            }
        }
        return description;
    }

    /**
     * Prints out which player's turn it is.
     * @return
     */
    public boolean playersTurn() {
        if (isTurn == true) {
            System.out.println("It is " + name + "'s turn.");
            return true;
        }
        else return false;
    }

    /**
     * Sets the turn of a player.
     */
    public void setTurn(){
        isTurn = true;
    }

    /**
     * Unsets the turn of a player.
     */
    public void unsetTurn(){
        isTurn = false;
    }

    /**
     * Sets the player as a CPU player.
     */
    public void setCPU(){
        isCPU = true;
    }


    /**
     * Unsets the turn of a player.
     */
    public boolean isCPU(){
        return isCPU;
    }

}