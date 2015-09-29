package game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entities.Card;
import entities.Color;
import entities.Pawn;
import entities.Player;

public class Game
{
	private List<Player> listPlayers = new LinkedList<Player>();
	private List<Card> listCard = new LinkedList<Card>();
	private Scanner sc = new Scanner(System.in);
	private Scanner sc2 = new Scanner(System.in);
	private int currentCard = 0;
	private boolean isFinished = false;
	private int currentTour = 1;
	
	public Game(){}
	
	public void initGame()
	{
		System.out.println("---------- Bakari ----------\n\n");
		initPlayers();
		initPawns();
		initCard();
		startGame();
	}
	
	private void startGame()
	{
		System.out.println("Démarrage de la partie...");
		
		while(!isFinished)
		{
			takeCard();
			
			for(Player pl : this.listPlayers)
			{
				checkTour(pl);
				this.currentTour++;
			}
			isFinished = true;
		}
	}
	
	private void checkTour(Player pl)
	{
		System.out.println("Tour " + this.currentTour + " - Joueur : " + pl.getNom());
		System.out.println("Indiquez les coordonnées pour votre pion :\n x :");
		int x = sc.nextInt();
		System.out.println("y :");
		int y = sc.nextInt();
		
		//deplacer(pl.getPawns().get(currentTour), x, y);
	}
	
	private void initPlayers()
	{
		System.out.println("Entrez le nombre de joueur (2-3-4) : ");
		int nbPlayer = sc.nextInt();
		if(nbPlayer>1 && nbPlayer<5)
		{
			for(int i=1;i<nbPlayer + 1;i++)
			{
				String playerName = "";
				do
				{
					System.out.println("Entre le nom du joueur "+i+" :");	
					playerName = sc2.nextLine();
					this.listPlayers.add(new Player(playerName));
				}
				while(playerName.isEmpty());
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
					for(int j =0;j<6;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, j));
				else
					for(int j=6;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, j));
			}
		}
		else if(this.listPlayers.size() == 3)
		{
			for(int i = 0; i < 3; i++)
			{
				if(i == 0)
					for(int j=0;j<4;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, j));
				else if(i == 1)
					for(int j=4;j<8;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, j));
				else
					for(int j=8;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.GREEN, 0, j));
			}
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				if(i == 0)
					for(int j=0;j<3;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.RED, 0, j));
				else if(i == 1)
					for(int j=3;j<6;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, 0, j));
				else if(i == 2)
					for(int j=6;j<9;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.GREEN, 0, j));
				else
					for(int j=9;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.WHITE, 0, j));
			}
		}
		
		// Boucle qui sert juste de test
		/*for(Player pl : this.listPlayers)
		{
			pl.afficher();
		}*/ 
	}
	
	private void initCard()
	{
		this.listCard.add(new Card(Color.GREEN));
		this.listCard.add(new Card(Color.PINK));
		this.listCard.add(new Card(Color.BLUE));
		this.listCard.add(new Card(Color.ORANGE));
		Collections.shuffle(listCard);
		System.out.println("Cartes initialisées et mélangées.");
		
	}
	
	public void afficher()
	{
		for(Card c : this.listCard)
		{
			System.out.println("Couleur des cartes : " + c.getColor());
			System.out.println("Visible : " + c.isVisible());
		}
	}
	public Card takeCard()
	{
		Card theCard;
		if(currentCard<listCard.size())
		{
			theCard = listCard.get(currentCard);
			currentCard++;
		}
		else
		{
			Card lastCard=listCard.get(listCard.size()-1);
			Collections.shuffle(listCard);
			while(listCard.get(0)==lastCard)
				Collections.shuffle(listCard);
			currentCard=0;
			return takeCard();
		}
		return theCard;	
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
