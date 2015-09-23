package entities;

import java.util.LinkedList;
import java.util.List;

public class Pawn
{
	private Color color;
	private int positionX;
	private int positionY;
	private List<Pawn> listPawns;
	
	public Pawn()
	{
		this.positionX = 0;
		this.positionY = 0;
		this.color = null;
		this.listPawns = new LinkedList<Pawn>();
	}
	
	public Pawn(Color color, int x, int y)
	{
		this.positionX = x;
		this.positionY = y;
		this.color = color;
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

	public List<Pawn> getListPawns()
	{
		return listPawns;
	}
	public void setListPawns(List<Pawn> listPawns)
	{
		this.listPawns = listPawns;
	}
}
