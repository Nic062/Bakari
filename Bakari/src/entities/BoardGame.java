package entities;


public class BoardGame
{
	private int nbRow;
	private int nbCol;
	private char [][] grid;
	private Color color;
	
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
					{'o','g','o','p','g','b','p','b','o','p','b','g'},
					{'p','o','b','g','o','p','o','g','b','g','p','o'},
					{'g','p','g','b','p','b','g','o','p','o','g','p'},
					{'b','o','p','o','b','o','p','b','g','b','o','b'},
					{'o','p','b','g','o','g','o','g','b','p','g','p'},
					{'g','b','o','p','b','p','g','b','o','b','o','g'},
					{'b','g','p','b','g','b','p','g','p','o','p','b'},
					{'o','p','g','o','p','g','o','b','o','p','b','g'},
					{'p','b','o','p','b','p','b','g','p','b','g','o'},
					{'g','o','g','b','o','g','o','p','o','g','b','p'},
					{'b','p','b','p','g','o','p','g','p','o','g','o'},
					{'o','g','p','o','b','g','b','o','g','b','p','b'},
					{'x','x','x','f','f','f','f','f','f','x','x','x'}
			};
			
	}
	public void setColor(Color c){
		this.color= c;
	}
	public int getSizeGridY(){
		return grid.length;
	}
	public int getSizeGridX(){
		if(grid.length==0) return 0;
		else return grid[0].length;
	}
	public char getChar(int x, int y)
	{
		return grid[y][x];
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
		char letter = grid[y][x];
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
	public char[][] getGrid() {
		return grid;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	

}