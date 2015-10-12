package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GameBoardPanel extends JPanel
{
	private static final long serialVersionUID = 6286662266796398181L;
	
	private char[][] grid;
	
	public GameBoardPanel()
	{
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
		
		this.setLayout(new GridLayout(13, 12));
		initCases();
	}
	
	private void initCases()
	{
		for(char cases[] : grid)
		{
			for(char c : cases)
			{
				if(c == 'o')
					addCase(Color.ORANGE);
				if(c == 'g')
					addCase(Color.GREEN);
				if(c == 'b')
					addCase(Color.BLUE);
				if(c == 'p')
					addCase(new Color(138, 2, 177)); // Violet
				if(c == 'x')
					addCase(Color.WHITE);
				if(c == 'f')
					addCase(Color.BLACK);
			}
		}
	}
	
	private void addCase(Color color)
	{
		CasePanel c = new CasePanel(color);
		this.add(c);
	}
}
