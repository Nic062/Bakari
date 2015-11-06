package gui.frames;

import game.Game;
import game.GameServer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MultiplayerWindow extends JFrame
{
	private static final long serialVersionUID = 2500625025662008645L;

	private int width = 200;
	private int height = 200;

	private JButton hostButton;
	private JButton joinButton;
	private JButton quitButton;
	
	private GameServer gameServer;
	
	private Game game;

	public MultiplayerWindow(Game g)
	{
		this.game = g;
		
		this.setSize(width, height);
		this.setTitle("Bakari");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Dimension size;

		hostButton = new JButton("Héberger");
		size = hostButton.getPreferredSize();
		hostButton.setBounds((int) ((0.5 * width) - (0.5 * 100)), 25, 100, size.height);
		hostButton.addActionListener(new HostListener());
		this.add(hostButton);
		
		joinButton = new JButton("Rejoindre");
		size = joinButton.getPreferredSize();
		joinButton.setBounds((int) ((0.5 * width) - (0.5 * 100)), 75, 100, size.height);
		joinButton.addActionListener(new JoinListener());
		this.add(joinButton);
		
		quitButton = new JButton("Retour");
		size = quitButton.getPreferredSize();
		quitButton.setBounds((int) ((0.5 * width) - (0.5 * 100)), 125, 100, size.height);
		quitButton.addActionListener(new QuitListener());
		this.add(quitButton);

		this.setVisible(true);
	}

	class HostListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(gameServer == null) {
				try {
					gameServer = new GameServer();
					gameServer.enregistrementService(6666);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				gameServer.close();
				gameServer = null;
			}
			
		}
	}
	
	class JoinListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			game.start();
		}
	}

	class QuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			MultiplayerWindow.this.dispose();
		}
	}
}
