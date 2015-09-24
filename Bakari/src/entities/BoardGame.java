package entities;


public class BoardGame
{
	private int nbRow;
	private int nbCol;
	private char [][] grid;
	
	
	public BoardGame() 
	{
		// Création de la grille de jeu officiel
			// a -> Lézard
			// b -> Oiseau
			// c -> Rhino
			// d -> Taureau
			grid= new char[][]
			{
					{'a','c','a','b','c','d','b','d','a','b','d','c'},
					{'b','a','d','c','a','b','a','c','d','c','b','a'},
					{'c','b','c','d','b','d','c','a','b','a','c','b'},
					{'d','a','b','a','d','a','b','d','c','d','a','d'},
					{'a','b','d','c','a','c','a','c','d','b','c','b'},
					{'c','d','a','b','d','b','c','d','a','d','a','c'},
					{'d','c','b','d','c','d','b','c','b','a','b','d'},
					{'a','b','c','a','b','c','a','d','a','b','d','c'},
					{'b','d','a','b','d','b','d','c','b','d','c','a'},
					{'c','a','c','d','a','c','a','b','a','c','d','b'},
					{'d','b','d','b','c','a','b','c','b','a','c','a'},
					{'a','c','b','a','d','c','d','a','c','d','b','d'}
			};
	}


	public BoardGame(int n, int p) {
		nbRow = n;
		nbCol = p;
		grid = new char[nbRow][nbCol];
		for(int i=0;i<nbRow;i++){
			for(int j=0;j<nbCol;j++){
				grid[i][j]='x';
			}
			
		}
	}
	public void afficher(){
		System.out.println();
		for(int i=0;i<12;i++){
			for(int j=0;j<12;j++){
				System.out.print(" | " + grid[i][j]);
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
	public char[][] getGrid() {
		return grid;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	
	
	public static void main(String[] args) {
		BoardGame g1 = new BoardGame();
		g1.afficher();

	}
	
	
	

}
