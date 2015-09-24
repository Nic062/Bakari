package entities;


public class Pawn
{
	private Color color;
	private int positionX;
	private int positionY;
	private int nombre;
	
	public Pawn()
	{
		this.positionX = 0;
		this.positionY = 0;
		this.color = null;
		this.nombre = 0;
	}
	
	public Pawn(Color color, int x, int y, int nombre)
	{
		this.positionX = x;
		this.positionY = y;
		this.color = color;
		this.nombre = nombre;
	}
	
	public Pawn(int x, int y)
	{
		this.positionX = x;
		this.positionY = y;
	}
	
	public int getPositionX()
	{
		return this.positionX;
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
	
	public Color getColor()
	{
		return this.color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public int getNombre()
	{
		return this.nombre;
	}
	public void setNombre(int nombre)
	{
		this.nombre = nombre;
	}
}
