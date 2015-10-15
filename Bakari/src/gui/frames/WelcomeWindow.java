package gui.frames;

import gui.panels.init.ButtonsPanel;
import gui.panels.init.LogoPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class WelcomeWindow extends JFrame
{
	private static final long serialVersionUID = 2500625025662008645L;
	
	private int width;
	private int height;
	private String title;
	
	private LogoPanel logoPanel = new LogoPanel("logo.png");
	private ButtonsPanel bPanel = new ButtonsPanel(this);

	public WelcomeWindow(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(logoPanel, BorderLayout.CENTER);
		this.add(bPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public String getTitle()
	{
		return this.title;
	}
}
