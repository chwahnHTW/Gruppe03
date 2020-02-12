package kbe.frontendmgmt;


import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
    
    int passCounter = 0;




    @Override
    public void init() {
        System.out.println("Initializing.......");
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

    
	/**
	 * Validiert einen Spielzug. Nimmt hierfuer Userinput entgegen und vergleicht
	 * diesen mit BoardCards. Ueber den Rueckgabewert der Eingabeaufforderung wird
	 * der Spielfluss gesteuert Valider Move : Karten vom Spieler abziehen, als
	 * BoardCards setzen, Spielstatus pruefen, nextPlayer setzen und weiterspielen(
	 * oder Dialog zum Beenden / Neustarten des Spiels aufrufen) Invalider move :
	 * Erneute Eingabeaufforderung, kein getNextPlayer
	 * 
	 */
    public void validateMove() {

		// Eingabe öffnen für Auswählen der Karten
		try {
			String cardIndexes = JOptionPane.showInputDialog(null,
					"Bitte Karten angeben (Positionen: 0-11, mit Komma getrennt)");

			// Eingabe in Array speichern, ueber Komma getrennt
			String[] cardsIndexesArray = cardIndexes.split(",");
			// Liste der zu spielenden Indizes
			List cardIndexesToBePlayed = new LinkedList<Integer>();

			// für die Länge des Userinputs Indizes der Karten speichern
			for (int i = 0; i < cardsIndexesArray.length; i++) {
				int f = Integer.parseInt(cardsIndexesArray[i]);

				if (f < 0 | f > 11) {
					validateMove();
					break;
				}
				cardIndexesToBePlayed.add(f);
			}

			// Liste, in der die aus dem Array ausgelesenen, selektierten Karten erfasst und
			// gehalten werden
			List tempCardList = new LinkedList<Card>();
			Boolean tempCardsEqual = true;

			// geclickte Kartenfelder( Frontend) auslesen
			for (int i = 0; i < cardIndexesToBePlayed.size(); i++) {
				// tempCards werden anhand der eingegeben Zahlen geholt
				int r = (Integer) cardIndexesToBePlayed.get(i);
				tempCardList.add((gameInstance.getCurrentPlayer().getHand().get(r)));
				// Wenn Karte im Frontend geclickt wurde, wird Sie in selectedCards
				// erfasst. Durch dessen Iterierung erhalten wir alle selektierten Karten

				// tempCards valid? compareTo -> die ausgewählten Karten müssen die gleichen
				// Zahlen haben
				if (tempCardList.size() >= 2) {
					// gerade hinzugefügte Card
					Card x = (Card) tempCardList.get(i);
					Card y = (Card) ((LinkedList) tempCardList).getFirst();

					int c = x.compareTo(y);
					if (c == 0) {
						tempCardsEqual = true;
					} else {
						tempCardsEqual = false;
					}
				}
			}

			// Spielzug validieren
			// hier findet später die Überprüfung statt, ob der Spielzug richtig ist. Wenn
			// die Liste die von Compare zurückkommt die gleiche ist wie die,die wir in
			// tempList als selektierte Karten haben
			// wenn ja, Karten von Hand des CurrentPlayers abziehen und mit getNextPlayer
			// das Spiel weiterlaufen lassen.
			// wenn nicht, Auffoderung, erneut Karten auszuwählen

			// tempCards valid? compareTo -> die ausgwählten Karten müssen höher sein als
			// die BoardCards

			if (tempCardsEqual) {
				// BoardCards = null -> alle gespielten KArten sind valide, solange sie
				// denselben Zahlenwert haben
				if (gameInstance.getBoardCards() == null) {
					gameInstance.setBoardCards(tempCardList);
					// Entfernen der Karten des validen Spielzuges aus Hand des Players
					PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
					// Prueft, ob aktueller Spieler noch Karten hat, oder nicht
					// wenn nein, wird er in result ( "Siegerliste, Rangfolge der Spieler" )
					// aufgenommen, um spaeter die Rollen fuer ein potentielles
					// weiteres Spiel zu ermitteln
					addCurrentPlayerToResult();
					// setzt naechsten Spieler nach validem Spielzug
					gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
					// Update Frontend
					frontendView.updateCurrentBoardCardPanels(gameInstance);
					frontendView.updateCardButtons(gameInstance);
					frontendView.updateCurrentPlayerLabel();
				} else {
					// ungueltiger Spielzug : erneute Eingabe
					if (tempCardList.size() != gameInstance.getBoardCards().size()) {
						tempCardList = null;
						validateMove();
					}
					// Vergleich der selektierten Indizes mit den BoardCards
					try {
						Card y = (Card) ((LinkedList) tempCardList).getFirst();

						Card b = gameInstance.getBoardCards().get(0);

						int c = y.compareTo(b);
						// valider Spielzug
						if (c == 1) {
							// tempCards werden als Boardcards gesetzt
							gameInstance.setBoardCards(tempCardList);
							// tempCards werden von der Hand des Spielers entfernt
							PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
							// Pruefung, obn Spieler keine Karten mehr hat
							addCurrentPlayerToResult();

							// nächsten Spieler setzen
							gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
							// Update Frontend
							frontendView.updateCurrentBoardCardPanels(gameInstance);
							frontendView.updateCardButtons(gameInstance);
							frontendView.updateCurrentPlayerLabel();
						} else {
							// falsche Karten ausgewählt
							validateMove();
						}
					}
					// Exception Handling
					catch (IndexOutOfBoundsException e) {
						System.out.print("no board cards to validate against, move passed");
						gameInstance.setBoardCards(tempCardList);
						PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);

						addCurrentPlayerToResult();

						gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

						frontendView.updateCurrentBoardCardPanels(gameInstance);
						frontendView.updateCardButtons(gameInstance);
						frontendView.updateCurrentPlayerLabel();
					}

				}
			} else {
				// falsche Karten ausgewählt
				validateMove();
			}
			System.out.println(cardIndexesToBePlayed.size());
		} catch (Exception e) {

		}
		// Wenn BoardCards = Ass, wird das Feld abgeraumt,
		try {
			if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {

				// Setzen der boardCards auf null, Update Frontend
				gameInstance.setBoardCards(null);
				frontendView.updateCurrentBoardCardPanels(gameInstance);

			}
		} catch (NullPointerException e) {
			gameInstance.setBoardCards(null);
			frontendView.updateCurrentBoardCardPanels(gameInstance);
		} catch (IndexOutOfBoundsException IOOB) {
			gameInstance.setBoardCards(null);
			frontendView.updateCurrentBoardCardPanels(gameInstance);
		}
		// Reset des PAssspielzug-Counters nach jedem validen Spielzug
		passCounter  = 0;
	}
    
	private void addCurrentPlayerToResult() {
		if (gameInstance.getCurrentPlayer().getHand().isEmpty()) {
			if (!gameInstance.getResult().contains(gameInstance.getCurrentPlayer())) {
				gameInstance.setResult(gameInstance.getCurrentPlayer());
				System.out.println("RESULT LISTE " + gameInstance.getResult().size());
			}
		}
	}


}


