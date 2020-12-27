package tarot.project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Scanner;

import javax.swing.*;

/**
 * 
 * Manage the main interface (JFrame) of the application
 * Displays the menu bar and the main panel
 *
 */
public class Interface extends JFrame {
	
	private static JPanel menu = new JPanel();

	public HashMap<JButton, String> menuButtons= new HashMap<>();
	private static JButton createBtn = new JButton("Create a new card");
	private static JButton collectionBtn = new JButton("See my cards");
	private static JButton drawBtn = new JButton("Draw cards");
	private static JButton settingsBtn = new JButton("Settings");
	
	private static JPanel mainPanel = new JPanel();
	
	static ManagerPanel managerPanel = new ManagerPanel();
	static CollectionPanel collectionPanel = new CollectionPanel();
	static CreationPanel creationPanel = new CreationPanel();
	static DrawPanel drawPanel = new DrawPanel();
	
	String creationPanelName = "creation";
	String searchingPanelName = "searching";
	String drawPanelName = "draw";
	String settingsPanelName = "settings";
	
	
	public static CardLayout mainLayout = new CardLayout();
	
	/**
	 * Constructs the main interface of the application
	 */
	public Interface() {
		
		setTitle("Tarot by Melinna");
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Add an action listener to each menu button
		menuButtons.put(createBtn ,creationPanelName);
		menuButtons.put(collectionBtn, searchingPanelName);
		menuButtons.put(drawBtn, drawPanelName);
		menuButtons.put(settingsBtn, settingsPanelName);
		
		for (JButton btn : menuButtons.keySet()) {
            btn.addActionListener(new ActionListener() {		
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				if (btn==createBtn) {
    					creationPanel = new CreationPanel();
    					mainPanel.add(creationPanel, creationPanelName);
    				}
    				
    				mainLayout.show(mainPanel, menuButtons.get(btn));
    				
    				if (btn==drawBtn) {
    					drawPanel.launch();
    				}
    			}
    		});
		}
		
		//Fill the menu
		menu.add(createBtn);	
		menu.add(collectionBtn);
		menu.add(drawBtn);
		menu.add(settingsBtn);
	
		//Fill the main Panel using the card layout
		mainPanel.setLayout(mainLayout);		
		mainPanel.add(collectionPanel, searchingPanelName);
		mainPanel.add(drawPanel, drawPanelName);
		mainPanel.add(managerPanel, settingsPanelName);
		
		//Add JPanels to the JFrame
		add(menu, BorderLayout.PAGE_START);
		add(mainPanel);
		
		setVisible(true);				
	}
	
	public static JButton getCreateButton() {
		return createBtn;
	}
	
	public static JButton getSearchButton() {
		return collectionBtn;
	}
	
	public static JButton getSettingsButton() {
		return settingsBtn;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
}
