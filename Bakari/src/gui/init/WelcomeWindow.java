package gui.init;

import gui.listeners.ButtonListener;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomeWindow extends JFrame
{
	private static final long serialVersionUID = -8952743849774863136L;
	
	private int width;
	private int height;
	private String title;
	
	private WelcomePanel panel = new WelcomePanel("logo.png");
	private JButton playButton = new JButton("Jouer !");
	
	public WelcomeWindow(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.add(playButton, BorderLayout.SOUTH);
		
		playButton.addMouseListener(new ButtonListener(playButton, this));
		
		this.setVisible(true);
	}
}
