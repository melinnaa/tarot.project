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

/**
 * 
 *	Manage main settings of the interface
 *
 */
public class ManagerPanel extends JPanel {
	
	//Both main colors of the interface
	static Color colors[] = new Color[2];
	JLabel title1 = new JLabel("Settings of your card deck");
	JLabel title2 = new JLabel("Settings of the your collection interface");
	
	JButton deleteBtn = new JButton("Reset my deck");
	JButton color1Btn = new JButton("Change background color 1");
	JButton color2Btn = new JButton("Change background color 2");
	
	/**
	 * Displays and enable AppManager interface
	 */
	public ManagerPanel() {
		setLayout(null);
		
		title1.setBounds(100, 70, 500, 22);
		deleteBtn.setBounds(100, 110, 200, 22);
		
		title2.setBounds(100, 160, 500, 22);
		color1Btn.setBounds(100, 200, 400, 22);
		color2Btn.setBounds(100, 240, 400, 22);
		
		add(title1);
		add(title2);
		
		add(deleteBtn);
		add(color1Btn);
		add(color2Btn);
		
		deserializeColors();
		
		/**
		 * ActionListener to change an interface color on click of one the color settings buttons 
		 */
		ActionListener colorAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent onClick) {
				Color color = JColorChooser.showDialog(null, "Choose your color", Color.white);
				if (color==null) {
					color = Color.white;
				}
				
				if (onClick.getSource() == color1Btn) {
					colors[0] = color;
					setBgColor(CollectionPanel.cardView, color);
					setBgColor(Interface.creationPanel.cardView, color);
				}
				else {
					colors[1] = color;
					setBgColor(Interface.creationPanel.fieldsPanel, color);
					setBgColor(CollectionPanel.rightPanel, color);
				}	
				serializeColors();
				deserializeColors();
			}		
		};
		
		color1Btn.addActionListener(colorAction);
		color2Btn.addActionListener(colorAction);
			
		
		/**
		 * Action listener to delete all the cards of the collection
		 */
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent onClick) {
				//Boite de dialogue pour confirmer
				Collection.allCards = new ArrayList<>(); 	
			
				CollectionPanel.cardView.resetPanel();;
				
				
				Main.saveAndRefresh();
			}		
		});	
	}	
	
	/**
	 * Set the background color of a panel
	 * @param panel		the panel to change the color
	 * @param color		the color selected
	 */
	public static void setBgColor(JPanel panel, Color color) {
		panel.setBackground(color);
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Save the colors in the array in a json file
	 */
	private static void serializeColors(){
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
	
	/**
	 * Fill the current array of colors with the colors stored in the json file
	 */
	public static void deserializeColors() {
		Gson gson = new Gson();
		
		//We read the json string to identify each Color object
		try {		
			BufferedReader reader = new BufferedReader(new FileReader("user_colors.json"));
			
			try {
				StringBuilder sb =  new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				//List to stock each color identified
				Color colorsJson[] = new Color[2];
				int i = 0;
				
				//Loop that enables to identify each Color object in the json string
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
				//We overwrite the current list of colors with the ones contained in the json file.
				colors = colorsJson;
			
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		} catch (FileNotFoundException e1 ) {
			e1.printStackTrace();
		}
	}
	
}
