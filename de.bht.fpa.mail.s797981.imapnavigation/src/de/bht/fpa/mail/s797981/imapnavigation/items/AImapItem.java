package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.imapnavigation.Activator;

public abstract class AImapItem implements IMessageTreeItem {
	
	protected Account account;
	
	/**
	 * Specified path for account or imap folder image icon
	 */
	protected String imagePath;
	
	public AImapItem() {
		this.account = null;
	}
	
	public AImapItem(Account account) {
		this.account = account;
	}
	
	public abstract String getName();
	
	public abstract List<IMessageTreeItem> getChildren();

	@Override
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
	}
}
