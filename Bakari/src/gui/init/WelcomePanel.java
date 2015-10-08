package gui.init;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel
{
	private static final long serialVersionUID = -4525478095708834136L;
	
	private String imgName;
	
	public WelcomePanel(String imgName)
	{
		this.imgName = imgName;
	}
	
	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(new File(this.imgName));
			g.drawImage(img, 70, 35, this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
