package frontendmgmt;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame {

	
	JLabel currentBoardCardName;
	JTextField playerCountInput;

	public GUI() throws HeadlessException {

		this.currentBoardCardName = new JLabel();
		this.playerCountInput = new JTextField();
		
		
		this.setVisible(true);
		this.currentBoardCardName.setVisible(true);
		
	}
}
