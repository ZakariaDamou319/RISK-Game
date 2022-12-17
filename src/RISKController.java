import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class RISKController implements ActionListener, Serializable {
    private Game game;
    private RISKView rv;
    private boolean showTroops = false;

    /**
     * Constructs a new RISKController with a Game and RISKView.
     * @param game
     * @param rv
     */
    public RISKController(Game game, RISKView rv) {
        this.game = game;
        this.rv = rv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rv.quit) {
            rv.quitGame();
        } else if (e.getSource() == rv.help) {
            rv.helpMe();
        } else if (e.getSource() == rv.endTurn) {
            rv.changePlayerTurn();
            rv.turnMessage(game.playerLinkedList.getFirst().getName());
        } else if (e.getSource() == rv.showTroops) {
            showTroops = true;
        } else if(e.getSource() == rv.endAttack){
            rv.endAttack();

        } else if (e.getSource() == rv.save) {
            rv.saveGame("SavedGames");
        } else if (e.getSource() == rv.load) {
            rv.loadGame("SavedGames");
        } else {
            for (int i = 0; i < rv.buttonArrayList.size(); i++) {
                if (e.getSource() == rv.buttonArrayList.get(i)) {
                    if (showTroops){
                        rv.showTroops(rv.buttonArrayList.get(i));
                        showTroops = false;
                    } else {
                        if (rv.armiesPerTurn > 0) {
                            if (rv.isPlayerCountry(rv.buttonArrayList.get(i))) {
                                rv.setUpdraft(rv.buttonArrayList.get(i));
                            }
                        } else if(rv.isFortification){
                            if(!rv.territoryOneDone){
                                rv.setUpFortification(rv.buttonArrayList.get(i), false);
                            }else {
                                rv.setUpFortification(rv.buttonArrayList.get(i), true);
                            }
                        } else {
                            rv.setUpAttack(rv.buttonArrayList.get(i));
                        }
                    }

                }
            }
        }


    }
}