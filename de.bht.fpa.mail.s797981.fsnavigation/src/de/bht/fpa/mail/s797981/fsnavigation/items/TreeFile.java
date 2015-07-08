package de.bht.fpa.mail.s797981.fsnavigation.items;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This wrapper class for java.io.Fil represents a file with corresponding icon.
 * 
 * @author Maxim Novichkov
 */
public class TreeFile extends ATreeItem{
	/**
	 * Path to the file icon
	 */
	private static final String FILE_PATH = "icons/file_sm.png";
	/**
	 * Construct a new ITreeFile with icon 
	 * @param path The specified path
	 */
	public TreeFile(final String path) {
		super(path);
		this.imagePath = FILE_PATH;
	}
	
	@Override
	public String toString() {
		return "ITreeFile [imagePath=" + imagePath + ", path=" + path
				+ ", file=" + file + "]";
	}

	@Override
	public String getText() {
		return super.getName();
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}
}
