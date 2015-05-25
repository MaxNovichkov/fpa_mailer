package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This wrapper class for java.io.File represents a folder with corresponding
 * icon.
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
	 * 
	 * @param path The specified path
	 */
	public ITreeDirectory(final String path) {
		super(path);
		this.imagePath = FOLDER_PATH;
	}

	/**
	 * Check if this element has children
	 * 
	 * @return true if children exist
	 */
	public boolean hasChildren() {
		return getChildren().length > 0;
	}

	/**
	 * Returns an array with files and folders or empty array for folder if no
	 * files in it
	 * 
	 * @return Array with folders and files
	 */
	public Object[] getChildren() {
		final File[] files = this.file.listFiles();
		if (files == null) {
			return new Object[0];
		}

		final ArrayList<ITreeDirectory> directory = new ArrayList<ITreeDirectory>();
		for (File item : files) {
			if (item.isDirectory()) {
				directory.add(new ITreeDirectory(item.getPath()));
			}
		}
		return directory.toArray();
	}

	/**
	 * Check the directory if there some messages in xml format exist and return
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

	@Override
	public String toString() {
		return "ITreeDirectory [imagePath=" + imagePath + ", path=" + path
				+ ", file=" + file + "]";
	}
}
