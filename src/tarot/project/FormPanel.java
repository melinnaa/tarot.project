package tarot.project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * CardForm represents the form for the treatment of a card
 *
 */
public abstract class FormPanel extends JPanel {
	public static CardView cardView;
	public static JPanel fieldsPanel;
	
	protected JTextField nameField;
	protected JTextField arcanaField;
	protected JTextField descriptionField;
	protected JLabel nameLabel = new JLabel("Card name");
	protected JLabel arcanaLabel = new JLabel("Arcana");
	protected JLabel descriptionLabel = new JLabel("Description");
	protected JLabel imgLabel = new JLabel("Image");
	
	protected JButton nameSubmit = new JButton("Ok");
	protected JButton arcanaSubmit = new JButton("Ok");
	protected JButton descriptionSubmit = new JButton("Ok");
	
	protected JLabel messageLabel = new JLabel();
	protected JButton imgBtn  = new JButton("Select PNG/JPEG image");
	protected JButton submit = new JButton("Submit");
	
	public ArrayList<JButton> buttons = new ArrayList<>();

	/**
	 * Initialize fields according if the user is creating or changing a card
	 */
	protected abstract void initFields();
	
	/**
	 * Initialize the form interface
	 */
	protected void init() {
		initFields();
		
		//Add an actionListener each submit button that changes the displayPanel
		buttons.add(nameSubmit);
		buttons.add(arcanaSubmit);
		buttons.add(descriptionSubmit);
		
		for (JButton btn : buttons) {
            btn.addActionListener(new ActionListener() {		
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				if (btn==nameSubmit) {
    					cardView.name = nameField.getText();
		
    				}
    				else if (btn==arcanaSubmit) {
    					cardView.arcana =  arcanaField.getText();
    				}
    				else if (btn==descriptionSubmit) {
    					cardView.description = descriptionField.getText();
    				}
    				cardView.repaint();	
    			}
    		});
		}
		
		//add an actionListener to the imgBtn to display the File chooser
		imgBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent click) {
				FileChooser chooser = new FileChooser(cardView);
				if (!chooser.success) {
					imgBtn.setForeground(Color.RED);
				}
				else {
					imgBtn.setForeground(Color.BLACK);
				}
			}
		});
		
		submit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitVerification(cardView.name, cardView.arcana, cardView.description, cardView.image);
			}			
		});
		
		fieldsPanel.setBackground(ManagerPanel.colors[1]);
		fieldsPanel.setLayout(null);
		
		messageLabel.setForeground(Color.RED);
	
		messageLabel.setBounds(70, 100, 240, 22);
		nameLabel.setBounds(70, 130, 240, 22);
		nameField.setBounds(70, 160, 240, 22);
		nameSubmit.setBounds(320, 160, 50, 22);
		
		arcanaLabel.setBounds(70, 200, 240, 22);
		arcanaField.setBounds(70, 230, 240, 22);
		arcanaSubmit.setBounds(320, 230, 50, 22);
		
		descriptionLabel.setBounds(70, 270, 240, 22);
		descriptionField.setBounds(70, 300, 240, 22);
		descriptionSubmit.setBounds(320, 300, 50, 22);
		
		imgLabel.setBounds(70, 340, 200, 22);
		imgBtn.setBounds(70, 370, 200, 22);
		submit.setBounds(70, 460, 100, 22);
		
		fieldsPanel.add(messageLabel);
		
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(arcanaLabel);
		fieldsPanel.add(descriptionLabel);
		fieldsPanel.add(imgLabel);
		
		fieldsPanel.add(nameField);
		fieldsPanel.add(arcanaField);
		fieldsPanel.add(descriptionField);
		
		fieldsPanel.add(nameSubmit);
		fieldsPanel.add(arcanaSubmit);
		fieldsPanel.add(descriptionSubmit);
		
		fieldsPanel.add(imgBtn);
		fieldsPanel.add(submit);
		
		setLayout(new GridLayout(1,2));
		setBackground(ManagerPanel.colors[1]);
		
		add(cardView);
		add(fieldsPanel);

		validate();
	}
		
	protected abstract void submitVerification(String name, String arcana, String description, File image);
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextField getArcanaField() {
		return arcanaField;
	}
	
	public JTextField getDescriptionField() {
		return descriptionField;
	}
	
	public JButton getImgBtn() {
		return imgBtn;
	}
	
	public JButton getSubmitBtn() {
		return submit;
	}
	
}
