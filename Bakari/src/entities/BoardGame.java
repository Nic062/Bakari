package entities;


public class BoardGame
{
	private int nbRow;
	private int nbCol;
	private char [][] grid;
	
	
	public BoardGame() 
	{
		// Création de la grille de jeu officiel
			// o -> Lézard orange
			// p -> Oiseau rose
			// g -> Rhino vert
			// b -> Taureau bleu
			// x -> Case non jouable
			// f -> Zone de fin de partie
			grid= new char[][]
			{
					{'o','g','o','p','g','b','p','b','o','p','b','g','x'},
					{'p','o','b','g','o','p','o','g','b','g','p','o','x'},
					{'g','p','g','b','p','b','g','o','p','o','g','p','x'},
					{'b','o','p','o','b','o','p','b','g','b','o','b','f'},
					{'o','p','b','g','o','g','o','g','b','p','g','p','f'},
					{'g','b','o','p','b','p','g','b','o','b','o','g','f'},
					{'b','g','p','b','g','b','p','g','p','o','p','b','f'},
					{'o','p','g','o','p','g','o','b','o','p','b','g','f'},
					{'p','b','o','p','b','p','b','g','p','b','g','o','f'},
					{'g','o','g','b','o','g','o','p','o','g','b','p','x'},
					{'b','p','b','p','g','o','p','g','p','o','g','o','x'},
					{'o','g','p','o','b','g','b','o','g','b','p','b','x'}
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
	public Color getColor(int x, int y){
		char letter = grid[x][y];
		switch (letter) {
		case 'o':
			return Color.ORANGE;
		case 'p':
			return Color.PINK;
		case 'g':
			return Color.GREEN;
		case 'b':
			return Color.BLUE;
		default:
			return null;
		}
	}
	
	public void afficher(){
		System.out.println();
		for(int x=0;x<12;x++){
			for(int y=0;y<13;y++){
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
