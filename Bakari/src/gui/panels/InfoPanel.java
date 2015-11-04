package gui.panels;

import java.awt.Dimension;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Pawn;
import entities.Player;
import game.Game;

public class InfoPanel extends JPanel
{
	private static final long serialVersionUID = 7876870349639324992L;
	private JLabel label;
	
	private List<JLabel> playerNameList;
	private List<ImagePanel> pawnImageList;

	private Game game;

	public InfoPanel(Game g)
	{
		this.game = g;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(150, 600));
		
		this.label = new JLabel();
		this.label.setText("<html><u>Informations :</u></html>");
		this.label.setBounds(0, 20, 150, 30);
		this.add(label);
		
		this.playerNameList = new LinkedList<JLabel>();
		this.pawnImageList = new LinkedList<ImagePanel>();
		
		update();
	}
	
	public void update()
	{
		for(JLabel pn : playerNameList) {
			this.remove(pn);
		}
		playerNameList.clear();
		for(ImagePanel pi : pawnImageList) {
			this.remove(pi);
		}
		pawnImageList.clear();
		int x = 0;
		int y = 1;
		for(Player p : game.getListPlayers()) {
			x = 0;
			JLabel pn = new JLabel(p.getNom());
			pn.setBounds(0, y * 50, 150, 30);
			this.add(pn);
			playerNameList.add(pn);
			for(Pawn pa : p.getPawns()) {
				ImagePanel ip = new ImagePanel("pawn_" + pa.getColour().toString().toLowerCase() + ".png");
				ip.setBounds(x * 15, 30 + y * 50, 15, 15);
				this.add(ip);
				pawnImageList.add(ip);
				x++;
			}
			y++;
		}
		this.repaint();
	}
}
