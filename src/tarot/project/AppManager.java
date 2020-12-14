package tarot.project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;

public class AppManager extends JPanel {
	
	static Color colors[] = new Color[2];
	JLabel title1 = new JLabel("Settings of your card deck");
	JLabel title2 = new JLabel("Settings of the your collection interface");
	
	JButton deleteBtn = new JButton("Reset my deck");
	JButton searchColor1Btn = new JButton("Change background colors of searching interface");
	JButton searchColor2Btn = new JButton("Change background colors of searching interface");
	
	public AppManager() {
		setLayout(null);
		
		title1.setBounds(100, 70, 500, 22);
		deleteBtn.setBounds(100, 110, 200, 22);
		
		title2.setBounds(100, 160, 500, 22);
		searchColor1Btn.setBounds(100, 200, 400, 22);
		searchColor2Btn.setBounds(100, 240, 400, 22);
		
		add(title1);
		add(title2);
		
		add(deleteBtn);
		add(searchColor1Btn);
		add(searchColor2Btn);
		
		deserializeColors();
		setBgColor(SearchInterface.displayPanel, colors[0]);
		setBgColor(Interface.creationInterf.displayPanel, colors[0]);
		setBgColor(SearchInterface.rightPanel, colors[1]);
		setBgColor(Interface.creationInterf.rightPanel, colors[1]);
		
		ActionListener colorAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent onClick) {
				Color color = JColorChooser.showDialog(null, "Choose your color", Color.white);
				if (color==null) {
					color = Color.white;
				}
				
				if (onClick.getSource() == searchColor1Btn) {
					colors[0] = color;
					setBgColor(SearchInterface.displayPanel, color);
					setBgColor(Interface.creationInterf.displayPanel, color);
				}
				else {
					colors[1] = color;
					setBgColor(SearchInterface.rightPanel, color);
					setBgColor(Interface.creationInterf.rightPanel, color);
				}	
				serializeColors(color);
				deserializeColors();
			}		
		};
		
		searchColor1Btn.addActionListener(colorAction);
		searchColor2Btn.addActionListener(colorAction);
			
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent onClick) {
				//Boite de dialogue pour confirmer
				Main.allCards = new ArrayList<>(); 	
				/*Interface.searchInterf.displayPanel = new DisplayPanel();
				Interface.searchInterf.displayPanel.repaint();*/
				
				Interface.searchInterf.displayPanel.name ="";
				Interface.searchInterf.displayPanel.arcana ="";
				Interface.searchInterf.displayPanel.description ="";
				Interface.searchInterf.displayPanel.image =null;
				Interface.searchInterf.displayPanel.imgLabel.setIcon(null);
				
				Interface.searchInterf.repaint();
				
				
				Main.saveAndRefresh();
			}		
		});	
	}	
	
	public void setBgColor(JPanel panel, Color color) {
		panel.setBackground(color);
		panel.validate();
		panel.repaint();
	}
	
	private void serializeColors(Color color){
		Gson gson = new Gson();
		
		// 1. Java object to JSON file
		try (FileWriter writer = new FileWriter("user_colors.json")) {
            gson.toJson(colors, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }	    
	       
		// 2. Java object to JSON string
	    String jsonInString = gson.toJson(colors);
	    System.out.println(jsonInString); 
	}
	
	public static void deserializeColors() {
		Gson gson = new Gson();
		
		try {		
			BufferedReader reader = new BufferedReader(new FileReader("user_colors.json"));
			
			try {
				StringBuilder sb =  new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				
				Color colorsJson[] = new Color[2];
				int i = 0;
				
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
					
					String jsonColor = sb.substring(idStart, idEnd+1);
					sb.delete(idStart, idEnd+1);
					
					Color currColor = gson.fromJson(jsonColor, Color.class);
					
					colorsJson[i] = currColor;
					i++;
				}
				
				reader.close();
				colors = colorsJson;
			
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		} catch (FileNotFoundException e1 ) {
			e1.printStackTrace();
		}
	}
	
}
