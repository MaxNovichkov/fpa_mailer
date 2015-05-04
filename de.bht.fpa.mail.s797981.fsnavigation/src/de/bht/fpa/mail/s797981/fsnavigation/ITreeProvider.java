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
public abstract class ITreeProvider {
	/**
	 * This list will be used to store children
	 */
	List<ITreeProvider> children;
	/**
	 * Specified path for file or folder image icon
	 */
	String imagePath;
	/**
	 * Path for file or folder
	 */
	String absolutePath;
	
	public ITreeProvider(String absolutePath) {
		this.absolutePath = absolutePath;
		this.children = new ArrayList<ITreeProvider>();
	}
	
	public abstract void addChild(ITreeProvider p);
	
	public String getName() {
	    File file = new File(absolutePath);
	    return file.getName();
	  }

	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				imagePath).createImage();
	}
	
	public abstract ITreeProvider[] getChildren();
}
