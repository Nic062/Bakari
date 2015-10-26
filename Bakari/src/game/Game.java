package game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entities.BoardGame;
import entities.Card;
import entities.Colour;
import entities.Pawn;
import entities.Player;
import gui.frames.*;
import gui.panels.GameboardPanel;

public class Game
{
	private List<Player> listPlayers;
	private List<Player> listWinners;
	public List<Card> listCard;
	public List<String> blockedPos;
	public List<String> authorizedPos;
	private int currentCard;
	private boolean isFinished;
	private int currentTour;
	public BoardGame boardGame;
	public Card takedCard;
	
	public Game(){
		this.listPlayers = new LinkedList<Player>();
		this.listWinners = new LinkedList<Player>();
		this.listCard = new LinkedList<Card>();
		this.blockedPos = new LinkedList<String>();
		this.authorizedPos = new LinkedList<String>();
		this.currentCard = 0;
		this.isFinished = false;
		this.currentTour = 1;
		this.boardGame = new BoardGame();
	}
	
	/**
	 * Méthode qui initialise le jeu. 
	 */
	public void initGame()
	{
		initCard();
		new WelcomeWindow(this);
	}
	
	/**
	 * Méthode qui initialise les cartes en début de partie.
	 */
	private void initCard()
	{
		this.listCard.add(new Card(Colour.GREEN));
		this.listCard.add(new Card(Colour.PINK));
		this.listCard.add(new Card(Colour.BLUE));
		this.listCard.add(new Card(Colour.ORANGE));
		Collections.shuffle(listCard);
	}
	
	public void addPlayer(String nom) {
		Player p = new Player(nom);
		listPlayers.add(p);
	}
	
	/**
	 * Méthode qui gére la partie
	 */
	public void start()
	{
		initPawns();
		MainWindow mw = new MainWindow(this);
		while(!isFinished) {
			mw.updatePawns();
			takedCard = takeCard();
			mw.addText(takedCard.toString());
			for(Player pl : this.listPlayers) {
				mw.addText("C'est à " + pl.getNom() + " de jouer");
				
				/*
				while(!checkTour(pl)) {
					System.out.println("Déplacement impossible, veuillez réessayer");
				};
				*/
			}
			this.currentTour++;
			isFinished = true;
		}
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
		
		for(int x=0;x<boardGame.getSizeGridX();x++)
			for(int y=0;y<boardGame.getSizeGridY();y++)
				if(boardGame.getChar(x, y)==Colour.BLACK)
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
		for(int x=posX;x<boardGame.getSizeGridX();x++)
			if(boardGame.getColour(x, posY) != takedCard.getColor() || x==posX)
				authorizedPos.add("("+x+","+posY+")");
			else 
				break;
		for(int x=posX;x>=0;x--)
			if(boardGame.getColour(x, posY)!=takedCard.getColor() || x==posX)
				authorizedPos.add("("+x+","+posY+")");
			else
				break;	
		for(int y=posY;y<boardGame.getSizeGridY();y++)
			if(boardGame.getColour(posX, y)!=takedCard.getColor() || y==posY  || boardGame.getChar(posX, y)==Colour.WHITE)
				authorizedPos.add("("+posX+","+y+")");
			else
				break;	
		for(int y=posY;y>=0;y--)
			if(boardGame.getColour(posX, y)!=takedCard.getColor() || y==posY)
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
	 * Méthode qui vérifie qui doit jouer
	 * @param pl un joueur
	 */
	private boolean checkTour(Player pl)
	{
		boardGame.afficher();
		System.out.println("Tour " + this.currentTour + " - Joueur : " + pl.getNom());
		System.out.println("Selectionné un pion : ");
		for(int i=0;i<pl.getPawns().size();i++){
			System.out.println(i+1 +" - "+pl.getPawns().get(i).toString());
		}
		Scanner scP = new Scanner(System.in);
		int selectedPawn = scP.nextInt()-1;
		System.out.println("vous avez chosit le pion "+ pl.getPawns().get(selectedPawn).toString());
		System.out.println("Indiquez les coordonnées pour votre pion :\n x :");
		int x = 0;
		System.out.println("y :");
		int y = 0;
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
		if(boardGame.getChar(pa.getPositionX(),pa.getPositionY())==Colour.WHITE){
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
						this.listPlayers.get(i).addPawn(new Pawn(Colour.PINK, j, 0));
				else
					for(int j=6;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.ORANGE, j, 0));
			}
		}
		else if(this.listPlayers.size() == 3)
		{
			for(int i = 0; i < 3; i++)
			{
				if(i == 0)
					for(int j=0;j<4;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.PINK, j, 0));
				else if(i == 1)
					for(int j=4;j<8;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.ORANGE, j, 0));
				else
					for(int j=8;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.GREEN, j, 0));
			}
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				if(i == 0)
					for(int j=0;j<3;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.PINK, j, 0));
				else if(i == 1)
					for(int j=3;j<6;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.ORANGE, j, 0));
				else if(i == 2)
					for(int j=6;j<9;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.GREEN, j, 0));
				else
					for(int j=9;j<12;j++)
						this.listPlayers.get(i).addPawn(new Pawn(Colour.WHITE, j, 0));
			}
		}
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

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public List<Player> getListPlayers() {
		return listPlayers;
	}
	
	
}
