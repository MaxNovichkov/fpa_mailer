package de.bht.fpa.mail.s797981.fsnavigation.items;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This wrapper class for java.io.File represents a folder with corresponding
 * icon.
 * 
 * @author Maxim Novichkov
 */
public class TreeFolder extends ATreeItem {
	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/folder_s.png";

	/**
	 * Construct a new ITreeDIrectory with icon
	 * 
	 * @param path The specified path
	 */
	
	public TreeFolder(final String path) {
		super(path);
		this.imagePath = FOLDER_PATH;
	}

	/**
	 * Check if this element has children
	 * 
	 * @return true if children exist
	 */
	public boolean hasChildren() {
		return getChildren().size() > 0;
	}

	/**
	 * Returns an array with folders.
	 * 
	 * @return Array with folders and files
	 */
	public List<IMessageTreeItem> getChildren() {
		final File[] files = this.file.listFiles();
		if (files == null) {
			return super.list;
		}

		final List<IMessageTreeItem> directory = new ArrayList<IMessageTreeItem>();
		for (File item : files) {
			if (item.isDirectory()) {
				directory.add(new TreeFolder(item.getPath()));
			}
		}
		return directory;
	}
	
	/**
	 * Check the directory if there some messages in specified xml format exist and return
	 * list with this messages.
	 * 
	 * @return List with messages
	 */
	public LinkedList<Message> getMessages() {
		final FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".xml");
			}
		};

		final File[] myFiles = this.file.listFiles(filter);
		final LinkedList<Message> messages = new LinkedList<Message>();

		if (myFiles == null) {
			return messages;
		}

		for (File file : myFiles) {
			try {
				final Message message = JAXB.unmarshal(file, Message.class);
				if (messages == null) {
					continue;
				}
				messages.add(message);
			} catch (Exception e) {
				// If another xml file format, ignore it
			}
		}
		return messages;
	}
	/**
	 * Check if this {@link TreeFolder} exist
	 * @return True if exist
	 */
	public boolean exists(){
		return this.file.exists();
 	}

	@Override
	public String toString() {
		return "ITreeDirectory [imagePath=" + imagePath + ", path=" + path
				+ ", file=" + file + "]";
	}

	
}
