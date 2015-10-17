package gui.panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutPanel extends JPanel
{
	private static final long serialVersionUID = -4968528156393741794L;
	
	private JTextArea textZone = new JTextArea();
	
	public AboutPanel()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.textZone.setText("Au tour de joueur 1");
		this.add(textZone);
	}
}