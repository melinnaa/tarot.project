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

/**
 * 
 * Panel that displays a card with its properties
 *
 */
public class CardView extends JPanel {
	
	public String name="";
	public String number="";
	public String arcana="";
	public String description="";
	public File image;
	public JLabel imgLabel;

	/**
	 * Constructs the template of a card
	 */
	public CardView() {
		setBackground(ManagerPanel.colors[0]);
		setLayout(null);
		//JLabel to display the image
		imgLabel = new JLabel();
		imgLabel.setBounds(130, 130, 230, 230);	
		add(imgLabel);				
	}

	/**
	 * Shows a card with its properties
	 * @param card		the card to show (can be null to show nothing)
	 */
	public void displayCard(Card card) {		
		if (card!=null) {
			displayImage(card);
			displayProperties(card);
			repaint();
		}
	}
	
	/**
	 * Displays a card image
	 * @param card		the card we want to display the image
	 */
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
	
	/**
	 * Displays a card properties
	 * @param card		the card we want to display its properties
	 */
	public void displayProperties(Card card) {
		this.name = card.getName();
		this.number = card.getNumber().toString();
		this.arcana = card.getArcana();
		this.description = card.getDescription();
	}
	
	/**
	 * Set the current image of the panel passed in parameter
	 * Mostly used in the FileChooser
	 * @param fileImage 	image to display on the panel
	 */
	public void setCardImage(File fileImage) {
		try {
			BufferedImage image = ImageIO.read(fileImage);
			ImageIcon icon = new ImageIcon(image);
			this.imgLabel.setIcon(icon);
			this.image = fileImage;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Empties the Panel
	 */
	public void resetPanel() {
		name = "";
		number= "";
		arcana = "";
		description = "";
		image = null;
		imgLabel.setIcon(null);
		
		repaint();
	}

	/**
	 * Paint the card and write its properties on the panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRoundRect(120, 100, 250, 400, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString(name, 130, 120);
		
		g.setColor(Color.BLACK);
		g.drawString(number, 330, 120);
		
		g.setColor(Color.BLACK);
		g.drawString(arcana, 220, 400);
		
		g.setColor(Color.BLACK);
		g.drawString(description, 130, 450);

		
	}
}
