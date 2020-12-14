package tarot.project;

import java.util.ArrayList;

public class CardScanner {	
	
	public CardScanner() {
		//initCards();
	}
	//Update the deck of cards
	/*private void initCards() {
		Main.deserialize();
	}*/
	
	private void showAllCards() {
		if (Main.allCards != null) {
			for (Card currCarr : Main.allCards) {
				System.out.println(currCarr);
			}
		}
	}
	
	private void findCard(int id) {
		if (Main.allCards != null) {
			Main.allCards.get(id);
		}
	}
	
	private Card findCard(String name) {
		if (Main.allCards !=null) {
			for (Card currCard : Main.allCards) {
				if (name.equals(currCard.getName()) || name.equals(currCard.getArcana())) {
					return currCard;
				}
			}
		}
		return null;
	}
	
	
}
