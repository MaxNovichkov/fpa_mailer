package de.bht.fpa.mail.s797981.imapnavigation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public abstract class AImap implements IMessageTreeItem {
	
	protected final Account account;
	
	/**
	 * Specified path for file or folder image icon
	 */
	protected String imagePath;
	
	protected final List<IMessageTreeItem> list = new LinkedList<IMessageTreeItem>();
	
	public AImap() {
		this.account = null;
	}
	
	public AImap(Account account) {
		this.account = account;
	}
	
	public List<IMessageTreeItem> getChildren(){
		return list;
	}
	
	public abstract String getName();

	@Override
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
	}
}
