package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class IDirectory extends ITreeProvider {

	public IDirectory(String absolutePath) {
		super(absolutePath);
		this.imagePath = "icons/woman.png";
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
	 * Returns an array with files and folder
	 */
	public ITreeProvider[] getChildren() {
		File file = new File(absolutePath);
		File[] files = file.listFiles();
		if (files == null) {
			return new ITreeProvider[0];
		}

		ArrayList<ITreeProvider> directoriesandfiles = new ArrayList<ITreeProvider>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				directoriesandfiles.add(new IDirectory(files[i].getAbsolutePath()));
			} else {
				directoriesandfiles.add(new IFile(files[i].getAbsolutePath()));
			}
		}

		ITreeProvider[] result = new ITreeProvider[directoriesandfiles.size()];
		for (int i = 0; i < directoriesandfiles.size(); i++) {
			result[i] = directoriesandfiles.get(i);
		}
		return result;
	}
}
