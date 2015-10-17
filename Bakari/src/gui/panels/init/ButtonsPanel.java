package gui.panels.init;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entities.Player;
import gui.frames.MainWindow;
import gui.frames.WelcomeWindow;

public class ButtonsPanel extends JPanel
{
	private static final long serialVersionUID = 6247499294463790432L;
	
	private WelcomeWindow w;
	
	private JButton optionButton = new JButton("Options");
	private JButton playButton = new JButton("Jouer !");
	private JButton quitButton = new JButton("Quitter");
	
	private int nbPlayers = 0;
	private List<Player> players = new LinkedList<Player>();
	
	public ButtonsPanel(WelcomeWindow w)
	{
		this.w = w;
		
		this.setLayout(new BorderLayout());
		
		this.optionButton.addActionListener(new OptionsListener());
		this.playButton.addActionListener(new PlayListener());
		this.quitButton.addActionListener(new QuitListener());

		this.add(playButton, BorderLayout.NORTH);
		this.add(optionButton, BorderLayout.CENTER);
		this.add(quitButton, BorderLayout.SOUTH);
	}
	
	class OptionsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String[] listeNbPlayers = {"2", "3", "4"};
			nbPlayers = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Veuillez indiquer le nombre de joueurs :", "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE, null, listeNbPlayers, listeNbPlayers[0]));
		}
	}
	
	class PlayListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(nbPlayers == 0)
			{
				JOptionPane.showMessageDialog(null, "Vous devez d'abord définir le nombre de joueurs dans les options avant de commencer une partie.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 1; i <= nbPlayers; i++)
				{
					players.add(new Player(JOptionPane.showInputDialog(null, "Indiquez le nom du joueur " + i + " : ", "Nom du joueur " + i, JOptionPane.QUESTION_MESSAGE)));
				}
				
				MainWindow main = new MainWindow(players);
				main.setVisible(true);
				w.setVisible(false);
			}
		}
	}
	
	class QuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			w.dispose();
		}
	}
	
	public JButton getPlayButton()
	{
		return this.playButton;
	}
}
