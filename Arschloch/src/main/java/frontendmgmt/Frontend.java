package frontendmgmt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt das Frontend dar.
 */
public class Frontend implements ActionListener{

    JPanel panel = new JPanel();
    JButton startGameButton = new JButton("Start Game");
    JFrame frame = new JFrame("Arschloch");
    
    public Frontend() {
    	
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(700, 700);
    	startGameButton.addActionListener(this);
    	panel.add(startGameButton);
    	frame.getContentPane().setBackground(Color.GREEN);
    	panel.setBackground(Color.GREEN);
    	frame.add(panel);
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
   private int getUserCountInput() {
	return 0;}

    /**
     * ein Dialog zum Erfassen von Userinput ( Name der für das kommende Spiel zu erstellenden Spieler )
     *
     * @return Name der Spieler
     */
    private String getUserNameInput() {
		return null;}

    /**
     * ein Dialog zum Erfassen von Userinput ( gespielte Karten pro Zug )
     * In einer Runde kann jeder Spieler Karten spielen.
     * Hier wählt der Spieler aus, welche Karte(n) von seiner Hand er spielen möchte.
     * @return List<Card> Liste der vom Spieler gespielten Katen. null = Spielzug Pass
     */
    private List<Card> getPlayerSelectCardsMoveInput() {
		return null;}

    
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField tfSpieleranzahl = new JTextField("Spieleranzahl eingeben", 1);
		panel.add(tfSpieleranzahl);
		System.out.println("Spieleranzahl: " + 5);
		
	}

}
