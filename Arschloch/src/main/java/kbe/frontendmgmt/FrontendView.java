package kbe.frontendmgmt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerServiceImpl;

@Component
public class FrontendView implements ActionListener{
	
	@Autowired
	PlayerServiceImpl playerService;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		gameInstance.players = new LinkedList<Player>();
		int count = this.getUserCountInput();
    	for ( int i = 0 ;  i < count; i++) {
    		gameInstance.players.add(playerService.createPlayer(this.getUserNameInput()));}
    	this.setPlayerNamesInView();
    	frame.repaint();
	}
	
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
	 * Adapts frame according to user input
	 */
	private void setPlayerNamesInView() {
	panel.remove(startGameButton);	
	for ( int i = 0; i < gameInstance.players.size();i++) {
		//("Player" + i + gameInstance.players.get(i).getName()))
		JTextArea textAreaPlayerNames = new JTextArea(1, 1);
		textAreaPlayerNames.setText("Player" + i+1 + gameInstance.players.get(i).getName());
		textAreaPlayerNames.setVisible(true);
		//ta.setEditable(false);
	panel.add(textAreaPlayerNames);
	System.out.println("working");
	}	
	}

	
}
