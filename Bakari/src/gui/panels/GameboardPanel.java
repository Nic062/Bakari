package gui.panels;

import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import entities.Colour;
import entities.Pawn;
import entities.Player;
import game.Game;

public class GameboardPanel extends JPanel
{
	private static final long serialVersionUID = 168839659013621228L;
	
	private Game game;
	
	private JButton[][] lb;
	private Pawn pawnSelected;

	public GameboardPanel(Game g)
	{
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		try {
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		// Autorise la couleur sur les boutons pour Mac OSx
		this.game = g;
		this.setLayout(null);
		int y = g.getBoardGame().getGrid().length;
		int x = g.getBoardGame().getGrid()[0].length;
		lb = new JButton[y][x];
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				lb[i][j] = new JButton();
				lb[i][j].setMargin(new Insets(1,1,1,1));
				lb[i][j].setFont(new Font("Arial", Font.PLAIN, 35));
				lb[i][j].setBackground(Colour.colourToColor(g.getBoardGame().getGrid()[i][j]));
				lb[i][j].setOpaque(true);
				lb[i][j].setBounds(j * 35 + 35, i * 35, 35, 35);
				lb[i][j].addActionListener(new CaseListener());
				this.add(lb[i][j]);
			}
		}
	}
	
	public void update()
	{
		int y = game.getBoardGame().getGrid().length;
		int x = game.getBoardGame().getGrid()[0].length;
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				lb[i][j].setText("");
				lb[i][j].setIcon(new ImageIcon(""));
			}
		}
		for(Player pl : game.getListPlayers()) {
			for(Pawn pa : pl.getPawns()) {
				String img = "pawn_"+pa.getColour().toString().toLowerCase()+".png";
				lb[pa.getPositionY()][pa.getPositionX()].setIcon(new ImageIcon(this.getClass().getResource("/gui/panels/init/"+img)));
			}
		}
	}
	
	class CaseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(pawnSelected == null) {
				JButton button = (JButton)e.getSource();
				int y = game.getBoardGame().getGrid().length;
				int x = game.getBoardGame().getGrid()[0].length;
				for(int i = 0; i < y; i++) {
					for(int j = 0; j < x; j++) {
						if(lb[i][j].equals(button)) {
							for(Pawn p : game.getCurrentPlayer().getPawns()) {
								if(p.getPositionX() == j && p.getPositionY() == i) {
									pawnSelected = p;
									break;
								}
							}
							break;
						}
					}
				}
				
			}
			else {
				JButton button = (JButton)e.getSource();
				int y = game.getBoardGame().getGrid().length;
				int x = game.getBoardGame().getGrid()[0].length;
				for(int i = 0; i < y; i++) {
					for(int j = 0; j < x; j++) {
						if(lb[i][j].equals(button)) {
							if(!game.movePawn(game.getCurrentPlayer(), pawnSelected, j, i)) {
								JOptionPane.showMessageDialog(null, "Déplacement impossible");
							}
							break;
						}
					}
				}
				pawnSelected = null;
			}
		}
	}
}
