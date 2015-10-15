package gui.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class CasePanel extends JPanel
{
	private static final long serialVersionUID = -1388649513244640818L;
	
	private Color color;

	public CasePanel(Color color)
	{
		this.color = color;
		this.setLayout(new GridLayout(1, 0));
		this.setBackground(color);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, this.getBackground(), this.getWidth(), this.getHeight(), this.getForeground());
		
		g2d.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
