package tarot.project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class FileChooser {
	public File file;
	public int response;
	public JFileChooser chooser = new JFileChooser(".");
	public ImageIcon icon;
	
	public FileChooser(DisplayPanel displayPanel){
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		response = chooser.showOpenDialog(null);
		
		if (response == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile(); 
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType!=null) {
				if (mimeType.equals("image/jpeg") || mimeType.equals("image/png")) {
					try {
						BufferedImage image = ImageIO.read(file);
						icon = new ImageIcon(image);
						displayPanel.imgLabel.setIcon(icon);
						displayPanel.image = file;
						
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
				
				else {
					Interface.creationInterf.getImgBtn().setForeground(Color.RED);
				}
			}
			
			else {
				Interface.creationInterf.getImgBtn().setForeground(Color.RED);
			}
		}
	}	
	
}
