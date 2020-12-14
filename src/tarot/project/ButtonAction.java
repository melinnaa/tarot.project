package tarot.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ButtonAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent onClick) {
		
		//Main buttons
		if (onClick.getSource() == Interface.getCreateButton()) {
			Interface.mainLayout.show(Interface.getMainPanel(),"creation");
		}
		
		else if (onClick.getSource() == Interface.getSearchButton()) {					
			Interface.mainLayout.show(Interface.getMainPanel(),"searching");	
		}
		
		else if (onClick.getSource() == Interface.getSettingsButton()) {
			Interface.mainLayout.show(Interface.getMainPanel(),"settings");
		}
		
		//On the Searching Interface
		else if (onClick.getSource() == SearchInterface.getNameSubmit()) {
			String nameSearched = SearchInterface.nameSearch.getText();
			SearchInterface.searchByName(nameSearched);
		}
		
		else if (onClick.getSource() == SearchInterface.getNumberSubmit()) {
			String numberSearched = SearchInterface.numberSearch.getText();
			SearchInterface.searchByNumber(numberSearched);
		}
		
		else if (onClick.getSource() == SearchInterface.getLeftBtn()) {
			SearchInterface.previousCard();
		}
		
		else if (onClick.getSource() == SearchInterface.getRightBtn()) {
			SearchInterface.nextCard();
		}	
		
		else if (onClick.getSource() == SearchInterface.getEditBtn()) {
			if (SearchInterface.currCard!=null) {
				SearchInterface.currModif = new ModificationInterface(SearchInterface.currCard);
				Interface.getMainPanel().add(SearchInterface.currModif, "modification");
				Interface.mainLayout.show(Interface.getMainPanel(),"modification");
			}
		}
		
		else if (onClick.getSource() == SearchInterface.getDeleteBtn()) {
			if (SearchInterface.currCard!=null) {
				SearchInterface.currCard.deleteCard();
			}
		}
		
		//On the Creation Interface
		else if (onClick.getSource() == Interface.creationInterf.getSubmitBtn()) {
			String inputName = Interface.creationInterf.displayPanel.name;
			String inputArcana = Interface.creationInterf.displayPanel.arcana;
			String inputDescription = Interface.creationInterf.displayPanel.description;
			File inputImage =Interface.creationInterf.displayPanel.image;
			if (Interface.creationInterf.canCreate(inputName, inputArcana)) {
				Card card = new Card(inputName, inputArcana, inputDescription, inputImage);
			}
		}
		
		//On the Modification Interface
		else if (onClick.getSource() == SearchInterface.currModif.getSubmitBtn()) {
			String inputName = SearchInterface.currModif.displayPanel.name;
			String inputArcana = SearchInterface.currModif.displayPanel.arcana;
			String inputDescription = SearchInterface.currModif.displayPanel.description;
			File inputImage = SearchInterface.currModif.displayPanel.image;
			
			Card modifCard = SearchInterface.currModif.getCurrCard();
			
			modifCard.setProperties(inputName, inputArcana, inputDescription, inputImage);
		}
		 
	}
	

}
