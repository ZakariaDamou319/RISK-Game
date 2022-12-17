import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RISKView extends JFrame implements Serializable {

    protected boolean territoryOneDone = false;
    private JButton[][] buttons;
    private static Game game;
    private RISKController controller;
    private JMenu menu;
    private JMenu commandMenu;
    private JMenuBar menuBar;
    protected static JMenuItem quit;
    protected static JMenuItem help;
    protected static JMenuItem endTurn;
    protected static JMenuItem endAttack;
    protected static JMenuItem save;
    protected static JMenuItem load;
    protected static LinkedList<Player> playerOrder;

    protected boolean isFortification = false;

    private Territory firstTerritory;
    private Territory secondTerritory;

    protected static JMenuItem showTroops;
    protected static ArrayList<JButton> buttonArrayList;
    private static ArrayList<Integer> armies;
    private int attackingTerritoryIndex = 0;
    private int attackedTerritoryIndex = 0;
    protected int armiesPerTurn;
    private ArrayList<JButton> oceanButtons;


    /**
     * Constructor for RISKView. Initializes all global variables and calls startGame()
     * Also makes frame visible.
     */
    public RISKView() {
        super("RISK: Global Domination!");

        String[] options = {"World Map", "Canadian Map"};
        int choice = JOptionPane.showOptionDialog(null, "Which map would you like to play.", "Choose wisely", 0, 2, null, options, null);
        if (choice == 0) {
            save = new JMenuItem("Save");
            load = new JMenuItem("Load");

            oceanButtons = new ArrayList<>();
            firstTerritory = new Territory("null", 0);
            secondTerritory = new Territory("null", 0);

            playerOrder = new LinkedList<>();
            buttonArrayList = new ArrayList<>();
            controller = new RISKController(game, this);

            menu = new JMenu("Other Commands");
            commandMenu = new JMenu("Game Commands");
            commandMenu.setEnabled(true);
            menu.setEnabled(true);

            menuBar = new JMenuBar();
            quit = new JMenuItem("Quit");
            help = new JMenuItem("Help");
            endTurn = new JMenuItem("End Turn");
            showTroops = new JMenuItem("Show Troops");
            endAttack = new JMenuItem("End Attack");

            commandMenu.add(endTurn);
            commandMenu.add(endAttack);
            commandMenu.add(showTroops);

            endAttack.setVisible(false);

            menu.add(help);
            menu.add(quit);
            menu.add(save);
            menu.add(load);

            menuBar.add(commandMenu);
            menuBar.add(menu);
            this.setJMenuBar(menuBar);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(screenSize.width, screenSize.height);

            quit.addActionListener(controller);
            help.addActionListener(controller);
            endTurn.addActionListener(controller);
            endAttack.addActionListener(controller);
            showTroops.addActionListener(controller);
            save.addActionListener(controller);
            load.addActionListener(controller);

            // GAME STARTS HERE
            this.setLayout(new GridLayout(Game.ySIZE, Game.xSIZE));

            this.initializeFirstMapTerritoryButtons();

            game = new Game(true);
            game.addRISKView(this);
            armies = new ArrayList<>(game.armiesPerTurn);


            this.setVisible(true);
            startGame(true);
        } else if(choice == 1){
            save = new JMenuItem("Save");
            load = new JMenuItem("Load");

            oceanButtons = new ArrayList<>();
            firstTerritory = new Territory("null", 0);
            secondTerritory = new Territory("null", 0);

            playerOrder = new LinkedList<>();
            buttonArrayList = new ArrayList<>();
            controller = new RISKController(game, this);

            menu = new JMenu("Other Commands");
            commandMenu = new JMenu("Game Commands");
            commandMenu.setEnabled(true);
            menu.setEnabled(true);

            menuBar = new JMenuBar();
            quit = new JMenuItem("Quit");
            help = new JMenuItem("Help");
            endTurn = new JMenuItem("End Turn");
            showTroops = new JMenuItem("Show Troops");
            endAttack = new JMenuItem("End Attack");

            commandMenu.add(endTurn);
            commandMenu.add(endAttack);
            commandMenu.add(showTroops);

            endAttack.setVisible(false);

            menu.add(help);
            menu.add(quit);
            menu.add(save);
            menu.add(load);

            menuBar.add(commandMenu);
            menuBar.add(menu);
            this.setJMenuBar(menuBar);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(screenSize.width, screenSize.height);

            quit.addActionListener(controller);
            help.addActionListener(controller);
            endTurn.addActionListener(controller);
            endAttack.addActionListener(controller);
            showTroops.addActionListener(controller);
            save.addActionListener(controller);
            load.addActionListener(controller);

            // GAME STARTS HERE
            this.setLayout(new GridLayout(game.secondYSize, game.secondXSize));

            this.initializeSecondMapTerritoryButtons();

            game = new Game(false);
            game.addRISKView(this);
            armies = new ArrayList<>(game.armiesPerTurn);


            this.setVisible(true);
            startGame(false);
        }
    }


    /**
     * Initializes beginning of game. Adds players, initializes all territories and armies.
     */
    public void startGame(boolean isFirstMap) {

        String red = "RED";
        String org = "ORANGE";
        String yel = "YELLOW";
        String mag = "MAGENTA";
        String cyan = "CYAN";
        String green = "GREEN";
        String[] arr = {red, org, yel, mag, cyan, green};
        ArrayList<Integer> players = new ArrayList<>();
        players.add(2);
        players.add(3);
        players.add(4);
        players.add(5);
        players.add(6);
        String[] options = {"OK"};
        SpinnerListModel model = new SpinnerListModel(players);
        JSpinner spin = new JSpinner(model);
        spin.setBounds(100, 100, 50, 30);
        JOptionPane.showOptionDialog(null, spin, "Enter the amount of players: ", 0, 1, null, options, null);
        game.players = (int) spin.getValue();
        ArrayList<Integer> cpu = new ArrayList<>();
        for (int i = 0; i < game.players; i++) {
            cpu.add(i);
        }
        String[] options2 = {"OK"};
        SpinnerListModel model2 = new SpinnerListModel(cpu);
        JSpinner spin2 = new JSpinner(model2);
        spin.setBounds(100, 100, 50, 30);
        JOptionPane.showOptionDialog(null, spin2, "How many of those players will be cpu: ", 0, 1, null, options2, null);
        game.numberOfCPU = (int) spin2.getValue();

        game.numberOfRealPlayer = game.players - game.numberOfCPU;

        game.determineTotalStartingTroops();
        game.initializeTerritories(isFirstMap);
        if(isFirstMap) {
            game.allTerritoriesList = Territories.createFirstMapTerritoryList();
        } else{
            game.allTerritoriesList = Territories.createSecondMapTerritoryList();
        }
        game.initializePlayers();
        game.playerLinkedList.getFirst().setTurn();
        playerOrder = (LinkedList) game.playerLinkedList.clone();

        updateButtonText();

        operationLightUpSneakers(); // LIGHT YOUR TERRITORIES TO DIFFERENT COLOURS
        colourOcean(); // COLOUR OCEAN

        String colorMessage = "";
        for (int i = 0; i < playerOrder.size(); i++) {
            colorMessage += playerOrder.get(i).getName() + " is " + arr[i];
            if (playerOrder.get(i).isCPU()) {
                colorMessage += " and it is a CPU" + "\n";
            } else {
                colorMessage += " and it is not a CPU" + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, colorMessage);

        JOptionPane.showMessageDialog(null, "It is " + game.playerLinkedList.getFirst().getName() + "'s turn!");
        game.addTroopsEachTurn();
        this.armiesPerTurn = game.armiesPerTurn;
        JOptionPane.showMessageDialog(null, "You received " + game.armiesPerTurn + " troops this turn!");


        JOptionPane.showMessageDialog(null, "DRAFT PHASE!");

        this.setVisible(true);
    }

    /**
     * Updates the text of the button to show the updated armies.
     */
    private void updateButtonText() {

        for (int i = 0; i < buttonArrayList.size(); i++) {
            for (int j = 0; j < game.allTerritoriesList.size(); j++) {
                if (buttonArrayList.get(i).getText().contains(game.allTerritoriesList.get(j).getName())) {
                    buttonArrayList.get(j).setText(game.allTerritoriesList.get(j).getName() + ": " + game.allTerritoriesList.get(j).getNumberOfArmies());
                }
            }
        }
    }

    /**
     * Creates all buttons and ands actionListeners to them. Places them in our design of the game
     * Note: All terrirories have the same neighbours as in the actual RISK: Global Domination (although
     * it does not look like it in our frame).
     */
    public void initializeFirstMapTerritoryButtons() {
        Territories.createFirstMapTerritories();

        buttons = new JButton[Game.xSIZE][Game.ySIZE];
        saveWorldMap(buttons);
        loadWorldMap();

        buttons[2][0] = new JButton("");
        buttons[0][1] = new JButton("");
        buttons[0][2] = new JButton("");
        buttons[0][3] = new JButton("");
        buttons[0][4] = new JButton("");
        buttons[0][5] = new JButton("");
        buttons[0][6] = new JButton("");

        buttons[4][1] = new JButton("");
        buttons[3][2] = new JButton("");
        buttons[2][3] = new JButton("");
        buttons[1][4] = new JButton("");
        buttons[1][5] = new JButton("");
        buttons[1][6] = new JButton("");

        buttons[3][0] = new JButton("");
        buttons[3][3] = new JButton("");
        buttons[3][5] = new JButton("");
        buttons[3][6] = new JButton("");

        buttons[4][2] = new JButton("");
        buttons[4][3] = new JButton("");
        buttons[4][4] = new JButton("");
        buttons[4][5] = new JButton("");
        buttons[4][6] = new JButton("");

        buttons[5][6] = new JButton("");

        buttons[6][5] = new JButton("");
        buttons[6][6] = new JButton("");

        buttons[7][1] = new JButton("");
        buttons[7][3] = new JButton("");
        buttons[7][4] = new JButton("");
        buttons[7][6] = new JButton("");

        buttons[9][4] = new JButton("");
        buttons[8][3] = new JButton("");
        buttons[8][4] = new JButton("");
        buttons[8][5] = new JButton("");
        buttons[8][6] = new JButton("");

        buttons[10][3] = new JButton("");
        buttons[10][4] = new JButton("");
        buttons[10][6] = new JButton("");
        buttons[9][5] = new JButton("");
        buttons[9][6] = new JButton("");

        buttons[10][2] = new JButton("");
        buttons[11][3] = new JButton("");
        buttons[11][6] = new JButton("");

        buttons[12][0] = new JButton("");
        buttons[11][2] = new JButton("");
        buttons[12][2] = new JButton("");
        buttons[12][3] = new JButton("");
        buttons[12][4] = new JButton("");
        buttons[12][5] = new JButton("");
        buttons[12][6] = new JButton("");

        JButton AlaskaButton = new JButton(Territories.Alaska.getName());
        buttonArrayList.add(AlaskaButton);
        buttons[0][0] = AlaskaButton; // 1,0

        JButton NWTerritoryButton = new JButton(Territories.NWTerritory.getName());
        buttonArrayList.add(NWTerritoryButton);
        buttons[1][0] = NWTerritoryButton; //2, 0

        JButton GreenlandButton = new JButton(Territories.Greenland.getName());
        buttonArrayList.add(GreenlandButton);
        buttons[4][0] = GreenlandButton;

        JButton AlbertaButton = new JButton(Territories.Alberta.getName());
        buttonArrayList.add(AlbertaButton);
        buttons[1][1] = AlbertaButton; //2,1

        JButton OntarioButton = new JButton(Territories.Ontario.getName());
        buttonArrayList.add(OntarioButton);
        buttons[2][1] = OntarioButton; // 3,1

        JButton QuebecButton = new JButton(Territories.Quebec.getName());
        buttonArrayList.add(QuebecButton);
        buttons[3][1] = QuebecButton; // 4,1

        JButton WesternUSButton = new JButton(Territories.WesternUS.getName());
        buttonArrayList.add(WesternUSButton);
        buttons[1][2] = WesternUSButton; //2,2

        JButton EasternUSButton = new JButton(Territories.EasternUS.getName());
        buttonArrayList.add(EasternUSButton);
        buttons[2][2] = EasternUSButton; //3,2

        JButton CentralAmericaButton = new JButton(Territories.CentralAmerica.getName());
        buttonArrayList.add(CentralAmericaButton);
        buttons[1][3] = CentralAmericaButton; //2,3

        JButton VenezuelaButton = new JButton(Territories.Venezuela.getName());
        buttonArrayList.add(VenezuelaButton);
        buttons[2][4] = VenezuelaButton;

        JButton PeruButton = new JButton(Territories.Peru.getName());
        buttonArrayList.add(PeruButton);
        buttons[2][5] = PeruButton;

        JButton BrazilButton = new JButton(Territories.Brazil.getName());
        buttonArrayList.add(BrazilButton);
        buttons[3][4] = BrazilButton;

        JButton ArgentinaButton = new JButton(Territories.Argentina.getName());
        buttonArrayList.add(ArgentinaButton);
        buttons[2][6] = ArgentinaButton;

        JButton NorthAfricaButton = new JButton(Territories.NorthAfrica.getName());
        buttonArrayList.add(NorthAfricaButton);
        buttons[5][3] = NorthAfricaButton; //5,4

        JButton EgyptButton = new JButton(Territories.Egypt.getName());
        buttonArrayList.add(EgyptButton);
        buttons[6][3] = EgyptButton; //6,4

        JButton EastAfricaButton = new JButton(Territories.EastAfrica.getName());
        buttonArrayList.add(EastAfricaButton);
        buttons[6][4] = EastAfricaButton; //6,5

        JButton CongoButton = new JButton(Territories.Congo.getName());
        buttonArrayList.add(CongoButton);
        buttons[5][4] = CongoButton; //5,5

        JButton SouthAfricaButton = new JButton(Territories.SouthAfrica.getName());
        buttonArrayList.add(SouthAfricaButton);
        buttons[5][5] = SouthAfricaButton; //5,6

        JButton MadagascarButton = new JButton(Territories.Madagascar.getName());
        buttonArrayList.add(MadagascarButton);
        buttons[7][5] = MadagascarButton; //7,6

        JButton WEuropeButton = new JButton(Territories.WEurope.getName());
        buttonArrayList.add(WEuropeButton);
        buttons[5][2] = WEuropeButton; //5,3

        JButton BritainButton = new JButton(Territories.Britain.getName());
        buttonArrayList.add(BritainButton);
        buttons[5][1] = BritainButton;

        JButton IcelandButton = new JButton(Territories.Iceland.getName());
        buttonArrayList.add(IcelandButton);
        buttons[5][0] = IcelandButton;

        JButton ScandinaviaButton = new JButton(Territories.Scandinavia.getName());
        buttonArrayList.add(ScandinaviaButton);
        buttons[6][0] = ScandinaviaButton; //6,1

        JButton NEuropeButton = new JButton(Territories.NEurope.getName());
        buttonArrayList.add(NEuropeButton);
        buttons[6][1] = NEuropeButton; //6,2

        JButton SEuropeButton = new JButton(Territories.SEurope.getName());
        buttonArrayList.add(SEuropeButton);
        buttons[6][2] = SEuropeButton; //6,3

        JButton UkraineButton = new JButton(Territories.Ukraine.getName());
        buttonArrayList.add(UkraineButton);
        buttons[7][0] = UkraineButton; // 7,1

        JButton MiddleEastButton = new JButton(Territories.MiddleEast.getName());
        buttonArrayList.add(MiddleEastButton);
        buttons[7][2] = MiddleEastButton; //7,3

        JButton AfghanistanButton = new JButton(Territories.Afghanistan.getName());
        buttonArrayList.add(AfghanistanButton);
        buttons[8][1] = AfghanistanButton; //8,2

        JButton UralButton = new JButton(Territories.Ural.getName());
        buttonArrayList.add(UralButton);
        buttons[8][0] = UralButton; //8,1

        JButton SiberiaButton = new JButton(Territories.Siberia.getName());
        buttonArrayList.add(SiberiaButton);
        buttons[9][0] = SiberiaButton; //9,1

        JButton YakutskButton = new JButton(Territories.Yakutsk.getName());
        buttonArrayList.add(YakutskButton);
        buttons[10][0] = YakutskButton;

        JButton KamchatkaButton = new JButton(Territories.Kamchatka.getName());
        buttonArrayList.add(KamchatkaButton);
        buttons[11][0] = KamchatkaButton;

        JButton IrkutskButton = new JButton(Territories.Irkutsk.getName());
        buttonArrayList.add(IrkutskButton);
        buttons[11][1] = IrkutskButton; //10,1

        JButton MongoliaButton = new JButton(Territories.Mongolia.getName());
        buttonArrayList.add(MongoliaButton);
        buttons[10][1] = MongoliaButton;//10,2

        JButton ChinaButton = new JButton(Territories.China.getName());
        buttonArrayList.add(ChinaButton);
        buttons[9][1] = ChinaButton; //10,3

        JButton IndiaButton = new JButton(Territories.India.getName());
        buttonArrayList.add(IndiaButton);
        buttons[8][2] = IndiaButton; //9,4

        JButton SiamButton = new JButton(Territories.Siam.getName());
        buttonArrayList.add(SiamButton);
        buttons[9][2] = SiamButton; //10,4

        JButton IndonesiaButton = new JButton(Territories.Indonesia.getName());
        buttonArrayList.add(IndonesiaButton);
        buttons[9][3] = IndonesiaButton; //10,5

        JButton PapuaNewGuineaButton = new JButton(Territories.PapuaNewGuinea.getName());
        buttonArrayList.add(PapuaNewGuineaButton);
        buttons[11][4] = PapuaNewGuineaButton; //11,5

        JButton WesternAustraliaButton = new JButton(Territories.WesternAustralia.getName());
        buttonArrayList.add(WesternAustraliaButton);
        buttons[10][5] = WesternAustraliaButton; //10,6

        JButton EasternAustraliaButton = new JButton(Territories.EasternAustralia.getName());
        buttonArrayList.add(EasternAustraliaButton);
        buttons[11][5] = EasternAustraliaButton; //11,6

        JButton JapanButton = new JButton(Territories.Japan.getName());
        buttonArrayList.add(JapanButton);
        buttons[12][1] = JapanButton; //11,2


        for (int i = 0; i < Game.ySIZE; i++) {
            for (int j = 0; j < Game.xSIZE; j++) {
                this.add(buttons[j][i]);
            }
        }

        for (int i = 0; i < buttonArrayList.size(); i++) {
            buttonArrayList.get(i).addActionListener(controller);
        }

        for (int i = 0; i < Game.ySIZE; i++) {
            for (int j = 0; j < Game.xSIZE; j++) {

                if (buttons[j][i].getText().equals("")) {
                    oceanButtons.add(buttons[j][i]);
                }

            }
        }

    }

    /**
     * Saves the original RISK game to a .json file. Can be loaded later.
     * @param buttons
     */
    public void saveWorldMap(JButton[][] buttons) {
        File file = new File("WorldMap.json");

        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(buttons);

            objectStream.close();
            fileStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the custom Canada map to a .json file. Can be loaded later into game.
     * @param buttons
     */
    public void saveCanadaMap(JButton[][] buttons) {
        File file = new File("Canada.json");

        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(buttons);

            objectStream.close();
            fileStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads original RISK map to play.
     */
    public void loadWorldMap(){
        try {
            ObjectInputStream oStream = new ObjectInputStream(new FileInputStream( "WorldMap.json"));
            JButton[][] buttons = (JButton[][]) oStream.readObject();

            oStream.close();


            this.buttons = buttons;

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads Canada map to play.
     */
    public void loadCanadaMap(){
        try {
            ObjectInputStream oStream = new ObjectInputStream(new FileInputStream( "Canada.json"));
            JButton[][] buttons = (JButton[][]) oStream.readObject();

            oStream.close();


            this.buttons = buttons;

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates all territories for the Canada map and saves them.
     */
        public void initializeSecondMapTerritoryButtons() {
        Territories.createSecondMapTerritories();

        buttons = new JButton[game.secondXSize][game.secondYSize];
        saveCanadaMap(buttons);
        loadCanadaMap();

        buttons[0][0] =new JButton("");
        buttons[1][0] = new JButton("");
        buttons[2][0] = new JButton("");
        buttons[3][0] = new JButton("");
        buttons[4][0] = new JButton("");
        buttons[5][0] = new JButton("");
        buttons[6][0] = new JButton("");
        buttons[7][0] = new JButton("");
        buttons[8][0] = new JButton("");

        buttons[0][1] =new JButton("");
        buttons[1][1] = new JButton("");
        buttons[2][1] = new JButton("");
        buttons[3][1] = new JButton("");
        buttons[4][1] = new JButton("");
        buttons[5][1] = new JButton("");
        buttons[6][1] = new JButton("");
        buttons[7][1] = new JButton("");
        buttons[8][1] = new JButton("");


        JButton AlaskaButton = new JButton(Territories.Alaska.getName());
        buttonArrayList.add(AlaskaButton);
        buttons[0][0] = AlaskaButton;

        JButton YukonButton = new JButton(Territories.Yukon.getName());
        buttonArrayList.add(YukonButton);
        buttons[1][0] = YukonButton;

        JButton NWTerritoryButton = new JButton(Territories.NWTerritory.getName());
        buttonArrayList.add(NWTerritoryButton);
        buttons[2][0] = NWTerritoryButton;

        JButton NunavutButton = new JButton(Territories.Nunavut.getName());
        buttonArrayList.add(NunavutButton);
        buttons[3][0] = NunavutButton;

        JButton LabradorButton = new JButton(Territories.Labrador.getName());
        buttonArrayList.add(LabradorButton);
        buttons[6][0] = LabradorButton;

        JButton PrinceEdwardIslandButton = new JButton(Territories.PrinceEdwardIsland.getName());
        buttonArrayList.add(PrinceEdwardIslandButton);
        buttons[7][0] = PrinceEdwardIslandButton;

        JButton BritishColumbiaButton = new JButton(Territories.BritishColumbia.getName());
        buttonArrayList.add(BritishColumbiaButton);
        buttons[1][1] = BritishColumbiaButton;

        JButton AlbertaButton = new JButton(Territories.Alberta.getName());
        buttonArrayList.add(AlbertaButton);
        buttons[2][1] = AlbertaButton;

        JButton SaskatchewanButton = new JButton(Territories.Saskatchewan.getName());
        buttonArrayList.add(SaskatchewanButton);
        buttons[3][1] = SaskatchewanButton;

        JButton ManitobaButton = new JButton(Territories.Manitoba.getName());
        buttonArrayList.add(ManitobaButton);
        buttons[4][1] = ManitobaButton;

        JButton OntarioButton = new JButton(Territories.Ontario.getName());
        buttonArrayList.add(OntarioButton);
        buttons[5][1] = OntarioButton;

        JButton QuebecButton = new JButton(Territories.Quebec.getName());
        buttonArrayList.add(QuebecButton);
        buttons[6][1] = QuebecButton;

        JButton NewBrunswickButton = new JButton(Territories.NewBrunswick.getName());
        buttonArrayList.add(NewBrunswickButton);
        buttons[7][1] = NewBrunswickButton;

        JButton NovaScotiaButton = new JButton(Territories.NovaScotia.getName());
        buttonArrayList.add(NovaScotiaButton);
        buttons[8][1] = NovaScotiaButton;



        for (int i = 0; i < game.secondYSize; i++) {
            for (int j = 0; j < game.secondXSize; j++) {
                this.add(buttons[j][i]);
            }
        }

        for (int i = 0; i < buttonArrayList.size(); i++) {
            buttonArrayList.get(i).addActionListener(controller);
        }

        for (int i = 0; i < game.secondYSize; i++) {
            for (int j = 0; j < game.secondXSize; j++) {

                if (buttons[j][i].getText().equals("")) {
                    oceanButtons.add(buttons[j][i]);
                }

            }
        }
    }

    /**
     * Quits the game. Prints out a "thank you" message before exiting.
     */
    public static void quitGame() {
        JOptionPane.showMessageDialog(null, "Thank you for playing! Good bye.");
        System.exit(0);
    }

    /**
     * Prints out all players and explains the game.
     */
    public static void helpMe() {
        JOptionPane.showMessageDialog(null, "It is your turn, you need to choose a territory you " +
                "control and decide to DRAFT or ATTACK. DRAFT allocates armies to the territory you selected. \n" +
                "ATTACK allows you to attack a neighbouring territory. The goal is to conquer the world. \n");
    }

    /**
     * Prints who's turn it is.
     *
     * @param name
     */
    public void turnMessage(String name) {
        if (!(game.playerLinkedList.getFirst().isCPU())) {
            JOptionPane.showMessageDialog(null, "It is " + name + "'s turn!");
            JOptionPane.showMessageDialog(null, "DRAFT PHASE!");
        } else {
            JOptionPane.showMessageDialog(null, "It is the CPU's turn");
        }


        if (game.playerLinkedList.getFirst().isCPU()) {
            game.runCPU();
            JOptionPane.showMessageDialog(null, game.playerLinkedList.getFirst().getName() + "'s turn is over.");
            changePlayerTurn();
            turnMessage(game.playerLinkedList.getFirst().getName());
        }
    }

    /**
     * Prints territories and armies for each players.
     */
    public static void showTroops(JButton button) {
        JOptionPane.showMessageDialog(null, button.getText());
    }

    /**
     * Changes the turn to the next player and prints how many armies you get this turn.
     */
    public void changePlayerTurn() {
        game.setPlayerTurn();
        JOptionPane.showMessageDialog(null, "You received " + game.armiesPerTurn + " armies this turn!");
        this.armiesPerTurn = game.armiesPerTurn;
        isFortification = false;
    }

    /**
     * Checks if the territory is owned by the current player.
     * @param button
     * @return
     */
    public boolean isPlayerCountry(JButton button) {
        boolean flag = false;
        for (int j = 0; j < game.playerLinkedList.getFirst().playerTerritories.size(); j++) {
            if (button.getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(j).getName())) {
                flag = true;
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "You do not own this territory.");
        }
        return flag;
    }

    /**
     * Prints out a frame where you choose to draft, pick your attacking territory, and attack a territory.
     *
     * @param button
     */
    public void setUpdraft(JButton button) {
        String[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null, "Would you like to draft to this territory.", "Choose wisely", 0, 2, null, options, null);
        if (choice == 0) {
            getDraft(button);
        }
        this.armiesPerTurn = game.armiesPerTurn;
    }

    /**
     * Sets up the attack phase.
     * @param button
     */
    public void setUpAttack(JButton button) {
        String[] options = {"Choose attacking territory", "Choose attacked territory"};
        int choice = JOptionPane.showOptionDialog(null, "Make sure to select an attacking territory first.", "Choose wisely", 0, 2, null, options, null);
        if (choice == 0) {
            saveAttackingTerritory(button);
            if (!(attackVerification(attackingTerritoryIndex, game.playerLinkedList.getFirst()))) {
                JOptionPane.showMessageDialog(null, "You are in control of all neighboring territories.");

            }
        } else if (choice == 1) {
            boolean isDone = saveAttackedTerritory(button);
            if (isDone) {
                attack(button);
            }
        }
    }

    /**
     * Sets up the fortification phase.
     * @param button
     */
    public void setUpFortification(JButton button, boolean flag) {
        if(!flag){
            firstTerritory = saveYourTerritory(button);
            if (firstTerritory.getNumberOfArmies() < 2) {
                JOptionPane.showMessageDialog(null, "Please choose a territory with more then 2 troops.");
            }
            JOptionPane.showMessageDialog(null, "Choose the territory receiving troops.");
            territoryOneDone = true;
        } else {
                secondTerritory = saveYourTerritory(button);
            if (firstTerritory.getName().equals(secondTerritory.getName())) {
                JOptionPane.showMessageDialog(null, "You've chosen the same territory twice, please choose another territory to send armies.");

            } else {
                territoryOneDone = false;
                fortification(firstTerritory, secondTerritory);

            }
        }
    }

    /**
     * Saves the clicked button so it can be used later.
     * @param button
     * @return
     */
    public Territory saveYourTerritory(JButton button) {
        boolean isDone = false;
        int index = 0;
        for (int j = 0; j < game.playerLinkedList.getFirst().playerTerritories.size(); j++) {
            if (button.getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(j).getName())) {
                for (int i = 0; i < buttonArrayList.size(); i++) {
                    if (buttonArrayList.get(i) == button) {
                        index = i;
                        isDone = true;
                    }
                }
            }
        }
        if (!isDone) {
            JOptionPane.showMessageDialog(null, "You do not own this territory.");
        }
        Territory territory = new Territory("null", 0);
        for (int i = 0; i < game.allTerritoriesList.size(); i++) {
            if (buttonArrayList.get(index).getText().contains(game.allTerritoriesList.get(i).getName())) {
                territory = game.allTerritoriesList.get(i);
            }
        }
        return territory;
    }

    /**
     * Fortifies the second territory with troops from the first territory.
     * @param firstTerritory
     * @param secondTerritory
     */
    private void fortification(Territory firstTerritory, Territory secondTerritory) {
        ArrayList<Integer> attackingArmies = new ArrayList<>();

        for (int i = 1; i < firstTerritory.getNumberOfArmies(); i++) {
            attackingArmies.add(i);
        }

        String[] options = {"OK"};
        SpinnerListModel model = new SpinnerListModel(attackingArmies);
        JSpinner spin = new JSpinner(model);
        spin.setBounds(100, 100, 50, 30);
        JOptionPane.showOptionDialog(null, spin, "Enter the number of armies you want to move: ", 0, 1, null, options, null);
        int armiesToAdd = (int) spin.getValue();
        firstTerritory.removeNumberOfArmies(armiesToAdd);
        secondTerritory.addNumberOfArmies(armiesToAdd);
        updateButtonText();
        endAttack.setVisible(false);
        changePlayerTurn();
        turnMessage(game.playerLinkedList.getFirst().getName());
    }


    /**
     * Colours the non-territory buttons to ocean blue.
     */
    public void colourOcean() {
        Color blue = new Color(61,178,255);

        for (int p = 0; p < oceanButtons.size(); p++) {

            if (oceanButtons.get(p).getText().equals("")) {
                oceanButtons.get(p).setBackground(blue);
                oceanButtons.get(p).setOpaque(true);
            }
        }
        this.setVisible(true);
    }


    /**
     * Colours each button depending on who owns the corresponding territory. This method is called after a
     * territory is conquered.
     */
    public void operationLightUpSneakers() {

        Color green = Color.GREEN;
        Color mag = Color.MAGENTA;
        Color org = new Color(255,145,0);
        Color red = Color.RED;
        Color yel = Color.YELLOW;
        Color cyan = Color.CYAN;

        Color[] colArr = {red, org, yel, mag, cyan, green}; // col @ 0 == red
        ArrayList<JButton> allButtons = (ArrayList) buttonArrayList.clone();

        int col = 0;

        for (int j = 0; j < playerOrder.size(); j++) { // 2 players (0 or 1)
            for (int p = 0; p < allButtons.size(); p++) {
                for (int i = 0; i < playerOrder.get(j).playerTerritories.size(); i++) { // first player
                    if (allButtons.get(p).getText().contains(playerOrder.get(j).playerTerritories.get(i).getName())) {
                        allButtons.get(p).setBackground(colArr[col]);
                        allButtons.get(p).setOpaque(true);
                    }
                }
            }
            col += 1;
        }
        this.setVisible(true);
    }

    /**
     * Drafts however many armies the player has chosen, puts them in the territory that was clicked.
     *
     * @param button
     */
    public void getDraft(JButton button) {
        JOptionPane.showMessageDialog(null, "The total amount of armies you have left this turn: " + game.armiesPerTurn);

        armies = new ArrayList<>();

        for (int i = 1; i <= game.armiesPerTurn; i++) {
            armies.add(i);
        }

        if (game.armiesPerTurn > 0) {
            String[] options = {"OK"};
            SpinnerListModel model = new SpinnerListModel(armies);
            JSpinner spin = new JSpinner(model);
            spin.setBounds(100, 100, 50, 30);
            JOptionPane.showOptionDialog(null, spin, "Enter the number of armies you want to add: ", 0, 1, null, options, null);
            int armiesToAdd = (int) spin.getValue();


            for (int j = 0; j < game.playerLinkedList.getFirst().playerTerritories.size(); j++) {
                if (button.getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(j).getName())) {
                    game.playerLinkedList.getFirst().playerTerritories.get(j).addNumberOfArmies(armiesToAdd);
                    game.armiesPerTurn -= armiesToAdd;
                    break;
                }
            }
            if (game.armiesPerTurn == 0) {
                JOptionPane.showMessageDialog(null, "You do not have any armies left.");
                endAttack.setVisible(true);
                JOptionPane.showMessageDialog(null, "ATTACK PHASE!");
            }
        }
        updateButtonText();
        this.setVisible(true);
    }

    /**
     * Finds the corresponding territory with the same name as the button, then saves the territory's index to a
     * global variable so it can be used in the attack method.
     *
     * @param button
     */
    public void saveAttackingTerritory(JButton button) {
        boolean isDone = false;
        for (int j = 0; j < game.playerLinkedList.getFirst().playerTerritories.size(); j++) {
            if (button.getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(j).getName())) {
                for (int i = 0; i < buttonArrayList.size(); i++) {
                    if (buttonArrayList.get(i) == button) {
                        attackingTerritoryIndex = i;
                        isDone = true;
                    }
                }
            }
        }
        if (!isDone) {
            JOptionPane.showMessageDialog(null, "You do not own this territory.");
        }
    }

    /**
     * Finds the corresponding territory with the same name as the button, then saves the territory's index to a
     * global variable so it can be used in the attack method. If the territory is owned by the player or not
     * neighbouring the selected territory, it tells the player they cannot attack said territory.
     *
     * @param button
     * @return
     */
    public boolean saveAttackedTerritory(JButton button) {
        boolean isDone = false;
        boolean namePresent1 = true;
        boolean namePresent2 = false;
        String territoryName = "";
        int index = 0;

        for (int i = 0; i < game.playerLinkedList.getFirst().playerTerritories.size(); i++) {
            if (buttonArrayList.get(attackingTerritoryIndex).getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(i).getName())) {
                index = i;
            }
        }

        for (int i = 0; i < buttonArrayList.size(); i++) {
            if (buttonArrayList.get(i) == button) {
                attackedTerritoryIndex = i;
            }
        }

        for (int i = 0; i < buttonArrayList.size(); i++) {
            if (buttonArrayList.get(i) == button) {
                territoryName = buttonArrayList.get(i).getText();
            }
        }
        for (int i = 0; i < game.playerLinkedList.getFirst().playerTerritories.size(); i++) {
            if (territoryName.contains(game.playerLinkedList.getFirst().playerTerritories.get(i).getName())) {
                namePresent1 = false;
            }
        }

        for (int i = 0; i < game.playerLinkedList.getFirst().playerTerritories.get(index).getNeighbor().size(); i++) {
            if (territoryName.contains(game.playerLinkedList.getFirst().playerTerritories.get(index).getNeighbor().get(i).getName())) {
                namePresent2 = true;
            }
        }
        if (!namePresent1 || !namePresent2) {
            JOptionPane.showMessageDialog(null, "You cannot attack this territory.");
        } else {
            isDone = true;
        }
        return isDone;
    }

    /**
     * Finds the 'attacking territory' and attacks the 'attacked territory'. If player won, prints a message and changes
     * the colour of the button's corresponding territory. Removes territory from the loser's list of owned
     * territories.
     *
     * @param button
     */
    public void attack(JButton button) {
        //boolean flag = false;
        Territory attackingTerritory = chooseAttackingTerritory();
        if(attackingTerritory.getNumberOfArmies() > 1) {
        boolean isBlitz = false;
        String[] options2 = {"Yes", "No"};
        int choice2 = JOptionPane.showOptionDialog(null, "Would you like to blitz!", "Choose wisely", 0, 2, null, options2, null);
        if (choice2 == 0) {
            isBlitz = true;
        } else if (choice2 == 1) {
            isBlitz = false;
        }

            Territory attackedTerritory = chooseAttackedTerritory();
            if ((Game.attack(isBlitz, game.playerLinkedList.getFirst(), attackingTerritory, attackedTerritory))) {
                JOptionPane.showMessageDialog(null, "You have won the battle.");
                operationLightUpSneakers();
                updateButtonText();
            } else {
                JOptionPane.showMessageDialog(null, "You have lost the battle.");
                updateButtonText();
            }
            if (game.playerLinkedList.getFirst().playerTerritories.size() == game.allTerritoriesList.size()) {
                JOptionPane.showMessageDialog(null, "Congratulations!!!!! " + game.playerLinkedList.getFirst().getName() + " won the game!!!");
                quitGame();
            }

            for (int i = 0; i < game.playerLinkedList.size(); i++) {
                if (game.playerLinkedList.get(i).playerTerritories.isEmpty()) {
                    JOptionPane.showMessageDialog(null, game.playerLinkedList.get(i).getName() + " lost the game :(");
                    game.playerLinkedList.remove(i);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You must select a territory with more 1 army");
        }
    }

    /**
     * Selects the territory you chose when you pressed the corresponding button.
     *
     * @return
     */
    public Territory chooseAttackingTerritory() {
        Territory territory = new Territory("null", 0);
        for (int i = 0; i < game.allTerritoriesList.size(); i++) {
            if (buttonArrayList.get(attackingTerritoryIndex).getText().contains(game.allTerritoriesList.get(i).getName())) {
                territory = game.allTerritoriesList.get(i);
            }
        }
        return territory;
    }

    /**
     * Selects the territory you chose when you pressed the corresponding button.
     *
     * @return
     */
    public Territory chooseAttackedTerritory() {
        Territory territory = new Territory("null", 0);
        for (int i = 0; i < game.allTerritoriesList.size(); i++) {
            if (buttonArrayList.get(attackedTerritoryIndex).getText().contains(game.allTerritoriesList.get(i).getName())) {
                territory = game.allTerritoriesList.get(i);
            }
        }
        return territory;
    }

    /**
     * Verifies if all territories surounding attacking territory are owned by the current player.
     *
     * @param attackingIndex
     * @param attackingPlayer
     * @return
     */
    public static boolean attackVerification(int attackingIndex, Player attackingPlayer) {
        int index = 0;

        int ind = 0;

        for (int i = 0; i < game.playerLinkedList.getFirst().playerTerritories.size(); i++) {
            if (buttonArrayList.get(attackingIndex).getText().contains(game.playerLinkedList.getFirst().playerTerritories.get(i).getName())) {
                ind = i;
            }
        }

        for (int i = 0; i < attackingPlayer.playerTerritories.size(); i++) {
            for (int j = 0; j < attackingPlayer.playerTerritories.get(ind).getNeighbor().size(); j++) {
                if (attackingPlayer.playerTerritories.get(i).getName().equals(attackingPlayer.playerTerritories.get(ind).getNeighbor().get(j).getName())) {
                    index += 1;
                }
            }
        }

        if (attackingPlayer.playerTerritories.get(ind).getNeighbor().size() == index) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Shows messages that the player is done attacking, also sets up the fortification phase.
     */
    public void endAttack() {
        JOptionPane.showMessageDialog(null, "FORTIFICATION PHASE!");
        JOptionPane.showMessageDialog(null, "During the fortification phase, you can move troops from Territory#1 to Territory#2.");
        JOptionPane.showMessageDialog(null, "Choose the territory sending troops.");
        isFortification = true;
    }

    /**
     * Shows a message to the players that the CPU has drafted armies in a territory.
     * @param terr
     * @param armiesPerTurn
     */
    public void cpuDraftMessage(Territory terr, int armiesPerTurn) {
        updateButtonText();
        JOptionPane.showMessageDialog(null, "CPU drafted " + armiesPerTurn + " troops in " + terr.getName());
    }

    /**
     * Shows a message to the players that the CPU has attacked a territory.
     * @param isAttackWon
     */
    public void cpuAttackMessage(boolean isAttackWon) {
        if (isAttackWon) {
            JOptionPane.showMessageDialog(null, "CPU won the battle");
            updateButtonText();
            operationLightUpSneakers();
        } else {
            updateButtonText();
            JOptionPane.showMessageDialog(null, "CPU lost the battle.");
        }
        if (game.playerLinkedList.getFirst().playerTerritories.size() == 42) {
            JOptionPane.showMessageDialog(null, "CPU won the game!!!");
            quitGame();
        }

        for (int i = 0; i < game.playerLinkedList.size(); i++) {
            if (game.playerLinkedList.get(i).playerTerritories.isEmpty()) {
                JOptionPane.showMessageDialog(null, game.playerLinkedList.get(i).getName() + " lost the game :(");
                game.playerLinkedList.remove(i);
            }
        }
    }

    /**
     * Shows a message to the players that the CPU has fortified a territory.
     * @param territory1
     * @param territory2
     * @param armiesRandom
     * @param showMessage
     */
    public void cpuFortifyMessage(Territory territory1, Territory territory2, int armiesRandom, boolean showMessage) {
        updateButtonText();
        JOptionPane.showMessageDialog(null, territory1.getName() + " has " + territory1.getNumberOfArmies() + " and " + territory2.getName() + " has " + territory2.getNumberOfArmies());
        if (showMessage) {
            JOptionPane.showMessageDialog(null, "CPU is moving " + armiesRandom + " troops");
        }

    }

    /**
     * Saves the current game being played, can be loaded later on.
     * @param fileName
     */
    public void saveGame(String fileName) {

        File file = new File(fileName + ".json");

        try{
        FileOutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

        objectStream.writeObject(this);
        objectStream.writeObject(game);
        objectStream.writeObject(controller);
        objectStream.writeObject(help);
        objectStream.writeObject(quit);
        objectStream.writeObject(buttonArrayList);
        objectStream.writeObject(save);
        objectStream.writeObject(load);
        objectStream.writeObject(endAttack);
        objectStream.writeObject(endTurn);
        objectStream.writeObject(showTroops);

        objectStream.close();
        fileStream.close();

        JOptionPane.showConfirmDialog(this,
                "Saved game successfully.",
                "RISK",
                JOptionPane.DEFAULT_OPTION);
        }
        catch (Exception e) {
        JOptionPane.showConfirmDialog(this,
                e.toString() + "\nFailed to save game.",
                "RISK",
                JOptionPane.DEFAULT_OPTION);
        }
    }

    /**
     * Loads the saved game.
     * @param fileName
     */
    public void loadGame(String fileName) {

        //String[] options = {"Okay", "Cancel"};

        try {
            ObjectInputStream oStream = new ObjectInputStream(new FileInputStream(fileName + ".json"));
            RISKView rv = (RISKView) oStream.readObject();
            Game game = (Game) oStream.readObject();
            RISKController con = (RISKController) oStream.readObject();
            JMenuItem help = (JMenuItem) oStream.readObject();
            JMenuItem quit = (JMenuItem) oStream.readObject();
            ArrayList<JButton> bal = (ArrayList<JButton>) oStream.readObject();
            JMenuItem save = (JMenuItem) oStream.readObject();
            JMenuItem load = (JMenuItem) oStream.readObject();
            JMenuItem endAttack = (JMenuItem) oStream.readObject();
            JMenuItem endTurn = (JMenuItem) oStream.readObject();
            JMenuItem showTroops = (JMenuItem) oStream.readObject();

            oStream.close();

            rv.game = game;
            rv.controller = con;
            rv.help = help;
            rv.quit = quit;
            rv.buttonArrayList = bal;
            rv.save = save;
            rv.load = load;
            rv.endAttack = endAttack;
            rv.endTurn = endTurn;
            rv.showTroops = showTroops;

            rv.setVisible(true);
            this.dispose();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new RISKView();
    }


}