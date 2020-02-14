package kbe.frontendmgmt;


import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import kbe.historymgmt.HistoryService;
import kbe.historymgmt.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.playermgmt.PlayerService;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Controller zum Frontend. Hier werden Spielmodel gehalten und der programmseitige Kontrollfluss geregelt.
 */


@Controller
public class FrontendController implements FrontendService {

    private GameInstance gameInstance;

    @Autowired
    private GameInstanceService GISI;

    public void setGISI(GameInstanceService GISI) {
        this.GISI = GISI;
    }

    @Autowired
    private FrontendView frontendView;

    public void setFrontendView(FrontendView frontendView) {
        this.frontendView = frontendView;
    }

    @Autowired
    private PlayerService PLAYSI;

    public void setPLAYSI(PlayerService PLAYSI) {
        this.PLAYSI = PLAYSI;
    }



//    @Autowired
    private CardRulesService cardRulesService = new CardRulesServiceStandardImpl();


    @Autowired
    private HistoryService historyService;



    @Override
    public void init() {
        System.out.println("Initializing.......");

        historyService.tueEtwas();


        gameInstance = GISI.startGame();
        frontendView.createFrontendView(gameInstance);
    }


    /**
     * Eine Spielinstanz wird erstellt und zurückgegeben.
     * GUI wird mit Spielinstanz bestückt
     *
     * @return : eine Spielinstanz
     */
    private GameInstance startGame() {
        return new GameInstance();
    }


    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
    private void endRound(GameInstance game) {
    }

    public void validateMove(int[] selectedCards) {

        List tempCardList = new LinkedList<Card>();

        // geclickte Kartenfelder( Frontend) auslesen
        for (int i = 0; i > selectedCards.length; i++) {
            if (selectedCards[i] == 1) {
                tempCardList.add(gameInstance.getCurrentPlayer().getHand().get(i));
            }
        }
        // Spielzug validieren

        for (int validatedCounter = 0; validatedCounter < tempCardList.size(); validatedCounter++) {
            Card check = (Card) tempCardList.get(validatedCounter);

            // hier findet später die Überprüfung statt, ob der Spielzug richtig ist. Wenn die Liste die von Compare zurückkommt die gleiche ist wie die,
            // wenn ja, Karten von Hand des CurrentPlayers abziehen und mit getNextPlayer das Spiel weiterlaufen lassen.
            // wenn nicht, Auffoderung, erneut Karten auszuwählen
            if (check.compareTo(check) == 1) {

                PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);

                gameInstance.setBoardCards(tempCardList);

                gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

                frontendView.lblCurrentPlayer = new JLabel("Current Player : " + gameInstance.getCurrentPlayer().getName());

                if (gameInstance.getBoardCards().get(0).getZahl().toString() == "Ass") {
                    gameInstance.setBoardCards(null);
                }

                SwingUtilities.updateComponentTreeUI(frontendView);
            }
        }

    }

}


