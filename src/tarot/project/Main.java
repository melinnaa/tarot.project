package tarot.project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.*;

public class Main {
	
	public static ArrayList<Card> allCards = new ArrayList<Card>();
	
	public static void main(String ...args) {
		deserialize();   
		new Interface();
    }
	 
	public static void serialize(ArrayList<Card> cards) {
		Gson gson = new Gson();
		
		// 1. Java object to JSON file
		try (FileWriter writer = new FileWriter("all_user_cards.json")) {
            gson.toJson(cards, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }	    
	       
		// 2. Java object to JSON string
	    String jsonInString = gson.toJson(cards);
	    System.out.println(jsonInString);
	}
	
	
	public static void deserialize() {
		Gson gson = new Gson();
		
		try {		
			BufferedReader reader = new BufferedReader(new FileReader("all_user_cards.json"));
			
			try {
				StringBuilder sb =  new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				
				ArrayList<Card> cards = new ArrayList<>();
				
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
					System.out.println(sb.substring(idStart, idEnd+1));
					String jsonCard = sb.substring(idStart, idEnd+1);
					sb.delete(idStart, idEnd+1);
					
					Card currCard = gson.fromJson(jsonCard, Card.class);
					
					cards.add(currCard);
				}
				
				reader.close();
				allCards = cards;
			
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			
		} catch (FileNotFoundException e1 ) {
			e1.printStackTrace();
		} 
	}
	
	public static void saveAndRefresh() {
		Main.serialize(Main.allCards);
		Main.deserialize();
	}


}
