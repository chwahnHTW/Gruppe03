package kbe.frontendmgmt;


import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.historymgmt.HistoryService;
import kbe.playermgmt.BotPlayerService;
import kbe.playermgmt.BotPlayerServiceImpl;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServiceArschlochImpl;
import kbe.rulesmgmt.PlayerRulesServicePresidentImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Controller zum Frontend. Hier werden Spielmodel gehalten und der programmseitige Kontrollfluss geregelt.
 */


@Controller
public class FrontendController implements FrontendService {

    int passCounter = 0;
    private GameInstance gameInstance;
    @Autowired
    private GameInstanceService GISI;
    @Autowired
    private FrontendView frontendView;
    @Autowired
    private PlayerService PLAYSI;
    @Autowired
    private BotPlayerService botPlayerService = new BotPlayerServiceImpl();
    @Autowired
    private CardRulesService cardRulesService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private CardService cardService;

    public void setGISI(GameInstanceService GISI) {
        this.GISI = GISI;
    }

    public void setFrontendView(FrontendView frontendView) {
        this.frontendView = frontendView;
    }

    public void setPLAYSI(PlayerService PLAYSI) {
        this.PLAYSI = PLAYSI;
    }
    
    @Autowired
    private BotPlayerService botPlayerService = new BotPlayerServiceImpl();

<<<<<<< HEAD
    @Autowired
    @Qualifier("playerRulesServiceArschlochImpl")
    private PlayerRulesService playerRulesArschlochService;
    
    @Autowired
    @Qualifier("playerRulesServicePresidentImpl")
    private PlayerRulesService playerRulesPresidentService;

    int passCounter = 0;

    public int getPassCounter() {
		return passCounter;
	}

	public void setPassCounter(int passCounter) {
		this.passCounter = passCounter;
	}

	@Autowired
    private HistoryService historyService;

    @Autowired
    private CardService cardService;
    
    String initialPlayer = null;
=======
    public int getPassCounter() {
        return passCounter;
    }

    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }
>>>>>>> master

    @Override
    public void init() {
        System.out.println("Initializing.......");
        gameInstance = GISI.startGame();
        frontendView.createFrontendView(gameInstance);

    }

    public GameInstance getGameInstance() {
        return this.gameInstance;
    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
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
     */
    public void validateMove() {
<<<<<<< HEAD
    	
    	if(gameInstance.getCurrentPlayer().getName().contains("Bot")) {
    		botPlayerService.validateBotMove(gameInstance);
    	}
    	else {
    		// Eingabe öffnen für Auswählen der Karten
=======
        if (gameInstance.getCurrentPlayer().getName() == "BotPlayer") {
            System.out.println("botPlayerService.validateBotMove(gameInstance)");
            botPlayerService.validateBotMove(gameInstance);
        } else {

            // Eingabe öffnen für Auswählen der Karten
>>>>>>> master
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
                        addCurrentPlayerToResult(gameInstance);
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
                                addCurrentPlayerToResult(gameInstance);

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

                            addCurrentPlayerToResult(gameInstance);

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
            passCounter = 0;
<<<<<<< HEAD
    	}

=======
        }
>>>>>>> master
    }


    /**
     * Setzen der Spielerrollen, Ueber die Reihenfolge, in der die Spieler in die
     * Ergebnisliste aufgenommen wurden laesst sich ermitteln, welche Rolle sie
     * haben ( erster Spieler, der fertig ist =
     * gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT) usw. ) Simple
     * Implementierung, da momentan nur mit 3 Spielern spielbar
     */
    public void setPlayerRoles(GameInstance gameInstance) {
        int resultSize = gameInstance.getResult().size();
        gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT1);
        gameInstance.getResult().get(resultSize - 1).setRole(Player.Role.ARSCHLOCH1);

        if (resultSize > 3) {
            gameInstance.getResult().get(1).setRole(Player.Role.PRAESIDENT2);
            gameInstance.getResult().get(resultSize - 2).setRole(Player.Role.ARSCHLOCH2);
        }
    }

    /**
     * Methode, die ueberprueft, ob ein Spieler noch Karten hat, um ihn, falls dem
     * nicht so ist, in die Erbegnissliste einzutragen, anhand derer spaeter die
     * Rollen der Spieler ermittelt werden
     */
    public void addCurrentPlayerToResult(GameInstance gameInstance) {
        if (gameInstance.getCurrentPlayer().getHand().isEmpty()) {
            if (!gameInstance.getResult().contains(gameInstance.getCurrentPlayer())) {
                gameInstance.setResult(gameInstance.getCurrentPlayer());

                System.out.println("RESULT LISTE " + gameInstance.getResult().size());
            }
        }
    }
    
    /**
     * Fragt den User ab nach welcher Regel gespielt werden soll.
     * Ob Arschloch oder President bei einer neuen Runde anfangen soll.
     * 
     * @param gameInstance
     */
    void askInitialPlayerString (GameInstance gameInstance) {
    	String initialPlayerForNextRound = JOptionPane.showInputDialog(null,
                "Wer soll bei jeder neuen Runde anfangen (Arschloch (a)/Praesident (p))?");
    	System.out.println("askInitialPlayerString");
        if (initialPlayerForNextRound.equalsIgnoreCase("a")) {
        	System.out.println("Arschloch soll anfangen");
        	initialPlayer = "Arschloch";
        	System.out.println("Arschloch soll anfangen2");
        } else if (initialPlayerForNextRound.equalsIgnoreCase("p")) {
        	System.out.println("President soll anfangen");
            initialPlayer = "President";
            System.out.println("President soll anfangen2");
        } else {
           askInitialPlayerString(gameInstance);
        }
    }
    
    /**
     * Setzt den Spieler, der anfagen soll,
     * nachdem über askInitialPlayer nach der Regel gefragt wurde.
     * 
     * @param gameInstance
     * @throws IllegalArgumentException
     */
    void setInitialPlayer(GameInstance gameInstance) throws IllegalArgumentException {
    	System.out.println("setInitialPlayer");
        if (initialPlayer == "Arschloch") {
        	System.out.println("Arschloch soll anfangen");
        	playerRulesArschlochService = new PlayerRulesServiceArschlochImpl();
        	playerRulesArschlochService.determineInitialPlayer(gameInstance);
        } else if (initialPlayer == "President") {
        	System.out.println("President soll anfangen");
            playerRulesPresidentService = new PlayerRulesServicePresidentImpl();
            playerRulesPresidentService.determineInitialPlayer(gameInstance);
        } else {
            setInitialPlayer(gameInstance);
        }
    }

    /**
     * Methode, die nach Spielabschluss erfaesst, ob weitergepspielt werden soll
     *
     * @return boolean - weiterspielen oder nicht ( true , false )
     */
    Boolean getContinueGame() throws IllegalArgumentException {
        String continueGame = JOptionPane.showInputDialog(null, "Weiterspielen (J/N)?");
        if (continueGame.equalsIgnoreCase("j") | continueGame.equalsIgnoreCase("ja")) {
        	setInitialPlayer(gameInstance);
        	showInitialPlayer(gameInstance);
            return true;
        } else {
            return false;
        }
    }

    /**
     * fragt ab, ob ein Spiel neu gestartet werden soll oder ein altes wiederhergestellt werden soll
     *
     * @return
     */
    Boolean playNewGame() throws IllegalArgumentException {
        String newGame = JOptionPane.showInputDialog(null, "Neues Spiel beginnen oder gespeichertes wiederherstellen (J/N)?");
        if (newGame.equalsIgnoreCase("j") | newGame.equalsIgnoreCase("ja")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Methode, um die Namen der Spieler eines Spiels zu erfassen
     *
     * @return - String - Name fuer einen User
     */
    String getUserNameInput() {
        String spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");
        if (spielerName.isEmpty()) {
            return getUserNameInput();
        } else {
            return spielerName;
        }
    }

    /**
     * Zeigt die gespeicherte Id der Spielinstanz an, um sie später wieder herstellen zu können
     */
    public void showSavedGameId() {
        JOptionPane.showMessageDialog(null, "Die Spielnummer lautet: ");
    }


    public void showResultList(GameInstance gameInstance) {
        if (gameInstance.getResult().size() == 3) {
            JOptionPane.showMessageDialog(null, "Die Reihenfolge der Gewinner ist: " +
                    gameInstance.getResult().get(0).getName() + " ist Erster, " +
                    gameInstance.getResult().get(1).getName() + " ist Zweiter, " +
                    gameInstance.getResult().get(2).getName() + " ist Dritter, "
            );
        } else if (gameInstance.getResult().size() == 4) {
            JOptionPane.showMessageDialog(null, "Die Reihenfolge der Gewinner ist: " +
                    gameInstance.getResult().get(0).getName() + " ist Erster, " +
                    gameInstance.getResult().get(1).getName() + " ist Zweiter, " +
                    gameInstance.getResult().get(2).getName() + " ist Dritter, " +
                    gameInstance.getResult().get(3).getName() + " ist Vierter, "
            );
        } else if (gameInstance.getResult().size() == 5) {
            JOptionPane.showMessageDialog(null, "Die Reihenfolge der Gewinner ist: " +
                    gameInstance.getResult().get(0).getName() + " ist Erster, " +
                    gameInstance.getResult().get(1).getName() + " ist Zweiter, " +
                    gameInstance.getResult().get(2).getName() + " ist Dritter, " +
                    gameInstance.getResult().get(3).getName() + " ist Vierter, " +
                    gameInstance.getResult().get(4).getName() + " ist Fünfter, "
            );
        } else {

        }
    }
    
    public void showInitialPlayer(GameInstance gameInstance) {
    	JOptionPane.showMessageDialog(null, "Es fängt an: " + gameInstance.getCurrentPlayer().getName());
    }

    /**
     * Methode, um Userinput ( Spieleranzahl ) zu erhalten
     *
     * @return vom Spieler eingegebene Spieleranzahl
     * @throws IllegalArgumentException
     */
    int getUserCountInput() throws IllegalArgumentException {
<<<<<<< HEAD
		String userinput = JOptionPane.showInputDialog(null,
				"Bitte Anzahl Spieler/Bots eingeben (Spieleranzahl muss 3 bis 5 sein)");
		try{
			if (userinput.equals("3") | userinput.equals("4")| userinput.equals("5")) {
				int spieleranzahl = Integer.parseInt(userinput);
				return Integer.valueOf(spieleranzahl);
			} else {
				return getUserCountInput();
			}
		} catch (NumberFormatException e){
			return getUserCountInput();
		}
	}
=======
        String userinput = JOptionPane.showInputDialog(null,
                "Bitte Anzahl Spieler/Bots eingeben (Spieleranzahl muss 3 bis 5 sein)");
        try {
            if (userinput.equals("3") | userinput.equals("4") | userinput.equals("5")) {
                int spieleranzahl = Integer.parseInt(userinput);
                return Integer.valueOf(spieleranzahl);
            } else {
                return getUserCountInput();
            }
        } catch (NumberFormatException e) {
            return getUserCountInput();
        }

    }
>>>>>>> master

    /**
     * GameId eingeben, um Spiel später wieder herstellen zu können
     *
     * @return gameId
     * @throws IllegalArgumentException
     */
    int getGameId() throws IllegalArgumentException {
        String userinput = JOptionPane.showInputDialog(null,
                "Bitte die Spielnummer eingeben");
        try {

            return Integer.valueOf(userinput);
        } catch (NumberFormatException e) {
            return getGameId();
        }
    }

    /**
     * Hier wird ein neues Spiel erstellt, in dem neue Spieler eingegeben werden.
     * Jeder Spieler bekommt eine Hand an Karten und es wird der erste Spieler gesetzt
     *
     * @param gameInstance : Spiel Instanz
     */
    public void startNewGame(GameInstance gameInstance) {
    	askInitialPlayerString(gameInstance);
        List<Player> players = new LinkedList<>();
        boolean ifBotPlayer = getIfBotPlayer();
        int playerCount = getUserCountInput();
<<<<<<< HEAD
        
        for (int i = 0; i < playerCount; i++) {
			if(ifBotPlayer) {
				System.out.println("ifBotPlayer is true");
				if(i == 0) {
					System.out.println("i == 0");
					Player playerHuman = PLAYSI.createPlayer(getUserNameInput());
					players.add(playerHuman);
					System.out.println("human added");
				}
				String name = "Bot"+(i+1);
				Player playerBot = PLAYSI.createPlayer(name);
				players.add(playerBot);
				System.out.println("bots added");
			} else { //keine botplayer
				Player player = PLAYSI.createPlayer(getUserNameInput());
	            players.add(player);
			}
			
		}
=======

        for (int i = 0; i < playerCount; i++) {
            if (ifBotPlayer) {
                System.out.println("ifBotPlayer is true");
                if (i == 0) {
                    System.out.println("i == 0");
                    Player playerHuman = PLAYSI.createPlayer(getUserNameInput());
                    gameInstance.getPlayers().add(playerHuman);
                    System.out.println("human added");
                }
                Player playerBot = PLAYSI.createPlayer("BotPlayer");
                gameInstance.getPlayers().add(playerBot);
                System.out.println("bots added");
            } else { //keine botplayer
                Player player = PLAYSI.createPlayer(getUserNameInput());
                players.add(player);
            }
        }
>>>>>>> master
        gameInstance.setPlayers(players);
        cardService.dealCardsToPlayers(gameInstance);

        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        
//        for (int i = 0; i < playerCount; i++) {
//            Player player = PLAYSI.createPlayer(getUserNameInput());
//            players.add(player);
//        }
//        gameInstance.setPlayers(players);
//        cardService.dealCardsToPlayers(gameInstance);
//
//        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
    }

    /**
     * Hier wird eine gespeicherte Spielinstanz wieder hergestellt
     *
     * @param gameInstance gameInstanz
     */
    public void startSavedGame(GameInstance gameInstance) {
        int gameId = getGameId();
        gameInstance = historyService.getLastPlayedGame(gameId);

        gameInstance.setPlayers(gameInstance.getPlayers());
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

    }
<<<<<<< HEAD
    
    /**
     * Fragt, ob User mit Bots spielen möchte.
     * 
     * @return
     * @throws IllegalArgumentException
     */
    public boolean getIfBotPlayer() throws IllegalArgumentException {
		String botPlayer = JOptionPane.showInputDialog(null, "Mit Bots spielen (J/N)?");
		try {
			if (botPlayer.equalsIgnoreCase("j") | botPlayer.equalsIgnoreCase("ja")) {
				return true;
			} else if (botPlayer.equalsIgnoreCase("n") | botPlayer.equalsIgnoreCase("nein")) {
				return false;
			} else {
				return getIfBotPlayer();
			}
		} catch (Exception e) {
			return getIfBotPlayer();
		}
		
	}
	
//	public void whatever(){
//    	if ("eingabe" == "arschlosch") {
//    		cardRulesService = new CardRulesServiceStandardImpl();
//    	} else {
//    		cardRulesService = new CardRulesServicePresidentImpl();
//    	}
//    }
    
}

=======
>>>>>>> master

    /**
     * Fragt, ob User mit Bots spielen möchte.
     *
     * @return
     * @throws IllegalArgumentException
     */
    public boolean getIfBotPlayer() throws IllegalArgumentException {
        String botPlayer = JOptionPane.showInputDialog(null, "Mit Bots spielen (J/N)?");
        try {
            if (botPlayer.equalsIgnoreCase("j") | botPlayer.equalsIgnoreCase("ja")) {
                return true;
            } else if (botPlayer.equalsIgnoreCase("n") | botPlayer.equalsIgnoreCase("nein")) {
                return false;
            } else {
                return getIfBotPlayer();
            }
        } catch (Exception e) {
            return getIfBotPlayer();
        }

    }
}


//	public void whatever(){
//    	if ("eingabe" == "arschlosch") {
//    		cardRulesService = new CardRulesServiceStandardImpl();
//    	} else {
//    		cardRulesService = new CardRulesServicePresidentImpl();
//    	}
//    }
