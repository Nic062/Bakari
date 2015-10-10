package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import entities.BoardGame;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -1940264608294807000L;
	
	private int width;
	private int height;
	private String title;
	
	private GameBoardPanel gb;
	private CardPanel cPanel;
	
	public MainWindow(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.gb = new GameBoardPanel();
		this.cPanel = new CardPanel();
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		this.add(gb, BorderLayout.CENTER);
		this.add(cPanel, BorderLayout.WEST);
		
		this.setVisible(true);
	}
}
