package gui.panels.init;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel
{
	private static final long serialVersionUID = 4855918642590690918L;

	private String imgName;
	
	public LogoPanel(String imgName)
	{
		this.imgName = imgName;
	}
	
	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(this.getClass().getResource(imgName));
			g.drawImage(img, 50, 40, this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getImgName()
	{
		return this.imgName;
	}
}
