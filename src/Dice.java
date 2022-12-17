import java.util.Random;

public class Dice {

    /**
     * Returns a random integer with its bounds, depending on the number of armies.
     * @param territory
     * @return
     */
    public static int diceRoll(Territory territory) {
        if (territory.getNumberOfArmies() >= 4) {
            Random diceRoll = new Random();
            return diceRoll.nextInt(18) + 1;
        } else {
            Random diceRoll = new Random();
            return diceRoll.nextInt(12) + 1;
        }
    }
}
