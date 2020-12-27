package tarot.project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.*;

/**
 * Launch the application
 * Contain the main methods
 *
 */
public class Main {	
	/**
	 * Fill the collection of cards with the ones stored in a json file
	 * Displays the window with it interface
	 * @param args
	 */
	public static void main(String ...args) {
		deserializeCards();   
		new Interface();
    }
	
	/**
	 * Save the card collection in the json file
	 * Refresh the card collection with new values
	 */
	public static void saveAndRefresh() {
		Main.serializeCards(Collection.allCards);
		Main.deserializeCards();
	}
	 
	/**
	 * Save the list of cards in json data
	 * @param cards		List of cards to save
	 */
	public static void serializeCards(ArrayList<Card> cards) {
		Gson gson = new Gson();
		
		// 1. Java object to JSON file
		try (FileWriter writer = new FileWriter("all_user_cards.json")) {
            gson.toJson(cards, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }	   
	}
	
	/**
	 * Refresh the card collection with json data
	 */
	public static void deserializeCards() {
		Gson gson = new Gson();
		
		try {		
			BufferedReader reader = new BufferedReader(new FileReader("all_user_cards.json"));
			
			try {
				//We read the json string to identify each Card object.
				StringBuilder sb =  new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				//Array to stock each card identified
				ArrayList<Card> cards = new ArrayList<>();
				
				//Loop that enables to identify each Card object in the json string
				while (sb.indexOf("{")!=-1) {	
					int idStart= sb.indexOf("{");
					int idEnd;
					
					int idNextOpen = sb.indexOf("{", idStart+1);
					while (true) {
						if (idNextOpen!=-1) {
							if (sb.charAt(idNextOpen-1)!=':') {
								idEnd = sb.lastIndexOf("}", idNextOpen);
								break;
							}
							else {
								idNextOpen = sb.indexOf("{", idNextOpen+1);
							}							
						}
						
						else {
							idEnd = sb.lastIndexOf("}");
							break;
						}
					}
				
					String jsonCard = sb.substring(idStart, idEnd+1);
					sb.delete(idStart, idEnd+1);
					
					Card currCard = gson.fromJson(jsonCard, Card.class);
					
					cards.add(currCard);
				}
				
				reader.close();
				
				//We overwrite the current card collection with the one contained in the json file.
				Collection.allCards = cards;
			
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			
		} catch (FileNotFoundException e1 ) {
			e1.printStackTrace();
		} 
	}

}
