package de.bht.fpa.mail.s797981.filter.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.filter.ReadFilter;

public class ReadTest {
	
	final int READ_MESSAGES = 3;
	final int UNREAD_MESSAGES = 2;
	
	public List<Message> setUp() {
		final Message message_1 = new Message();
		final Message message_2 = new Message();
		final Message message_3 = new Message();
		final Message message_4 = new Message();
		final Message message_5 = new Message();
		message_1.setRead(false);
		message_2.setRead(false);
		message_3.setRead(true);
		message_4.setRead(true);
		message_5.setRead(true);
		final List<Message> messages = new ArrayList<Message>();
		messages.add(message_1);
		messages.add(message_2);
		messages.add(message_3);
		messages.add(message_4);
		messages.add(message_5);
		return messages;
	}

	@Test
	public void messageIsRead() {
		final Set<Message> messages = new ReadFilter(true).filter(setUp());
		assertEquals("Number of read messages", READ_MESSAGES, messages.size());
		assertNotEquals("Number of unread messages", UNREAD_MESSAGES, messages.size());
		for (Message mes : messages) {
			assertTrue("ReadFilter is true", mes.isRead());
		}

	}
	
	@Test
	public void messageIsNotRead() {
		final Set<Message> messages = new ReadFilter(false).filter(setUp());
		assertEquals("Number of unread messages", UNREAD_MESSAGES, messages.size());
		assertNotEquals("Number of read messages", READ_MESSAGES, messages.size());
		for (Message mes : messages) {
			assertFalse("ReadFilter is false", mes.isRead());
		}
	}
	
	@Test
	public void readTestWrongFalse() {
		final Set<Message> messages = new ReadFilter(false).filter(setUp());
		assertEquals("Number of unread messages", UNREAD_MESSAGES, messages.size());
		assertNotEquals("Number of read messages", READ_MESSAGES, messages.size());
		for (Message mes : messages) {
			assertFalse("Message is read, but should not be", mes.isRead());
		}
	}

}
