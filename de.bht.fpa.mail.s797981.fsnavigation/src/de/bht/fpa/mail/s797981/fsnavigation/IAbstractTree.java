package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;

import org.eclipse.swt.graphics.Image;

/**
 * This abstract class represent a wrapper class for java.io.File and contains methods for working with 
 * it representation such as file and directory. Specific methods implemented in corresponding classes.
 * 
 * @author Maxim Novichkov
 */
public abstract class IAbstractTree {
	
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
	 * Constructor with checking if this path exist  
	 * @param path The specified path
	 */
	public IAbstractTree(String path) {
		File file = new File(path);
		if(!file.exists()){
			throw new IllegalArgumentException("File with path " + path + " not exist");
		}
		this.file = file;
		this.path = path;
	}
	
	/**
	 * Returns an empty array if it's instance of file 
	 * @return An empty array
	 */
	public Object[] getChildren(){
		return new Object[0];
	};
	
	public String getName() {
		return file.getName();
	}
	
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
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
		IAbstractTree other = (IAbstractTree) obj;
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