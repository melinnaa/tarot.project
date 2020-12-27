package tarot.project;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * Displays the interface for card creation
 *
 */
public class CreationPanel extends FormPanel {
		
	/**
	 * Initialize the creation form interface
	 */
	public CreationPanel() {
		cardView = new CardView();
		fieldsPanel = new JPanel();
		init();
	}
	
	/**
	 * Initialize fields with empty strings
	 */
	public void initFields() {
		nameField = new JTextField("", 20);		
		arcanaField = new JTextField("", 20);		
		descriptionField = new JTextField("", 20);
	}

	@Override
	protected void submitVerification(String name, String arcana, String description, File image) {
		if (Card.canBeCreated(name, arcana, description, image)) {
			Card card = new Card(name, arcana, description, image);
			
			messageLabel.setForeground(Color.GREEN);
			validate();
			messageLabel.setText("Your card was created with success");
		}
		
		else {
			messageLabel.setForeground(Color.RED);
			validate();
			
			if (Card.isNameUsed(name)) {
				messageLabel.setText("This name is already used");
			}
			else if (name.equals("") || description.equals("")) {
				messageLabel.setText("You must fill all the fields");
			}
			
			else if (!Card.isArcana(arcana)) {
				messageLabel.setText("This arcana does not exist");
			}
			
			else if (image==null) {
				messageLabel.setText("You must add an image");
			}
		}	
	}	
}
