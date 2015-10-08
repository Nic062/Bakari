package gui.listeners;

import gui.MainWindow;
import gui.init.WelcomeWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonListener implements MouseListener
{
	private JButton button;
	private WelcomeWindow w;
	
	public ButtonListener(JButton button, WelcomeWindow w)
	{
		this.button = button;
		this.w = w;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(button.getText().equals("Jouer !"))
		{
			MainWindow mainWindow = new MainWindow(700, 600, "Bakari - Partie en cours");
			w.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
