package gui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -1940264608294807000L;
	
	private int width;
	private int height;
	private String title;
	
	public MainWindow(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		
	}
}
