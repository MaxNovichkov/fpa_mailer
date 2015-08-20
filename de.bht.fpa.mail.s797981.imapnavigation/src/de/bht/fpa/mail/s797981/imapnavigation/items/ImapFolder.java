package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.util.ArrayList;
import java.util.List;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
/**
 * This class represents a wrapped {@link Folder} with corresponding icon.
 * 
 * @author Novichkov Maxim
 */
public class ImapFolder extends AImapItem {
	/**
	 * Folder field
	 */
	private final Folder folder;
	
	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/folder_s.png";
	/**
	 * Construct new {@link ImapFolder} with corresponding icon.
	 * @param folder Provided {@link Folder}
	 */
	public ImapFolder(final Folder folder) {
		this.folder = folder;
		this.imagePath = FOLDER_PATH;
	}

	@Override
	public boolean hasChildren() {
		return folder.getMessages().isEmpty();
	}

	@Override
	public List<Message> getMessages() {
		return folder.getMessages();
	}
	/**
	 * Returns list with {@link ImapFolder}s
	 */
	public List<IMessageTreeItem> getChildren() {
		final List<IMessageTreeItem> children = new ArrayList<IMessageTreeItem>();
		for (Folder item : folder.getFolders()) {
				children.add(new ImapFolder(item));
		}
		return children;
	}
	
	
	public String getName() {
		return folder.getFullName();
	}
	
	@Override
	public String getText() {
		return getName();
	}
}
