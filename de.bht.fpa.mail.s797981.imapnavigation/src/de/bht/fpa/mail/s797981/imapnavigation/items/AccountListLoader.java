package de.bht.fpa.mail.s797981.imapnavigation.items;

import java.io.File;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;


public class AccountListLoader {
	
	private final static File file = new File(System.getProperty("user.home") + "/accountsList.xml");
	
	public static AccountsList readImapAccount(){
		AccountsList list = new AccountsList();
		try {
			list = JAXB.unmarshal(file, AccountsList.class);
		    } catch (DataBindingException e) {
		      System.err.println("File not found");
		    }
		    return list;
		
	}
	
	public void writeImapAccount(AccountsList accounts) {
	    try {
	      JAXB.marshal(accounts, file);
	    } catch (DataBindingException e) {
	      System.err.println("Failed to write");
	    }
	  }
}
