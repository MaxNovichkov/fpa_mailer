package de.bht.fpa.mail.s797981.imapnavigation.items;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newAccountBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newFolderBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

@XmlRootElement
public class AccountsList extends AImapItem{
	
	File file = new File(System.getProperty("user.home") + "/accountsList.xml");
	
	
	final private Collection<ImapAccount> toWrite;
	
	@XmlElement(name = "Account")
	final private List<IMessageTreeItem> item;
	
	public AccountsList() {
		item = new LinkedList<IMessageTreeItem>();
		toWrite = new ArrayList<ImapAccount>();
	}
	
	public ImapAccount readImapAccount(){
		
		
		return null;
	}
	
	public void writeImapAccount(final AccountsList list){
		
		 try {
		 JAXBContext jaxbContext = JAXBContext.newInstance(AccountsList.class);
		 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		 jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(list, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean hasChildren() {
		return item.size() > 0;
	}
	
	public List<IMessageTreeItem> addAccount(Account account) {
//		this.account = account;
		toWrite.add(new ImapAccount(account));
		item.add(new ImapAccount(account));
		return item;
	}
	
	public List<IMessageTreeItem> getChildren() {
		return item;
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
