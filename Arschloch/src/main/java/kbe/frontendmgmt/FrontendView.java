package kbe.frontendmgmt;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.gamemgmt.GameInstance;

import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;
import kbe.rulesmgmt.PlayerRulesServicePresidentFirstImpl;

@Component
public class FrontendView extends JFrame {

	
	//@Autowired
	private PlayerService PLAYSI = new PlayerServiceImpl();
	
	//@Autowired
	private CardService cardService = new CardServiceImpl();
	//@Autowired
	private PlayerRulesServicePresidentFirstImpl playerRuleService;
	
	
	private GameInstance gameInstance;
	
	private JPanel contentPane;
	private JButton btnPlaycards;
	private JButton btnPass;
	public JLabel lblCurrentPlayer;
	private JPanel currentBoardCardPanel1;
	private JPanel currentBoardCardPanel2;
	private JPanel currentBoardCardPanel3;
	private JPanel currentBoardCardPanel4;
	private JLabel lblPlayers;
	private JPanel playerNamesPanel;
	private JLabel lblCurrentBoardcards;
	private JButton btnStartGame;
	private JButton btnPlayerCard0;
	private JButton btnPlayerCard1;
	private JButton btnPlayerCard2;
	private JButton btnPlayerCard3;
	private JButton btnPlayerCard4;
	private JButton btnPlayerCard5;
	private JButton btnPlayerCard6;
	private JButton btnPlayerCard7;
	private JButton btnPlayerCard8;
	private JButton btnPlayerCard9;
	private JButton btnPlayerCard10;
	private JButton btnPlayerCard11;
	private JButton btnPlayerCard12;
	private JButton btnPlayerCard13;
	private JButton btnPlayerCard14;
	private JButton btnPlayerCard15;
	private int[] selectedCards = new int[15];
	
	/**
	 * Create the frame.
	 * @return 
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
			for ( int i = 0; i < playerCount; i++) {
			Player player = PLAYSI.createPlayer(getUserNameInput());	
			gameInstance.players.add(player);
			}
			System.out.println(gameInstance.getPlayers().size());

			try {
				gameInstance.currentPlayer = gameInstance.players.get(0);
				System.out.println(gameInstance.currentPlayer.getName());
				
				//cardService.dealCardsToPlayers(gameInstance);
				setupFrontend();
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
	
	   private int getUserCountInput() throws IllegalArgumentException{
		   String spieleranzahl = JOptionPane.showInputDialog(null, "Bitte Spieleranzahl eingeben");
		return Integer.valueOf(spieleranzahl);}
	   
	   
	   String getUserNameInput() {
			String spielerName;
			return spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");}
	   
	   
	    void setupFrontend() throws IOException {
		
		this.remove(btnStartGame);
		btnPlaycards = new JButton("PlayCard(s)");
		btnPlaycards.setForeground(Color.WHITE);
		btnPlaycards.setBackground(new Color(0, 0, 153));
		btnPlaycards.setBounds(721, 335, 99, 21);
		btnPlaycards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showInputDialog(null, "Hier können wir dann eine Karte auswählen");
			};});
		contentPane.add(btnPlaycards);
		
		btnPass = new JButton("Pass");
		btnPass.setForeground(Color.WHITE);
		btnPass.setBackground(new Color(255, 0, 0));
		btnPass.setBounds(859, 335, 99, 21);
		contentPane.add(btnPass);
		
		lblCurrentPlayer = new JLabel("Current Player :" + gameInstance.getCurrentPlayer().getName().toString());
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
	    JLabel jl=new JLabel();
	    jl.setIcon(new javax.swing.ImageIcon(getClass().getResource("emptyCard.jpg")));
	    currentBoardCardPanel4.add(jl);
		contentPane.add(currentBoardCardPanel4);
		
		lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(107, 67, 73, 29);
		contentPane.add(lblPlayers);
		
		
		playerNamesPanel = new JPanel();
		playerNamesPanel.setBounds(104, 93, 98, 125);
		String playerNames = "<html>";
		for ( int i = 0 ; i < gameInstance.getPlayers().size(); i++) {
			playerNames += gameInstance.getPlayers().get(i).getName() + "<br>";
		}
		playerNames += "</html>";
		playerNamesPanel.add(new JLabel(playerNames));
		contentPane.add(playerNamesPanel);
		
		lblCurrentBoardcards = new JLabel("Current BoardCard(s)");
		lblCurrentBoardcards.setBounds(833, 225, 125, 13);
		contentPane.add(lblCurrentBoardcards);
		
		 /////////////////////////////////////////
		btnPlayerCard0 = new JButton("Card 0");
		btnPlayerCard0.setBounds(104, 408, 98, 125);
		
		//String card0File = gameInstance.currentPlayer.getHand().get(0).getSymbol().toString() + "_" + 
		//gameInstance.currentPlayer.getHand().get(0).getZahl().toString() + ".png" ;
		
		String card1File = "/HERZ_10.jpg";
		btnPlayerCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource(card1File)));
		
		
		btnPlayerCard0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[0] = 1;
			};});
		contentPane.add(btnPlayerCard0);
		 /////////////////////////////////////////
		
		btnPlayerCard1 = new JButton("Card 1");
		btnPlayerCard1.setBounds(212, 408, 98, 125);
		btnPlayerCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[1] = 1;
			};});
		contentPane.add(btnPlayerCard1);
		
		btnPlayerCard2 = new JButton("Card 2");
		btnPlayerCard2.setBounds(320, 408, 98, 125);
		btnPlayerCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[2] = 1;
			};});
		contentPane.add(btnPlayerCard2);
		
		btnPlayerCard3 = new JButton("Card 3");
		btnPlayerCard3.setBounds(428, 408, 98, 125);
		btnPlayerCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[3] = 1;
			};});
		contentPane.add(btnPlayerCard3);
		
		btnPlayerCard4 = new JButton("Card 4");
		btnPlayerCard4.setBounds(536, 408, 98, 125);
		btnPlayerCard4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[4] = 1;
			};});
		contentPane.add(btnPlayerCard4);
		
		btnPlayerCard5 = new JButton("Card 5");
		btnPlayerCard5.setBounds(644, 408, 98, 125);
		btnPlayerCard5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[6] = 1;
			};});
		contentPane.add(btnPlayerCard5);
		
		btnPlayerCard6 = new JButton("Card 6");
		btnPlayerCard6.setBounds(752, 408, 98, 125);
		btnPlayerCard6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[6] = 1;
			};});
		contentPane.add(btnPlayerCard6);
		
		btnPlayerCard7 = new JButton("Card 7");
		btnPlayerCard7.setBounds(860, 408, 98, 125);
		btnPlayerCard7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[7] = 1;
			};});
		contentPane.add(btnPlayerCard7);
		
		btnPlayerCard8 = new JButton("Card 8");
		btnPlayerCard8.setBounds(104, 543, 98, 125);
		btnPlayerCard8.setVisible(false);
		btnPlayerCard8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[9] = 1;
			};});
		contentPane.add(btnPlayerCard8);
		
		btnPlayerCard9 = new JButton("Card 9");
		btnPlayerCard9.setBounds(212, 543, 98, 125);
		btnPlayerCard9.setVisible(false);
		btnPlayerCard9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[9] = 1;
			};});
		contentPane.add(btnPlayerCard9);
		
		btnPlayerCard10 = new JButton("Card 10");
		btnPlayerCard10.setBounds(320, 543, 98, 125);
		btnPlayerCard10.setVisible(false);
		btnPlayerCard10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[10] = 1;
			};});
		contentPane.add(btnPlayerCard10);
		
		btnPlayerCard11 = new JButton("Card 11");
		btnPlayerCard11.setBounds(428, 543, 98, 125);
		btnPlayerCard11.setVisible(false);
		btnPlayerCard11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[11] = 1;
			};});
		contentPane.add(btnPlayerCard11);
		
		btnPlayerCard12 = new JButton("Card 12");
		btnPlayerCard12.setBounds(536, 543, 98, 125);
		btnPlayerCard12.setVisible(false);
		btnPlayerCard12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[12] = 1;
			};});
		contentPane.add(btnPlayerCard12);
		
		btnPlayerCard13 = new JButton("Card 13");
		btnPlayerCard13.setBounds(644, 543, 98, 125);
		btnPlayerCard13.setVisible(false);
		btnPlayerCard13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[13] = 1;
			};});
		contentPane.add(btnPlayerCard13);
		
		btnPlayerCard14 = new JButton("Card 14");
		btnPlayerCard14.setBounds(752, 543, 98, 125);
		btnPlayerCard14.setVisible(false);
		btnPlayerCard14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[14] = 1;
			};});
		contentPane.add(btnPlayerCard14);
		
		btnPlayerCard15 = new JButton("Card 15");
		btnPlayerCard15.setBounds(860, 543, 98, 125);
		btnPlayerCard15.setVisible(false);
		btnPlayerCard15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			selectedCards[15] = 1;
			};});
		contentPane.add(btnPlayerCard15);
	
		//gameInstance.currentPlayer = playerRuleService.determineInitialPlayer(gameInstance);
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	

	private void updateCardButtons(GameInstance gameInstance) {
		
		List filePaths = new LinkedList<String>();
		
		for ( int i = 0 ; i < gameInstance.currentPlayer.getHand().size() ; i++) {
		String pathToJPG = "/" +  gameInstance.currentPlayer.getHand().get(i).getSymbol().toString() + "_" + 
		gameInstance.currentPlayer.getHand().get(i).getZahl().toString() + ".jpg" ;
		filePaths.add(pathToJPG);
		}
		
		try {
			btnPlayerCard0.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(0).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(1).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(2).toString())));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			btnPlayerCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(3).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(4).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(5).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(6).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			btnPlayerCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePaths.get(7).toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
	}
}

