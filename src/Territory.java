import java.io.Serializable;
import java.util.ArrayList;

public class Territory implements Serializable {

    private String name;
    private int numberOfArmies;
    public ArrayList<Territory> neighbour_list;

    /**
     * Creates a territory with a String name and int numberOfArmies. Also makes a list of neighbours.
     * @param name
     * @param numberOfArmies
     */
    public Territory(String name, int numberOfArmies) {
        this.name = name;
        this.numberOfArmies = numberOfArmies;
        neighbour_list = new ArrayList<Territory>(0);
    }

    /**
     * Returns name of territory.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name of territory.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns number of armies in a territory.
     * @return
     */
    public int getNumberOfArmies() {
        return this.numberOfArmies;
    }

    /**
     * Sets number of armies in a territory.
     * @param numberOfArmies
     */
    public void setNumberOfArmies(int numberOfArmies){
        this.numberOfArmies = numberOfArmies;
    }

    /**
     * Adds desired armies to a territory.
     * @param i
     */
    public void addNumberOfArmies(int i) {
        this.numberOfArmies = this.numberOfArmies+i;
    }

    /**
     * Removes desired armies from a territory.
     * @param i
     */
    public void removeNumberOfArmies(int i){
        this.numberOfArmies = this.numberOfArmies-i;
    }

    /**
     * Sets the neighbour
     * @param neighbor
     */
    public void setNeighbor(Territory neighbor){
        neighbour_list.add(neighbor);
    }

    /**
     * Prints name of territory and the number of armies it has.
     * @return
     */
    public String toString() {
        String label = "";
        label += getName();
        label += (": " + getNumberOfArmies());
        return label;
    }

    /**
     * Returns neighbour list.
     * @return
     */
    public ArrayList<Territory> getNeighbor(){
        return neighbour_list;
    }
}
