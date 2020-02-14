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

import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.BotPlayerService;
import kbe.playermgmt.BotPlayerServiceImpl;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServicePresidentFirstImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
// * @param selectedCards - vom Spieler durch Anclicken ausgewählte Karten
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
	@Autowired
	private FrontendController frontendController;
	private BotPlayerService botPlayerService = new BotPlayerServiceImpl();

	private GameInstance gameInstance;

	private GameInstanceService GISI = new GameInstanceServiceImpl();

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
	 * Methode, um das Frame des Frontends zu erstellen Im Frame befindet
	 * sich @param btnStartGame, ein JButton, der ueber einen actionListener das
	 * Spiel startet. der Spieler mit der Karte HERZ_SIEBEN wird über @method
	 * determineInitialPlayer() ermittelt. Anschließend wird die Karte HERZ_SIEBEN
	 * seiner Hand über
	 *
	 * @param gameInstance - Spielinstanz
	 * @return void
	 * @method removeFromHand() entzogen und als gameInstance.boardCards gesetzt.
	 *         Daraufhin wird der naechste Spieler im Spiel als currentPlayer
	 *         gesetzt und die UI geupdated (Update funktioniert irgendwie nicht
	 *         wirklich)
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
				boolean ifBotPlayer = getIfBotPlayer();
				int playerCount = getUserCountInput();
//                System.out.println(playerCount);
				
				for (int i = 0; i < playerCount; i++) {
					if(ifBotPlayer) {
						System.out.println("ifBotPlayer is true");
						if(i == 0) {
							System.out.println("i == 0");
							Player playerHuman = PLAYSI.createPlayer(getUserNameInput());
							gameInstance.players.add(playerHuman);
							System.out.println("human added");
						}
						Player playerBot = PLAYSI.createPlayer("BotPlayer");
						gameInstance.players.add(playerBot);
						System.out.println("bots added");
					} else {
						Player player = PLAYSI.createPlayer(getUserNameInput());
						gameInstance.players.add(player);
					}
				}
				cardService.dealCardsToPlayers(gameInstance);

				try {
					gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
					System.out.println(gameInstance.getCurrentPlayer().getName());

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
	 * Methode, um Userinput ( Spieleranzahl ) zu erhalten
	 *
	 * @return - int - vom Spieler eingegebene Spieleranzahl
	 * @throws IllegalArgumentException - falsche Eingabe HINWEIS : funktioniert nur
	 *                                  mit 3 Playern, da der Algorithmus zum
	 *                                  Erfassen des naechsten Spielers nicht
	 *                                  perfekt funktioniert
	 */
//	private int getUserCountInput() throws IllegalArgumentException {
//		String spieleranzahl = JOptionPane.showInputDialog(null,
//				"Bitte Spieleranzahl eingeben (Spieleranzahl muss 3 sein)");
//		if (spieleranzahl.equals("3") | spieleranzahl.equals("4")| spieleranzahl.equals("5")) {
//			return Integer.valueOf(spieleranzahl);
//		} else {
//			return getUserCountInput();
//		}
//	}
	
	private boolean getIfBotPlayer() throws IllegalArgumentException {
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
	
	private int getUserCountInput() throws IllegalArgumentException {
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

	/**
	 * Methode, um die Namen der Spieler eines Spiels zu erfassen
	 * 
	 * @return - String - Name fuer einen User
	 */
	String getUserNameInput() {
		String spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");
		if(spielerName.isEmpty()){
			return getUserNameInput();
		} else {
			return spielerName;
		}
	}

	/**
	 * Methode, um das Frontend zu initiieren
	 */
	public void setupFrontend() throws IOException {
		// StartGame Button von Panel entfernen
		this.remove(btnStartGame);

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
				validateMove();
				// Methode, um den SielStatus auszuwerden
				String gameState = GISI.calculateGameState(gameInstance);
				if (gameState.equals("Running")) {
					// Spiel geht weiter
				} else {
					// Weiter spielen? User Abfrage
					Boolean continueGame = getContinueGame();

					// letzten Spieler in Resultliste speichern, damit Roles richtig gesetzt werden
					for (Player player : gameInstance.getPlayers()) {
						if (PLAYSI.hasCards(player)) {
							gameInstance.setResult(player);
						}
					}
					if (continueGame) {
						// Rollen herausfinden
						setPlayerRoles();
						gameInstance.getResult().get(0).handCards = new LinkedList<Card>();
						gameInstance.getResult().get(1).handCards = new LinkedList<Card>();
						gameInstance.getResult().get(2).handCards = new LinkedList<Card>();

						// Spieler in neue Spielrunde uebernehmen
						gameInstance.setPlayers(gameInstance.getResult());
						// Karten austeilen
						cardService.dealCardsToPlayers(gameInstance);
						// Karten entsprechend der Rollen austauschen
						cardService.swapCards(gameInstance);
						// Setzen des ersten Spielers der nächsten Runde
						setInitialPlayerForNextRound();
						// Update der boardCards auf null, da frisches Spiel
						gameInstance.setBoardCards(null);
						// Frontend Update
						updateCurrentBoardCardPanels(gameInstance);
						updateCardButtons(gameInstance);
						updateCurrentPlayerLabel();
					} else {
						// wenn nicht weitergespielt werden soll , schließt sich die Anwendung
						System.exit(0);
					}
				}

				// Frontend Update
				updateCardButtons(gameInstance);
				updateCurrentBoardCardPanels(gameInstance);
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
				// setzen des Cpounters der PAss-Spielzuege
				System.out.println("PASSEN");
				passCounter++;
				// setzen des naechsten Spielers
				gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
				// Frontend Update
				updateCurrentPlayerLabel();
				updateCardButtons(gameInstance);
				// reset der Current BoardCard, da jeder Spieler 1x gepasst hat

				int playersWithCardsCounter = 0;
				for (Player player : gameInstance.getPlayers()){
					if(PLAYSI.hasCards(player)){
						playersWithCardsCounter++;
					}
				}

				if (passCounter == playersWithCardsCounter-1) {
					gameInstance.setBoardCards(null);
					System.out.println("Pass-Counter = Anzahl Spieler mit Karten - boardCards resettet");
					// Frontend Update
					updateCardButtons(gameInstance);
					updateCurrentBoardCardPanels(gameInstance);
				}
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
		lblCurrentBoardcards.setBounds(833, 225, 125, 13);
		contentPane.add(lblCurrentBoardcards);
		// Frontend update
		contentPane.revalidate();

		SwingUtilities.updateComponentTreeUI(this);

		/**
		 * Wenn nur noch ein spieler karten hat, dann
		 */

	}

	///////////////////////////////////////////////////////////////////////////////////

	/*
	 * Methode zum Updaten der currentBoardCardPanels Prueft, ob momentan eine Karte
	 * im entsprechenden Slot liegt Wenn ja, wird sie angezeigt, Wenn nein, wird ein
	 * leeres Feld angezeigt
	 * 
	 * @param gameInstance - Spielinstanz
	 * 
	 * @throws IndexOutOfBounds & Nullpointer Exception
	 * 
	 */
	public void updateCurrentBoardCardPanels(GameInstance gameInstance) {

		try {
			// Pruefung, ob BoardCards vorhanden
			if (gameInstance.getBoardCards() != null) {
				// wenn ja, neue Labels setzen
				try {
					currentBoardCardPanel4.removeAll();
					JLabel jl4 = new JLabel();
					jl4.setBounds(859, 93, 99, 125);

					String fileToBoardCard4 = "/" + gameInstance.boardCards.get(0).getSymbol().toString() + "_"
							+ gameInstance.boardCards.get(0).getZahl().toString() + ".jpg";
					jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard4)));
					currentBoardCardPanel4.add(jl4);
				} catch (IndexOutOfBoundsException e) {
					currentBoardCardPanel4.removeAll();
					JLabel jl4 = new JLabel();
					jl4.setBounds(859, 93, 99, 125);
					jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
					currentBoardCardPanel4.add(jl4);
				} catch (NullPointerException e) {
					currentBoardCardPanel4.removeAll();
					JLabel jl4 = new JLabel();
					jl4.setBounds(859, 93, 99, 125);
					jl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
					currentBoardCardPanel4.add(jl4);
				}

	///////////////////////////////////////////////////////////////////////////////////    	

				if (gameInstance.boardCards.size() == 2) {
					try {
						currentBoardCardPanel3.removeAll();
						JLabel jl3 = new JLabel();
						jl3.setBounds(859, 93, 99, 125);

						String fileToBoardCard3 = "/" + gameInstance.boardCards.get(1).getSymbol().toString() + "_"
								+ gameInstance.boardCards.get(1).getZahl().toString() + ".jpg";
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
				if (gameInstance.boardCards.size() == 3) {
					try {
						currentBoardCardPanel2.removeAll();
						JLabel jl2 = new JLabel();
						jl2.setBounds(859, 93, 99, 125);
						String fileToBoardCard2 = "/" + gameInstance.boardCards.get(2).getSymbol().toString() + "_"
								+ gameInstance.boardCards.get(2).getZahl().toString() + ".jpg";
						jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard2)));
						currentBoardCardPanel2.add(jl2);
					} catch (IndexOutOfBoundsException e) {
						currentBoardCardPanel2.removeAll();
						JLabel jl2 = new JLabel();
						jl2.setBounds(859, 93, 99, 125);
						jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
						currentBoardCardPanel2.add(jl2);
					} catch (NullPointerException e) {
						currentBoardCardPanel2.removeAll();
						JLabel jl2 = new JLabel();
						jl2.setBounds(859, 93, 99, 125);
						jl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
						currentBoardCardPanel2.add(jl2);
					}
				}
	///////////////////////////////////////////////////////////////////////////////////

				if (gameInstance.boardCards.size() == 4) {
					try {
						currentBoardCardPanel1.removeAll();
						JLabel jl1 = new JLabel();
						jl1.setBounds(859, 93, 99, 125);
						String fileToBoardCard1 = "/" + gameInstance.boardCards.get(3).getSymbol().toString() + "_"
								+ gameInstance.boardCards.get(3).getZahl().toString() + ".jpg";
						jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileToBoardCard1)));
						currentBoardCardPanel1.add(jl1);
					} catch (IndexOutOfBoundsException e) {
						currentBoardCardPanel1.removeAll();
						JLabel jl1 = new JLabel();
						jl1.setBounds(859, 93, 99, 125);
						jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
						currentBoardCardPanel1.add(jl1);
					} catch (NullPointerException e) {
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

				// Update Frontend

				updateCardButtons(gameInstance);
				contentPane.revalidate();

			}
		} catch (Exception e) {
			System.out.println("catch block");
		}
		

	}

	public void updateCardButtons(GameInstance gameInstance) {
///////////////////////////////////////////////////////////////////////////////////

		/*
		 * Methode zum Updaten der CardButtons Prueft, ob momentan eine Karte im
		 * entsprechenden Slot liegt Wenn ja, wird sie angezeigt, Wenn nein, wird ein
		 * leeres Feld angezeigt
		 * 
		 * @param gameInstance - Spielinstanz
		 * 
		 * @throws IndexOutOfBounds & Nullpointer Exception
		 * 
		 */

		try {

			// Feld fuer neu anzuzeigende Karte resetten
			btnPlayerCard0 = null;
			// neues Feld fuer neu anzuzeigende Karte erstellen
			btnPlayerCard0 = new JPanel();
			// Location des Feldes setzen
			btnPlayerCard0.setBounds(104, 408, 98, 125);
			// Pruefung, ob Karte vorhanden. Wenn ja, wird Pfad zum entsprechenden Bild als
			// String gespeichert. Falls nicht,
			String card0File = "/" + gameInstance.currentPlayer.getHand().get(0).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(0).getZahl().toString() + ".jpg";
			JLabel imageCard0 = new JLabel();
			// Falls ja wird das Bild zum Pfad in imageCard geladen
			imageCard0.setBounds(104, 408, 98, 125);
			// als Icon gesetzt
			imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource(card0File)));
			// und auf die Pane geadded
			btnPlayerCard0.add(imageCard0);
			contentPane.add(btnPlayerCard0);
			// falls nein bleibt das Feld ueber Exception-Handling leer
		} catch (NullPointerException e) {
			System.out.println("no card at slot 0");
			JLabel imageCard0 = new JLabel("no card");
			imageCard0.setBounds(104, 408, 98, 125);
			imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard0.add(imageCard0);
			contentPane.add(btnPlayerCard0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("no card at slot 0");
			JLabel imageCard0 = new JLabel("no card");
			imageCard0.setBounds(104, 408, 98, 125);
			imageCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard0.add(imageCard0);
			contentPane.add(btnPlayerCard0);
		}
		;
///////////////////////////////////////////////////////////////////////////////////
		// Alle weiteren Buttons funktionieren analog zu btnPlayerCard0
		try {
			btnPlayerCard1 = null;
			btnPlayerCard1 = new JPanel();
			btnPlayerCard1.setBounds(212, 408, 98, 125);
			String card1File = "/" + gameInstance.currentPlayer.getHand().get(1).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(1).getZahl().toString() + ".jpg";
			JLabel imageCard1 = new JLabel();
			imageCard1.setBounds(212, 408, 98, 125);
			imageCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(card1File)));
			btnPlayerCard1.add(imageCard1);
			contentPane.add(btnPlayerCard1);
		} catch (NullPointerException e) {

			btnPlayerCard1 = null;
			btnPlayerCard1 = new JPanel();
			btnPlayerCard1.setBounds(212, 408, 98, 125);
			JLabel imageCard1 = new JLabel();
			imageCard1.setBounds(212, 408, 98, 125);
			imageCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard1.add(imageCard1);
			contentPane.add(btnPlayerCard1);
		} catch (IndexOutOfBoundsException e) {
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
			String card2File = "/" + gameInstance.currentPlayer.getHand().get(2).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(2).getZahl().toString() + ".jpg";
			JLabel imageCard2 = new JLabel();
			imageCard2.setBounds(320, 408, 98, 125);
			imageCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(card2File)));
			btnPlayerCard2.add(imageCard2);
			contentPane.add(btnPlayerCard2);
		} catch (NullPointerException e) {

			btnPlayerCard2 = null;
			btnPlayerCard2 = new JPanel();
			btnPlayerCard2.setBounds(320, 408, 98, 125);
			JLabel imageCard2 = new JLabel("no cards");
			imageCard2.setBounds(320, 408, 98, 125);
			imageCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard2.add(imageCard2);
			contentPane.add(btnPlayerCard2);
		} catch (IndexOutOfBoundsException e) {
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
			String card3File = "/" + gameInstance.currentPlayer.getHand().get(3).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(3).getZahl().toString() + ".jpg";
			JLabel imageCard3 = new JLabel();
			imageCard3.setBounds(428, 408, 98, 125);
			imageCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(card3File)));
			btnPlayerCard3.add(imageCard3);
			contentPane.add(btnPlayerCard3);
		} catch (NullPointerException e) {
			btnPlayerCard3 = null;
			btnPlayerCard3 = new JPanel();
			btnPlayerCard3.setBounds(428, 408, 98, 125);
			JLabel imageCard3 = new JLabel("no card");
			imageCard3.setBounds(428, 408, 98, 125);
			imageCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard3.add(imageCard3);
			contentPane.add(btnPlayerCard3);
		} catch (IndexOutOfBoundsException e) {
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
			String card4File = "/" + gameInstance.currentPlayer.getHand().get(4).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(4).getZahl().toString() + ".jpg";
			JLabel imageCard4 = new JLabel();
			imageCard4.setBounds(536, 408, 98, 125);
			imageCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(card4File)));
			btnPlayerCard4.add(imageCard4);
			contentPane.add(btnPlayerCard4);
		} catch (NullPointerException e) {
			btnPlayerCard4 = null;
			btnPlayerCard4 = new JPanel();
			btnPlayerCard4.setBounds(536, 408, 98, 125);
			JLabel imageCard4 = new JLabel("no card");
			imageCard4.setBounds(536, 408, 98, 125);
			imageCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard4.add(imageCard4);
			contentPane.add(btnPlayerCard4);
		} catch (IndexOutOfBoundsException e) {
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
			String card5File = "/" + gameInstance.currentPlayer.getHand().get(5).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(5).getZahl().toString() + ".jpg";
			JLabel imageCard5 = new JLabel();
			imageCard5.setBounds(644, 408, 98, 125);
			imageCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(card5File)));
			btnPlayerCard5.add(imageCard5);
			contentPane.add(btnPlayerCard5);
		} catch (NullPointerException e) {
			btnPlayerCard5 = null;
			btnPlayerCard5 = new JPanel();
			btnPlayerCard5.setBounds(644, 408, 98, 125);
			JLabel imageCard5 = new JLabel();
			imageCard5.setBounds(644, 408, 98, 125);
			imageCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard5.add(imageCard5);
			contentPane.add(btnPlayerCard5);
		} catch (IndexOutOfBoundsException e) {
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
			String card6File = "/" + gameInstance.currentPlayer.getHand().get(6).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(6).getZahl().toString() + ".jpg";
			JLabel imageCard6 = new JLabel();
			imageCard6.setBounds(752, 408, 98, 125);
			imageCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(card6File)));
			btnPlayerCard6.add(imageCard6);
			contentPane.add(btnPlayerCard6);
		} catch (NullPointerException e) {
			btnPlayerCard6 = null;
			btnPlayerCard6 = new JPanel();
			btnPlayerCard6.setBounds(752, 408, 98, 125);
			JLabel imageCard6 = new JLabel("no card");
			imageCard6.setBounds(752, 408, 98, 125);
			imageCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard6.add(imageCard6);
			contentPane.add(btnPlayerCard6);
		} catch (IndexOutOfBoundsException e) {
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
			String card7File = "/" + gameInstance.currentPlayer.getHand().get(7).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(7).getZahl().toString() + ".jpg";
			JLabel imageCard7 = new JLabel();
			imageCard7.setBounds(860, 408, 98, 125);
			imageCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource(card7File)));
			btnPlayerCard7.add(imageCard7);
			contentPane.add(btnPlayerCard7);
		} catch (NullPointerException e) {
			btnPlayerCard7 = null;
			btnPlayerCard7 = new JPanel();
			btnPlayerCard7.setBounds(860, 408, 98, 125);
			JLabel imageCard7 = new JLabel("no card");
			imageCard7.setBounds(860, 408, 98, 125);
			imageCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard7.add(imageCard7);
			contentPane.add(btnPlayerCard7);
		} catch (IndexOutOfBoundsException e) {
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
			String card8File = "/" + gameInstance.currentPlayer.getHand().get(8).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(8).getZahl().toString() + ".jpg";
			JLabel imageCard8 = new JLabel();
			imageCard8.setBounds(320, 543, 98, 125);
			imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource(card8File)));
			btnPlayerCard8.add(imageCard8);
			contentPane.add(btnPlayerCard8);
		} catch (NullPointerException e) {
			btnPlayerCard8 = null;
			btnPlayerCard8 = new JPanel();
			btnPlayerCard8.setBounds(320, 543, 98, 125);
			JLabel imageCard8 = new JLabel("no card");
			imageCard8.setBounds(320, 543, 98, 125);
			imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard8.add(imageCard8);
			contentPane.add(btnPlayerCard8);

		} catch (IndexOutOfBoundsException e) {
			btnPlayerCard8 = null;
			btnPlayerCard8 = new JPanel();
			btnPlayerCard8.setBounds(320, 543, 98, 125);
			JLabel imageCard8 = new JLabel("no card");
			imageCard8.setBounds(320, 543, 98, 125);
			imageCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard8.add(imageCard8);
			contentPane.add(btnPlayerCard8);
		}
		;
///////////////////////////////////////////////////////////////////////////////////
		try {
			btnPlayerCard9 = null;
			btnPlayerCard9 = new JPanel();
			btnPlayerCard9.setBounds(428, 543, 98, 125);
			String card9File = "/" + gameInstance.currentPlayer.getHand().get(9).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(9).getZahl().toString() + ".jpg";
			JLabel imageCard9 = new JLabel();
			imageCard9.setBounds(428, 543, 98, 125);
			imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource(card9File)));
			btnPlayerCard9.add(imageCard9);
			contentPane.add(btnPlayerCard9);
		} catch (NullPointerException e) {
			btnPlayerCard9 = null;
			btnPlayerCard9 = new JPanel();
			btnPlayerCard9.setBounds(428, 543, 98, 125);
			JLabel imageCard9 = new JLabel("no Card");
			imageCard9.setBounds(428, 543, 98, 125);
			imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard9.add(imageCard9);
			contentPane.add(btnPlayerCard9);
		} catch (IndexOutOfBoundsException e) {
			btnPlayerCard9 = null;
			btnPlayerCard9 = new JPanel();
			btnPlayerCard9.setBounds(428, 543, 98, 125);
			JLabel imageCard9 = new JLabel("no Card");
			imageCard9.setBounds(428, 543, 98, 125);
			imageCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard9.add(imageCard9);
			contentPane.add(btnPlayerCard9);
		}
		;

///////////////////////////////////////////////////////////////////////////////////
		try {
			btnPlayerCard10 = null;
			btnPlayerCard10 = new JPanel();
			btnPlayerCard10.setBounds(536, 543, 98, 125);
			String card10File = "/" + gameInstance.currentPlayer.getHand().get(10).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(10).getZahl().toString() + ".jpg";
			JLabel imageCard10 = new JLabel();
			imageCard10.setBounds(536, 543, 98, 125);
			imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource(card10File)));
			btnPlayerCard10.add(imageCard10);
			contentPane.add(btnPlayerCard10);
		} catch (NullPointerException e) {
			btnPlayerCard10 = null;
			btnPlayerCard10 = new JPanel();
			btnPlayerCard10.setBounds(536, 543, 98, 125);
			JLabel imageCard10 = new JLabel("no card");
			imageCard10.setBounds(536, 543, 98, 125);
			imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard10.add(imageCard10);
			contentPane.add(btnPlayerCard10);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("less than 11 cards left for this player");
			btnPlayerCard10 = null;
			btnPlayerCard10 = new JPanel();
			btnPlayerCard10.setBounds(536, 543, 98, 125);
			JLabel imageCard10 = new JLabel("no card");
			imageCard10.setBounds(536, 543, 98, 125);
			imageCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard10.add(imageCard10);
			contentPane.add(btnPlayerCard10);
		}
		;

///////////////////////////////////////////////////////////////////////////////////
		try {
			btnPlayerCard11 = null;
			btnPlayerCard11 = new JPanel();
			btnPlayerCard11.setBounds(644, 543, 98, 125);
			String card11File = "/" + gameInstance.currentPlayer.getHand().get(11).getSymbol().toString() + "_"
					+ gameInstance.currentPlayer.getHand().get(11).getZahl().toString() + ".jpg";
			JLabel imageCard11 = new JLabel();
			imageCard11.setBounds(644, 543, 98, 125);
			imageCard11.setIcon(new javax.swing.ImageIcon(getClass().getResource(card11File)));
			btnPlayerCard11.add(imageCard11);
			contentPane.add(btnPlayerCard11);
		} catch (NullPointerException e) {
			btnPlayerCard11 = null;
			btnPlayerCard11 = new JPanel();
			btnPlayerCard11.setBounds(644, 543, 98, 125);
			JLabel imageCard11 = new JLabel();
			imageCard11.setBounds(644, 543, 98, 125);
			imageCard11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard11.add(imageCard11);
			contentPane.add(btnPlayerCard11);
		} catch (IndexOutOfBoundsException e) {
			btnPlayerCard11 = null;
			btnPlayerCard11 = new JPanel();
			btnPlayerCard11.setBounds(644, 543, 98, 125);
			JLabel imageCard11 = new JLabel();
			imageCard11.setBounds(644, 543, 98, 125);
			imageCard11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emptyCard.jpg")));
			btnPlayerCard11.add(imageCard11);
			contentPane.add(btnPlayerCard11);
		}
		;
		// SwingUtilities.updateComponentTreeUI(this);

		contentPane.revalidate();

///////////////////////////////////////////////////////////////////////////////////

	}

//------------------------------------------------------------------------------------------------------------------------------------------

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
		if(gameInstance.getCurrentPlayer().getName() == "BotPlayer") {
			botPlayerService.validateBotMove(gameInstance);
		}
		else {
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
						updateCurrentBoardCardPanels(gameInstance);
						updateCardButtons(gameInstance);
						updateCurrentPlayerLabel();
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
								updateCurrentBoardCardPanels(gameInstance);
								updateCardButtons(gameInstance);
								updateCurrentPlayerLabel();
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

							updateCurrentBoardCardPanels(gameInstance);
							updateCardButtons(gameInstance);
							updateCurrentPlayerLabel();
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
					updateCurrentBoardCardPanels(gameInstance);

				}
			} catch (NullPointerException e) {
				gameInstance.setBoardCards(null);
				updateCurrentBoardCardPanels(gameInstance);
			} catch (IndexOutOfBoundsException IOOB) {
				gameInstance.setBoardCards(null);
				updateCurrentBoardCardPanels(gameInstance);
			}
			// Reset des PAssspielzug-Counters nach jedem validen Spielzug
			passCounter = 0;
		}
		
	}
	
	public void validateBotMove() {
		System.out.println("*****BOT MOVE*****");
		List<Card> tempCardList = new LinkedList<Card>();
		List<Card> botHandCards = new LinkedList<Card>();
		botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
		boolean twoCardsEqual = true;
		
		if(gameInstance.getBoardCards() == null) {
			System.out.println("BOT MOVE; BOARD NULL");
			Card x = (Card) botHandCards.get(0);
			Card y = (Card) botHandCards.get(1);
			
			int c = x.compareTo(y);
			if (c == 0) {
				twoCardsEqual = true;
			} else {
				twoCardsEqual = false;
			}
			if(twoCardsEqual) {
				tempCardList.add(x);
				tempCardList.add(y);
				
			} else {
				tempCardList.add(x);
			}
		} else {
			System.out.println("BOT MOVE; BOARD HAT KARTEN");
			Card b = gameInstance.getBoardCards().get(0);
			
			for(Card card : botHandCards) {//durch alle karten des bot
				if(card.getZahl().equals(b.getZahl())) { //die karten, die mit board stimmen
					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
						tempCardList.add(card);
					}
				}
//				if(card.getZahl().compareTo(b.getZahl())==1) { //die karten, die mit board stimmen
//					//card.getZahl().equals(b.getZahl())
//					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
//						tempCardList.add(card);
//					}
//				}
			}
		}
		gameInstance.setBoardCards(tempCardList);
		PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
		addCurrentPlayerToResult();
		gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
		updateCurrentBoardCardPanels(gameInstance);
		updateCardButtons(gameInstance);
		updateCurrentPlayerLabel();
		
		try {
			if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {

				// Setzen der boardCards auf null, Update Frontend
				gameInstance.setBoardCards(null);
				updateCurrentBoardCardPanels(gameInstance);

			}
		} catch (NullPointerException e) {
			gameInstance.setBoardCards(null);
			updateCurrentBoardCardPanels(gameInstance);
		} catch (IndexOutOfBoundsException IOOB) {
			gameInstance.setBoardCards(null);
			updateCurrentBoardCardPanels(gameInstance);
		}
		// Reset des PAssspielzug-Counters nach jedem validen Spielzug
		//passCounter = 0;
	}
	
	/**
	 * NUR ZUM TESTEN VON BOT MOVE
	 * @param tempCardList
	 * @param k
	 * @return
	 */
	public boolean checkSelectedCardsIfEqual(List<Card> tempCardList, int k) {
		// tempCards valid? compareTo -> die ausgewählten Karten müssen die gleichen
		// Zahlen haben
		boolean tempCardsEqual = true;
		
		// gerade hinzugefügte Card
		Card x = (Card) tempCardList.get(k);
		Card y = (Card) ((LinkedList) tempCardList).getFirst();

		int c = x.compareTo(y);
		if (c == 0) {
			tempCardsEqual = true;
		} else {
			tempCardsEqual = false;
		}
		return tempCardsEqual;
	}

	/*
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
			System.out.println("update player label 2");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("update player label 3");
		}
		lblCurrentPlayer.repaint();

	}

	/*
	 * Methode, die nach Spielabschluss erfaesst, ob weitergepspielt werden soll
	 * 
	 * @return boolean - weiterspielen oder nicht ( true , false )
	 * 
	 */
	Boolean getContinueGame() throws IllegalArgumentException {
		String continueGame = JOptionPane.showInputDialog(null, "Weiterspielen (J/N)?");
		if (continueGame.equalsIgnoreCase("j") | continueGame.equalsIgnoreCase("ja")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Fragt den User ab, wer die naechste Runde anfangen soll zu legen, und setzt
	 * entweder Arschloch oder Praesident als current player.
	 *
	 * @throws IllegalArgumentException
	 */
	void setInitialPlayerForNextRound() throws IllegalArgumentException {
		String initialPlayerForNextRound = JOptionPane.showInputDialog(null,
				"Wer soll anfangen (Arschloch (a)/Praesident (p))?");
		if (initialPlayerForNextRound.equalsIgnoreCase("a")) {
			for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
				try {
					if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.ARSCHLOCH1)) {
						gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i)); // current player setzen mit arschloch
						System.out.println("ARSCHLOCH1 faengt an");
					}
				} catch (Exception e) {

				}
			}
		} else if (initialPlayerForNextRound.equalsIgnoreCase("p")) {
			for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
				try {
					if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.PRAESIDENT1)) {
						gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i)); // current player setzen mit praesident
						System.out.println("PRAESIDENT1 faengt an");
					}
				} catch (Exception e) {

				}
			}
		} else {
			setInitialPlayerForNextRound();
		}
	}

	/*
	 * Methode, die ueberprueft, ob ein Spieler noch Karten hat, um ihn, falls dem
	 * nicht so ist, in die Erbegnissliste einzutragen, anhand derer spaeter die
	 * Rollen der Spieler ermittelt werden
	 */
	public void addCurrentPlayerToResult() {
		if (gameInstance.getCurrentPlayer().getHand().isEmpty()) {
			if (!gameInstance.getResult().contains(gameInstance.getCurrentPlayer())) {
				gameInstance.setResult(gameInstance.getCurrentPlayer());

				System.out.println("RESULT LISTE " + gameInstance.getResult().size());

			}
		}
		
		//um den letzten Spieler aufzunehmen, da dieser noch Karten hat,
		// aber trotzdem in die result liste muss
		if(gameInstance.getResult().size() == gameInstance.players.size()-1) {
			gameInstance.setResult(gameInstance.getCurrentPlayer());
		}
	}

	/*
	 * Setzen der Spielerrollen, Ueber die Reihenfolge, in der die Spieler in die
	 * Ergebnisliste aufgenommen wurden laesst sich ermitteln, welche Rolle sie
	 * haben ( erster Spieler, der fertig ist =
	 * gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT) usw. ) Simple
	 * Implementierung, da momentan nur mit 3 Spielern spielbar
	 * 
	 */
	private void setPlayerRoles() {
		int resultSize = gameInstance.getResult().size();
		gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT1);
		gameInstance.getResult().get(resultSize-1).setRole(Player.Role.ARSCHLOCH1);

		if(resultSize>3) {
			gameInstance.getResult().get(1).setRole(Player.Role.PRAESIDENT2);
			gameInstance.getResult().get(resultSize-2).setRole(Player.Role.ARSCHLOCH2);
		}
	}
}
