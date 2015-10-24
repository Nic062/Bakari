package gui.panels;

import entities.BoardGame;
import entities.Pawn;
import game.Game;
import gui.frames.MainWindow;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameboardPanel extends JPanel
{
	private static final long serialVersionUID = 168839659013621228L;
	
	private char[][] grid;
	
	private MainWindow main;
	private AboutPanel aPanel;
	private Game g;

	public GameboardPanel(MainWindow main)
	{
		System.out.println("Constructeur GameboardPanel");
		this.g = new Game();
		this.main = main;
		this.aPanel = main.getAboutPanel();
		this.aPanel.setTextZone("Initialisation du jeu et des joueurs...");
		this.aPanel.setTextZone("Début de la partie...");
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(13, 12));
		
		this.grid = BoardGame.getGrid();
		
		
		initCases();
		initPawn();
	}
	
	private void initCases()
	{
		for(char cases[] : grid)
		{
			for(char c : cases)
			{
				if(c == 'o')
					addCase(Color.ORANGE);
				else if(c == 'b')
					addCase(Color.BLUE);
				else if(c == 'g')
					addCase(Color.GREEN);
				else if(c == 'p')
					addCase(new Color(138, 2, 177)); // Violet
				else if(c == 'x')
					addCase(Color.WHITE);
				else
					addCase(Color.BLACK);
			}
		}
	}
	public void initPawn(){
		System.err.println("NB JOUEUR : ");//+main.getPlayers().size());
		//for(Player p : main.getPlayers())
		//	System.out.println("JOUEUR");
			
	}
	private void addPawn(Pawn p){
		PawnPanel c = new PawnPanel(p);
		System.out.println("AJOUT");
	}
	private void addCase(Color color)
	{
		CasePanel c = new CasePanel(color);
		this.add(c);
	}
}
