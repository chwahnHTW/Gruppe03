package kbe.frontendmgmt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.gamemgmt.GameInstance;

import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServicePresidentFirstImpl;
import org.springframework.stereotype.Service;

///**
// * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
// * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
// * Class - FrontendView
// * Eine Klasse, die die Spielinstanz realisiert
// * Hier läuft das Spiel im Großteil ab.
// * @param PLAYSI - PlayerServiceImplementierung
// * @param cardService - CardServiceImplementierung
// * @param playerRulesService - PlayerRuleServiceImplementierung
// * @param cardRulesService - CardRuleServiceImplementierung
// * @param gameInstance - Spielinstanz - Hier werden Spieler und deren Karten gehalten
// * @param contentPane - Content-Pane - Hauptfenster der GUI
// * @param btnPlaycards . Button, mit dem die Validierung und Ausführung eines Spielzugs getriggert wird.
// * @param btnPass - Button, mit dem ein Parr-Spielzug getätigt wird
// * @param lblCurrentPlayer - Label, das den momentanen Spieler anzeigt
// * @param currentBoardCardPanel1-4 - Panels, die gameInstance.boardCards anzeigen
// * @param lblPlayers - Label fuer die Playernamen
// * @param playerNamesPanel - Panel fuer die PlayerNamen
// * @param lblCurrentBoardcards -  LAbel fuer gameInstance.boardCards
// * @param btnStartGame - Button, um das Spiel zu starten
// * @param btnPlayerCard0-11 - Buttons, um Karten anzuzeigen
// * @param selectedCards - vom Süpieler durch Anclicken ausgewählte Karten
// * @param
// * @param
// *
// *
// */


@Service
public class FrontendView extends JFrame {

//	private FrontendController frontendController

    // @Autowired
    private PlayerService PLAYSI = new PlayerServiceImpl();
    // @Autowired
    private CardService cardService = new CardServiceImpl();
    // @Autowired
    private PlayerRulesService playerRulesService = new PlayerRulesServicePresidentFirstImpl();

    private CardRulesService cardRulesService = new CardRulesServiceStandardImpl();
    // Autowired
//	private FrontendController frontendController = new FrontendController();

    private GameInstance gameInstance;

    private JPanel contentPane;
    private JButton btnPlaycards;
    private JButton btnPass;
    int passCounter = 0;
    public JLabel lblCurrentPlayer;
    private JPanel currentBoardCardPanel1;
    private JPanel currentBoardCardPanel2;
    private JPanel currentBoardCardPanel3;
    private JPanel currentBoardCardPanel4;
    private JLabel lblPlayers;
    private JPanel playerNamesPanel;
    private JLabel lblCurrentBoardcards;
    private JButton btnStartGame;
    private JPanel btnPlayerCard0;
    private JPanel btnPlayerCard1;
    private JPanel btnPlayerCard2;
    private JPanel btnPlayerCard3;
    private JPanel btnPlayerCard4;
    private JPanel btnPlayerCard5;
    private JPanel btnPlayerCard6;
    private JPanel btnPlayerCard7;
    private JPanel btnPlayerCard8;
    private JPanel btnPlayerCard9;
    private JPanel btnPlayerCard10;
    private JPanel btnPlayerCard11;
//    private int[] selectedCards = new int[12];

    /**
     * Methode, um das Frame des Frontends zu erstellen
     * Im Frame befindet sich @param btnStartGame, ein JButton, der ueber einen actionListener das Spiel startet. der Spieler mit der
     * Karte HERZ_SIEBEN wird über @method determineInitialPlayer() ermittelt. Anschließend wird die Karte HERZ_SIEBEN seiner Hand über
     *
     * @param gameInstance - Spielinstanz
     * @return void
     * @method removeFromHand() entzogen und als gameInstance.boardCards gesetzt. Daraufhin wird der naechste Spieler im Spiel als currentPlayer
     * gesetzt und die UI geupdated (Update funktioniert irgendwie nicht wirklich)
     */
    public void createFrontendView(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 153));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameInstance.players = new LinkedList<>();
                int playerCount = getUserCountInput();
                System.out.println(playerCount);
                for (int i = 0; i < playerCount; i++) {
                    Player player = PLAYSI.createPlayer(getUserNameInput());
                    gameInstance.players.add(player);
                }
                cardService.dealCardsToPlayers(gameInstance);
                try {
                    gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

                    System.out.println(gameInstance.getCurrentPlayer().getName());

                    // nachdem alle automatischen Vorbereitungen getroffen sind, kann das Frontend vollstaendig aufgebaut werden
                    setupFrontend();
                    // images in btnPlayerCard0-11 updaten, da anderer Spieler an der Reihe sein sollte ( funktioniert nicht, ohne getNextPlayer()
                    // keine genaue Fehlerquelle bestimmbar
                    updateCardButtons(gameInstance);

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        btnStartGame.setForeground(Color.WHITE);
        btnStartGame.setBackground(new Color(0, 0, 153));
        btnStartGame.setBounds(489, 262, 180, 63);
        contentPane.add(btnStartGame);
    }

    /**
     * Methode, um Userinput ( Spieleranzahl ) zu erhalten
     *
     * @return -
     * @throws IllegalArgumentException -
     */
    private int getUserCountInput() throws IllegalArgumentException {
        String spieleranzahl = JOptionPane.showInputDialog(null, "Bitte Spieleranzahl eingeben (Spieleranzahl muss 3 sein)");
        if (spieleranzahl.equals("3")) {
            return Integer.valueOf(spieleranzahl);
        } else {
            return getUserCountInput();
        }
    }

    /**
     * @return -
     */
    String getUserNameInput() {
        String spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");
        return spielerName;
    }


    /**
     * Create the frame.
     */
    void setupFrontend() throws IOException {

        this.remove(btnStartGame);
        btnPlaycards = new JButton("PlayCard(s)");
        btnPlaycards.setForeground(Color.WHITE);
        btnPlaycards.setBackground(new Color(0, 0, 153));
        btnPlaycards.setBounds(721, 335, 99, 21);
        btnPlaycards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateMove();
                updateCardButtons(gameInstance);
            }
        });

        contentPane.add(btnPlaycards);

        btnPass = new JButton("Pass");
        btnPass.setForeground(Color.WHITE);
        btnPass.setBackground(new Color(255, 0, 0));
        btnPass.setBounds(859, 335, 99, 21);
        contentPane.add(btnPass);
        btnPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("PASSEN");
                passCounter++;
                
                if(passCounter == gameInstance.getPlayers().size()) {
                	gameInstance.boardCards.clear();
                	System.out.println("COUNTER gleich Anzahl Spieler");
                }
            }
        });

        lblCurrentPlayer = new JLabel("Current Player :" + gameInstance.getCurrentPlayer().getName());
        lblCurrentPlayer.setBounds(104, 276, 155, 34);
        contentPane.add(lblCurrentPlayer);

        currentBoardCardPanel1 = new JPanel();
        currentBoardCardPanel1.setBounds(532, 93, 99, 125);
        contentPane.add(currentBoardCardPanel1);

        currentBoardCardPanel2 = new JPanel();
        currentBoardCardPanel2.setBounds(641, 93, 99, 125);
        contentPane.add(currentBoardCardPanel2);

        currentBoardCardPanel3 = new JPanel();
        currentBoardCardPanel3.setBounds(750, 93, 99, 125);
        contentPane.add(currentBoardCardPanel3);

        currentBoardCardPanel4 = new JPanel();
        currentBoardCardPanel4.setBounds(859, 93, 99, 125);
        contentPane.add(currentBoardCardPanel4);

        lblPlayers = new JLabel("Players");
        lblPlayers.setBounds(107, 67, 73, 29);
        contentPane.add(lblPlayers);

        playerNamesPanel = new JPanel();
        playerNamesPanel.setBounds(104, 93, 98, 125);
        String playerNames = "<html>";
        for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            playerNames += gameInstance.getPlayers().get(i).getName() + "<br>";
        }
        playerNames += "</html>";
        playerNamesPanel.add(new JLabel(playerNames));
        contentPane.add(playerNamesPanel);

        lblCurrentBoardcards = new JLabel("Current BoardCard(s)");
        lblCurrentBoardcards.setBounds(833, 225, 125, 13);
        contentPane.add(lblCurrentBoardcards);

        SwingUtilities.updateComponentTreeUI(this);
        
        /**
         * Wenn nur noch ein spieler karten hat,
         * dann 
         */
       
    }


    /**
     * Create the frame.
     */
    private void updateCardButtons(GameInstance gameInstance) {

//        JLabel jl = new JLabel();
//        String fileToBoardCard4 = "/" + gameInstance.boardCards.get(0).getSymbol().toString() + "_"
//                + gameInstance.boardCards.get(0).getZahl().toString() + ".jpg";
//        jl.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard4)));
//        currentBoardCardPanel4.add(jl);
//        contentPane.add(currentBoardCardPanel4);
///////////////////////////////////////////////////////////////////////////////////
        // Card 0
        btnPlayerCard0 = null;
        btnPlayerCard0 = new JPanel();
        btnPlayerCard0.setBounds(104, 408, 98, 125);
        String card0File ="/" + gameInstance.currentPlayer.getHand().get(0).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(0).getZahl().toString() + ".jpg";
        JLabel imageCard0 = new JLabel();
        imageCard0.setBounds(104, 408, 98, 125);
        imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource(card0File)));
        btnPlayerCard0.add(imageCard0);
        contentPane.add(btnPlayerCard0);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard1 = new JPanel();
        btnPlayerCard1.setBounds(212, 408, 98, 125);
        String card1File ="/" + gameInstance.currentPlayer.getHand().get(1).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(1).getZahl().toString() + ".jpg";
        JLabel imageCard1 = new JLabel();
        imageCard1.setBounds(212, 408, 98, 125);
        imageCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(card1File)));
        btnPlayerCard1.add(imageCard1);
        contentPane.add(btnPlayerCard1);

///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard2 = new JPanel();
        btnPlayerCard2.setBounds(320, 408, 98, 125);
        String card2File ="/" + gameInstance.currentPlayer.getHand().get(2).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(2).getZahl().toString() + ".jpg";
        JLabel imageCard2 = new JLabel();
        imageCard2.setBounds(320, 408, 98, 125);
        imageCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(card2File)));
        btnPlayerCard2.add(imageCard2);
        contentPane.add(btnPlayerCard2);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard3 = new JPanel();
        btnPlayerCard3.setBounds(428, 408, 98, 125);
        String card3File = "/" + gameInstance.currentPlayer.getHand().get(3).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(3).getZahl().toString() + ".jpg";
        JLabel imageCard3 = new JLabel();
        imageCard3.setBounds(428, 408, 98, 125);
        imageCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(card3File)));
        btnPlayerCard3.add(imageCard3);
        contentPane.add(btnPlayerCard3);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard4 = new JPanel();
        btnPlayerCard4.setBounds(536, 408, 98, 125);
        String card4File = "/" + gameInstance.currentPlayer.getHand().get(4).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(4).getZahl().toString() + ".jpg";
        JLabel imageCard4 = new JLabel();
        imageCard4.setBounds(536, 408, 98, 125);
        imageCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(card4File)));
        btnPlayerCard4.add(imageCard4);
        contentPane.add(btnPlayerCard4);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard5 = new JPanel();
        btnPlayerCard5.setBounds(644, 408, 98, 125);
        String card5File = "/" + gameInstance.currentPlayer.getHand().get(5).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(5).getZahl().toString() + ".jpg";
        JLabel imageCard5 = new JLabel();
        imageCard5.setBounds(644, 408, 98, 125);
        imageCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(card5File)));
        btnPlayerCard5.add(imageCard5);
        contentPane.add(btnPlayerCard5);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard6 = new JPanel();
        btnPlayerCard6.setBounds(752, 408, 98, 125);
        String card6File = "/" + gameInstance.currentPlayer.getHand().get(6).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(6).getZahl().toString() + ".jpg";
        JLabel imageCard6 = new JLabel();
        imageCard6.setBounds(752, 408, 98, 125);
        imageCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(card6File)));
        btnPlayerCard6.add(imageCard6);
        contentPane.add(btnPlayerCard6);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard7 = new JPanel();
        btnPlayerCard7.setBounds(860, 408, 98, 125);
        String card7File = "/" + gameInstance.currentPlayer.getHand().get(7).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(7).getZahl().toString() + ".jpg";
        JLabel imageCard7 = new JLabel();
        imageCard7.setBounds(860, 408, 98, 125);
        imageCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource(card7File)));
        btnPlayerCard7.add(imageCard7);
        contentPane.add(btnPlayerCard7);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard8 = new JPanel();
        btnPlayerCard8.setBounds(320, 543, 98, 125);
        String card8File = "/" + gameInstance.currentPlayer.getHand().get(8).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(8).getZahl().toString() + ".jpg";
        JLabel imageCard8 = new JLabel();
        imageCard8.setBounds(320, 543, 98, 125);
        imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource(card8File)));
        btnPlayerCard8.add(imageCard8);
        contentPane.add(btnPlayerCard8);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard9 = new JPanel();
        btnPlayerCard9.setBounds(428, 543, 98, 125);
        String card9File = "/" + gameInstance.currentPlayer.getHand().get(9).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(9).getZahl().toString() + ".jpg";
        JLabel imageCard9 = new JLabel();
        imageCard9.setBounds(428, 543, 98, 125);
        imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource(card9File)));
        btnPlayerCard9.add(imageCard8);
        contentPane.add(btnPlayerCard9);
///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard10 = new JPanel();
        btnPlayerCard10.setBounds(536, 543, 98, 125);
        String card10File = "/" + gameInstance.currentPlayer.getHand().get(10).getSymbol().toString() + "_"
                + gameInstance.currentPlayer.getHand().get(10).getZahl().toString() + ".jpg";
        JLabel imageCard10 = new JLabel();
        imageCard10.setBounds(536, 543, 98, 125);
        imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource(card10File)));
        btnPlayerCard10.add(imageCard10);
        contentPane.add(btnPlayerCard10);
//////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////
        btnPlayerCard11 = new JPanel();
        btnPlayerCard11.setBounds(644, 543, 98, 125);

        contentPane.add(btnPlayerCard11);

        SwingUtilities.updateComponentTreeUI(this);

        contentPane.revalidate();
        ///////////////////////////////////////////////////////////////////////////////////

    }

//------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Create the frame.
     *
     * @return
     */
    public void validateMove() {

        // eingabe öffnen für auswählen der karten
        String cardIndexes = JOptionPane.showInputDialog(null, "Bitte Karten angeben (Positionen: 0-11, mit Komma getrennt)");

        String[] cardsIndexesArray = cardIndexes.split(",");

        List cardIndexesToBePlayed = new LinkedList<Integer>();

        //für die Länge des Userinputs Indizes der Karten speichern
        for (int i = 0; i < cardsIndexesArray.length; i++) {
            int f = Integer.parseInt(cardsIndexesArray[i]);
            cardIndexesToBePlayed.add(f);
        }

        // Liste, in der die aus dem Array ausgelesenen, selektierten Karten erfasst und gehalten werden
        List tempCardList = new LinkedList<Card>();
        Boolean tempCardsEqual = true;

//        System.out.println(cardIndexesToBePlayed.size());
        // geclickte Kartenfelder( Frontend) auslesen
        for (int i = 0; i < cardIndexesToBePlayed.size(); i++) {
            // tempCards werden anhand der eingegeben Zahlen geholt
            int r = (Integer) cardIndexesToBePlayed.get(i);
            tempCardList.add((gameInstance.getCurrentPlayer().getHand().get(r)));
//            System.out.println(gameInstance.getCurrentPlayer().getHand().get(r));
            // ALT: Wenn Karte im Frontend geclickt wurde, wird Sie in selectedCards erfasst. Durch dessen Iterierung erhalten wir alle selektierten Karten

            //tempCards valid? compareTo -> die ausgewählten Karten müssen die gleichen Zahlen haben
            if (tempCardList.size() >= 2) {
                //gerade hinzugefügte Card
                Card x = (Card) tempCardList.get(i);
                Card y = (Card) ((LinkedList) tempCardList).getFirst();

                int c = x.compareTo(y);
                if (c == 0) {
                    tempCardsEqual = true;
                } else {
                    tempCardsEqual = false;
                }
            }

//            System.out.println(tempCardList);

            // Spielzug validieren
            // hier findet später die Überprüfung statt, ob der Spielzug richtig ist. Wenn
            // die Liste die von Compare zurückkommt die gleiche ist wie die,die wir in tempList als selektierte Karten haben
            // wenn ja, Karten von Hand des CurrentPlayers abziehen und mit getNextPlayer
            // das Spiel weiterlaufen lassen.
            // wenn nicht, Auffoderung, erneut Karten auszuwählen

            //tempCards valid? compareTo -> die ausgwählten Karten müssen höher sein als die BoardCards
            if (tempCardsEqual) {

                if (gameInstance.getBoardCards() == null) {
                    PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
                    gameInstance.setBoardCards(tempCardList);
                    gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
                } else {
                    Card y = (Card) ((LinkedList) tempCardList).getFirst();
                    Card b = gameInstance.getBoardCards().get(0);

                    int c = y.compareTo(b);

                    if (c == 1) {
                        //tempCards werden von der Hand des Spielers entfernt
                        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);

                        //tempCards werden als Boardcards gesetzt
                        gameInstance.setBoardCards(tempCardList);

                        //nächsten Spieler setzen
                        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));

                        //resettet Boardcards, falls Ass gespielt wurde
                        // TODO - reset, wenn alle Spieler 1x nacheinander gepasst haben

                        SwingUtilities.updateComponentTreeUI(this);
                    } else {
                        //falsche Karten ausgewählt
                        validateMove();
                    }
                }

            } else {
                //falsche Karten ausgewählt
                validateMove();
            }

            currentBoardCardPanel4.removeAll();

            JLabel jl = new JLabel();
            jl.setBounds(859, 93, 99, 125);

            String fileToBoardCard4 = "/" + gameInstance.boardCards.get(0).getSymbol().toString() + "_"
                    + gameInstance.boardCards.get(0).getZahl().toString() + ".jpg";
            jl.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard4)));
            currentBoardCardPanel4.add(jl);

            if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {
                gameInstance.setBoardCards(null);

                System.out.println("ENDE ENDE ENDE ENDE ENDE");
            }

        }
        passCounter = 0;
    }
}
