package gui.frames;

import gui.panels.init.LogoPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.Game;

public class WelcomeWindow extends JFrame
{
	private static final long serialVersionUID = 2500625025662008645L;
	
	private int width = 600;
	private int height = 350;
	
	private LogoPanel logoPanel;
	
	private JButton playButton;
	private JButton optionButton;
	private JButton quitButton;
	
	private Game game;

	public WelcomeWindow(Game g)
	{
		this.game = g;
		this.setSize(width, height);
		this.setTitle("Bakari");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Dimension size;
		
		logoPanel = new LogoPanel("logo.png");
		logoPanel.setBounds(0, 0, width, height);
		this.add(logoPanel);
		
		playButton = new JButton("Jouer");
		size = playButton.getPreferredSize();
		playButton.setBounds((int)((0.5 * width) - (0.5 * size.width)), 230, size.width, size.height);
		playButton.addActionListener(new PlayListener());
		this.add(playButton);
		
		optionButton = new JButton("Options");
		size = optionButton.getPreferredSize();
		optionButton.setBounds((int)((0.5 * width) - (0.5 * size.width)), 260, size.width, size.height);
		optionButton.addActionListener(new OptionsListener());
		this.add(optionButton);
		
		quitButton = new JButton("Quitter");
		size = quitButton.getPreferredSize();
		quitButton.setBounds((int)((0.5 * width) - (0.5 * size.width)), 290, size.width, size.height);
		quitButton.addActionListener(new QuitListener());
		this.add(quitButton);
		
		this.setVisible(true);
	}
	
	class PlayListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String nomPlayer = "";
			int i = 1;
			while(i < 5) {
				nomPlayer = "";
				nomPlayer = JOptionPane.showInputDialog(null, "Indiquez le nom du joueur " + i + " : ", "Nom du joueur " + i, JOptionPane.QUESTION_MESSAGE);
				if(nomPlayer.isEmpty() && i > 2) {
					break;
				}
				else if(!nomPlayer.isEmpty()) {
					game.addPlayer(nomPlayer);
					i++;
				}
			}
			WelcomeWindow.this.dispose();
			game.start();
		}
	}
	
	class OptionsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, "to do");
		}
	}
	
	class QuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			WelcomeWindow.this.dispose();
		}
	}
	
}
