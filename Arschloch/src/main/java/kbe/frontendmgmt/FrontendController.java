package kbe.frontendmgmt;


import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.playermgmt.BotPlayerService;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServiceArschlochImpl;
import kbe.rulesmgmt.PlayerRulesServicePresidentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Controller zum Frontend. Hier werden Spielmodel gehalten und der programmseitige Kontrollfluss geregelt.
 */
@Controller
@Transactional
public class FrontendController implements FrontendService {

    int passCounter = 0;
    private GameInstance gameInstance;

    public void setGISI(GameInstanceService GISI) {
        this.GISI = GISI;
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    public GameInstanceService getGISI() {
        return GISI;
    }

    public FrontendView getFrontendView() {
        return frontendView;
    }

    public PlayerService getPLAYSI() {
        return PLAYSI;
    }

    public BotPlayerService getBotPlayerService() {
        return botPlayerService;
    }
    public CardRulesService getCardRulesService() {
        return cardRulesService;


    public CardService getCardService() {
        return cardService;
    }

    @Autowired
    GameInstanceRepository gameInstanceRepository;

    @Autowired
    public GameInstanceService GISI;

    public void setFrontendView(FrontendView frontendView) {
        this.frontendView = frontendView;
    }

    @Autowired
    public FrontendView frontendView;

    public void setPLAYSI(PlayerService PLAYSI) {
        this.PLAYSI = PLAYSI;
    }

    @Autowired
    public PlayerService PLAYSI;

    public void setBotPlayerService(BotPlayerService botPlayerService) {
        this.botPlayerService = botPlayerService;
    }

    @Autowired
    public BotPlayerService botPlayerService;
    
    @Autowired
    @Qualifier("playerRulesServiceArschlochImpl")
    private PlayerRulesService playerRulesArschlochService;
    
    @Autowired
    @Qualifier("playerRulesServicePresidentImpl")
    private PlayerRulesService playerRulesPresidentService;

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public CardService cardService;

    public int getPassCounter() {
        return passCounter;
    }

    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }
    
    String initialPlayer = null;

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

    @Override
    public void validateMove() {
        if (gameInstance.getCurrentPlayer().getName().contains("Bot")) {
            System.out.println("botPlayerService.validateBotMove(gameInstance)");
            botPlayerService.validateBotMove(gameInstance);
        } else {

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
                        addCurrentPlayerToResult(gameInstance);
                        // setzt naechsten Spieler nach validem Spielzug
                        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
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
                        }
                    }
                } else {
                    // falsche Karten ausgewählt
                    validateMove();
                }
                System.out.println(cardIndexesToBePlayed.size());
            } catch (Exception e) {
                e.getStackTrace();
            }
            // Wenn BoardCards = Ass, wird das Feld abgeraumt,
            try {
                if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {

                    // Setzen der boardCards auf null, Update Frontend
                    gameInstance.setBoardCards(null);
                    frontendView.updateCurrentBoardCardPanels(gameInstance);

                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                gameInstance.setBoardCards(null);
                frontendView.updateCurrentBoardCardPanels(gameInstance);
            }
            // Reset des PAssspielzug-Counters nach jedem validen Spielzug
            passCounter = 0;
        }
    }

    @Override
    public void setPlayerRoles(GameInstance gameInstance) {
        int resultSize = gameInstance.getResult().size();
        gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT1);
        gameInstance.getResult().get(resultSize - 1).setRole(Player.Role.ARSCHLOCH1);

        if (resultSize > 3) {
            gameInstance.getResult().get(1).setRole(Player.Role.PRAESIDENT2);
            gameInstance.getResult().get(resultSize - 2).setRole(Player.Role.ARSCHLOCH2);
        }
    }

    @Override
    public void addCurrentPlayerToResult(GameInstance gameInstance) {
        if (gameInstance.getCurrentPlayer().getHand().isEmpty()) {
            if (!gameInstance.getResult().contains(gameInstance.getCurrentPlayer())) {
                gameInstance.setResult(gameInstance.getCurrentPlayer());
//                System.out.println("RESULT LISTE " + gameInstance.getResult().size());
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
    public void setInitialPlayer(GameInstance gameInstance) throws IllegalArgumentException {
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

    @Override
    public Boolean getContinueGame() throws IllegalArgumentException {
        String continueGame = JOptionPane.showInputDialog(null, "Weiterspielen (J/N)?");
        if (continueGame.equalsIgnoreCase("j") | continueGame.equalsIgnoreCase("ja")) {
        	setInitialPlayer(gameInstance);
        	showInitialPlayer(gameInstance);
            return true;
        } else {
            return false;
        }
    }
    
    public void showInitialPlayer(GameInstance gameInstance) {
    	JOptionPane.showMessageDialog(null, "Es fängt an: " + gameInstance.getCurrentPlayer().getName());
    }

    @Override
    public Boolean playNewGame() throws IllegalArgumentException {
        String newGame = JOptionPane.showInputDialog(null, "Neues Spiel beginnen oder gespeichertes wiederherstellen (J/N)?");
        if (newGame.equalsIgnoreCase("j") | newGame.equalsIgnoreCase("ja")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getUserNameInput() {
        String spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");
        if (spielerName.isEmpty()) {
            return getUserNameInput();
        } else {
            return spielerName;
        }
    }

    @Override
    public void showSavedGameId() {
        JOptionPane.showMessageDialog(null, "Die Spielnummer lautet: " + getGameIdForUser(gameInstance));
    }

    @Override
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

    @Override
    public int getUserCountInput() throws IllegalArgumentException {
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

    @Override
    public int getGameId() throws IllegalArgumentException {
        String userinput = JOptionPane.showInputDialog(null,
                "Bitte die Spielnummer eingeben");
        try {

            return Integer.valueOf(userinput);
        } catch (NumberFormatException e) {
            return getGameId();
        }
    }

    @Override
    public void startNewGame(GameInstance gameInstance) {
    	askInitialPlayerString(gameInstance);
        List<Player> players = new LinkedList<>();
        boolean ifBotPlayer = getIfBotPlayer();
        int playerCount = getUserCountInput();
        
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

    @Override
    public void startSavedGame(GameInstance gameInstance) {
        int gameId = getGameId();
        gameInstance = getLastPlayedGame(gameId);

        gameInstance.setPlayers(gameInstance.getPlayers());
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

    }

    @Override
    public Boolean getIfBotPlayer() throws IllegalArgumentException {
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

@Override
    public void startGame(GameInstance instance){
        if (playNewGame()) {
            startNewGame(gameInstance);

        } else {
            startSavedGame(gameInstance);
        }
    }

    @Override
    public void pass(GameInstance gameInstance){
        passCounter++;
        // setzen des naechsten Spielers
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        // reset der Current BoardCard, da jeder Spieler 1x gepasst hat

        int playersWithCardsCounter = 0;
        for (Player player : gameInstance.getPlayers()) {
            if (PLAYSI.hasCards(player)) {
                playersWithCardsCounter++;
            }
        }

        if (passCounter == playersWithCardsCounter - 1) {
            gameInstance.setBoardCards(null);
        }
    }

    @Override
    public void gameStateEvaluation(GameInstance gameInstance){
        String gameState = GISI.calculateGameState(gameInstance);
        if (gameState.equals("Running")) {
        } else {
            // letzten Spieler in Resultliste speichern, damit Roles richtig gesetzt werden
            for (Player player : gameInstance.getPlayers()) {
                if (PLAYSI.hasCards(player)) {
                    gameInstance.setResult(player);
                }
            }
            showResultList(gameInstance);
            // Weiter spielen? User Abfrage
            Boolean continueGame = getContinueGame();

            if (continueGame) {
                // Rollen herausfinden
                setPlayerRoles(gameInstance);

                for (int i = 0; i < gameInstance.getResult().size(); i++) {
                    gameInstance.getResult().get(i).setHandCards(new LinkedList<>());
                }
                // Spieler in neue Spielrunde uebernehmen
                gameInstance.setPlayers(gameInstance.getResult());
                // Karten austeilen
                getCardService().dealCardsToPlayers(gameInstance);
                // Karten entsprechend der Rollen austauschen
                getCardService().swapCards(gameInstance);
                // Setzen des ersten Spielers der nächsten Runde
                setInitialPlayerForNextRound(gameInstance);
                // Update der boardCards auf null, da frisches Spiel
                gameInstance.setBoardCards(null);
                // Frontend Update

            } else {
                // wenn nicht weitergespielt werden soll , schließt sich die Anwendung

                System.exit(0);
            }
        }
    }

    @Override
    public GameInstance getLastPlayedGame(int gameId) {

        GameInstance gameInstance = gameInstanceRepository.findByGameId(gameId);
//        List<Player> players = gameInstanceRepository.findPlayersByGameId(gameId);
//        for (Player player : players) {
//            Player player1 = new Player();
//            player1.setName(player.getName());
//            player1.setRole(player.getRole());
//        }
//
//        List<Card> cards = cardRepository.findAllCards();
//        for (Card card : cards) {
//            Card card1 = new Card();
//            card1.setSymbol(card.getSymbol());
//            card1.setZahl(card.getZahl());
//        }
        return gameInstance;

    }

    public int getGameIdForUser (GameInstance instance){
        return instance.getGameId();
    }

    @Override
    public void saveCurrentGame(GameInstance instance) {
//        gameInstanceRepository.deleteAll();
//        playerRepository.deleteAll();
//        cardRepository.deleteAll();
//
//        List<Player> players = instance.getPlayers();
//        for (Player player : players) {
//
//            for (Card card : player.getHandCards()) {
//                cardRepository.save(card);
//            }
//            playerRepository.save(player);
//        }
//        if (!instance.getBoardCards().isEmpty()) {
//            for (Card card : instance.getBoardCards()) {
//                cardRepository.save(card);
//            }
//        }
        gameInstanceRepository.save(instance);
    }
}