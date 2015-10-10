package gui.init;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.MainWindow;

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
		
		playButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MainWindow mainWindow = new MainWindow(700, 600, "Bakari - Partie en cours");
				setVisible(false);
			}
		});
		
		this.setVisible(true);
	}
}
