package game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entities.BoardGame;
import entities.Card;
import entities.Color;
import entities.Pawn;
import entities.Player;

public class Game
{
	private List<Player> listPlayers = new LinkedList<Player>();
	private List<Card> listCard = new LinkedList<Card>();
	private List<String> blockedPos = new LinkedList<String>();
	private List<String> AuthorizedPos = new LinkedList<String>();
	private Scanner sc = new Scanner(System.in);
	private Scanner sc2 = new Scanner(System.in);
	private int currentCard = 0;
	private BoardGame bg;
	
	public Game(){}
	
	public void initGame()
	{
		bg = new BoardGame();
		System.out.println("---------- Bakari ----------\n\n");
		initPlayers();
		initPawns();
		initCard();
		startGame();
	}
	
	private void generateBlockedList()
	{
		for(int x=0;x<bg.getNbRow();x++)
			for(int y=0;y<bg.getNbCol();y++)
				if(takedCard.getColor()==bg.getColor(x, y)){
					blockedPos.add("("+x+","+y+")");
				}
		for(Player p : this.listPlayers){
			for(Pawn pa : p.getPawns()){
				int x = pa.getPositionX();
				int y = pa.getPositionY();
				blockedPos.add("("+x+","+y+")");
			}
		}
	}
	public void possibility(int x1, int y1, int x2, int y2){
		
		for(String b : this.blockedPos){
			int xb =  Integer.parseInt(b.substring(1,2));
			int yb =  Integer.parseInt(b.substring(3,4));
			for(int x=x1;x<x2;x++)
				for(int y=y1;y<y2;y++)
					if(x<xb && y<yb)
						AuthorizedPos.add("("+x+","+y+")");
						
		}
	}
	
	private void startGame()
	{
		System.out.println("Démmarage de la partie...");
		int currentPlayer = 1;
		
		
	}
	
	public boolean movePawn(Pawn p, int x, int y){
		int lastPosX = p.getPositionX();
		int lastPosY = p.getPositionY();
		Color actualCardColor = listCard.get(currentCard).getColor();
		possibility(lastPosX, lastPosY, x, y);
		if(bg.getColor(x, y)!=actualCardColor && lastPosX==x || lastPosY==y && AuthorizedPos.contains("("+x+","+y+")")){
			return true;
		}else {
			return false;
		}
	}
	
	private void initPlayers()
	{
		int nbPlayer;
		System.out.println("Entrez le nombre de joueur (2-3-4) : ");
		nbPlayer = sc.nextInt();
		if(nbPlayer>1 && nbPlayer<5)
		{
			for(int i=0;i<nbPlayer;i++)
			{	
				String playerName;
				System.out.println("Entre le nom du joueur "+i+" :");	
				playerName = sc2.nextLine();
				if(playerName != "" && playerName != null)
				{
					this.listPlayers.add(new Player(playerName));
				}	
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
		System.out.println("Carte initialisé et mélangé.");
		
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
