package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class CasePanel extends JPanel
{
	private static final long serialVersionUID = 5223898050430461255L;
	
	private Color color;
	
	public CasePanel(Color color)
	{
		this.color = color;
		this.setLayout(new GridLayout(1, 0));
		this.setBackground(color);
	}
	
	public void paintComponent(Graphics g)
	{
		//g.fillRect(1, 1, this.getWidth(), this.getHeight());
		
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, this.getBackground(), this.getWidth(), this.getHeight(),	Color.GRAY);
		g2d.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public Color getColor()
	{
		return this.color;
	}
}
