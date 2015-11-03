package gui.panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AboutPanel extends JPanel
{
	private static final long serialVersionUID = -4968528156393741794L;

	private JTextArea textZone;
	private JScrollPane scrollPane;

	public AboutPanel()
	{
		this.textZone = new JTextArea(4, 20);
		this.scrollPane = new JScrollPane(textZone);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		this.textZone.setEditable(false);
		this.add(scrollPane);
	}

	public void addText(String t)
	{
		textZone.append(t + "\n");
		textZone.setCaretPosition(textZone.getText().length());
	}
}
