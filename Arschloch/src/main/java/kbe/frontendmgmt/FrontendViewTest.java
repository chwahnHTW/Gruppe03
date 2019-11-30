package kbe.frontendmgmt;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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

import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;




public class FrontendViewTest extends JFrame {

	private JPanel contentPane;
	private JButton btnPass;
	private JButton btnPlaycards;
	private JButton btnStartGame;
	private JLabel lblCurrentPlayer;
	private JPanel playerCardPanel0;
	private JPanel playerCardPanel1;
	private JPanel playerCardPanel2;
	private JPanel playerCardPanel3;
	private JPanel playerCardPanel4;
	private JPanel playerCardPanel5;
	private JPanel playerCardPanel6;
	private JPanel playerCardPanel7;
	private JPanel playerCardPanel8;
	private JPanel playerCardPanel9;
	private JPanel playerCardPanel10;
	private JPanel playerCardPanel11;
	private JPanel playerCardPanel12;
	private JPanel playerCardPanel13;
	private JPanel playerCardPanel14;
	private JPanel playerCardPanel15;
	private JPanel playerNamesPanel;
	private JPanel currentBoardCardPanel1;
	private JPanel currentBoardCardPanel2;
	private JPanel currentBoardCardPanel3;
	private JPanel currentBoardCardPanel4;
	private JLabel lblPlayers;
	private JLabel lblPlayername;
	private JLabel lblCurrentBoardcards;
	
	
	private GameInstanceService GISI = new GameInstanceServiceImpl();
	private PlayerService PLAYSI = new PlayerServiceImpl();
	private GameInstance gameInstance;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontendViewTest frame = new FrontendViewTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontendViewTest() {
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
			gameInstance = GISI.startGame();
			gameInstance.players = new LinkedList<>();
			int playerCount = getUserCountInput();
			for ( int i = 0; i < playerCount; i++) {
			Player player = PLAYSI.createPlayer(getUserNameInput());	
			gameInstance.players.add(player);
			}
			System.out.println(gameInstance.getPlayers().size());
			try {
				setupFrontend();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnStartGame.setForeground(Color.WHITE);
		btnStartGame.setBackground(new Color(0, 0, 153));
		btnStartGame.setBounds(456, 276, 180, 63);
		contentPane.add(btnStartGame);
		
	}
	
	   private int getUserCountInput() throws IllegalArgumentException{
		   String spieleranzahl = JOptionPane.showInputDialog(null, "Bitte Spieleranzahl eingeben");
		return Integer.valueOf(spieleranzahl);}
	   
	   
	   private String getUserNameInput() {
			String spielerName;
			return spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");}
	   
	   
	private void setupFrontend() throws IOException {
		
		this.remove(btnStartGame);
		
		
		btnPlaycards = new JButton("PlayCard(s)");
		btnPlaycards.setForeground(Color.WHITE);
		btnPlaycards.setBackground(new Color(0, 0, 153));
		btnPlaycards.setBounds(727, 262, 99, 21);
		contentPane.add(btnPlaycards);
		
		btnPass = new JButton("Pass");
		btnPass.setForeground(Color.WHITE);
		btnPass.setBackground(new Color(255, 0, 0));
		btnPass.setBounds(727, 318, 99, 21);
		contentPane.add(btnPass);
		
		lblCurrentPlayer = new JLabel("Current Player :");
		lblCurrentPlayer.setBounds(104, 276, 155, 34);
		contentPane.add(lblCurrentPlayer);
		
		 playerCardPanel0 = new JPanel();
		playerCardPanel0.setBounds(103, 369, 99, 125);
		contentPane.add(playerCardPanel0);
		
		 playerCardPanel1 = new JPanel();
		playerCardPanel1.setBounds(212, 369, 99, 125);
		contentPane.add(playerCardPanel1);
		
		 playerCardPanel2 = new JPanel();
		playerCardPanel2.setBounds(321, 369, 99, 125);
		contentPane.add(playerCardPanel2);
		
		 playerCardPanel3 = new JPanel();
		playerCardPanel3.setBounds(430, 369, 99, 125);
		contentPane.add(playerCardPanel3);
		
		 playerCardPanel4 = new JPanel();
		playerCardPanel4.setBounds(555, 369, 99, 125);
		contentPane.add(playerCardPanel4);
		
		 playerCardPanel5 = new JPanel();
		playerCardPanel5.setBounds(666, 369, 99, 125);
		contentPane.add(playerCardPanel5);
		
		 playerCardPanel6 = new JPanel();
		playerCardPanel6.setBounds(775, 369, 99, 125);
		contentPane.add(playerCardPanel6);
		
		 playerCardPanel7 = new JPanel();
		playerCardPanel7.setBounds(884, 369, 99, 125);
		contentPane.add(playerCardPanel7);
		
		 playerCardPanel8 = new JPanel();
		 playerCardPanel8.setVisible(false);
		playerCardPanel8.setBounds(103, 527, 99, 125);
		contentPane.add(playerCardPanel8);
		
		 playerCardPanel9 = new JPanel();
		 playerCardPanel9.setVisible(false);
		playerCardPanel9.setBounds(212, 527, 99, 125);
		contentPane.add(playerCardPanel9);
		
		 playerCardPanel10 = new JPanel();
		 playerCardPanel10.setVisible(false);
		playerCardPanel10.setBounds(321, 527, 99, 125);
		contentPane.add(playerCardPanel10);
		
		 playerCardPanel11 = new JPanel();
		 playerCardPanel11.setVisible(false);
		playerCardPanel11.setBounds(430, 527, 99, 125);
		contentPane.add(playerCardPanel11);
		
		 playerCardPanel12 = new JPanel();
		 playerCardPanel12.setVisible(false);
		playerCardPanel12.setBounds(555, 527, 99, 125);
		contentPane.add(playerCardPanel12);
		
		 playerCardPanel13 = new JPanel();
		 playerCardPanel13.setVisible(false);
		playerCardPanel13.setBounds(666, 527, 99, 125);
		contentPane.add(playerCardPanel13);
		
		 playerCardPanel14 = new JPanel();
		 playerCardPanel14.setVisible(false);
		playerCardPanel14.setBounds(775, 527, 99, 125);
		contentPane.add(playerCardPanel14);
		
		playerCardPanel15 = new JPanel();
		playerCardPanel15.setVisible(false);
		playerCardPanel15.setBounds(884, 527, 99, 125);
		contentPane.add(playerCardPanel15);
		
		 currentBoardCardPanel1 = new JPanel();
		currentBoardCardPanel1.setBounds(377, 93, 99, 125);
		contentPane.add(currentBoardCardPanel1);
		
		 currentBoardCardPanel2 = new JPanel();
		currentBoardCardPanel2.setBounds(486, 93, 99, 125);
		contentPane.add(currentBoardCardPanel2);
		
		 currentBoardCardPanel3 = new JPanel();
		currentBoardCardPanel3.setBounds(595, 93, 99, 125);
		contentPane.add(currentBoardCardPanel3);
		
		 currentBoardCardPanel4 = new JPanel();
		currentBoardCardPanel4.setBounds(704, 93, 99, 125);
		contentPane.add(currentBoardCardPanel4);
		
		lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(50, 54, 73, 29);
		contentPane.add(lblPlayers);
		
		lblPlayername = new JLabel("PlayerName");
		lblPlayername.setBounds(104, 310, 139, 37);
		contentPane.add(lblPlayername);
		
		playerNamesPanel = new JPanel();
		playerNamesPanel.setBounds(50, 93, 99, 125);
		
		String playerNames = "<html>";
		for ( int i = 0 ; i < gameInstance.getPlayers().size(); i++) {
			playerNames += gameInstance.getPlayers().get(i).getName() + "<br>";
		}
		playerNames += "</html>";
		playerNamesPanel.add(new JLabel(playerNames));
		contentPane.add(playerNamesPanel);
		
		
		lblCurrentBoardcards = new JLabel("Current BoardCard(s)");
		lblCurrentBoardcards.setBounds(544, 54, 125, 13);
		contentPane.add(lblCurrentBoardcards);
		
	    JLabel jl=new JLabel();
	    jl.setIcon(new javax.swing.ImageIcon(getClass().getResource("emptyCard.jpg")));
	    currentBoardCardPanel1.add(jl);
		
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
	
}