package tarot.project;

import java.io.File;
import java.util.ArrayList;
/**
 * Card represents a card with that has a number, a name, an arcana, a description and an image.
 * 
 * @author melinna
 *
 */
public class Card {
	
	private int number;
	private String name;
	private String arcana;
	private String description;
	private File image;
	
	private static String[] arcanas = {"fool", "magician", "high priestess", "empress", "emperor", 
			"hierophant", "lovers", "chariot","strength", "hermit", "wheel of fortune", "justice", 
			"the hanged man", "death", "temperance", "devil","the tower", "the star", "the moon",
			"the sun", "judgement", "the world"};	 

	/**
	 * Constructs a card with the values passed in parameters
	 * @param name			Card name
	 * @param arcana		Card arcana
	 * @param description	Card description
	 * @param img			Card image
	 */
	public Card(String name, String arcana, String description, File img) {
		addNumber();
		setName(name);
		setArcana(arcana);
		setDescription(description);
		setImage(img);
		addCard();
		Main.saveAndRefresh();
	}
	
	/**
	 * Add a number during the card creation
	 */
	private void addNumber() {
		
		//If there is a least one card in the deck
		if (Collection.allCards != null) {
			
			//Creation of an array of card numbers 
			ArrayList<Integer> cardNbs = new ArrayList<>();
			for (Card card: Collection.allCards) {
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
	
	/**
	 * Add a card in the user collection
	 */
	private void addCard() {
		Collection.allCards.add(this);
	}
	
	/**
	 * Test if the name passed in parameter is already used
	 * @param testName		the name to test 
	 * @return a boolean
	 */
	public static boolean isNameUsed(String testName) {
		ArrayList<String> allNames = new ArrayList<>();
		for (Card card :Collection.allCards) {
			allNames.add(card.getName());
		}
		if (allNames.contains(testName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Test if the string passed in parameter correspond to an arcana
	 * @param testArcana		the string to test
	 * @return
	 */
	public static boolean isArcana(String testArcana) {
		for (int i=0; i<arcanas.length;i++) {
			if (arcanas[i].equals(testArcana.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Delete the current card from the user collection
	 */
	public void deleteCard() {
		
		Collection.allCards.remove(this);
		Main.saveAndRefresh();
		
		if (Collection.allCards.size()>0) {			
			CollectionPanel.nextCard();
		}
		
		else {
			CollectionPanel.currCard=null;
			CollectionPanel.cardIndex=0;
			CollectionPanel.cardView.name = "";
			CollectionPanel.cardView.arcana = "";
			CollectionPanel.cardView.description = "";
			CollectionPanel.cardView.image = null;
			CollectionPanel.cardView.imgLabel.setIcon(null);
			
			CollectionPanel.cardView.repaint();
		}		
	}
	
	/**
	 * Test if a card can be created with the name and the arcana passed in parameters
	 * @param name		the name to test
	 * @param arcana	the arcana to  test
	 * @return			true if the the name and the arcana are available
	 */
	public static boolean canBeCreated(String name, String arcana, String description, File img) {
		if (Card.isNameUsed(name) || name.equals("") || description.equals("") || img==null) {
			return false;
		}		
		else if (!Card.isArcana(arcana)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Test if a card can be updated with the name and the arcana passed in parameters
	 * @param name		the name to test
	 * @param arcana	the arcana to  test
	 * @return			true if the the name and the arcana are available
	 */
	public boolean canBeUpdated(String name, String arcana, String description, File img) {
		if (!name.equals(this.name) && Card.isNameUsed(name) || description.equals("") || img==null) {
			return false;
		}		
		else if (!Card.isArcana(arcana)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Set the card properties with the values passed in parameters
	 * @param name			new name
	 * @param arcana		new arcana
	 * @param description	new description
	 * @param img			new image
	 */
	public void setProperties(String name, String arcana, String description, File img) {
		setName(name);
		setArcana(arcana);
		setDescription(description);
		setImage(img);
		Main.saveAndRefresh();
	}
	
	/**
	 * Set the card name
	 * @param name		new name
	 */
	public void setName(String name) {
		if (!isNameUsed(name)) {
			this.name = name;
		}
	}
	
	/**
	 * Set the card arcana
	 * @param arcana	new arcana
	 */
	public void setArcana(String arcana) {
		if (isArcana(arcana)) {
			this.arcana = arcana;
		}
	}
	
	/**
	 * Set the card description
	 * @param description	new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Set the card image
	 * @param img	new img
	 */
	public void setImage(File img) {
		this.image = img;
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
	
	public File getImage() {
		return this.image;
	}
	
}
