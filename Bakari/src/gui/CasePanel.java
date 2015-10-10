package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class CasePanel extends JPanel
{
	private static final long serialVersionUID = 5223898050430461255L;
	
	private Color color;
	
	public CasePanel(Color color)
	{
		this.color = color;
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(color);
	}
	
	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
