package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import org.eclipse.swt.graphics.Image;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s797981.imapnavigation.Activator;
/**
 * This abstract class represent an {@link AImapItem} with icon.
 * 
 * @author Novichkov Maxim
 */
public abstract class AImapItem implements IMessageTreeItem {
	
	@XmlElement
	protected Account account;
	/**
	 * Specified path for account or imap folder with icon
	 */
	protected String imagePath;
	/**
	 * Default constructor
	 */
	public AImapItem() {
		this.account = null;
	}
	/**
	 * Construct {@link AImapItem}
	 *  
	 * @param account Provided {@link Account}
	 */
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
