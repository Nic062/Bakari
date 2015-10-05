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
	private boolean isFinished = false;
	private int currentTour = 1;
	private BoardGame bg;
	private Card takedCard;

	
	public Game(){}
	
	
	/**
	 * Méthode qui initialise le jeu. 
	 */
	public void initGame()
	{
		bg = new BoardGame();
		System.out.println("---------- Bakari ----------\n\n");
		initPlayers();
		initPawns();
		initCard();
		startGame();
	}
	
	/**
	 * Méthode qui génére toutes les cases bloquées pour chaque tour
	 */
	private void generateBlockedList()
	{
		for(int x=0;x<bg.getNbRow();x++)
			for(int y=0;y<bg.getNbCol();y++)
				if(takedCard.getColor()==bg.getColor(x, y) || bg.getChar(x, y)=='x' ){
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
	
	/**
	 * Méthode qui liste dans un tableau la liste des cases où le déplacement est possible pour un pion
	 * @param x1 position X initial du pion
	 * @param y1 position Y initial du pion
	 * @param x2 position X souhaité du pion
	 * @param y2 position Y souhaité du pion
	 */
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
	
	/**
	 * Méthode qui gére la partie
	 */
	private void startGame()
	{
		System.out.println("Démarrage de la partie...");
		takedCard = takeCard();
		System.out.println("La premiere carte tiré est :" +takeCard().toString());
		while(!isFinished)
		{
			//takeCard();
			
			for(Player pl : this.listPlayers)
			{
				checkTour(pl);
				
			}
			this.currentTour++;
			isFinished = true;
		}
	}
	
	/**
	 * Méthode qui vérifie qui doit jouer
	 * @param pl un joueur
	 */
	private void checkTour(Player pl)
	{
		System.out.println("Tour " + this.currentTour + " - Joueur : " + pl.getNom());
		System.out.println("Indiquez les coordonnées pour votre pion :\n x :");
		int x = sc.nextInt();
		System.out.println("y :");
		int y = sc.nextInt();
		
		//deplacer(pl.getPawns().get(currentTour), x, y);
	}
	
	/**
	 * Méthode qui permet de déplacer un pion
	 * @param p un pion
	 * @param x la position souhaité du pion
	 * @param y la position souhaité du pion
	 * @return vrai si le pion a été posé
	 */
	public boolean movePawn(Pawn p, int x, int y){
		int lastPosX = p.getPositionX();
		int lastPosY = p.getPositionY();
		Color actualCardColor = listCard.get(currentCard).getColor();
		possibility(lastPosX, lastPosY, x, y);
		if(bg.getColor(x, y)!=actualCardColor && lastPosX==x || lastPosY==y && AuthorizedPos.contains("("+x+","+y+")")){
			// Changement de position à coder
			return true;
		}
		return false;
	}

	/**
	 * Méthode qui verifie si un pion arrive à l'arrivé
	 * @param p un joueur
	 * @param pa un pion
	 * @return vrai si un pion est bien arrivé
	 */
	public boolean checkWin(Player p, Pawn pa)
	{
		if(bg.getChar(pa.getPositionX(),pa.getPositionY())=='f'){
			p.removePawn(pa);
			takedCard = takeCard();
			System.out.println("Un pion est arrivé à destination, changement de carte...");
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode qui initialise les joueurs en début de partie
	 */
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

	/**
	 * Méthode qui initialise les pions en début de partie.
	 */
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
	
	/**
	 * Méthode qui initialise les cartes en début de partie.
	 */
	private void initCard()
	{
		System.out.println("Methode appellé");
		this.listCard.add(new Card(Color.GREEN));
		this.listCard.add(new Card(Color.PINK));
		this.listCard.add(new Card(Color.BLUE));
		this.listCard.add(new Card(Color.ORANGE));
		Collections.shuffle(listCard);
		System.out.println("Cartes initialisées et mélangées.");
		
	}
	
	/**
	 * Méthode qui permet de tirer une carte
	 * @return la carte tiré
	 */
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
	
	/**
	 * Méthode qui retourne la liste des joueurs
	 * @return
	 */
	public List<Player> getPlayers()
	{
		return this.listPlayers;
	}
	
}
