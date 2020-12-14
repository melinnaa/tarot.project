package tarot.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldAction implements ActionListener {
	String inputName;
	String inputArcana;
	String inputDescription;

	@Override
	public void actionPerformed(ActionEvent textAction) {
		if (textAction.getSource() == Interface.creationInterf.getNameField()) {			
			//Rajouter une condition pour vérifier que le textfield n'est pas null ou non défini
			inputName = Interface.creationInterf.getNameField().getText();
			Interface.creationInterf.displayPanel.name = inputName;	
			Interface.creationInterf.displayPanel.repaint();	
		}
		
		else if (textAction.getSource() == Interface.creationInterf.getArcanaField()) {
			//Rajouter une condition pour vérifier que le textfield n'est pas null ou non défini
			inputArcana = Interface.creationInterf.getArcanaField().getText();
			Interface.creationInterf.displayPanel.arcana = inputArcana;
			Interface.creationInterf.displayPanel.repaint();	
		}
		
		else if (textAction.getSource() == Interface.creationInterf.getDescriptionField()) {
			//Rajouter une condition pour vérifier que le textfield n'est pas null ou non défini
			inputDescription = Interface.creationInterf.getDescriptionField().getText();
			Interface.creationInterf.displayPanel.description = inputDescription;
			Interface.creationInterf.displayPanel.repaint();	
		}
		
		else if (textAction.getSource() == SearchInterface.currModif.getNameField()) {
			//Rajouter une condition pour vérifier que le textfield n'est pas null ou non défini
			inputName = SearchInterface.currModif.getNameField().getText();
			SearchInterface.currModif.displayPanel.name = inputName;	
			SearchInterface.currModif.displayPanel.repaint();	
		}
		
		else if (textAction.getSource() == SearchInterface.currModif.getArcanaField()) {
			inputArcana = SearchInterface.currModif.getArcanaField().getText();
			SearchInterface.currModif.displayPanel.arcana = inputArcana;
			SearchInterface.currModif.displayPanel.repaint();
		}
		
		else if (textAction.getSource() == SearchInterface.currModif.getDescriptionField()) {
			inputDescription = SearchInterface.currModif.getDescriptionField().getText();
			SearchInterface.currModif.displayPanel.description = inputDescription;
			SearchInterface.currModif.displayPanel.repaint();
		}
		

	}
	
}
