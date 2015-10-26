package entities;


public class Pawn
{
	private Colour colour;
	private int positionX;
	private int positionY;
	private int nombre;
	
	public Pawn()
	{
		this.positionX = 0;
		this.positionY = 0;
		this.colour = null;
		this.nombre = 0;
	}
	
	public Pawn(Colour colour, int x, int y, int nombre)
	{
		this.positionX = x;
		this.positionY = y;
		this.colour = colour;
		this.nombre = nombre;
	}
	
	public Pawn(int x, int y)
	{
		this.positionX = x;
		this.positionY = y;
	}
	public Pawn(Colour colour, int x, int y)
	{
		this.positionX = x;
		this.positionY = y;
		this.colour = colour;
	}
	
	public int getPositionX()
	{
		return this.positionX;
	}
	
	public void setPositions(int x, int y){
		this.positionX = x;
		this.positionY = y;
	}
	public void setPositionX(int x)
	{
		this.positionX = x;
	}
	
	public int getPositionY()
	{
		return this.positionY;
	}
	public void setPositionY(int y)
	{
		this.positionY = y;
	}
	
	public Colour getColour()
	{
		return this.colour;
	}
	public void setColour(Colour colour)
	{
		this.colour = colour;
	}
	
	public int getNombre()
	{
		return this.nombre;
	}
	public void setNombre(int nombre)
	{
		this.nombre = nombre;
	}
	
	public String toString()
	{
		return "Pion (" + positionX + ", " + positionY + ") de couleur " + colour.toString();
	}
}
