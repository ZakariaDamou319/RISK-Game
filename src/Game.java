import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    protected static int players;
    protected static int numberOfCPU;
    protected static int numberOfRealPlayer;

    private static ArrayList<Territory> allTerritories;
    protected static ArrayList<Territory> allTerritoriesList;
    private static int numOfTerritoriesPerPerson;
    protected static LinkedList<Player> playerLinkedList;
    private static int remainderTerritories;
    private String blitz;
    protected int armiesPerTurn;
    private char[][] grid;
    private boolean isBlitz;
    public static final int xSIZE = 13;
    public static final int ySIZE = 7;
    public static final int secondXSize = 9;
    public static final int secondYSize = 2;
    private List<RISKView> RISKViews;

    private Territory attackingCPU;
    private Territory attackedCPU;

    protected static int totalTroops;

    /**
     * Creates a new game with a grid, RISKView (JFrame), and initializes armiesPerTurn, remainderTerritories, and playerLinkedList.
     */
    public Game(boolean isFirstMap) {
        attackingCPU = new Territory("", 0);
        attackedCPU = new Territory("", 0);
        if(isFirstMap) {
            grid = new char[xSIZE][ySIZE];
        } else{
            grid = new char[secondXSize][secondYSize];
        }
        RISKViews = new ArrayList<>();
        isBlitz = false;
        blitz = "";
        armiesPerTurn = 0;
        remainderTerritories = 0;
        playerLinkedList = new LinkedList<>();
    }

    /**
     * Adds a RISKView to the RISKView list.
     *
     * @param rv
     */
    public void addRISKView(RISKView rv) {
        RISKViews.add(rv);
    }

    /**
     * Removes a RISKView to the RISKView list.
     *
     * @param rv
     */
    public void removeRISKView(RISKView rv) {
        RISKViews.remove(rv);
    }


    /**
     * It uses the toString in player to print the details of each territory.
     *
     * @param player
     */
    private void printTerritories(Player player) {
        player.toString();
    }

    /**
     * It initializes each player and evenly divides the total territories and distributes them to each player.
     */
    protected void initializePlayers() {
        switch (players) {
            case 2:
                Player player1 = new Player("player1");
                Player player2 = new Player("player2");
                players = 2;
                numOfTerritoriesPerPerson = allTerritories.size() / players;
                evenlyDivideTerritories(player1, 2);
                evenlyDivideTerritories(player2, 2);
                playerLinkedList.add(player1);
                playerLinkedList.add(player2);
                break;

            case 3:
                player1 = new Player("player1");
                player2 = new Player("player2");
                Player player3 = new Player("player3");
                players = 3;
                numOfTerritoriesPerPerson = allTerritories.size() / players;
                evenlyDivideTerritories(player1, 3);
                evenlyDivideTerritories(player2, 3);
                evenlyDivideTerritories(player3, 3);
                playerLinkedList.add(player1);
                playerLinkedList.add(player2);
                playerLinkedList.add(player3);
                break;

            case 4:
                player1 = new Player("player1");
                player2 = new Player("player2");
                player3 = new Player("player3");
                Player player4 = new Player("player4");
                players = 4;
                numOfTerritoriesPerPerson = allTerritories.size() / players;
                remainderTerritories = (allTerritories.size() % players);
                evenlyDivideTerritories(player1, 4);
                evenlyDivideTerritories(player2, 4);
                evenlyDivideTerritories(player3, 4);
                evenlyDivideTerritories(player4, 4);
                playerLinkedList.add(player1);
                playerLinkedList.add(player2);
                playerLinkedList.add(player3);
                playerLinkedList.add(player4);
                break;

            case 5:
                player1 = new Player("player1");
                player2 = new Player("player2");
                player3 = new Player("player3");
                player4 = new Player("player4");
                Player player5 = new Player("player5");
                players = 5;
                numOfTerritoriesPerPerson = allTerritories.size() / players;
                remainderTerritories = (allTerritories.size() % players);
                evenlyDivideTerritories(player1, 5);
                evenlyDivideTerritories(player2, 5);
                evenlyDivideTerritories(player3, 5);
                evenlyDivideTerritories(player4, 5);
                evenlyDivideTerritories(player5, 5);
                playerLinkedList.add(player1);
                playerLinkedList.add(player2);
                playerLinkedList.add(player3);
                playerLinkedList.add(player4);
                playerLinkedList.add(player5);
                break;

            case 6:
                player1 = new Player("player1");
                player2 = new Player("player2");
                player3 = new Player("player3");
                player4 = new Player("player4");
                player5 = new Player("player5");
                Player player6 = new Player("player6");
                players = 6;
                numOfTerritoriesPerPerson = allTerritories.size() / players;
                evenlyDivideTerritories(player1, 6);
                evenlyDivideTerritories(player2, 6);
                evenlyDivideTerritories(player3, 6);
                evenlyDivideTerritories(player4, 6);
                evenlyDivideTerritories(player5, 6);
                evenlyDivideTerritories(player6, 6);
                playerLinkedList.add(player1);
                playerLinkedList.add(player2);
                playerLinkedList.add(player3);
                playerLinkedList.add(player4);
                playerLinkedList.add(player5);
                playerLinkedList.add(player6);
                break;
        }
        setCPU();
    }

    /**
     * Sets a number of players to CPUs.
     */
    protected void setCPU() {
        for (int i = numberOfRealPlayer; i < playerLinkedList.size(); i++) {
            playerLinkedList.get(i).setCPU();
        }
    }


    /**
     * Sets armies to each territory, and initializes each territory.
     */
    protected static void initializeTerritories(boolean isFirstMap) {
        if(isFirstMap) {
            Territories.createFirstMapTerritories();
            allTerritories = Territories.createFirstMapTerritoryList();
        } else{
            Territories.createSecondMapTerritories();
            allTerritories = Territories.createSecondMapTerritoryList();
        }
        int tempTotalTroops = totalTroops - allTerritories.size();
        while (tempTotalTroops != 0) {
            Random randomNumber = new Random();
            int upperBounds = allTerritories.size() - 1;
            int territoryIndex = randomNumber.nextInt(upperBounds);
            allTerritories.get(territoryIndex).addNumberOfArmies(1);
            tempTotalTroops = tempTotalTroops - 1;
        }
    }


    /**
     * Evenly divides the territories depending on how many players there are.
     *
     * @param player
     * @param numOfPlayers
     */
    public static void evenlyDivideTerritories(Player player, int numOfPlayers) {

        for (int i = 0; i < numOfTerritoriesPerPerson; i++) {
            player.addTerritories(allTerritories.get(i));
        }

        for (int j = 0; j < numOfTerritoriesPerPerson; j++) {
            allTerritories.remove(0);
        }

        if (remainderTerritories != 0) {
            for (int a = 0; a < remainderTerritories; a++) {
                player.addTerritories(allTerritories.get(a));
                remainderTerritories -= 1;
                allTerritories.remove(0);
            }
        }
    }


    /**
     * Depending on how many players, the total armies are initialized.
     */
    public void determineTotalStartingTroops() {
        switch (players) {
            case 2:
                totalTroops = 100;
                break;
            case 3:
                totalTroops = 105;
                break;
            case 4:
                totalTroops = 120;
                break;
            case 5:
                totalTroops = 125;
                break;
            case 6:
                totalTroops = 120;
                break;
        }
    }


    /**
     * Gives bonus troops depending on if the player has a continent (or multiple). Adds them to the armiesPerTurn field.
     */
    public void addTroopsEachTurn() {

        int troops = playerLinkedList.getFirst().playerTerritories.size() / 3;

        if (playerLinkedList.getFirst().playerTerritories.size() < 9) {
            troops = 3; //minimum troops recieved
        }

        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.Alaska) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Alberta)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Ontario) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Quebec)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.NWTerritory) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Greenland)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.CentralAmerica) && playerLinkedList.getFirst().playerTerritories.contains(Territories.WesternUS)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.EasternUS)) {

            troops += 5; // North American BONUS

        }
        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.Brazil) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Argentina)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Peru) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Venezuela)) {

            troops += 2; // South America BONUS

        }
        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.Britain) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Iceland)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.NEurope) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Scandinavia)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.SEurope) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Ukraine)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.WEurope)) {

            troops += 5; // Europe BONUS

        }
        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.Congo) && playerLinkedList.getFirst().playerTerritories.contains(Territories.EastAfrica)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Egypt) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Madagascar)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.NorthAfrica) && playerLinkedList.getFirst().playerTerritories.contains(Territories.SouthAfrica)) {

            troops += 3; // Africa BONUS

        }
        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.Afghanistan) && playerLinkedList.getFirst().playerTerritories.contains(Territories.China)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.India) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Irkutsk)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Japan) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Kamchatka)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.MiddleEast) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Mongolia)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Siam) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Siberia)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.Ural) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Yakutsk)) {

            troops += 7; // Asia BONUS

        }
        if (playerLinkedList.getFirst().playerTerritories.contains(Territories.EasternAustralia) && playerLinkedList.getFirst().playerTerritories.contains(Territories.Indonesia)
                && playerLinkedList.getFirst().playerTerritories.contains(Territories.PapuaNewGuinea) && playerLinkedList.getFirst().playerTerritories.contains(Territories.WesternAustralia)) {

            troops += 2; // Oceania BONUS

        }

        armiesPerTurn = troops;

    }


    /**
     * Sets the turn of each player.
     */
    public void setPlayerTurn() {
        Player tempPlayer = playerLinkedList.getFirst();
        playerLinkedList.removeFirst();
        tempPlayer.unsetTurn();
        playerLinkedList.getFirst().setTurn();
        playerLinkedList.add(tempPlayer);
        addTroopsEachTurn();
    }


    /**
     * Attacks any territory that isn't your own and is a neighbour to the CHOSEN territory.
     *
     * @param isBlitz
     * @param player
     * @param attackingTerritory
     * @param attackedTerritory
     * @return
     */
    public static boolean attack(boolean isBlitz, Player player, Territory attackingTerritory, Territory attackedTerritory) {
        boolean flag = false;
        Player attackedPlayer = new Player("null");
        for (int i = 0; i < playerLinkedList.size(); i++) {
            for (int j = 0; j < playerLinkedList.get(i).playerTerritories.size(); j++) {
                if (playerLinkedList.get(i).playerTerritories.get(j).getName().equals(attackedTerritory.getName())) {
                    attackedPlayer = playerLinkedList.get(i);
                }
            }
        }
        if (isBlitz == true) {
            while (attackingTerritory.getNumberOfArmies() > 0) {
                int diceRoll1 = Dice.diceRoll(attackingTerritory);
                int diceRoll2 = Dice.diceRoll(attackedTerritory);
                if (diceRoll1 > diceRoll2) {
                    for (int i = 0; i < attackedPlayer.playerTerritories.size(); i++) {
                        if (attackedPlayer.playerTerritories.get(i).getName().equals(attackedTerritory.getName())) {
                            attackedPlayer.playerTerritories.remove(i);
                        }
                    }
                    player.playerTerritories.add(attackedTerritory);
                    attackedTerritory.setNumberOfArmies(1);
                    flag = true;
                    break;
                } else {
                    attackingTerritory.removeNumberOfArmies(1);
                }
            }
            if (attackingTerritory.getNumberOfArmies() == 0) {
                attackingTerritory.setNumberOfArmies(1);
                flag = false;
            }
        } else {
            if (attackingTerritory.getNumberOfArmies() > 1) {
                int diceRoll1 = Dice.diceRoll(attackingTerritory);
                int diceRoll2 = Dice.diceRoll(attackedTerritory);
                if (diceRoll1 > diceRoll2) {
                    for (int i = 0; i < attackedPlayer.playerTerritories.size(); i++) {
                        if (attackedPlayer.playerTerritories.get(i).getName().equals(attackedTerritory.getName())) {
                            attackedPlayer.playerTerritories.remove(i);
                        }
                    }

                    player.playerTerritories.add(attackedTerritory);
                    attackedTerritory.setNumberOfArmies(1);
                    attackingTerritory.removeNumberOfArmies(1);
                    flag = true;
                } else {
                    attackingTerritory.removeNumberOfArmies(1);
                    flag = false;
                }
            }
        }
        return flag;
    }


    /**
     * CPU drafts, attacks, and fortifies when it's their turn.
     */
    protected void runCPU() {

        draftCPU(); //drafts armies
        attackCPU(); // attacks a territory
        fortifyCPU(); // fortifies

    }


    /**
     * Drafts troops for the CPU (AI)
     */
    private void draftCPU() {

        boolean flag = false;
        Territory terr = new Territory(" ", 0);

        if (playerLinkedList.getFirst().isCPU() == true) {

            while (flag != true) {

                int rand = (int) (Math.random() * playerLinkedList.getFirst().playerTerritories.size());

                for (int i = 0; i < playerLinkedList.getFirst().playerTerritories.get(rand).getNeighbor().size(); i++) {
                    if (!(playerLinkedList.getFirst().playerTerritories.contains(playerLinkedList.getFirst().playerTerritories.get(rand).getNeighbor().get(i)))) {

                        terr = playerLinkedList.getFirst().playerTerritories.get(rand);

                        flag = true;
                    }
                }
            }
            terr.addNumberOfArmies(armiesPerTurn);
            attackingCPU = terr;
            RISKViews.get(0).cpuDraftMessage(terr, armiesPerTurn);
        }
    }


    /**
     * Chooses the enemy territory to attack.
     * @return
     */
    public Territory randomTerritory() {

        ArrayList<Territory> terr = new ArrayList<>();

        for (int j = 0; j < attackingCPU.getNeighbor().size(); j++) {

            if (!(playerLinkedList.getFirst().playerTerritories.contains(attackingCPU.getNeighbor().get(j)))) {
                terr.add(attackingCPU.getNeighbor().get(j));
            }
        }
        int index = (int) (Math.random() * terr.size());
        return terr.get(index);
    }


    /**
     * CPU attacks a territory.
     */
    private void attackCPU() {
        Territory attackingTerritory = attackingCPU;
        attackedCPU = randomTerritory();
        RISKViews.get(0).cpuAttackMessage(attack(true, playerLinkedList.getFirst(), attackingTerritory, attackedCPU));
    }

    /**
     * CPU fortifies a territory.
     */
    private void fortifyCPU(){
        ArrayList<Territory> terr = new ArrayList<>();
        for(int i = 0; i < playerLinkedList.getFirst().playerTerritories.size(); i++){
            if(playerLinkedList.getFirst().playerTerritories.get(i).getNumberOfArmies() > 2){
                terr.add(playerLinkedList.getFirst().playerTerritories.get(i));
            }
        }

        int firstIndex = (int) (Math.random() * terr.size());

        Territory secondTerritory = attackingCPU;
        for(int i = 0; i < playerLinkedList.getFirst().playerTerritories.size(); i++){
            if(attackedCPU.getName().equals(playerLinkedList.getFirst().playerTerritories.get(i).getName())){
                secondTerritory = attackedCPU;
            }
        }
        Territory firstTerritory = terr.get(firstIndex);

        int armiesRandom =  (int) (Math.random() *  firstTerritory.getNumberOfArmies() - 1) + 1;
        RISKViews.get(0).cpuFortifyMessage(firstTerritory, secondTerritory, armiesRandom, true);
        firstTerritory.removeNumberOfArmies(armiesRandom);
        secondTerritory.addNumberOfArmies(armiesRandom);
        RISKViews.get(0).cpuFortifyMessage(firstTerritory, secondTerritory, armiesRandom, false);
    }


}
