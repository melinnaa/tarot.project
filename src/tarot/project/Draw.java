package tarot.project;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * Draw represents a draw of Tarot card
 *
 */
public class Draw {
	ArrayList <Card> deck;
	HashMap <String, Card> drawCards= new HashMap<>();
	String [] words = {"state", "problem", "advice", "response"};
	int numberCards = 4;
	
	public Draw() {
		
		deck = new ArrayList<Card>(Collection.allCards);
		
		for (int i=0; i<numberCards; i++) {
			int index = (int)(Math.random()*deck.size());
			Card currCard = deck.get(index);
			drawCards.put(words[i], currCard);
			deck.remove(index);
		}
	}
}