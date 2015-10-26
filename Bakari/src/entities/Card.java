package entities;


public class Card
{
	private Colour colour;
	private boolean isVisible;
	
	public Card(){}
	public Card(Colour colour)
	{
		this.colour = colour;
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
	
	public Colour getColor()
	{
		return this.colour;
	}
	public void setColor(Colour colour)
	{
		this.colour = colour;
	}

	public String toString()
	{
		return "Carte de couleur " + colour.toString() + ", " + ((isVisible) ? "visible" : "non visible");
	}
}
