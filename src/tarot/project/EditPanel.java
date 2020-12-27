package tarot.project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends FormPanel {
	
	private Card currCard;
	private JButton exitBtn = new JButton("Exit");
	
	public EditPanel(Card card) {
		cardView = new CardView();
		fieldsPanel = new JPanel();
		currCard = card;
		init();
		cardView.displayCard(currCard);	
		fieldsPanel.setBackground(Color.GRAY);
		exitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent click) {
				exit();			
			}
		});
		
		exitBtn.setBounds(250, 460, 100, 22);
		fieldsPanel.add(exitBtn);
	}
	
	@Override
	protected void initFields() {	
		nameField = new JTextField(currCard.getName(), 20);	
		arcanaField = new JTextField(currCard.getArcana(), 20);	
		descriptionField = new JTextField(currCard.getDescription(), 20);	
	}
	
	public void exit() {
		Interface.getMainPanel().remove(this);
		Interface.mainLayout.show(Interface.getMainPanel(), "searching");	
		CollectionPanel.cardView.displayCard(CollectionPanel.currCard);
		Interface.getCreateButton().setEnabled(true);
		Interface.getSettingsButton().setEnabled(true);
		Interface.getSearchButton().setEnabled(true);
	}
	
	public Card getCurrCard() {
		return currCard;
	}

	@Override
	protected void submitVerification(String name, String arcana, String description, File image) {
		if (currCard.canBeUpdated(name, arcana, description, image)) {
			currCard.setProperties(name, arcana, description, image);
			
			messageLabel.setForeground(Color.GREEN);
			messageLabel.setText("Your card was updated with success");
		}
		
		else {
			String message="";
			
			if (Card.isNameUsed(name) && !name.equals(currCard.getName())) {		
				message = "This name is already used";
			}
			
			else if (name.equals("") || description.equals("")) {
				message = "You must fill all the fields";
			}
			
			else if (!Card.isArcana(arcana)) {
				message = "This arcana does not exist";
			}
			
			else if (image==null) {
				message = "You must add an image";
			}
			
			messageLabel.setForeground(Color.RED);
			validate();
			messageLabel.setText(message);
		}
	}
	
}
