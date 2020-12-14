package tarot.project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ModificationInterface extends CreationInt {
	
	private Card currCard;
	private JButton exitBtn = new JButton("Exit");
	
	public ModificationInterface(Card card) {
		currCard = card;
		init();
		displayPanel.displayCard(currCard);	
		rightPanel.setBackground(Color.GRAY);
		exitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent click) {
				exit();			
			}
		});
		
		rightPanel.add(exitBtn);
	}
	
	@Override
	protected void initFields() {	
		nameField = new JTextField(currCard.getName(),20);	
		arcanaField = new JTextField(currCard.getArcana(), 20);	
		descriptionField = new JTextField(currCard.getDescription(), 20);	
	}
	
	public void exit() {
		Interface.getMainPanel().remove(this);
		Interface.mainLayout.show(Interface.getMainPanel(), "searching");	
		Interface.searchInterf.displayPanel.displayCard(Interface.searchInterf.currCard);
	}
	
	public  Card getCurrCard() {
		return currCard;
	}
	
}
