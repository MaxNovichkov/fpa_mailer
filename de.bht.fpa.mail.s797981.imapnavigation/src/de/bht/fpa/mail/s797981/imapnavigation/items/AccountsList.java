package de.bht.fpa.mail.s797981.imapnavigation.items;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newAccountBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newFolderBuilder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
/**
 * This class represents the list of saved {@link Account}s. Created and used list contains {@link Account}
 *  wrapped in {@link ImapAccount}. All the time will be used only one instance (Singleton) of AccountsList.
 *  
 * @author Novichkov Maxim
 */
@XmlRootElement
public class AccountsList extends AImapItem{
	/**
	 * {@link ImapAccount} list
	 */
	@XmlElement(name = "AccountList")
	final private List<ImapAccount> imapAccountsList;
	/**
	 * Instance of {@link AccountsList}
	 */
	final private static AccountsList INSTANCE_ACCOUNT_LIST = new AccountsList();
	/**
	 * Construct new private {@link AccountsList} with {@link ImapAccount} list.  
	 */
	private AccountsList() {
		imapAccountsList = new LinkedList<ImapAccount>();
	}
	/**
	 * Provides instance of {@link AccountsList}
	 * @return The instance of {@link AccountsList}
	 */
	public static AccountsList getInstance() {
		return INSTANCE_ACCOUNT_LIST;
	}
	
	@Override
	public boolean hasChildren() {
		return imapAccountsList.size() > 0;
	}
	/**
	 * This method wrap provided {@link Account} in {@link ImapAccount}
	 * @param account Provided {@link Account}
	 * @return List with {@link ImapAccount}s
	 */
	public List<ImapAccount> addAccount(final Account account) {
		imapAccountsList.add(new ImapAccount(account));
		return imapAccountsList;
	}
	
	/**
	 * Removes existing in the list account and exchange it with updated (fresh) one
	 * @param account Synchronized account
	 * @return List without duplicate accounts and with synchronized one
	 */
	public List<ImapAccount> addAccountAndRemoveDuplicate(final Account account) {
		for(ImapAccount item : imapAccountsList){
			if(item.getName().equals(account.getName())){
				imapAccountsList.remove(item);
			}
		}
		imapAccountsList.add(new ImapAccount(account));
		return imapAccountsList;
	}
	/**
	 * This method adds {@link ImapAccount} to the {@link AccountsList}
	 * @param account Provided {@link ImapAccount}
	 * @return List with {@link ImapAccount}s
	 */
	public List<ImapAccount> addAccount(final ImapAccount account) {
		imapAccountsList.add(account);
		return imapAccountsList;
	}
	
	public List<ImapAccount> getListImapAccounts() {
		return imapAccountsList;
	}
	
	public Account getAccount() {
	    return account;
	  }
	
	public List<IMessageTreeItem> getChildren() {
		List<IMessageTreeItem> list = new ArrayList<IMessageTreeItem>();
		for(ImapAccount account : imapAccountsList){
			list.add(account);
		}
		return list;
	}
	/**
	 * {@link AccountsList} doesn't have messages.
	 * @return An empty list 
	 */
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
	/**
	 * Creates specified test gmail account
	 * @return Specified account
	 */
	public static Account getBeuthGmailAccount(){
		return newAccountBuilder()
			      .name("FPA-Gmail")
			      .host("imap.gmail.com")
			      .username("fpabht")
			      .password("FPAG-mail").build();
	}
	/**
	 * Creates dummy test account with folders and messages
	 * @return Created account
	 */
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
