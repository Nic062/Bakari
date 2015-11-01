package game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import entities.BoardGame;
import entities.Colour;
import entities.Pawn;
import entities.Player;
import gui.frames.MainWindow;
import gui.frames.WelcomeWindow;

public class Game extends Thread
{
	private MainWindow mainWindow;
	private List<Player> listPlayers;
	private List<Player> listWinners;
	public List<Colour> listCard;
	public List<String> blockedPos;
	public List<String> authorizedPos;
	private Player currentPlayer;
	private Colour currentCard;
	private boolean isStarted;
	private boolean endturn;
	private boolean isFinished;
	private int currentTour;
	public BoardGame boardGame;
	
	public Game(){
		this.listPlayers = new LinkedList<Player>();
		this.listWinners = new LinkedList<Player>();
		this.listCard = new LinkedList<Colour>();
		this.blockedPos = new LinkedList<String>();
		this.authorizedPos = new LinkedList<String>();
		this.currentPlayer = new Player();
		this.isStarted = false;
		this.endturn = false;
		this.isFinished = false;
		this.currentTour = 1;
		this.boardGame = new BoardGame();
		this.start();
	}
	
	/**
	 * Méthode qui gére la partie
	 */
	public synchronized void run()
	{
		initCard();
		new WelcomeWindow(this);
		while(!isStarted) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		initPawns();
		mainWindow = new MainWindow(this);
		takeCard();
		mainWindow.updateCard();
		mainWindow.addText("La carte tirée est " + currentCard.toString());
		while(!isFinished) {
			mainWindow.updatePawns();
			mainWindow.addText("Tour n°" + currentTour);
			for(Player pl : listPlayers) {
				currentPlayer = pl;
				mainWindow.addText("C'est à " + pl.getNom() + " de jouer");
				while(!endturn) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				endturn = false;
				mainWindow.updatePawns();
			}
			currentTour++;
		}
		mainWindow.addText("Fin de la partie");
	}
	
	/**
	 * Méthode qui initialise les cartes en début de partie.
	 */
	private void initCard()
	{
		this.listCard.add(Colour.GREEN);
		this.listCard.add(Colour.PINK);
		this.listCard.add(Colour.BLUE);
		this.listCard.add(Colour.ORANGE);
		Collections.shuffle(listCard);
	}
	
	public void addPlayer(String nom) {
		Player p = new Player(nom);
		listPlayers.add(p);
	}
	
	public synchronized void startGame() {
		isStarted = true;
		notify();
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
				if(boardGame.getColour(x, y)==Colour.BLACK)
					blockedPos.add("("+x+","+y+")");
		
	}
	
	/**
	 * Méthode qui liste dans un tableau la liste des cases où le déplacement est possible pour un pion p
	 * @param p
	 */
	public void possibility(Pawn p){
		generateBlockedList();
		int posX = p.getPositionX();
		int posY = p.getPositionY();
		
		//Algorithme de recherche de positions autorisées
		for(int x=posX;x<boardGame.getSizeGridX();x++)
			if(boardGame.getColour(x, posY) != currentCard || x == posX)
				authorizedPos.add("("+x+","+posY+")");
			else 
				break;
		for(int x=posX;x>=0;x--)
			if(boardGame.getColour(x, posY) != currentCard || x == posX)
				authorizedPos.add("("+x+","+posY+")");
			else
				break;	
		for(int y=posY;y<boardGame.getSizeGridY();y++)
			if(boardGame.getColour(posX, y) != currentCard || y == posY  || boardGame.getColour(posX, y) == Colour.WHITE)
				authorizedPos.add("("+posX+","+y+")");
			else
				break;	
		for(int y=posY;y>=0;y--)
			if(boardGame.getColour(posX, y) != currentCard || y == posY)
				authorizedPos.add("("+posX+","+y+")");
			else
				break;
		
		//Suppression de la position actuelle du pion dans les positions autorisées
		while(authorizedPos.contains("("+posX+","+posY+")"))
			authorizedPos.remove("("+posX+","+posY+")");
		//Suppression des positions non autorisées
		for(int i=0;i<blockedPos.size();i++)
			if(authorizedPos.contains(blockedPos.get(i)))
				authorizedPos.remove(blockedPos.get(i));
	}
	
	/**
	 * Méthode qui permet de déplacer un pion
	 * @param p un pion
	 * @param x la position souhaitée du pion
	 * @param y la position souhaitée du pion
	 * @return vrai si le pion a été posé
	 */
	public synchronized boolean movePawn(Player thePlayer,Pawn p, int x, int y) {
		possibility(p);
		if(authorizedPos.contains("("+x+","+y+")") && !blockedPos.contains("("+x+","+y+")")){
			mainWindow.addText("Déplacement : " + p.getPositionX() + ";" + p.getPositionY() + " => " + x + ";" + y);
			p.setPositions(x, y);
			endturn = true;
			notify();
			checkWin(thePlayer,p);
			return true;
		}
		else {
			System.out.println("*********DETAIL*********");
			System.out.println("Pion non placé !");
			System.out.println("Couleur carte = " + currentCard);
			System.out.println("Taille tab = "+authorizedPos.size());
			System.out.println("Position demandée : ("+x+","+y+")");
			System.out.print("Valeur(s) possible(s) : ");
			for(int i=0;i<authorizedPos.size();i++)
				System.out.print(authorizedPos.get(i));
			System.out.println("");			
			System.out.println("**********FIN**********");
		}
		return false;
	}

	/**
	 * Méthode qui vérifie si un pion arrive à l'arrivée
	 * @param p un joueur
	 * @param pa un pion
	 * @return vrai si un pion est bien arrivé
	 */
	public boolean checkWin(Player p, Pawn pa)
	{
		if(boardGame.getColour(pa.getPositionX(),pa.getPositionY())==Colour.WHITE){
			p.removePawn(pa);
			if(p.getPawns().size()==0){
				mainWindow.addText("Fin de la partie pour " +p.getNom());
				this.listPlayers.remove(p);
				listWinners.add(p);
			}
			if(listPlayers.size()==1){
				mainWindow.addText("Fin de la partie. \nLe gagnant est "+listWinners.get(0));
				isFinished=true;
			}
			if(listCard.size()!=0){
			takeCard();
			mainWindow.addText("Un pion est arrivé à destination, changement de carte...");
			mainWindow.updateCard();
			return true;
			}
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
	 */
	public void takeCard(){
		if(listCard.size() == 0) {
			initCard();
		}
		currentCard = listCard.get(0);
		listCard.remove(0);
		if(listCard.size() == 0) {
			initCard();
		}
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public List<Player> getListPlayers() {
		return listPlayers;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Colour getCurrentCard() {
		return currentCard;
	}
	
}
