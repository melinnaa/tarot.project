package tarot.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	
	public String name="";
	public String arcana="";
	public String description="";
	public File image;
	public JLabel imgLabel;

	public DisplayPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		//JLabel to display the image
		imgLabel = new JLabel();
		imgLabel.setBounds(130, 130, 230, 230);	
		add(imgLabel);				
	}

	public void displayCard(Card card) {
		displayImage(card);
		displayProperties(card);
		repaint();
	}
	
	public void displayImage(Card card) {
		File image = card.getImage();
		if (image!=null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(image);
				ImageIcon icon = new ImageIcon(bufferedImage);
				imgLabel.setIcon(icon);
				this.image = image;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		else {
			imgLabel.setIcon(null);
		}
	}
	
	public void displayProperties(Card card) {
		this.name = card.getName();
		this.arcana = card.getArcana();
		this.description = card.getDescription();
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRoundRect(120, 100, 250, 400, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString(name, 130, 120);
		
		g.setColor(Color.BLACK);
		g.drawString(arcana, 220, 400);
		
		g.setColor(Color.BLACK);
		g.drawString(description, 130, 450);

		
	}
}
