package tarot.project;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchInterface extends JPanel{

	public static Card currCard;
	public static int cardIndex;
	
	public static DisplayPanel displayPanel = new DisplayPanel();
	public static JPanel rightPanel = new JPanel();

	public static JLabel searchTitle =new JLabel("Search");
	public static JTextField nameSearch = new JTextField("",20);
	public static JTextField numberSearch = new JTextField("", 20);	
	
	private static JButton nameSubmit  = new JButton("Search by name");
	private static JButton numberSubmit = new JButton("Search by number");
	
	private static JButton leftBtn = new JButton("Previous");
	private static JButton rightBtn = new JButton("Next");
	
	public static JLabel btnTitle =new JLabel("What do you want to do?");
	private static JButton editBtn = new JButton("Edit");
	private static JButton deleteBtn = new JButton("Delete");
	
	
	public static TextFieldAction textFieldAction = new TextFieldAction();
	public static ButtonAction btnAction = new ButtonAction();
	
	public static ModificationInterface currModif=null;
	
	public SearchInterface() {
		
		if (Main.allCards.size()>0) {
			currCard=Main.allCards.get(0);
			cardIndex=0;
			displayPanel.displayCard(Main.allCards.get(0));
		}
		
		displayPanel.setBackground(Color.YELLOW);
		displayPanel.setLayout(null);
		
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.PINK);
			
		nameSearch.addActionListener(textFieldAction);

		searchTitle.setBounds(70, 70, 200, 22);
		nameSearch.setBounds(70, 100, 240, 22);	
		nameSubmit.setBounds(320, 100, 150, 22);
		
		numberSearch.setBounds(70, 140, 240, 22);		
		numberSubmit.setBounds(320, 140, 150, 22);
		
		btnTitle.setBounds(70, 210, 500, 22);
		editBtn.setBounds(70, 240, 100, 22);
		deleteBtn.setBounds(70, 280, 100, 22);
		
		leftBtn.setBounds(110, 400, 100,22);
		rightBtn.setBounds(290, 400, 100, 22);
		
		numberSearch.addActionListener(textFieldAction);
			
		nameSubmit.addActionListener(btnAction);					
		numberSubmit.addActionListener(btnAction);
		
		rightBtn.addActionListener(btnAction);		
		leftBtn.addActionListener(btnAction);
		
		editBtn.addActionListener(btnAction);
		deleteBtn.addActionListener(btnAction);
		
		rightPanel.add(searchTitle);
		
		rightPanel.add(nameSearch);
		rightPanel.add(nameSubmit);

		rightPanel.add(numberSearch);
		rightPanel.add(numberSubmit);
		
		rightPanel.add(btnTitle);
		rightPanel.add(editBtn);
		rightPanel.add(deleteBtn);
		
		rightPanel.add(rightBtn);
		rightPanel.add(leftBtn);
			
		setLayout(new GridLayout(1,2));
		
		add(displayPanel);
		add(rightPanel);

		validate();
	}
	
	public static void nextCard() {
		if (Main.allCards.size()>0) {
			if (cardIndex+1 < Main.allCards.size()) {
				cardIndex++;
			}
			
			else {
				cardIndex = 0;
			}
		
			changeCurrentCard(cardIndex);
		}
	}
	
	public static void previousCard() {
		if (Main.allCards.size()>0) {
			if (0 <= cardIndex-1) {
				cardIndex--;
			}
			
			else {
				cardIndex = Main.allCards.size()-1;
			}
			
			changeCurrentCard(cardIndex);
		}
	}
	
	
	public static boolean searchByName(String name) {
		if (Main.allCards.size()>0) {
			for (int i=0; i < Main.allCards.size(); i++) {
				if (Main.allCards.get(i).getName().equals(name)){
					changeCurrentCard(i);
					return true;
				}
			}
		}
		System.out.println("We don't find a card by this name");
		return false;
	}
	
	public static boolean searchByNumber(String number) {
		if (Main.allCards.size()>0) {
			for (int i=0; i < Main.allCards.size(); i++) {
				if (Main.allCards.get(i).getNumber().toString().equals(number)){
					changeCurrentCard(i);
					return true;
				}
			}
		}
		System.out.println("We don't find a card by this number");
		return false;
	}
	
	public static int findIndexByNumber(int number) {
		if (Main.allCards.size()>0) {
			for (int i=0; i < Main.allCards.size(); i++) {
				if (Main.allCards.get(i).getNumber()==number){
					changeCurrentCard(i);
					return i;
				}
			}
		}
		System.out.println("We don't find a card by this number");
		return 0;
	}
	
	public void setCard(Card card) {
		int number = card.getNumber();
		int index = findIndexByNumber(number);
		Main.allCards.set(index, card);
	}
	
	private static void changeCurrentCard(int i) {
		if (Main.allCards.size()>0) {
			cardIndex = i;
			currCard = Main.allCards.get(cardIndex);
			displayPanel.displayCard(currCard);
		}
	}
	
	public static JButton getNameSubmit() {
		return nameSubmit;
	}

	public static JButton getNumberSubmit() {
		return numberSubmit;
	}
	
	public static JButton getLeftBtn() {
		return leftBtn;
	}
	
	public static JButton getRightBtn() {
		return rightBtn;
	}
	
	public static JButton getEditBtn() {
		return editBtn;
	}
	
	public static JButton getDeleteBtn() {
		return deleteBtn;
	}
		
}
