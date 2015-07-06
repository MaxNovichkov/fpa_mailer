package de.bht.fpa.mail.s797981.imapnavigation;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImapAccount extends AImap{

	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/file_s.png";
	
	public ImapAccount(final Account account) {
		super(account);
		this.imagePath = FOLDER_PATH;
	}
	
	public boolean hasChildren() {
		return getChildren().size() > 0;
	}
	
	public List<IMessageTreeItem> getChildren() {
		final List<Folder> folder = this.account.getFolders();
		if (folder == null) {
			return super.list;
		}

		final List<IMessageTreeItem> children = new ArrayList<IMessageTreeItem>();
		for (Folder item : folder) {
			if (item instanceof Folder) {
				children.add(new ImapFolder(item));
			}
		}
		return children;
	}

	@Override
	public List<Message> getMessages() {
		final List<Message> messages = new ArrayList<Message>();
		return messages;
	}
	
	public String getName() {
		return account.getName();
	}

	@Override
	public String getText() {
		return getName();
	}
}
