package entities;

import java.util.LinkedList;
import java.util.List;

public class Player
{
	private String nom;
	private List<Pawn> listPawns;
	
	public static int nbInstances = 0;
	
	public Player()
	{
		nbInstances++;
		
		this.nom = "Joueur " + nbInstances; // Permet de donner un nom par défaut (Ex : joueur 1, joueur 2, etc...)
		this.listPawns = new LinkedList<Pawn>();
	}
	
	public Player(String nom)
	{
		this.nom = nom;
		this.listPawns = new LinkedList<Pawn>();
		
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
	
	public List<Pawn> getPawns()
	{
		return this.listPawns;
	}
	public void setPawns(List<Pawn> list)
	{
		this.listPawns = list;
	}
	
	public void addPawn(Pawn pawn)
	{
		this.listPawns.add(pawn);
	}
	
	public void afficher()
	{
		System.out.println("Nom du joueur : " + this.nom);
		System.out.println("Liste des pions : \n");
		for(Pawn p : this.listPawns)
		{
			System.out.println("Couleur des pions : " + p.getColor());
			System.out.println("Nombre de pions : " + p.getNombre());
			System.out.println("Position des pions : " + p.getPositionX() + ", " + p.getPositionY());
		}
		System.out.println();
		System.out.println();
	}
}
