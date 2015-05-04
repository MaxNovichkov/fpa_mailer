package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;

/**
 * 
 * @author Max
 *
 */
public class IDirectory extends ITreeProvider {

	public IDirectory(String absolutePath) {
		super(absolutePath);
		this.imagePath = "icons/folder_s.png";
	}

	/**
	 * Concrete implementation of adding children for folder
	 */
	public void addChild(ITreeProvider p) {
		this.children.add(p);
	}

	public boolean hasChildren() {
		return getChildren().length > 0;
	}
	
	/**
	 * Returns an array with files and folders or empty array for folder if no files in it
	 */
	public ITreeProvider[] getChildren() {
		File file = new File(absolutePath);
		File[] files = file.listFiles();
		if (files == null) {
			return new ITreeProvider[0];
		}

		ITreeProvider[] directoriesandfiles = new ITreeProvider[files.length];
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				directoriesandfiles[i] = new IDirectory(files[i].getAbsolutePath());
			} else {
				directoriesandfiles[i] = new IFile(files[i].getAbsolutePath());
			}
		}
		return directoriesandfiles;
	}
}
