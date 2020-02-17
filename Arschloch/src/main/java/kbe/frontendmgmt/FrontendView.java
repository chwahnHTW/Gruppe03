package kbe.frontendmgmt;

import kbe.gamemgmt.GameInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * @param PLAYSI - PlayerServiceImplementierung
 * @param cardService - CardServiceImplementierung
 * @param playerRulesService - PlayerRuleServiceImplementierung
 * @param cardRulesService - CardRuleServiceImplementierung
 * @param gameInstance - Spielinstanz - Hier werden Spieler und deren Karten gehalten
 * @param contentPane - Content-Pane - Hauptfenster der GUI
 * @param btnPlaycards . Button, mit dem die Validierung und Ausführung eines Spielzugs getriggert wird.
 * @param btnPass - Button, mit dem ein Parr-Spielzug getätigt wird
 * @param lblCurrentPlayer - Label, das den momentanen Spieler anzeigt
 * @param currentBoardCardPanel1-4 - Panels, die gameInstance.boardCards anzeigen
 * @param lblPlayers - Label fuer die Playernamen
 * @param playerNamesPanel - Panel fuer die PlayerNamen
 * @param lblCurrentBoardcards -  LAbel fuer gameInstance.boardCards
 * @param btnStartGame - Button, um das Spiel zu starten
 * @param btnPlayerCard0-11 - Buttons, um Karten anzuzeigen
 * @param selectedCards - vom Spieler durch Anclicken ausgewählte Karten
 */

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * <p>
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * Class - FrontendView
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */
@Service
public class FrontendView extends JFrame {

    @Autowired
    public FrontendService frontendController;

    private GameInstance gameInstance;

    public JLabel lblCurrentPlayer;
    private JPanel contentPane;
    private JButton btnPlaycards;
    private JButton btnPass;
    private JPanel currentBoardCardPanel1;
    private JPanel currentBoardCardPanel2;
    private JPanel currentBoardCardPanel3;
    private JPanel currentBoardCardPanel4;
    private JLabel lblPlayers;
    private JPanel playerNamesPanel;
    private JLabel lblCurrentBoardcards;
    private JButton btnSaveGame;
    private JButton btnCancel;
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

    /**
     * Methode, um das Frame des Frontends zu erstellen Im Frame befindet
     * sich @param btnStartGame, ein JButton, der ueber einen actionListener das
     * Spiel startet.
     *
     * @param gameInstance - Spielinstanz
     * @return void
     * @method removeFromHand() entzogen und als gameInstance.boardCards gesetzt.
     * Daraufhin wird der naechste Spieler im Spiel als currentPlayer
     * gesetzt und die UI geupdated (Update funktioniert irgendwie nicht
     * wirklich)
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

                frontendController.startGame(gameInstance);

                try {
                    // nachdem alle automatischen Vorbereitungen getroffen sind, kann das Frontend
                    // vollstaendig aufgebaut werden
                    setupFrontend();
                    // images in btnPlayerCard0-11 updaten, da anderer Spieler an der Reihe sein
                    // sollte ( funktioniert nicht, ohne getNextPlayer()
                    // keine genaue Fehlerquelle bestimmbar
                    updateCardButtons(gameInstance);
                    updateCurrentPlayerLabel();

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
     * Methode, um das Frontend zu initiieren, nachdem das Spiel gestartet wurde
     *
     * @throws IOException
     */
    void setupFrontend() throws IOException {
        // StartGame Button von Panel entfernen
        this.remove(btnStartGame);

        btnCancel = new JButton("Cancel Game");

        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(0, 0, 153));
        btnCancel.setBounds(445, 335, 99, 21);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameInstance game = new GameInstance();
                gameInstance.setBoardCards(null);
                // Frontend Update
                updateCurrentBoardCardPanels(gameInstance);
                updateCardButtons(gameInstance);
                updateCurrentPlayerLabel();
                createFrontendView(game);
            }
        });
        contentPane.add(btnCancel);

        btnSaveGame = new JButton("Save Game");
        btnSaveGame.setForeground(Color.WHITE);
        btnSaveGame.setBackground(new Color(0, 0, 153));
        btnSaveGame.setBounds(583, 335, 99, 21);
        btnSaveGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frontendController.saveCurrentGame(gameInstance);
                frontendController.showSavedGameId();
            }
        });
        contentPane.add(btnSaveGame);

        // Button :btnPlayCards einrichten
        btnPlaycards = new JButton("PlayCard(s)");
        btnPlaycards.setForeground(Color.WHITE);
        btnPlaycards.setBackground(new Color(0, 0, 153));
        btnPlaycards.setBounds(721, 335, 99, 21);
        btnPlaycards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // bei Click auf den Button wird die methode validateMove() aufgerufen, welche
                // einen Spielzug in Form
                // einer Eingabe aufnimmt und Auswertet
                //validateMove();
                frontendController.validateMove();
                updateCurrentBoardCardPanels(gameInstance);
                updateCardButtons(gameInstance);
                updateCurrentPlayerLabel();
                // Methode, um den SielStatus auszuwerden

                frontendController.gameStateEvaluation(gameInstance);

                updateCurrentBoardCardPanels(gameInstance);
                updateCardButtons(gameInstance);
                updateCurrentPlayerLabel();
            }
        });
        // Hier wird das Frontend mit dem Button btnPlayCards bestückt
        contentPane.add(btnPlaycards);
        // Hier wird der Button fuer einen Pass-Spielzug erstellt
        btnPass = new JButton("Pass");
        btnPass.setForeground(Color.WHITE);
        btnPass.setBackground(new Color(255, 0, 0));
        btnPass.setBounds(859, 335, 99, 21);
        contentPane.add(btnPass);
        btnPass.addActionListener(new ActionListener() {
            // ActionListener fuer den Passspielzug
            public void actionPerformed(ActionEvent e) {
                frontendController.pass(gameInstance);
                updateCurrentPlayerLabel();
                updateCardButtons(gameInstance);


            }
        });
        // Label, das den Namen des aktuellen Spielers traegt
        lblCurrentPlayer = new JLabel("Current Player: " + gameInstance.getCurrentPlayer().getName());
        lblCurrentPlayer.setBounds(104, 276, 155, 34);
        contentPane.add(lblCurrentPlayer);

        // Label fuer CurrentBoardCards
        currentBoardCardPanel1 = new JPanel();
        currentBoardCardPanel1.setBounds(532, 93, 99, 125);
        contentPane.add(currentBoardCardPanel1);
        // Label fuer CurrentBoardCards
        currentBoardCardPanel2 = new JPanel();
        currentBoardCardPanel2.setBounds(641, 93, 99, 125);
        contentPane.add(currentBoardCardPanel2);
        // Label fuer CurrentBoardCards
        currentBoardCardPanel3 = new JPanel();
        currentBoardCardPanel3.setBounds(750, 93, 99, 125);
        contentPane.add(currentBoardCardPanel3);
        // Label fuer CurrentBoardCards
        currentBoardCardPanel4 = new JPanel();
        currentBoardCardPanel4.setBounds(859, 93, 99, 125);
        contentPane.add(currentBoardCardPanel4);
        // Label fuer Spielernamen
        lblPlayers = new JLabel("Players");
        lblPlayers.setBounds(107, 67, 73, 29);
        contentPane.add(lblPlayers);
        // Panel fuer Spielernamen
        playerNamesPanel = new JPanel();
        playerNamesPanel.setBounds(104, 93, 98, 125);
        String playerNames = "<html>";
        for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            playerNames += gameInstance.getPlayers().get(i).getName() + "<br>";
        }
        playerNames += "</html>";
        playerNamesPanel.add(new JLabel(playerNames));
        contentPane.add(playerNamesPanel);
        // Label fuer CurrentBoardCards
        lblCurrentBoardcards = new JLabel("Current BoardCard(s)");
        lblCurrentBoardcards.setBounds(833, 225, 170, 13);
        contentPane.add(lblCurrentBoardcards);
        // Frontend update
        contentPane.revalidate();

        SwingUtilities.updateComponentTreeUI(this);

    }

    /**
     * Methode zum Updaten der currentBoardCardPanels Prueft, ob momentan eine Karte
     * im entsprechenden Slot liegt Wenn ja, wird sie angezeigt, Wenn nein, wird ein
     * leeres Feld angezeigt
     *
     * @param gameInstance - Spielinstanz
     */
    public void updateCurrentBoardCardPanels(GameInstance gameInstance) {

        // Pruefung, ob BoardCards vorhanden
        if (gameInstance.getBoardCards() != null) {
            // wenn ja, mind. 1 neue Karten-Symbole setzen
            try {
                currentBoardCardPanel4.removeAll();
                JLabel jl4 = new JLabel();
                jl4.setBounds(859, 93, 99, 125);

                String fileToBoardCard4 = "/" + gameInstance.getBoardCards().get(0).getSymbol().toString() + "_"
                        + gameInstance.getBoardCards().get(0).getZahl().toString() + ".jpg";
                jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard4)));
                currentBoardCardPanel4.add(jl4);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                currentBoardCardPanel4.removeAll();
                JLabel jl4 = new JLabel();
                jl4.setBounds(859, 93, 99, 125);
                jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
                currentBoardCardPanel4.add(jl4);
            }

///////////////////////////////////////////////////////////////////////////////////
            if (gameInstance.getBoardCards().size() >= 2) {

                try {
                    currentBoardCardPanel3.removeAll();
                    JLabel jl3 = new JLabel();
                    jl3.setBounds(859, 93, 99, 125);

                    String fileToBoardCard3 = "/" + gameInstance.getBoardCards().get(1).getSymbol().toString() + "_"
                            + gameInstance.getBoardCards().get(1).getZahl().toString() + ".jpg";
                    jl3.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard3)));
                    currentBoardCardPanel3.add(jl3);
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    currentBoardCardPanel3.removeAll();
                    JLabel jl3 = new JLabel();
                    jl3.setBounds(859, 93, 99, 125);
                    jl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
                    currentBoardCardPanel3.add(jl3);
                }
            }
///////////////////////////////////////////////////////////////////////////////////

            if (gameInstance.getBoardCards().size() >= 3) {
                try {
                    currentBoardCardPanel2.removeAll();
                    JLabel jl2 = new JLabel();
                    jl2.setBounds(859, 93, 99, 125);
                    String fileToBoardCard2 = "/" + gameInstance.getBoardCards().get(2).getSymbol().toString() + "_"
                            + gameInstance.getBoardCards().get(2).getZahl().toString() + ".jpg";
                    jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard2)));
                    currentBoardCardPanel2.add(jl2);
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    currentBoardCardPanel2.removeAll();
                    JLabel jl2 = new JLabel();
                    jl2.setBounds(859, 93, 99, 125);
                    jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
                    currentBoardCardPanel2.add(jl2);
                }
            }
///////////////////////////////////////////////////////////////////////////////////
            if (gameInstance.getBoardCards().size() == 4) {
                try {
                    currentBoardCardPanel1.removeAll();
                    JLabel jl1 = new JLabel();
                    jl1.setBounds(859, 93, 99, 125);
                    String fileToBoardCard1 = "/" + gameInstance.getBoardCards().get(3).getSymbol().toString() + "_"
                            + gameInstance.getBoardCards().get(3).getZahl().toString() + ".jpg";
                    jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard1)));
                    currentBoardCardPanel1.add(jl1);
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    currentBoardCardPanel1.removeAll();
                    JLabel jl1 = new JLabel();
                    jl1.setBounds(859, 93, 99, 125);
                    jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
                    currentBoardCardPanel1.add(jl1);
                }
            }
            // Frontend Update
            updateCardButtons(gameInstance);
            contentPane.revalidate();
        } else {
            // wenn currentBoardCards = null ( keine BoardCards , z.B. nach Ass oder zu
            // Spielbeginn )
            currentBoardCardPanel1.removeAll();
            JLabel jl1 = new JLabel();
            jl1.setBounds(859, 93, 99, 125);
            jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            currentBoardCardPanel1.add(jl1);

            currentBoardCardPanel2.removeAll();
            JLabel jl2 = new JLabel();
            jl2.setBounds(859, 93, 99, 125);
            jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            currentBoardCardPanel2.add(jl2);

            currentBoardCardPanel3.removeAll();
            JLabel jl3 = new JLabel();
            jl3.setBounds(859, 93, 99, 125);
            jl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            currentBoardCardPanel3.add(jl3);

            currentBoardCardPanel4.removeAll();
            JLabel jl4 = new JLabel();
            jl4.setBounds(859, 93, 99, 125);
            jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            currentBoardCardPanel4.add(jl4);

            updateCardButtons(gameInstance);
            contentPane.revalidate();
        }
    }

    /**
     * Methode zum Updaten der CardButtons Prueft, ob momentan eine Karte im
     * entsprechenden Slot liegt Wenn ja, wird sie angezeigt, Wenn nein, wird ein
     * leeres Feld angezeigt
     *
     * @param gameInstance - Spielinstanz
     */
    public void updateCardButtons(GameInstance gameInstance) {
        try {
            // Feld fuer neu anzuzeigende Karte resetten
            btnPlayerCard0 = null;
            // neues Feld fuer neu anzuzeigende Karte erstellen
            btnPlayerCard0 = new JPanel();
            // Location des Feldes setzen
            btnPlayerCard0.setBounds(104, 408, 98, 125);
            // Pruefung, ob Karte vorhanden. Wenn ja, wird Pfad zum entsprechenden Bild als
            // String gespeichert. Falls nicht,
            String card0File = "/" + gameInstance.getCurrentPlayer().getHand().get(0).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(0).getZahl().toString() + ".jpg";
            JLabel imageCard0 = new JLabel();
            // Falls ja wird das Bild zum Pfad in imageCard geladen
            imageCard0.setBounds(104, 408, 98, 125);
            // als Icon gesetzt
            imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource(card0File)));
            // und auf die Pane geadded
            btnPlayerCard0.add(imageCard0);
            contentPane.add(btnPlayerCard0);
            // falls nein bleibt das Feld ueber Exception-Handling leer
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("no card at slot 0");
            JLabel imageCard0 = new JLabel("no card");
            imageCard0.setBounds(104, 408, 98, 125);
            imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard0.add(imageCard0);
            contentPane.add(btnPlayerCard0);
        }

///////////////////////////////////////////////////////////////////////////////////
        // Alle weiteren Buttons funktionieren analog zu btnPlayerCard0
        try {
            btnPlayerCard1 = null;
            btnPlayerCard1 = new JPanel();
            btnPlayerCard1.setBounds(212, 408, 98, 125);
            String card1File = "/" + gameInstance.getCurrentPlayer().getHand().get(1).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(1).getZahl().toString() + ".jpg";
            JLabel imageCard1 = new JLabel();
            imageCard1.setBounds(212, 408, 98, 125);
            imageCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(card1File)));
            btnPlayerCard1.add(imageCard1);
            contentPane.add(btnPlayerCard1);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard1 = null;
            btnPlayerCard1 = new JPanel();
            btnPlayerCard1.setBounds(212, 408, 98, 125);
            JLabel imageCard1 = new JLabel();
            imageCard1.setBounds(212, 408, 98, 125);
            imageCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard1.add(imageCard1);
            contentPane.add(btnPlayerCard1);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard2 = null;
            btnPlayerCard2 = new JPanel();
            btnPlayerCard2.setBounds(320, 408, 98, 125);
            String card2File = "/" + gameInstance.getCurrentPlayer().getHand().get(2).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(2).getZahl().toString() + ".jpg";
            JLabel imageCard2 = new JLabel();
            imageCard2.setBounds(320, 408, 98, 125);
            imageCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(card2File)));
            btnPlayerCard2.add(imageCard2);
            contentPane.add(btnPlayerCard2);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard2 = null;
            btnPlayerCard2 = new JPanel();
            btnPlayerCard2.setBounds(320, 408, 98, 125);
            JLabel imageCard2 = new JLabel("no cards");
            imageCard2.setBounds(320, 408, 98, 125);
            imageCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard2.add(imageCard2);
            contentPane.add(btnPlayerCard2);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard3 = null;
            btnPlayerCard3 = new JPanel();
            btnPlayerCard3.setBounds(428, 408, 98, 125);
            String card3File = "/" + gameInstance.getCurrentPlayer().getHand().get(3).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(3).getZahl().toString() + ".jpg";
            JLabel imageCard3 = new JLabel();
            imageCard3.setBounds(428, 408, 98, 125);
            imageCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(card3File)));
            btnPlayerCard3.add(imageCard3);
            contentPane.add(btnPlayerCard3);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard3 = null;
            btnPlayerCard3 = new JPanel();
            btnPlayerCard3.setBounds(428, 408, 98, 125);
            JLabel imageCard3 = new JLabel("no card");
            imageCard3.setBounds(428, 408, 98, 125);
            imageCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard3.add(imageCard3);
            contentPane.add(btnPlayerCard3);
        }
///////////////////////////////////////////////////////////////////////////////////

        try {
            btnPlayerCard4 = null;
            btnPlayerCard4 = new JPanel();
            btnPlayerCard4.setBounds(536, 408, 98, 125);
            String card4File = "/" + gameInstance.getCurrentPlayer().getHand().get(4).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(4).getZahl().toString() + ".jpg";
            JLabel imageCard4 = new JLabel();
            imageCard4.setBounds(536, 408, 98, 125);
            imageCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(card4File)));
            btnPlayerCard4.add(imageCard4);
            contentPane.add(btnPlayerCard4);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard4 = null;
            btnPlayerCard4 = new JPanel();
            btnPlayerCard4.setBounds(536, 408, 98, 125);
            JLabel imageCard4 = new JLabel("no card");
            imageCard4.setBounds(536, 408, 98, 125);
            imageCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard4.add(imageCard4);
            contentPane.add(btnPlayerCard4);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard5 = null;
            btnPlayerCard5 = new JPanel();
            btnPlayerCard5.setBounds(644, 408, 98, 125);
            String card5File = "/" + gameInstance.getCurrentPlayer().getHand().get(5).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(5).getZahl().toString() + ".jpg";
            JLabel imageCard5 = new JLabel();
            imageCard5.setBounds(644, 408, 98, 125);
            imageCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(card5File)));
            btnPlayerCard5.add(imageCard5);
            contentPane.add(btnPlayerCard5);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard5 = null;
            btnPlayerCard5 = new JPanel();
            btnPlayerCard5.setBounds(644, 408, 98, 125);
            JLabel imageCard5 = new JLabel();
            imageCard5.setBounds(644, 408, 98, 125);
            imageCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard5.add(imageCard5);
            contentPane.add(btnPlayerCard5);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard6 = null;
            btnPlayerCard6 = new JPanel();
            btnPlayerCard6.setBounds(752, 408, 98, 125);
            String card6File = "/" + gameInstance.getCurrentPlayer().getHand().get(6).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(6).getZahl().toString() + ".jpg";
            JLabel imageCard6 = new JLabel();
            imageCard6.setBounds(752, 408, 98, 125);
            imageCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(card6File)));
            btnPlayerCard6.add(imageCard6);
            contentPane.add(btnPlayerCard6);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard6 = null;
            btnPlayerCard6 = new JPanel();
            btnPlayerCard6.setBounds(752, 408, 98, 125);
            JLabel imageCard6 = new JLabel("no card");
            imageCard6.setBounds(752, 408, 98, 125);
            imageCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard6.add(imageCard6);
            contentPane.add(btnPlayerCard6);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard7 = null;
            btnPlayerCard7 = new JPanel();
            btnPlayerCard7.setBounds(860, 408, 98, 125);
            String card7File = "/" + gameInstance.getCurrentPlayer().getHand().get(7).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(7).getZahl().toString() + ".jpg";
            JLabel imageCard7 = new JLabel();
            imageCard7.setBounds(860, 408, 98, 125);
            imageCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource(card7File)));
            btnPlayerCard7.add(imageCard7);
            contentPane.add(btnPlayerCard7);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard7 = null;
            btnPlayerCard7 = new JPanel();
            btnPlayerCard7.setBounds(860, 408, 98, 125);
            JLabel imageCard7 = new JLabel("no card");
            imageCard7.setBounds(860, 408, 98, 125);
            imageCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard7.add(imageCard7);
            contentPane.add(btnPlayerCard7);
        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard8 = null;
            btnPlayerCard8 = new JPanel();
            btnPlayerCard8.setBounds(320, 543, 98, 125);
            String card8File = "/" + gameInstance.getCurrentPlayer().getHand().get(8).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(8).getZahl().toString() + ".jpg";
            JLabel imageCard8 = new JLabel();
            imageCard8.setBounds(320, 543, 98, 125);
            imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource(card8File)));
            btnPlayerCard8.add(imageCard8);
            contentPane.add(btnPlayerCard8);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard8 = null;
            btnPlayerCard8 = new JPanel();
            btnPlayerCard8.setBounds(320, 543, 98, 125);
            JLabel imageCard8 = new JLabel("no card");
            imageCard8.setBounds(320, 543, 98, 125);
            imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard8.add(imageCard8);
            contentPane.add(btnPlayerCard8);

        }
///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard9 = null;
            btnPlayerCard9 = new JPanel();
            btnPlayerCard9.setBounds(428, 543, 98, 125);
            String card9File = "/" + gameInstance.getCurrentPlayer().getHand().get(9).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(9).getZahl().toString() + ".jpg";
            JLabel imageCard9 = new JLabel();
            imageCard9.setBounds(428, 543, 98, 125);
            imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource(card9File)));
            btnPlayerCard9.add(imageCard9);
            contentPane.add(btnPlayerCard9);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard9 = null;
            btnPlayerCard9 = new JPanel();
            btnPlayerCard9.setBounds(428, 543, 98, 125);
            JLabel imageCard9 = new JLabel("no Card");
            imageCard9.setBounds(428, 543, 98, 125);
            imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard9.add(imageCard9);
            contentPane.add(btnPlayerCard9);
        }

///////////////////////////////////////////////////////////////////////////////////
        try {
            btnPlayerCard10 = null;
            btnPlayerCard10 = new JPanel();
            btnPlayerCard10.setBounds(536, 543, 98, 125);
            String card10File = "/" + gameInstance.getCurrentPlayer().getHand().get(10).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(10).getZahl().toString() + ".jpg";
            JLabel imageCard10 = new JLabel();
            imageCard10.setBounds(536, 543, 98, 125);
            imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource(card10File)));
            btnPlayerCard10.add(imageCard10);
            contentPane.add(btnPlayerCard10);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard10 = null;
            btnPlayerCard10 = new JPanel();
            btnPlayerCard10.setBounds(536, 543, 98, 125);
            JLabel imageCard10 = new JLabel("no card");
            imageCard10.setBounds(536, 543, 98, 125);
            imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard10.add(imageCard10);
            contentPane.add(btnPlayerCard10);
        }
///////////////////////////////////////////////////////////////////////////////////

        try {
            btnPlayerCard11 = null;
            btnPlayerCard11 = new JPanel();
            btnPlayerCard11.setBounds(644, 543, 98, 125);
            String card11File = "/" + gameInstance.getCurrentPlayer().getHand().get(11).getSymbol().toString() + "_"
                    + gameInstance.getCurrentPlayer().getHand().get(11).getZahl().toString() + ".jpg";
            JLabel imageCard11 = new JLabel();
            imageCard11.setBounds(644, 543, 98, 125);
            imageCard11.setIcon(new javax.swing.ImageIcon(getClass().getResource(card11File)));
            btnPlayerCard11.add(imageCard11);
            contentPane.add(btnPlayerCard11);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            btnPlayerCard11 = null;
            btnPlayerCard11 = new JPanel();
            btnPlayerCard11.setBounds(644, 543, 98, 125);
            JLabel imageCard11 = new JLabel();
            imageCard11.setBounds(644, 543, 98, 125);
            imageCard11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
            btnPlayerCard11.add(imageCard11);
            contentPane.add(btnPlayerCard11);
        }
        contentPane.revalidate();
    }

    /**
     * Methode, um lblCurrentPlayer zu updaten
     */
    public void updateCurrentPlayerLabel() {
        try {
            lblCurrentPlayer.removeAll();
            contentPane.remove(lblCurrentPlayer);
            lblCurrentPlayer = null;
            lblCurrentPlayer = new JLabel("Current Player: " + gameInstance.getCurrentPlayer().getName());
            lblCurrentPlayer.setBounds(104, 276, 155, 34);
            contentPane.add(lblCurrentPlayer);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        lblCurrentPlayer.repaint();

    }
}
