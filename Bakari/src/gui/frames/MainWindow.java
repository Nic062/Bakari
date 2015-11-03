package gui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import game.Game;
import gui.panels.AboutPanel;
import gui.panels.CardPanel;
import gui.panels.GameboardPanel;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -809655995462146760L;

	private CardPanel cPanel;
	private AboutPanel aPanel;
	private GameboardPanel gPanel;

	private Game game;

	public MainWindow(Game g)
	{
		this.game = g;
		this.cPanel = new CardPanel(g);
		this.aPanel = new AboutPanel();
		this.gPanel = new GameboardPanel(g);
		this.setSize(800, 600);
		this.setTitle("Bakari - En cours");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(aPanel, BorderLayout.SOUTH);
		this.add(cPanel, BorderLayout.WEST);
		this.add(gPanel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void addText(String t)
	{
		aPanel.addText(t);
	}

	public void updateCard()
	{
		cPanel.update();
	}

	public void updatePawns()
	{
		gPanel.update();
	}
}
