package kbe.frontendmgmt;


import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.playermgmt.BotPlayerService;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.repositories.GameInstanceRepository;
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

    public int getPassCounter() {
        return passCounter;
    }

    public void setPassCounter(int passCounter) {
        this.passCounter = passCounter;
    }

    String initialPlayer = null;

    private GameInstance gameInstance;

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Autowired
    GameInstanceRepository gameInstanceRepository;

    @Autowired
    public GameInstanceService GISI;

    public GameInstanceService getGISI() {
        return GISI;
    }

    public void setGISI(GameInstanceService GISI) {
        this.GISI = GISI;
    }

    @Autowired
    public FrontendView frontendView;

    public FrontendView getFrontendView() {
        return frontendView;
    }

    public void setFrontendView(FrontendView frontendView) {
        this.frontendView = frontendView;
    }

    @Autowired
    public PlayerService PLAYSI;

    public PlayerService getPLAYSI() {
        return PLAYSI;
    }

    public void setPLAYSI(PlayerService PLAYSI) {
        this.PLAYSI = PLAYSI;
    }

    @Autowired
    public BotPlayerService botPlayerService;

    public BotPlayerService getBotPlayerService() {
        return botPlayerService;
    }

    public void setBotPlayerService(BotPlayerService botPlayerService) {
        this.botPlayerService = botPlayerService;
    }

    @Autowired
    @Qualifier("playerRulesServiceArschlochImpl")
    private PlayerRulesService playerRulesArschlochService;

    public PlayerRulesService getPlayerRulesArschlochService() {
        return playerRulesArschlochService;
    }

    public void setPlayerRulesArschlochService(PlayerRulesService playerRulesArschlochService) {
        this.playerRulesArschlochService = playerRulesArschlochService;
    }

    @Autowired
    @Qualifier("playerRulesServicePresidentImpl")
    private PlayerRulesService playerRulesPresidentService;

    public PlayerRulesService getPlayerRulesPresidentService() {
        return playerRulesPresidentService;
    }

    public void setPlayerRulesPresidentService(PlayerRulesService playerRulesPresidentService) {
        this.playerRulesPresidentService = playerRulesPresidentService;
    }

    @Autowired
    public CardService cardService;

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

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
            validateBotMove(gameInstance);
        } else {
            try {
                String cardIndexes = JOptionPane.showInputDialog(null,
                        "Bitte Karten angeben (Positionen: 0-11, mit Komma getrennt)");
                String[] cardsIndexesArray = cardIndexes.split(",");
                List cardIndexesToBePlayed = new LinkedList<Integer>();
                for (int i = 0; i < cardsIndexesArray.length; i++) {
                    int f = Integer.parseInt(cardsIndexesArray[i]);

                    if (f < 0 | f > 11) {
                        validateMove();
                        break;
                    }
                    cardIndexesToBePlayed.add(f);
                }

                List tempCardList = new LinkedList<Card>();
                Boolean tempCardsEqual = true;

                for (int i = 0; i < cardIndexesToBePlayed.size(); i++) {
                    int r = (Integer) cardIndexesToBePlayed.get(i);
                    tempCardList.add((gameInstance.getCurrentPlayer().getHand().get(r)));
                    if (tempCardList.size() >= 2) {
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
                if (tempCardsEqual) {
                    if (gameInstance.getBoardCards() == null) {
                        gameInstance.setBoardCards(tempCardList);
                        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
                        addCurrentPlayerToResult(gameInstance);
                        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
                    } else {
                        if (tempCardList.size() != gameInstance.getBoardCards().size()) {
                            tempCardList = null;
                            validateMove();
                        }
                        try {
                            Card y = (Card) ((LinkedList) tempCardList).getFirst();

                            Card b = gameInstance.getBoardCards().get(0);

                            int c = y.compareTo(b);
                            if (c == 1) {
                                gameInstance.setBoardCards(tempCardList);
                                PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
                                addCurrentPlayerToResult(gameInstance);
                                gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
                            } else {
                                validateMove();
                            }
                        } catch (IndexOutOfBoundsException e) {
                            gameInstance.setBoardCards(tempCardList);
                            PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
                            addCurrentPlayerToResult(gameInstance);
                            gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
                        }
                    }
                } else {
                    validateMove();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
            try {
                if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {
                    gameInstance.setBoardCards(null);
                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                gameInstance.setBoardCards(null);
            }
            passCounter = 0;
        }
    }

    @Override
    public void validateBotMove(GameInstance gameInstance) {
        List<Card> cardsToPlay = new LinkedList<Card>();
        List<Card> botHandCards = new LinkedList<Card>();
        botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
        List<Card> higherCards = new LinkedList<Card>();
        if (gameInstance.getBoardCards() == null) {
            if (botHandCards.size() < 2) {
                cardsToPlay = botHandCards;
            } else if (botHandCards.size() >= 2) {
                cardsToPlay = botPlayerService.setTwoEqualCards(botHandCards);
            }
            updateAll(cardsToPlay, gameInstance);
        } else if (gameInstance.getBoardCards().size() == 1) {
            higherCards = botPlayerService.findHigherCards(botHandCards, gameInstance);
            if (higherCards.isEmpty()) {
                pass(gameInstance);
            } else {
                cardsToPlay.add(higherCards.get(0));
                updateAll(cardsToPlay, gameInstance);
            }
        } else if (gameInstance.getBoardCards().size() == 2) {
            List<Card> temp = new LinkedList<Card>();
            higherCards = botPlayerService.findHigherCards(botHandCards, gameInstance);
            if (higherCards.isEmpty()) {
                pass(gameInstance);
            } else {
                botPlayerService.setEqualCards(gameInstance, higherCards);
            }
        }
        botPlayerService.assOnBoard(gameInstance);
    }

    @Override
    public void updateAll(List<Card> cardsToPlay, GameInstance gameInstance) {
        gameInstance.setBoardCards(cardsToPlay);
        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), cardsToPlay);
        addCurrentPlayerToResult(gameInstance);
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        setPassCounter(0);
    }

    @Override
    public void pass(GameInstance gameInstance) {
        passCounter++;
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

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
            }
        }
    }

    @Override
    public void askInitialPlayerString(GameInstance gameInstance) {
        String initialPlayerForNextRound = JOptionPane.showInputDialog(null,
                "Wer soll bei jeder neuen Runde anfangen (Arschloch (a)/Praesident (p))?");
        if (initialPlayerForNextRound.equalsIgnoreCase("a")) {
            initialPlayer = "Arschloch";
        } else if (initialPlayerForNextRound.equalsIgnoreCase("p")) {
            initialPlayer = "President";
        } else {
            askInitialPlayerString(gameInstance);
        }
    }

    @Override
    public void setInitialPlayer(GameInstance gameInstance) throws IllegalArgumentException {
        if (initialPlayer == "Arschloch") {
            playerRulesArschlochService = new PlayerRulesServiceArschlochImpl();
            playerRulesArschlochService.determineInitialPlayer(gameInstance);
        } else if (initialPlayer == "President") {
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
            if (ifBotPlayer) {
                if (i == 0) {
                    Player playerHuman = PLAYSI.createPlayer(getUserNameInput());
                    players.add(playerHuman);
                }
                String name = "Bot" + (i + 1);
                Player playerBot = PLAYSI.createPlayer(name);
                players.add(playerBot);
            } else {
                Player player = PLAYSI.createPlayer(getUserNameInput());
                players.add(player);
            }

        }
        gameInstance.setPlayers(players);
        cardService.dealCardsToPlayers(gameInstance);

        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
    }

    @Override
    public void startSavedGame(GameInstance gameInstance) {
        JOptionPane.showMessageDialog(null, "Leider funktioniert die Datenbank momentan nicht. Sie werden zu einem neuen Spiel umgeleitet.");
        startNewGame(gameInstance);
//        int gameId = getGameId();
//        gameInstance = getLastPlayedGame(gameId);
//
//        gameInstance.setPlayers(gameInstance.getPlayers());
//        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
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
    public void startGame(GameInstance instance) {
        if (playNewGame()) {
            startNewGame(gameInstance);

        } else {
            startSavedGame(gameInstance);
        }
    }

    @Override
    public void gameStateEvaluation(GameInstance gameInstance) {
        String gameState = GISI.calculateGameState(gameInstance);
        if (gameState.equals("Running")) {
        } else {
            for (Player player : gameInstance.getPlayers()) {
                if (PLAYSI.hasCards(player)) {
                    gameInstance.setResult(player);
                }
            }
            showResultList(gameInstance);
            Boolean continueGame = getContinueGame();

            if (continueGame) {
                setPlayerRoles(gameInstance);

                for (int i = 0; i < gameInstance.getResult().size(); i++) {
                    gameInstance.getResult().get(i).setHandCards(new LinkedList<>());
                }
                gameInstance.getPlayers().clear();
                gameInstance.setPlayers(gameInstance.getResult());
                cardService.dealCardsToPlayers(gameInstance);
                cardService.swapCards(gameInstance);

                gameInstance.setBoardCards(null);
            } else {
                System.exit(0);
            }
        }
    }

    @Override
    public GameInstance getLastPlayedGame(int gameId) {
        GameInstance gameInstance = gameInstanceRepository.findByGameId(gameId);
        return gameInstance;
    }

    public int getGameIdForUser(GameInstance instance) {
        return instance.getGameId();
    }

    @Override
    public void saveCurrentGame(GameInstance instance) {
        gameInstanceRepository.save(instance);
    }

}