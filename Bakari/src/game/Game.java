package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entities.Color;
import entities.Pawn;
import entities.Player;

public class Game
{
	private List<Player> listPlayers = new LinkedList<Player>();
	private Scanner sc = new Scanner(System.in);
	
	public Game(){}
	
	public void initGame()
	{
		System.out.println("---------- Bakari ----------\n\n");
		initPlayers();
		initPawns();
	}
	
	private void initPlayers()
	{
		String reponse = "";
		while(!reponse.equals("fin"))
		{
			System.out.println("Entrez les noms des joueurs puis écrivez \"fin\" quand vous avez fini :");
			reponse = sc.nextLine();
			if(!reponse.equals("fin"))
			{
				this.listPlayers.add(new Player(reponse));
			}
		}
	}
	
	private void initPawns()
	{
		if(this.listPlayers.size() == 2)
		{
			for(int i = 0; i < 2; i++)
			{
				if(i == 0)
					this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, 0, 6));
				else
					this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, 0, 6));
			}
		}
		else if(this.listPlayers.size() == 3)
		{
			for(int i = 0; i < 3; i++)
			{
				if(i == 0)
					this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, 0, 4));
				else if(i == 1)
					this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, 0, 4));
				else
					this.listPlayers.get(i).addPawn(new Pawn(Color.GREEN, 0, 0, 4));
			}
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				if(i == 0)
					this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, 0, 3));
				else if(i == 1)
					this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, 0, 4));
				else if(i == 2)
					this.listPlayers.get(i).addPawn(new Pawn(Color.GREEN, 0, 0, 4));
				else
					this.listPlayers.get(i).addPawn(new Pawn(Color.WHITE, 0, 0, 4));
			}
		}
		
		// Boucle qui sert juste de test
		/*for(Player pl : this.listPlayers)
		{
			pl.afficher();
		}*/ 
	}
	
	public List<Player> getPlayers()
	{
		return this.listPlayers;
	}
	public void setPlayers(List<Player> list)
	{
		this.listPlayers = list;
	}
}
