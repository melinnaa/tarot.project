package tarot.project;

import java.util.ArrayList;

/**
 * Collection reoresents the collection of user cards
 * @author melinna
 *
 */
public class Collection {
	/**
	 * User card collection
	 */
	public static ArrayList<Card> allCards = new ArrayList<Card>();
	
	/**
	 * Search a card by its name
	 * @param name		the name of the card searched
	 * @return			the card found
	 */
	public static Card searchByName(String name) {
		if (allCards.size()>0) {
			for (int i=0; i < allCards.size(); i++) {
				if (allCards.get(i).getName().equals(name)){
					return allCards.get(i);
				}
			}
		}
		return null;
	}
	
	/**
	 * Search a card by its number
	 * @param number	the number of the card searched
	 * @return			the card found
	 */
	public static Card searchByNumber(String number) {
		if (allCards.size()>0) {
			for (int i=0; i <allCards.size(); i++) {
				if (allCards.get(i).getNumber().toString().equals(number)){
					return allCards.get(i);
				}
			}
		}
		return null;
	}
	
	/**	
	 * Get the index of a card passed in parameters
	 * @param card	the card we want to know the index 
	 * @return 		the index
	 */
	public static int getIndexCard(Card card) {
		for (int i=0; i<allCards.size(); i++) {
			if (allCards.get(i)==card) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Set a card properties (except the number)
	 * @param card		the new version of the card identified by its number
	 */
	public void setCard(Card card) {
		int index = getIndexCard(card);
		allCards.set(index, card);
	}
}
