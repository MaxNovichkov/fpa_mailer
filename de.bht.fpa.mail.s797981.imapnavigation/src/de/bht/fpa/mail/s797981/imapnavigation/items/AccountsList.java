package de.bht.fpa.mail.s797981.imapnavigation.items;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newAccountBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newFolderBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s797981.imapnavigation.Activator;

@XmlRootElement
public class AccountsList extends AImapItem{
	
	@Override
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	imagePath).createImage();
	}
	
	@XmlElement(name = "AccountList")
	final private List<ImapAccount> toRead;
	
	
//	final private List<IMessageTreeItem> item;
	
	public AccountsList() {
//		item = new ArrayList<IMessageTreeItem>();
		toRead = new LinkedList<ImapAccount>();
	}
	
	
	
	@Override
	public boolean hasChildren() {
		return toRead.size() > 0;
	}
	
	public List<ImapAccount> addAccount(Account account) {
//		this.account = account;
		toRead.add(new ImapAccount(account));
//		item.add(new ImapAccount(account));
		return toRead;
	}
	
	public List<IMessageTreeItem> getChildren() {
		List<IMessageTreeItem> list = new ArrayList<IMessageTreeItem>();
		for(ImapAccount account : toRead){
			list.add(account);
		}
		return list;
	}

	@Override
	public List<Message> getMessages() {
		return new ArrayList<Message>();
	}

	@Override
	public String getName() {
		return account.getName();
	}
	
	@Override
	public String getText() {
		return account.getName();
	}
	
	public static Account getBeuthGmailAccount(){
		
		return newAccountBuilder()
			      .name("FPA-Gmail")
			      .host("imap.gmail.com")
			      .username("fpabht")
			      .password("FPAG-mail").build();
	}
	
	public static Account generateDummyAccount() {
		  return newAccountBuilder()
			      .name("Test")
			      .host("imap.beuth-hochschule.de")
			      .username("max")
			      .password("test")
			      .folder(
			          newFolderBuilder()
			            .fullName("INBOX")
			            .builtMessages(new RandomTestDataProvider((int) (Math.random() * 15) + 1).getMessages())
			            .folder(
			                newFolderBuilder()
			                  .fullName("Customers")
			                  .builtMessages(
			                      new RandomTestDataProvider((int) (Math.random() * 15) + 1).getMessages())
			            )
			            .folder(
			                newFolderBuilder()
			                  .fullName("Friends")
			                  .builtMessages(
			                      new RandomTestDataProvider((int) (Math.random() * 15) + 1).getMessages())
			            )
			            .folder(
			                newFolderBuilder()
			                  .fullName("Family")
			                  .builtMessages(
			                      new RandomTestDataProvider((int) (Math.random() * 15) + 1).getMessages())
			            )
			      ).folder(
			          newFolderBuilder()
			            .fullName("Sent")
			            .builtMessages(new RandomTestDataProvider((int) (Math.random() * 15) + 1).getMessages())
			      ).build();
			  }
}
