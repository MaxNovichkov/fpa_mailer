package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.io.File;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
/**
 * Provide ability to load imap accounts from xml file.
 * 
 * @author Novichkov Maxim
 */
public class AccountListLoader {
	
	// Test xml file placed in project folder files ("de.bht.fpa.mail.s797981.imapnavigation/files/accountsList.xml"),
	// please, just copy it to user.home location
	/**
	 * File with saved imap accounts.
	 */
	private final static File file = new File(System.getProperty("user.home") +  "/accountsList.xml");
	
	/**
	 * Load accounts from xml file, if file not exist, 
	 * test xml file placed in project folder files ("de.bht.fpa.mail.s797981.imapnavigation/files/accountsList.xml"), 
	 * please, just copy it to your user.home location.
	 */
	public static void loadFromFileImapAccounts(){
	    try {
	      for(ImapAccount account : JAXB.unmarshal(file, AccountsList.class).getListImapAccounts()){
	    	  AccountsList.getInstance().addAccount(account.getAccount());
	      }
	    } catch (DataBindingException e) {
	      System.err.println("File " + file.getName() + " not found");
	    }
	}
}
