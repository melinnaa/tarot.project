package tarot.project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * Enables the user to upload a local file
 *
 */
public class FileChooser {
	public File file;
	public int response;
	public JFileChooser chooser = new JFileChooser("./resources");
	public ImageIcon icon;
	public boolean success;
	
	/**
	 * Choose a file to upload
	 * @param displayPanel	the panel that will display the file selected
	 */
	public FileChooser(CardView cardView){
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		response = chooser.showOpenDialog(null);
		
		if (response == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile(); 
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType!=null) {
				if (mimeType.equals("image/jpeg") || mimeType.equals("image/png")) {
					 cardView.setCardImage(file);
					 success=true;
				}
				
				else {
					success=false;
				}
			}
			
			else {
				success=false;
			}
		}
	}	
	
}
