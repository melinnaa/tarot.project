package tarot.project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.*;
import java.util.Scanner;

import javax.swing.*;

public class Interface extends JFrame {
	
	private static JPanel menu = new JPanel();
	private static ButtonAction btnAction = new ButtonAction();
	private static JButton createBtn = new JButton("Create a new card");
	private static JButton searchBtn = new JButton("See my cards");
	private static JButton settingsBtn = new JButton("Settings");
	private static JPanel mainPanel = new JPanel();
	static SearchInterface searchInterf = new SearchInterface();
	static CreationInterface creationInterf = new CreationInterface();
	static AppManager appManager = new AppManager();
	
	
	public static CardLayout mainLayout = new CardLayout();
	
	
	public Interface() {
		
		setTitle("Tarot by Melinna");
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		createBtn.addActionListener(btnAction);	
		searchBtn.addActionListener(btnAction);	
		settingsBtn.addActionListener(btnAction);	
		
		menu.add(createBtn);	
		menu.add(searchBtn);
		menu.add(settingsBtn);
	
		mainPanel.setLayout(mainLayout);
		mainPanel.setBackground(Color.YELLOW);
		
		mainPanel.add(creationInterf, "creation");
		mainPanel.add(searchInterf, "searching");
		mainPanel.add(appManager, "settings");
		
		
		//Add JPanels to the JFrame
		add(menu, BorderLayout.PAGE_START);
		add(mainPanel);
		
		setVisible(true);				
	}
	
	public static JButton getCreateButton() {
		return createBtn;
	}
	
	public static JButton getSearchButton() {
		return searchBtn;
	}
	
	public static JButton getSettingsButton() {
		return settingsBtn;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
}
