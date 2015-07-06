package de.bht.fpa.mail.s797981.imapnavigation;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newAccountBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newFolderBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;



public class DummyAccount {
	
	public static Account generateDummyAccount() {

//		return newAccountBuilder()
//		.host("de.somewhere.com")
//		.username("alice")
//		.password("secret")
//		.name("Alice-IMAP")
//		.folder(newFolderBuilder()
//		.fullName("INBOX")
//		.builtMessages(
//		new RandomTestDataProvider(20).getMessages())
//		.folder(newFolderBuilder().fullName("Customers")
//		.builtMessages(
//		new RandomTestDataProvider(30)
//		.getMessages())))
//		.folder(newFolderBuilder().fullName("Sent").builtMessages(
//		new RandomTestDataProvider(5).getMessages())).build();
//		}
				
				  return newAccountBuilder()
					      .name("Test")
					      .host("imap.beuth-hochschule.de")
					      .username("seb")
					      .password("sebsecrete")
					      .folder(
					          newFolderBuilder()
					            .fullName("INBOx")
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
					    //@formatter:on
					  }
}
