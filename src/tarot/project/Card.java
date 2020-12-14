package tarot.project;

import java.io.File;
import java.util.ArrayList;

public class Card {
	private static final long serialVersionUID = 1L;
	
	private int number;
	private String name;
	private String arcana;
	private String description;
	private File image;
	
	private static String[] arcanas = {"fool", "magician", "high priestess", "empress", "emperor", 
			"hierophant", "lovers", "chariot","strength", "hermit", "wheel of fortune", "justice", 
			"the hanged man", "death", "temperance", "devil","the tower", "the star", "the moon",
			"the sun", "judgement", "the world"};	 

	public Card(String name, String arcana, String description, File img) {
		addNumber();
		setName(name);
		setArcana(arcana);
		setDescription(description);
		setImage(img);
		addCard();
		Main.saveAndRefresh();
	}
	
	public void setProperties(String name, String arcana, String description, File img) {
		setName(name);
		setArcana(arcana);
		setDescription(description);
		setImage(img);
		
	}
	
	public void setCard() {
		
	}
	
	public void setName(String name) {
		if (name.equals(this.name) || !isNameUsed(name)) {
			this.name = name;
		}
		Main.saveAndRefresh();
	}
	
	public void setArcana(String arcana) {
		if (isArcana(arcana)) {
			this.arcana = arcana;
		}
		Main.saveAndRefresh();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getNumber() {
		return this.number;
	}

	public String getArcana() {
		return this.arcana;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	private void createCard() {
		
	}
	
	/*private void changeCard() {
		setNumber();
		setName();
		setDescription();
	}*/
	
	
	private void addNumber() {
		
		//If there is a least one card in the deck
		if (Main.allCards != null) {
			
			//Creation of an array of card numbers 
			ArrayList<Integer> cardNbs = new ArrayList<>();
			for (Card card: Main.allCards) {
				cardNbs.add(card.getNumber());		
			}
			
			//Find the smallest number not used through those that already exist
			int nb=-1;
			for (int i = 0; i < cardNbs.size(); i++) {
				if (!cardNbs.contains(i)) {
					nb=i;
					break;
				}
			}			
			if (nb==-1) {
				nb = cardNbs.size();
			}
			
			this.number = nb;	
		}	
		
		//If the currentCard is the first one created
		else {
			this.number = 0;
		}
	}
	
	
	public void setDescription(String description) {
		this.description = description;
		Main.saveAndRefresh();
	}
	
	public void setImage(File img) {
		this.image = img;
		Main.saveAndRefresh();
	}
	
	private void addCard() {
		Main.allCards.add(this);
	}
	
	public static boolean isNameUsed(String testName) {
		ArrayList<String> allNames = new ArrayList<>();
		for (Card card : Main.allCards) {
			allNames.add(card.getName());
		}
		if (allNames.contains(testName)) {
			System.out.println("This name is already used");
			return true;
		}
		return false;
	}
	
	public static boolean isArcana(String testArcana) {
		for (int i=0; i<arcanas.length;i++) {
			if (arcanas[i].equals(testArcana.toLowerCase())) {
				return true;
			}
		}
		System.out.println("The input do not correspond to an arcana");
		return false;
	}
	

	public void deleteCard() {
		
		if (Main.allCards.size()-1>0) {
			Interface.searchInterf.nextCard();
		}
		
		else {
			Interface.searchInterf.displayPanel.name = "";
			Interface.searchInterf.displayPanel.arcana = "";
			Interface.searchInterf.displayPanel.description = "";
			Interface.searchInterf.displayPanel.image = null;
			Interface.searchInterf.displayPanel.imgLabel.setIcon(null);
			
			Interface.searchInterf.repaint();
		}
		
		Main.allCards.remove(this);
		Main.saveAndRefresh();
	}	
	
	public File getImage() {
		return this.image;
	}
	
}
