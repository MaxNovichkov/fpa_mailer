package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.util.ArrayList;
import java.util.List;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
/**
 * This class represents a wrapped {@link Account} with corresponding icon.
 * 
 * @author Novichkov Maxim
 */
public class ImapAccount extends AImapItem {

	/**
	 * Path to the folder icon
	 */
	private static final String FOLDER_PATH = "icons/file_s.png";
	/**
	 * Default constructor
	 */
	public ImapAccount() {
		super();
	}
	/**
	 * Construct new {@link ImapAccount} with icon
	 * @param account Provided {@link Account}
	 */
	public ImapAccount(final Account account) {
		super(account);
		this.imagePath = FOLDER_PATH;
	}

	public Account getAccount() {
		return account;
	}

	public boolean hasChildren() {
		return getChildren().size() > 0;
	}
	/**
	 * Returns list with {@link ImapFolder}
	 */
	public List<IMessageTreeItem> getChildren() {
		final List<IMessageTreeItem> children = new ArrayList<IMessageTreeItem>();
		for (Folder item : account.getFolders()) {
			children.add(new ImapFolder(item));
		}
		return children;
	}

	@Override
	public List<Message> getMessages() {
		return new ArrayList<Message>();
	}

	public String getName() {
		return account.getName();
	}

	@Override
	public String getText() {
		return getName();
	}
}
