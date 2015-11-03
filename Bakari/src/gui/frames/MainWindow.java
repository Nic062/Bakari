package gui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import game.Game;
import gui.panels.CardPanel;
import gui.panels.InfoPanel;
import gui.panels.AboutPanel;
import gui.panels.GameboardPanel;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -809655995462146760L;

	private CardPanel cPanel;
	private InfoPanel iPanel;
	private AboutPanel aPanel;
	private GameboardPanel gPanel;

	public MainWindow(Game g)
	{
		this.cPanel = new CardPanel(g);
		this.iPanel = new InfoPanel(g);
		this.aPanel = new AboutPanel();
		this.gPanel = new GameboardPanel(g);
		this.setSize(800, 600);
		this.setTitle("Bakari - En cours");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(cPanel, BorderLayout.WEST);
		this.add(iPanel, BorderLayout.EAST);
		this.add(aPanel, BorderLayout.SOUTH);
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
	
	public void updateInfo()
	{
		iPanel.update();
	}

	public void updatePawns()
	{
		gPanel.update();
	}
}
