import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    private static ArrayList<Territory> allTerritories;
    private static Game game;

    @org.junit.Test
    public void initializeTerritories() {

        allTerritories = Territories.createFirstMapTerritoryList();

        assertEquals(allTerritories.size(), 42);

    }

    @org.junit.Test
    public void initializePlayers() {

        game = new Game(true);

        game.players = 3;

        game.initializeTerritories(true);
        game.initializePlayers();

        assertEquals(game.playerLinkedList.size(), 3);

    }

    @org.junit.Test
    public void determineTotalStartingTroops() {

        game = new Game(true);

        game.players = 3;

        game.determineTotalStartingTroops();

        assertEquals(game.totalTroops, 105);

    }

    @org.junit.Test
    public void evenlyDivideTerritories() {

        game = new Game(true);

        game.players = 2;

        game.initializeTerritories(true);
        game.initializePlayers();

        assertEquals(game.playerLinkedList.getFirst().playerTerritories.size(), 21);

    }
    @org.junit.Test
    public void addTroopsEachTurn() {

        game = new Game(true);
        game.players = 2;
        game.determineTotalStartingTroops();
        game.initializeTerritories(true);
        game.initializePlayers();
        game.playerLinkedList.getFirst().setTurn();
        game.addTroopsEachTurn();

        assertEquals(game.armiesPerTurn, 17);

    }
    @org.junit.Test
    public void setCPU() {

        game = new Game(true);
        game.players = 2;
        game.numberOfRealPlayer = 1;

        game.determineTotalStartingTroops();
        game.initializeTerritories(true);
        game.initializePlayers();
        game.setCPU();

        assertEquals(game.playerLinkedList.get(1).isCPU(), true);

    }

    @org.junit.Test
    public void setPlayerTurn() {

        game = new Game(true);
        game.players = 2;
        game.numberOfRealPlayer = 2;

        game.determineTotalStartingTroops();
        game.initializeTerritories(true);
        game.initializePlayers();

        game.setPlayerTurn();

        assertEquals(game.playerLinkedList.getFirst().isTurn, true);
    }

}