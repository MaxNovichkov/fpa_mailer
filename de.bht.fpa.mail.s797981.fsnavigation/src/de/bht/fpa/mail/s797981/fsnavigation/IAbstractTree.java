package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * This abstract class contains general methods for working with .....file system. Specific methods implemented in 
 * corresponding classes.
 * 
 * @author Maxim Novichkov
 *
 */
//TODO: comments

public abstract class IAbstractTree {
	
	/**
	 * Specified path for file or folder image icon
	 */
	protected String imagePath;
	/**
	 * Path for file or folder
	 */
	protected final String absolutePath;

	private final File file;
	
	public IAbstractTree(String absolutePath) {
		File file = new File(absolutePath);
		if(!file.exists()){
			throw new IllegalArgumentException("File with path" + absolutePath + "not exist");
		}
		this.file = file;
		this.absolutePath = absolutePath;
	}
	
	public String getName() {
	    return file.getName();
	  }

	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
	}
	
	/**
	 * Returns an empty array if no children there
	 */
	public Object[] getChildren(){
		return new Object[0];
	};
}
