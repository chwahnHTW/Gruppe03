package frontendmgmt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.picocontainer.annotations.Inject;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import playermgmt.Player;
import playermgmt.PlayerService;
import playermgmt.PlayerServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt das Frontend dar.
 */
public class Frontend implements ActionListener{

	//@Inject
	PlayerService playerService = new PlayerServiceImpl();
	
    JPanel panel = new JPanel();
    JButton startGameButton = new JButton("Start Game");
    JFrame frame = new JFrame("Arschloch");
    GameInstance gameInstance;
    JPanel playerPanel = new JPanel();
    
    
    
    
    public void createFrame(GameInstance gameInstance) {
    	this.gameInstance = gameInstance;
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(700, 700);
    	startGameButton.addActionListener(this);
    	panel.add(startGameButton);
    	frame.getContentPane().setBackground(Color.GREEN);
    	//frame.getContentPane().add(panel);
    	panel.setBackground(Color.BLUE);
    	frame.add(panel);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    }
    
    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
   private void endRound(GameInstance game) {};

    /**
     * Eine Spielinstanz wird erstellt und zurückgegeben.
     * GUI wird mit Spielinstanz bestückt
     *
     * @return : eine Spielinstanz
     */
    private GameInstance startGame() {
		return new GameInstance();}

    /**
     * ein Dialog zum Erfassen von Userinput ( Anzahl der für das kommende Spiel zu erstellenden Spieler )
     *
     * @return Anzahl der zu erstellenden Spieler
     */
   private int getUserCountInput() throws IllegalArgumentException{
	   String spieleranzahl = JOptionPane.showInputDialog(null, "Bitte Spieleranzahl eingeben");
	return Integer.valueOf(spieleranzahl);}

    /**
     * ein Dialog zum Erfassen von Userinput ( Name der für das kommende Spiel zu erstellenden Spieler )
     *
     * @return Name der Spieler
     */
    private String getUserNameInput() {
		String spielerName;
		return spielerName = JOptionPane.showInputDialog(null, "Bitte Spielernamen eingeben");}

    /**
     * ein Dialog zum Erfassen von Userinput ( gespielte Karten pro Zug )
     * In einer Runde kann jeder Spieler Karten spielen.
     * Hier wählt der Spieler aus, welche Karte(n) von seiner Hand er spielen möchte.
     * @return List<Card> Liste der vom Spieler gespielten Katen. null = Spielzug Pass
     */
    private List<Card> getPlayerSelectCardsMoveInput() {
		return null;}

    
    
   // @Inject
	@Override
	public void actionPerformed(ActionEvent e) {
		gameInstance.players = new LinkedList<Player>();
		int count = this.getUserCountInput();
    	for ( int i = 0 ;  i < count; i++) {
    		gameInstance.players.add(playerService.createPlayer(this.getUserNameInput()));}
    	this.enableInstanceView();
    	frame.repaint();
	}

	/**
	 * AdaptsFrame according to UserInput
	 */
	private void enableInstanceView() {
	panel.remove(startGameButton);	
	for ( int i = 0; i < gameInstance.players.size();i++) {
		//("Player" + i + gameInstance.players.get(i).getName()))
		JTextArea ta = new JTextArea(1, 1);
		ta.setText("Player" + i+1 + gameInstance.players.get(i).getName());
		ta.setVisible(true);
		//ta.setEditable(false);
	panel.add(ta);
	System.out.println("working");
	}	
	}
}
