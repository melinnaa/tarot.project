package tarot.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * DrawPanel corresponds to the panel that display the graphic a draw of cards
 *
 */
public class DrawPanel extends JPanel {

	JLabel panelTitle = new JLabel("Think about a question and return cards..");
	
	JButton cardUp = new JButton();
	JButton cardBottom = new JButton();
	JButton cardLeft = new JButton();
	JButton cardRight = new JButton();
	
	JLabel stateLabel = new JLabel();
	JLabel problemLabel = new JLabel();
	JLabel adviceLabel = new JLabel();
	JLabel responseLabel = new JLabel();
	
	JButton newBtn = new JButton("New draw");
	Draw currDraw;
	
	ImageIcon icon;
	
	
	public DrawPanel() {	
		setLayout(null);
		setBackground(new Color(129,209,176));
	}
	
	public void launch() {
		
		if (Collection.allCards.size()<4) {
			JLabel msg = new JLabel("Vous devez avoir au moins 4 cartes dans votre collection pour lancer un tirage");
			add(msg);
			msg.setBounds(250, 200, 800, 22);
		}
		
		else {	
			add(panelTitle);
			
			add(cardUp);
			add(cardBottom);
			add(cardLeft);
			add(cardRight);
			
			add(stateLabel);
			add(problemLabel);
			add(adviceLabel);
			add(responseLabel);
			
			add(newBtn);
			
			panelTitle.setBounds(350, 50, 800, 22);
			cardUp.setBounds(250, 100, 125, 220);
			cardBottom.setBounds(250, 400, 125, 220);
			cardLeft.setBounds(50, 250, 125, 220);
			cardRight.setBounds(450, 250, 125, 220);
			
			stateLabel.setBounds(600, 100, 400, 100);
			problemLabel.setBounds(600, 200, 400, 100);
			adviceLabel.setBounds(600, 300, 400, 100);
			responseLabel.setBounds(600, 400, 400, 100);
			
			newBtn.setBounds(425, 690, 100, 22);
			
			try {
				BufferedImage image = ImageIO.read(new File("resources/cardback.jpg"));
				icon = new ImageIcon(image);
				cardUp.setIcon(icon);
				cardBottom.setIcon(icon);
				cardLeft.setIcon(icon);
				cardRight.setIcon(icon);
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
					
			validate();
			
			currDraw = new Draw();
			
			cardUp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BufferedImage image;
					try {
						image = ImageIO.read(currDraw.drawCards.get("advice").getImage());
						ImageIcon icon = new ImageIcon(image);
						cardUp.setIcon(icon);
						cardUp.setText(currDraw.drawCards.get("advice").getName());
						adviceLabel.setText("The advice of universe: "+currDraw.drawCards.get("advice").getDescription());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			cardBottom.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BufferedImage image;
					try {
						image = ImageIO.read(currDraw.drawCards.get("response").getImage());
						ImageIcon icon = new ImageIcon(image);
						cardBottom.setIcon(icon);
						cardBottom.setText(currDraw.drawCards.get("response").getName());
						responseLabel.setText("The response of your question: "+currDraw.drawCards.get("response").getDescription());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			cardLeft.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BufferedImage image;
					try {
						image = ImageIO.read(currDraw.drawCards.get("state").getImage());
						ImageIcon icon = new ImageIcon(image);
						cardLeft.setIcon(icon);
						cardLeft.setText(currDraw.drawCards.get("state").getName());
						stateLabel.setText("Your current state: "+currDraw.drawCards.get("state").getDescription());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			cardRight.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BufferedImage image;
					try {
						image = ImageIO.read(currDraw.drawCards.get("problem").getImage());
						ImageIcon icon = new ImageIcon(image);
						cardRight.setIcon(icon);
						cardRight.setText(currDraw.drawCards.get("problem").getName());
						problemLabel.setText("The problem you will meet: "+currDraw.drawCards.get("problem").getDescription());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			newBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					stateLabel.setText("");
					problemLabel.setText("");
					adviceLabel.setText("");
					responseLabel.setText("");
					
					cardUp.setIcon(icon);
					cardBottom.setIcon(icon);
					cardLeft.setIcon(icon);
					cardRight.setIcon(icon);
					
					currDraw = new Draw();
				}
			});
		}
	}
	
}


