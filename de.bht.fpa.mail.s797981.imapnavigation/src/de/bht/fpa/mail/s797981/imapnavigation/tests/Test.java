package de.bht.fpa.mail.s797981.imapnavigation.tests;

import java.io.File;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountsList;
import de.bht.fpa.mail.s797981.imapnavigation.items.ImapAccount;

/**
 * Tests for reading accounts from xml file 
 * @author Novichkov Maxim
 */
public class Test {

	public static void main(String[] args) {
	
		System.out.println("Read account from XML:");
	    readXML("files/account.xml");
	    System.out.println();
		
		System.out.println("Read accounts from XML:");
		printAccounts(readAccountsFromXML("files/accountsList.xml"));	
	}
	
	public static void readXML(String filename) {
	    try {
	      Account account = JAXB.unmarshal(new File(filename), Account.class);
	      System.out.println(account);
	    } catch (DataBindingException e) {
	      System.err.println("File '" + filename + "' not found");
	    }
	  }
	
	public static AccountsList readAccountsFromXML(String filename) {
	    try {
	    	for(ImapAccount account : JAXB.unmarshal(new File(filename) , AccountsList.class).getListImapAccounts()){
	    		AccountsList.getInstance().addAccount(account);
	    	}
	    } catch (DataBindingException e) {
	      System.err.println("File '" + filename + "' not found");
	    }
	    return AccountsList.getInstance();
	}  	

	private static void printAccounts(AccountsList accounts) {
		for (ImapAccount o : accounts.getListImapAccounts()) {
			System.out.println(o.getAccount());
		}
		System.out.println();
	}
}
