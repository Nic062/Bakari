package gui.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import entities.Pawn;

public class PawnPanel extends JPanel
{
	private static final long serialVersionUID = -1388649513244640818L;
	
	private entities.Color color;

	public PawnPanel(Pawn pa)
	{
		this.color = pa.getColor();
		this.setLayout(new GridLayout(1, 0));
		JButton btn = new JButton("X");
		this.add(btn);
	}
	
	
	
}
