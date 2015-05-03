package de.bht.fpa.mail.s797981.fsnavigation;

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
	 * Name of file or folder
	 */
	String name;
	/**
	 * Specified path for file or folder image icon
	 */
	String imagePath;
	
	public ITreeProvider(String name) {
		this.name = name;
		this.children = new ArrayList<ITreeProvider>();
	}
	
	public abstract void addChild(ITreeProvider p);
	
	public String getName() {
		return name;
	}

	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				imagePath).createImage();
	}
	
	public ITreeProvider[] getChildren() {
		return children.toArray(new ITreeProvider[0]);
	}
}
