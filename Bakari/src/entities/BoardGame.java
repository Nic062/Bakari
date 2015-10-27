package entities;

public class BoardGame
{
	private static final long serialVersionUID = -5525980078260793399L;
	
	private int nbRow;
	private int nbCol;
	private Colour[][] grid = new Colour[][]
	{
		{Colour.ORANGE,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.GREEN,Colour.BLUE,Colour.PINK,Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.GREEN},
		{Colour.PINK,Colour.ORANGE,Colour.BLUE,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.ORANGE,Colour.GREEN,Colour.BLUE,Colour.GREEN,Colour.PINK,Colour.ORANGE},
		{Colour.GREEN,Colour.PINK,Colour.GREEN,Colour.BLUE,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.ORANGE,Colour.GREEN,Colour.PINK},
		{Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.ORANGE,Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.BLUE,Colour.ORANGE,Colour.BLUE},
		{Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.ORANGE,Colour.GREEN,Colour.ORANGE,Colour.GREEN,Colour.BLUE,Colour.PINK,Colour.GREEN,Colour.PINK},
		{Colour.GREEN,Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.PINK,Colour.GREEN,Colour.BLUE,Colour.ORANGE,Colour.BLUE,Colour.ORANGE,Colour.GREEN},
		{Colour.BLUE,Colour.GREEN,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.BLUE,Colour.PINK,Colour.GREEN,Colour.PINK,Colour.ORANGE,Colour.PINK,Colour.BLUE},
		{Colour.ORANGE,Colour.PINK,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.GREEN,Colour.ORANGE,Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.GREEN},
		{Colour.PINK,Colour.BLUE,Colour.ORANGE,Colour.PINK,Colour.BLUE,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.PINK,Colour.BLUE,Colour.GREEN,Colour.ORANGE},
		{Colour.GREEN,Colour.ORANGE,Colour.GREEN,Colour.BLUE,Colour.ORANGE,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.ORANGE,Colour.GREEN,Colour.BLUE,Colour.PINK},
		{Colour.BLUE,Colour.PINK,Colour.BLUE,Colour.PINK,Colour.GREEN,Colour.ORANGE,Colour.PINK,Colour.GREEN,Colour.PINK,Colour.ORANGE,Colour.GREEN,Colour.ORANGE},
		{Colour.ORANGE,Colour.GREEN,Colour.PINK,Colour.ORANGE,Colour.BLUE,Colour.GREEN,Colour.BLUE,Colour.ORANGE,Colour.GREEN,Colour.BLUE,Colour.PINK,Colour.BLUE},
		{Colour.BLACK,Colour.BLACK,Colour.BLACK,Colour.WHITE,Colour.WHITE,Colour.WHITE,Colour.WHITE,Colour.WHITE,Colour.WHITE,Colour.BLACK,Colour.BLACK,Colour.BLACK}
	};
	
	public BoardGame(){}

	public int getSizeGridY(){
		return grid.length;
	}
	public int getSizeGridX(){
		if(grid.length==0) return 0;
		else return grid[0].length;
	}
	
	public Colour getColour(int x, int y){
		return grid[y][x];
	}
	
	public void afficher(){
		System.out.println();
		for(int x=0;x<13;x++){
			for(int y=0;y<12;y++){
				System.out.print(" | " + grid[x][y]);
			}
			System.out.println(" | ");
			
		}
		System.out.println();
	}
	
	public int getNbRow() {
		return nbRow;
	}
	public void setNbRow(int nbRow) {
		this.nbRow = nbRow;
	}
	public int getNbCol() {
		return nbCol;
	}
	public void setNbCol(int nbCol) {
		this.nbCol = nbCol;
	}
	public Colour[][] getGrid() {
		return grid;
	}
	public void setGrid(Colour[][] grid) {
		this.grid = grid;
	}
}