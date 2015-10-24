package gui.frames;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import entities.Player;
import gui.panels.AboutPanel;
import gui.panels.CardPanel;
import gui.panels.GameboardPanel;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -809655995462146760L;
	
	private List<Player> players;
	
	private CardPanel cPanel = new CardPanel();
	private AboutPanel aPanel = new AboutPanel();
	private GameboardPanel gPanel = new GameboardPanel(this);

	public MainWindow()
	{
		this.setSize(800, 600);
		this.setTitle("Bakari - Partie en cours");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(gPanel, BorderLayout.CENTER);
		this.add(aPanel, BorderLayout.SOUTH);
		this.add(cPanel, BorderLayout.WEST);
	}
	
	public AboutPanel getAboutPanel()
	{
		return this.aPanel;
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> p){
		this.players=p;
	}
}
