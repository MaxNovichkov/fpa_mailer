package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;

/**
 * This wrapper class for java.io.File represents a folder with corresponding icon.
 * 
 * @author Maxim Novichkov
 */
public class ITreeDirectory extends IAbstractTree {
	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/folder_s.png";
	
	/**
	 * Construct a new ITreeDIrectory with icon 
	 * @param path The specified path
	 */
	public ITreeDirectory(String path) {
		super(path);
		this.imagePath = FOLDER_PATH;
	}
	
	/**
	 * Check if this element has children
	 * @return true if children exist
	 */
	public boolean hasChildren() {
		return getChildren().length > 0;
	}

	/**
	 * Returns an array with files and folders or empty array for folder if no files in it
	 * @return Array with folders and files  
	 */
	public Object[] getChildren() {
		File[] files = this.file.listFiles();
		if (files == null) {
			return new Object[0];
		}

		IAbstractTree[] result = new IAbstractTree[files.length];
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				result[i] = new ITreeDirectory(files[i].getPath());
			} else {
				result[i] = new ITreeFile(files[i].getPath());
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "ITreeDirectory [imagePath=" + imagePath + ", path=" + path
				+ ", file=" + file + "]";
	}
}
