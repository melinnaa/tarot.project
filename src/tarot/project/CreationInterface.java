package tarot.project;

import javax.swing.JTextField;

public class CreationInterface extends CreationInt {
		
	public CreationInterface() {
		init();
	}
	
	public void initFields() {
		nameField = new JTextField("",20);		
		arcanaField = new JTextField("", 20);		
		descriptionField = new JTextField("", 20);
	}
	
	public boolean canCreate(String name, String arcana) {
		if (Card.isNameUsed(name)) {
			return false;
		}		
		else if (!Card.isArcana(arcana)) {
			return false;
		}
		return true;
	}
	
}
