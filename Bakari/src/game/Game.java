package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
		
		for(Player pl : listPlayers)
			System.out.println(pl.getNom());
	}
	
	private void initPawns()
	{
		if(this.listPlayers.size() == 2)
		{
			
		}
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
