package de.bht.fpa.mail.s797981.imapnavigation;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImapFolder extends AImap{
	
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
	
	public String getName() {
		return folder.getFullName();
	}
	
	@Override
	public String getText() {
		return getName();
	}
}
