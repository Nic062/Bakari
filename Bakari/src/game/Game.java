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
	private List<Player> listWinners = new LinkedList<Player>();
	public List<Card> listCard;
	public List<String> blockedPos;
	public List<String> authorizedPos;
	private Scanner sc = new Scanner(System.in);
	private Scanner sc2 = new Scanner(System.in);
	private int currentCard = 0;
	private boolean isFinished = false;
	private int currentTour = 1;
	public BoardGame bg;
	public Card takedCard;

	
	public Game(){
		authorizedPos = new LinkedList<String>();
		blockedPos = new LinkedList<String>();
		listCard = new LinkedList<Card>();
	}
	
	
	/**
	 * Méthode qui initialise le jeu. 
	 */
	public void initGame()
	{
		bg = new BoardGame();
		new Game();
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
		for(Player p : this.listPlayers)
			for(Pawn pa : p.getPawns()){
				int x = pa.getPositionX();
				int y = pa.getPositionY();
				blockedPos.add("("+x+","+y+")");
			}
		
		for(int x=0;x<bg.getSizeGridX();x++)
			for(int y=0;y<bg.getSizeGridY();y++)
				if(bg.getChar(x, y)=='x')
					blockedPos.add("("+x+","+y+")");
		
	}
	
	/**
	 * Méthode qui liste dans un tableau la liste des cases où le déplacement est possible pour un pion p
	 * @param p
	 */
	public void possibility(Pawn p){
		//generateBlockedList(); // Génération de la liste blockedList
		int posX = p.getPositionX();
		int posY = p.getPositionY();
		
		//Algorithme de recherche de positions autorisées
		for(int x=posX;x<bg.getSizeGridX();x++)
			if(bg.getColor(x, posY) != takedCard.getColor() || x==posX)
				authorizedPos.add("("+x+","+posY+")");
			else 
				break;
		for(int x=posX;x>=0;x--)
			if(bg.getColor(x, posY)!=takedCard.getColor() || x==posX)
				authorizedPos.add("("+x+","+posY+")");
			else
				break;	
		for(int y=posY;y<bg.getSizeGridY();y++)
			if(bg.getColor(posX, y)!=takedCard.getColor() || y==posY  || bg.getChar(posX, y)=='f')
				authorizedPos.add("("+posX+","+y+")");
			else
				break;	
		for(int y=posY;y>=0;y--)
			if(bg.getColor(posX, y)!=takedCard.getColor() || y==posY)
				authorizedPos.add("("+posX+","+y+")");
			else
				break;
		
		//Suppression de la position actuelle du pion dans les positions autorisées
		while(authorizedPos.contains("("+posX+","+posY+")"))
			authorizedPos.remove("("+posX+","+posY+")");
		//Suppression des positions non autorisé
		for(int i=0;i<blockedPos.size();i++)
			if(authorizedPos.contains(blockedPos.get(i)))
				authorizedPos.remove(blockedPos.get(i));
	}
	
	/**
	 * Méthode qui gére la partie
	 */
	private void startGame()
	{
		takedCard = takeCard();
		System.out.println("Démarrage de la partie...");
		System.out.println("La premiere carte tiré est : " +takedCard.getColor());
		while(!isFinished)
		{
			
			for(Player pl : this.listPlayers)
			{
				while(! checkTour(pl)){
					System.out.println("Déplacement impossible, veuillez réessayer");
				};
				
			}
			this.currentTour++;
		}
	}
	
	/**
	 * Méthode qui vérifie qui doit jouer
	 * @param pl un joueur
	 */
	private boolean checkTour(Player pl)
	{
		bg.afficher();
		System.out.println("Tour " + this.currentTour + " - Joueur : " + pl.getNom());
		System.out.println("Selectionné un pion : ");
		for(int i=0;i<pl.getPawns().size();i++){
			System.out.println(i+1 +" - "+pl.getPawns().get(i).toString());
		}
		Scanner scP = new Scanner(System.in);
		int selectedPawn = scP.nextInt()-1;
		System.out.println("vous avez chosit le pion "+ pl.getPawns().get(selectedPawn).toString());
		System.out.println("Indiquez les coordonnées pour votre pion :\n x :");
		int x = sc.nextInt();
		System.out.println("y :");
		int y = sc.nextInt();
		if(movePawn(pl, pl.getPawns().get(selectedPawn), x, y))
			return true;
		return false;
	}
	
	/**
	 * Méthode qui permet de déplacer un pion
	 * @param p un pion
	 * @param x la position souhaité du pion
	 * @param y la position souhaité du pion
	 * @return vrai si le pion a été posé
	 */
	public boolean movePawn(Player thePlayer,Pawn p, int x, int y){
		possibility(p);
		if(authorizedPos.contains("("+x+","+y+")") && !blockedPos.contains("("+x+","+y+")")){
			p.setPositions(x, y);
			System.out.println("********* Pion déplacé *********");
			checkWin(thePlayer,p);
			return true;
		}
		else {
			System.out.println("*********DETAIL*********");
			System.out.println("Pion non placé !");
			System.out.println("Couleur carte = " + takedCard.getColor());
			System.out.println("Taille tab = "+authorizedPos.size());
			System.out.println("Position demandé : ("+x+","+y+")");
			System.out.print("Valeur possible : ");
			for(int i=0;i<authorizedPos.size();i++)
				System.out.print(authorizedPos.get(i));
			System.out.println("");			
			System.out.println("**********FIN**********");
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
			if(p.getPawns().size()==0){
				System.out.println("Fin de la partie pour " +p.getNom());
				this.listPlayers.remove(p);
				listWinners.add(p);
			}
			if(listPlayers.size()==1){
				System.out.println("Fin de la partie. \nLe gagnant est "+listWinners.get(0));
				isFinished=true;
			}
			if(listCard.size()!=0)
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
						this.listPlayers.get(i).addPawn(new Pawn(Color.RED, j, 11));
				else
					for(int j=6;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Color.ORANGE, j, 11));
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
	}
	
	/**
	 * Méthode qui initialise les cartes en début de partie.
	 */
	private void initCard()
	{
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
	
	
	
}
