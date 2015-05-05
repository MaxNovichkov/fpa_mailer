package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//	TODO: to string and comments

public class ITreeDirectory extends IAbstractTree {
	
	/**
	 * This list will be used to store children
	 */
	private final Collection<IAbstractTree> children;
	
	private static final String FOLDER_PATH = "icons/folder_s.png";
	
	public ITreeDirectory(String absolutePath) {
		super(absolutePath);
		this.imagePath = FOLDER_PATH;
		this.children = new ArrayList<IAbstractTree>();
		
	}

	public boolean hasChildren() {
		return getChildren().length > 0;
	}
	
	/**
	 * Returns an array with files and folders or empty array for folder if no files in it
	 */
	public Object[] getChildren() {
		File file = new File(absolutePath);
		File[] files = file.listFiles();
		if (files == null) {
			return new Object[0];
		}

		IAbstractTree[] result = new IAbstractTree[files.length];
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				result[i] = new ITreeDirectory(files[i].getAbsolutePath());
			} 
			else {
				result[i] = new ITreeFile(files[i].getAbsolutePath());
			}
		}
		return result;
	}
	
}
