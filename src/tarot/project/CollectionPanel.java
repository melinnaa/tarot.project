package tarot.project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CollectionPanel extends JPanel{

	public static Card currCard;
	public static int cardIndex;
	
	public static CardView cardView = new CardView();
	public static JPanel rightPanel = new JPanel();

	private JLabel messageLabel = new JLabel();
	
	public static JLabel searchLabel = new JLabel("Search");
	public static JTextField nameSearch = new JTextField("",20);
	public static JTextField numberSearch = new JTextField("", 20);	
	
	private static JButton nameSubmit  = new JButton("Search by name");
	private static JButton numberSubmit = new JButton("Search by number");
	
	private static JButton leftBtn = new JButton("Previous");
	private static JButton rightBtn = new JButton("Next");
	
	public static JLabel btnLabel =new JLabel("What do you want to do?");
	private static JButton editBtn = new JButton("Edit");
	private static JButton deleteBtn = new JButton("Delete");
	
	public static EditPanel currUpdateInterface=null;
	
	public CollectionPanel() {
		
		if (Collection.allCards.size()>0) {
			currCard=Collection.allCards.get(0);
			cardIndex=0;
			cardView.displayCard(Collection.allCards.get(0));
		}
		
		cardView.setLayout(null);
		
		rightPanel.setLayout(null);
		rightPanel.setBackground(ManagerPanel.colors[1]);

		messageLabel.setForeground(Color.RED);
		messageLabel.setBounds(70, 100, 240, 22);
		
		searchLabel.setBounds(70, 130, 200, 22);
		nameSearch.setBounds(70, 160, 240, 22);	
		nameSubmit.setBounds(320, 160, 150, 22);
		
		numberSearch.setBounds(70, 200, 240, 22);		
		numberSubmit.setBounds(320, 200, 150, 22);
		
		btnLabel.setBounds(70, 260, 500, 22);
		editBtn.setBounds(70, 290, 100, 22);
		deleteBtn.setBounds(70, 330, 100, 22);
		
		leftBtn.setBounds(110, 450, 100,22);
		rightBtn.setBounds(290, 450, 100, 22);
			
		
		nameSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Card card = Collection.searchByName(nameSearch.getText());
				if (card!=null) {
					changeCurrentCard(card);
				}
				else {
					messageLabel.setText("We don't find a card with this name");
					repaint();
				}
			}		
		});
		
		numberSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Card card = Collection.searchByNumber(numberSearch.getText());
				if (card!=null) {
					changeCurrentCard(card);
				}
				else {
					messageLabel.setText("We don't find a card with this number");
					repaint();
				}
			}		
		});
		
		rightBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextCard();
			}		
		});
		
		leftBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				previousCard();
			}		
		});
			
		/**
		 * New panel for the current card edit
		 */
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (currCard!=null) {
					Interface.getMainPanel().add(new EditPanel(currCard), "update");
					Interface.mainLayout.show(Interface.getMainPanel(),"update");
					
					Interface.getCreateButton().setEnabled(false);
					Interface.getSettingsButton().setEnabled(false);
					Interface.getSearchButton().setEnabled(false);
				}								
			}			
		});
		
		deleteBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currCard!=null) {
					currCard.deleteCard();
					messageLabel.setText("Card removed from the collection");
					repaint();
				}			
			}
		});
		
		rightPanel.add(searchLabel);
		
		rightPanel.add(nameSearch);
		rightPanel.add(nameSubmit);

		rightPanel.add(numberSearch);
		rightPanel.add(numberSubmit);
		
		rightPanel.add(btnLabel);
		rightPanel.add(editBtn);
		rightPanel.add(deleteBtn);
		
		rightPanel.add(rightBtn);
		rightPanel.add(leftBtn);
			
		setLayout(new GridLayout(1,2));
		
		add(cardView);
		add(rightPanel);

		validate();
	}
	
	/**
	 * Display the next card of the list
	 */
	public static void nextCard() {
		if (Collection.allCards.size()>0) {
			if (cardIndex+1 < Collection.allCards.size()) {
				cardIndex++;
			}
			
			else {
				cardIndex = 0;
			}
			changeCurrentCard(cardIndex);
		}
	}
	
	/**
	 * Display the previous card of the list
	 */
	public static void previousCard() {
		if (Collection.allCards.size()>0) {
			if (0 <= cardIndex-1) {
				cardIndex--;
			}
			
			else {
				cardIndex = Collection.allCards.size()-1;
			}		
			changeCurrentCard(cardIndex);
		}
	}
	
	private static void changeCurrentCard(int index) {
		if (Collection.allCards.size()>0) {
			cardIndex = index;
			currCard = Collection.allCards.get(cardIndex);
			cardView.displayCard(currCard);
		}
	}
	
	private static void changeCurrentCard(Card card) {
		if (Collection.allCards.size()>0) {
			cardIndex = Collection.getIndexCard(card);
			cardView.displayCard(card);
		}
	}
	
	/*
	 * Empty the searching Interface
	 */
	public static void emptyInterface() {
		currCard=null;
		cardIndex=0;
		cardView.resetPanel();
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
