package entities;

public class Player
{
	private String nom;
	private Pawn piece;
	
	public static int nbInstances = 0;
	
	public Player()
	{
		nbInstances++;
		
		this.nom = "Joueur " + nbInstances; // Permet de donner un nom par défaut (Ex : joueur 1, joueur 2, etc...)
		this.piece = new Pawn();
	}
	
	public Player(String nom, Pawn piece)
	{
		this.nom = nom;
		this.piece = piece;
		
		nbInstances++;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public Pawn getPiece()
	{
		return this.piece;
	}
	public void setPiece(Pawn piece)
	{
		this.piece = piece;
	}
}
