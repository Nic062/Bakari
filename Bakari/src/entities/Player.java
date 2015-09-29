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
	
	public String toString()
	{
		String str = "Nom du joueur : " + this.nom + "\n";
		if(!this.listPawns.isEmpty())
		{
			str += "Liste des pions : \n";
			for(Pawn p : this.listPawns)
			{
				str += "Couleur des pions : " + p.getColor();
				str += "Nombre de pions : " + p.getNombre();
				str += "Position des pions : " + p.getPositionX() + ", " + p.getPositionY();
			}
		}
		
		return str;
	}
}
