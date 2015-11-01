package gui.panels;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Game;
import gui.panels.init.ImagePanel;

public class CardPanel extends JPanel
{
	private static final long serialVersionUID = 7876870349639324992L;
	private JLabel label;
	private ImagePanel currentImagePanel;
	
	private Game game;
	
	public CardPanel(Game g)
	{
		this.game = g;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(150, 600));
		
		this.label = new JLabel();
		this.label.setText("<html><u>Carte tirée :</u></html>");
		this.label.setBounds(10, 20, 134, 30);
		this.add(label);
		currentImagePanel = new ImagePanel("dos.png");
		currentImagePanel.setBounds(10, 60, 134, 132);
		this.add(currentImagePanel);
	}
	
	public void update()
	{
		if(game.getCurrentCard() != null) {
			currentImagePanel.setImgName(game.getCurrentCard().toString().toLowerCase() + ".png");
		}
		else {
			currentImagePanel.setImgName("dos.png");
		}
	}
}
