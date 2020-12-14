package tarot.project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class CreationInt extends JPanel {
	public DisplayPanel displayPanel = new DisplayPanel();
	public JPanel rightPanel = new JPanel();
	
	protected JTextField nameField;
	protected JTextField arcanaField;
	protected JTextField descriptionField;
	protected JLabel nameTitle = new JLabel("Card name");
	protected JLabel arcanaTitle = new JLabel("Arcana");
	protected JLabel descriptionTitle = new JLabel("Description");
	protected JLabel imgTitle = new JLabel("Image");
	
	protected JButton imgBtn  = new JButton("Select PNG/JPEG image");
	protected JButton submit = new JButton("Submit");
	
	protected TextFieldAction textFieldAction = new TextFieldAction();
	protected ButtonAction btnAction = new ButtonAction();
	
	protected abstract void initFields();
	
	protected void init() {
		initFields();
		nameField.addActionListener(textFieldAction);
		arcanaField.addActionListener(textFieldAction);
		descriptionField.addActionListener(textFieldAction);
		
		imgBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent click) {
				new FileChooser(displayPanel);		
			}
		});
		
		submit.addActionListener(btnAction);
		
		rightPanel.setBackground(Color.CYAN);
		rightPanel.setLayout(null);
		
		nameTitle.setBounds(70, 70, 240, 22);
		nameField.setBounds(70, 100, 240, 22);
		
		arcanaTitle.setBounds(70, 140, 240, 22);
		arcanaField.setBounds(70, 170, 240, 22);
		
		descriptionTitle.setBounds(70, 210, 240, 22);
		descriptionField.setBounds(70, 240, 240, 22);
		
		imgTitle.setBounds(70, 280, 200, 22);
		imgBtn.setBounds(70, 310, 200, 22);
		submit.setBounds(70, 400, 100, 22);
		
		rightPanel.add(nameTitle);
		rightPanel.add(arcanaTitle);
		rightPanel.add(descriptionTitle);
		rightPanel.add(imgTitle);
		
		rightPanel.add(nameField);
		rightPanel.add(arcanaField);
		rightPanel.add(descriptionField);
		
		rightPanel.add(imgBtn);
		rightPanel.add(submit);
	
		setLayout(new GridLayout(1,2));
		
		add(displayPanel);
		add(rightPanel);

		validate();
	}
	
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
