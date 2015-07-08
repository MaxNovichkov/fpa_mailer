package de.bht.fpa.mail.s797981.fsnavigation.items;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s797981.fsnavigation.Activator;

/**
 * This abstract class represent a wrapper class for java.io.File and contains methods for working with 
 * it representation such as file and directory. Specific methods implemented in corresponding classes.
 * 
 * @author Maxim Novichkov
 */
public abstract class ATreeItem implements IMessageTreeItem{
	
	/**
	 * Specified path for file or folder image icon
	 */
	protected String imagePath;
	/**
	 * Path for file or folder
	 */
	protected final String path;
	/**
	 * File specified by path
	 */
	protected final File file;
	/**
	 * Construct {@link ATreeItem} 
	 * @param path The specified path
	 */
	
	protected final List<IMessageTreeItem> list = new LinkedList<IMessageTreeItem>();
	
	public ATreeItem(String path) {
		this.file = new File(path);
		this.path = path;
	}
	
	/**
	 * Returns an empty array if it's instance of file 
	 * @return An empty array
	 */
	public List<IMessageTreeItem> getChildren(){
		return list;
	};
	
	/**
	 * Returns path of tree
	 * @return The path of selected element
	 */
	public String getPath() {
		return path;
	}
	
	public String getName() {
		return file.getName();
	}
	
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
	}
	
	@Override
	public String getText() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ATreeItem other = (ATreeItem) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
}
