package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.util.ArrayList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImapFolder extends AImapItem{
	
	private Folder folder;
	
	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/folder_s.png";
	
	public ImapFolder(Folder folder) {
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
