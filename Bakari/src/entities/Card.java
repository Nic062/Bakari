package entities;


public class Card
{
	private Color color;
	private boolean isVisible;
	
	public Card(){}
	public Card(Color color)
	{
		this.color = color;
		this.isVisible = false;
	}
	
	
	public boolean isVisible()
	{
		return isVisible;
	}
	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}

}
